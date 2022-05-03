package com.example.payucart.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.payucart.R;
import com.example.payucart.activity.AddProfileActivity;
import com.example.payucart.adapter.MyBuyAdapter;
import com.example.payucart.adapter.SliderImageAdapter;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.interfaces.OnClickPackageList;
import com.example.payucart.model.Result;
import com.example.payucart.model.buy.BuyBody;
import com.example.payucart.model.buy.BuyModel;
import com.example.payucart.model.slider.SliderImageBody;
import com.example.payucart.model.slider.SliderImageModel;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PackageFragments extends Fragment {

    private RecyclerView recyclerView;

    private MyBuyAdapter myBuyAdapter;

    private List<BuyBody> buyModelArrayList;
    private ProgressDialog progressDialog;
    private SliderView sliderView;
    private SliderImageAdapter sliderImageAdapter;
    private List<SliderImageBody> sliderImageBodies;



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_package_fragments, container, false);

        recyclerView=view.findViewById(R.id.package_recyclerview);

        progressDialog=new ProgressDialog(getContext());
        sliderView=view.findViewById(R.id.package_slider);

        recyclerView.setHasFixedSize(false);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        addPackage();
        getBanner();

        return view;
    }
    private void getBanner() {
        try{
            SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

            ApiCheck.api.getBanner(retrivedToken).enqueue(new Callback<SliderImageModel>() {
                @Override
                public void onResponse(Call<SliderImageModel> call, Response<SliderImageModel> response) {

                    try{
                        sliderImageBodies=new ArrayList<>();

                        SliderImageBody sliderImageBody=new SliderImageBody();

                        sliderImageBody.setBannerImage(sliderImageBody.getBannerImage());

                        sliderImageBodies.addAll(response.body().getImages());


                        sliderImageAdapter=new SliderImageAdapter(sliderImageBodies,getContext());

                        sliderView.setSliderAdapter(sliderImageAdapter);

                        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                        // below line is for setting auto cycle duration.
                        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);

                        // below line is for setting
                        // scroll time animation
                        sliderView.setScrollTimeInSec(3);

                        // below line is for setting auto
                        // cycle animation to our slider
                        sliderView.setAutoCycle(true);

                        // below line is use to start
                        // the animation of our slider view.
                        sliderView.startAutoCycle();
                        //Toast.makeText(getContext(),"URL : "+response.body().getImages(),Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        Toast.makeText(getContext(),"Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<SliderImageModel> call, Throwable t) {
                    Toast.makeText(getContext(),"Failure : "+t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){

        }

    }

    public void addPackage() {
        try {
            progressDialog.setTitle("Payucart");
            progressDialog.setMessage("Loading Package...");
            progressDialog.show();

            SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.
            ApiCheck.api.getPackage(retrivedToken).enqueue(new Callback<BuyModel>() {
                @Override
                public void onResponse(Call<BuyModel> call, Response<BuyModel> response) {

                    if (response.isSuccessful()) {
                        buyModelArrayList=response.body().getResult();

                        for(int i=0; i<buyModelArrayList.size(); i++){
                            myBuyAdapter = new MyBuyAdapter(buyModelArrayList, getContext(), new OnClickPackageList() {
                                @Override
                                public void getPackageId(BuyBody buyBody) {

                                    buyBody.getId();

                                }

                                @Override
                                public void getPackageAmount(BuyBody buyBody) {
                                    buyBody.getPlan();
                                }
                            });
                            recyclerView.setAdapter(myBuyAdapter);
                            myBuyAdapter.notifyDataSetChanged();
                            progressDialog.dismiss();

                        }

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<BuyModel> call, Throwable t) {
                    Toast.makeText(getContext(),"Failure"+t.getMessage(),Toast.LENGTH_SHORT);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSharedPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("payucart", Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static void setSharedPref(Context context, String key, String value){
        SharedPreferences prefs = context.getSharedPreferences("payukart", Context.MODE_PRIVATE);
        prefs.edit().putString(key,value).commit();
    }
}