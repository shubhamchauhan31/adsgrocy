package com.example.payucart.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.payucart.R;
import com.example.payucart.api.ApiCheck;
import com.example.payucart.model.profile.EditProfileReq;
import com.example.payucart.model.profile.EditProfileResponse;
import com.example.payucart.model.profile.UserResModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.soundcloud.android.crop.Crop;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProfileActivity extends AppCompatActivity {

    @BindView(R.id.add_profile_image)
    ImageView addProfileImage;

    @BindView(R.id.add_profile_user_image)
    CircleImageView setProfile;

    @BindView(R.id.add_profile_user_name)
    TextView etUserName;

    @BindView(R.id.add_profile_user_mobile)
    TextView etMobile;

    @BindView(R.id.add_profile_tv_submit)
    TextView tvAddProfileSubmit;


    @BindView(R.id.add_profile_buy_plan)
    TextView tvProfileBuyPlan;

    @BindView(R.id.add_profile_plan_status)
    TextView tvProfilePlanStatus;

    @BindView(R.id.add_profile_in_active)
    ImageView imageViewInActive;

    private Bitmap photo;
    private String imageString;
   private MultipartBody.Part profileFilePart;

   private ProgressDialog progressDialog;

    private TextView tvProfileUserName;
    private TextView tvProfileMobile;


    public static final int RESULT_GALLERY = 0;
    public static final int CAMERA_REQUEST = 1;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    public ImageLoader loader;
    Uri photoURI, finalUri;
    File photoFile;
    Bitmap resized;

    String imagePath="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add_profile);


        ButterKnife.bind(this);

        tvProfileUserName=findViewById(R.id.add_profile_user_name);
        tvProfileMobile=findViewById(R.id.add_profile_user_mobile);

        DataDoesNotChange();

        progressDialog=new ProgressDialog(AddProfileActivity.this);
    }


    @OnClick(R.id.add_profile_back_btn) void backButtonPage(){
        startActivity(new Intent(AddProfileActivity.this,HomePageActivity.class));
    }

    @OnClick(R.id.add_profile_image) void addProfileImage(){
        displayCustomLauncher();
    }


    @OnClick(R.id.add_profile_tv_submit) void changeProfile(){
        createImageString();
    }


    public void changeImage(){
        progressDialog.setTitle("Image Upload ...");
        progressDialog.setMessage("Please Wait");

        SharedPreferences preferences = AddProfileActivity.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        RequestBody type = RequestBody.create(MediaType.parse("text/plain"), true + "");


        EditProfileReq editProfileReq=new EditProfileReq();
        editProfileReq.setImage(profileFilePart);



       // editProfileReq.setEditProfile(profileFilePart);
       // editProfileReq.setEditProfile(profileFilePart);
        Toast.makeText(getApplicationContext(), "Token" +getSharedPref(getApplicationContext(),"token"), Toast.LENGTH_SHORT).show();


        ApiCheck.api.editProfile(retrivedToken,type, profileFilePart).enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                if(response.isSuccessful()){


                    Toast.makeText(getApplicationContext(), "Image Upload Succeessful "+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Image Not Upload Succeessful ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure :   "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void selectImage() {
//       final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
////        final CharSequence[] options = {  "Choose from Gallery","Cancel" };
//        AlertDialog.Builder builder = new AlertDialog.Builder(AddProfileActivity.this);
//        builder.setTitle("Add Photo!");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                if (options[item].equals("Take Photo"))
//                {
//                    Intent camera_intent
//                            = new Intent(MediaStore
//                            .ACTION_IMAGE_CAPTURE);
//
//                    startActivityForResult(camera_intent, 100);
//
//                }
//                else
//                if (options[item].equals("Choose from Gallery"))
//                {
//                    Intent intent=new Intent();
//                    intent.setType("image/*");
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
//                    startActivityForResult(intent, 200);
//                }
//                else if (options[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == 100) {
//
//                photo = (Bitmap)data.getExtras()
//                        .get("data");
//
//                // Set the image in imageview for display
//                persistImage();
//                //createImageString();
//                setProfile.setImageBitmap(photo);
//
//
//            } else if (requestCode == 200) {
//            //    Uri selectedImage = data.getData();
//
//                Uri selectedImageUri = data.getData();
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    setProfile.setImageURI(selectedImageUri);
//
//                }
//            }
//        }
//    }



    private void displayCustomLauncher(){
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.custom_bottom_image_chooser_dialog);
        dialog.show();

        ImageView img_camera = dialog.findViewById(R.id.img_camera);
        ImageView img_gallery = dialog.findViewById(R.id.img_gallery);

        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    int status =AddProfileActivity.this.getPackageManager().checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            AddProfileActivity.this.getPackageName());

                    if (status == PackageManager.PERMISSION_GRANTED) {
                        dialog.dismiss();

                        clickPicture();
                    }else {

                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10);
                    }
                }else
                {
                    dialog.dismiss();
                    clickPicture();
                }

            }
        });

        img_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent galleryIntent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent , RESULT_GALLERY );
            }
        });
    }

    private void clickPicture(){

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        }

        else {
//            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(cameraIntent, CAMERA_REQUEST);
//        }

            Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//            if (takePictureIntent.resolveActivity(ActivitySignupDetails.this.getPackageManager()) != null) {
            if (takePictureIntent != null) {
                Log.e("TAG", "1");
                File photoFile = null;
                try {

                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File

                }
                if (photoFile != null) {
                    Log.e("TAG", "3");
                    photoURI = FileProvider.getUriForFile(AddProfileActivity.this,
                            "com.payucart.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                } else {
                    Log.e("TAG", "4");
                }
            } else {
                Log.e("TAG", "2");
            }
        }

    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, String filePath) {
        File file = new File(filePath);
        // create RequestBody instance from file
        MultipartBody.Part body = MultipartBody.Part.createFormData(partName, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        return body;
    }
    private File createImageFile() throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",java.util.Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        /*File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+ "/Validity");*/
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

//    public void handleCrop(int resultCode, Intent result) {
//
//        if (resultCode == RESULT_OK) {
//            File file = new File(Crop.getOutput(result).getPath());
//            imagePath=ImageCompress.compressGalleryImage(file.getAbsolutePath(),70);
//            profileFilePart = prepareFilePart("image", imagePath);
//
//            setProfile.setImageBitmap(photo);
//
////            changeImage();
//
//        } else if (resultCode == Crop.RESULT_ERROR) {
//            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        Log.d("IMAGE", "onActivityResult: "+requestCode);
        Log.d("IMAGE", "onActivityResult: "+ CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            image.setImageBitmap(photo);

            CropImage.activity(photoURI)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setRequestedSize(2048, 2048, CropImageView.RequestSizeOptions.RESIZE_INSIDE)
                    .setBackgroundColor(this.getResources().getColor(R.color.grey))
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setBorderLineColor(this.getResources().getColor(R.color.purple_200))
                    .setGuidelinesColor(this.getResources().getColor(R.color.purple_200))
                    .setFixAspectRatio(true)
                    .setAspectRatio(1,1)
                    .setBorderLineColor(this.getResources().getColor(R.color.teal_700))
                    .start(this);

        }

        else if (resultCode == RESULT_OK && requestCode == RESULT_GALLERY) {

            if (data != null) {

                Uri selectedImage = data.getData();

                CropImage.activity(selectedImage)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setRequestedSize(2048, 2048, CropImageView.RequestSizeOptions.RESIZE_INSIDE)
                        .setBackgroundColor(this.getResources().getColor(R.color.grey))
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setBorderLineColor(this.getResources().getColor(R.color.purple_200))
                        .setGuidelinesColor(this.getResources().getColor(R.color.purple_200))
                        .setFixAspectRatio(true)
                        .setAspectRatio(1,1)
                        .setBorderLineColor(this.getResources().getColor(R.color.teal_700))
                        .start(this);

            }
        }

//        else if(requestCode== Crop.REQUEST_CROP){
//
//                handleCrop(resultCode, data);
//        }

        else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            Log.d("IMAGE", "onActivityResult: ");
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                finalUri = result.getUri();

                BitmapFactory.Options options = new BitmapFactory.Options();

                final Bitmap bitmap = BitmapFactory.decodeFile(result.getUri().getPath(),
                        options);
                resized = getResizedBitmap(bitmap, 540);
                setProfile.setImageBitmap(resized);

                long time = 0;
                time = System.currentTimeMillis();
                String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() +
                        "/payucart/profile";
                File dir = new File(file_path);
                if (!dir.exists())
                    dir.mkdirs();
                photoFile = new File(dir, "payucart" + time + ".jpg");

                OutputStream os;
                try {
                   // createImageString();
                    os = new FileOutputStream(photoFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                    os.flush();
                    os.close();
                    AddProfileActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(photoFile)));
                } catch (Exception e) {

                }

//                CALL API HERE FOR IMAGE UPLOAD
//
//                validateFields();
//                uploadAttendance();
            }

            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("cam","result");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_PERMISSION_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public void createImageString(){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.instaimage);
        resized.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        Log.d("Image", "createImageString: "+imageString);

        Toast.makeText(getApplicationContext(),"Encoded : "+imageString,Toast.LENGTH_SHORT).show();
        changeImage();
    }


    private void DataDoesNotChange(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);//second parameter default value.

        ApiCheck.api.getProfile(retrivedToken).enqueue(new Callback<UserResModel>() {
            @Override
            public void onResponse(Call<UserResModel> call, Response<UserResModel> response) {
                UserResModel userResModel=response.body();
                if (response.isSuccessful()){
                    boolean planBuy=true;
                    String  name=response.body().getName();
                    String mobile=response.body().getMobile();
                    String status=response.body().getStatus();
                    String plan= String.valueOf(response.body().getPlan());
                    Toast.makeText(getApplicationContext(), "Get Data", Toast.LENGTH_SHORT).show();
                    tvProfileUserName.setText(name);
                    tvProfileMobile.setText(mobile);
                    if (planBuy){
                        tvProfileBuyPlan.setText(plan);
                        tvProfilePlanStatus.setText(status);
                        imageViewInActive.setImageResource(R.drawable.ic_baseline_circle_24);

                    }else {
                        tvProfileBuyPlan.setText("Buy Plan");
                        tvProfilePlanStatus.setText("InActive");
                        imageViewInActive.setImageResource(R.drawable.red_inactive);
                    }

                    Toast.makeText(getApplicationContext(), "Details"+userResModel.getName()+"\n"+userResModel.getMobile(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserResModel> call, Throwable t) {

            }
        });
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