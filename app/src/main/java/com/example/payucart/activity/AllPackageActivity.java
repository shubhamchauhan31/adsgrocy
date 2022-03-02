package com.example.payucart.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.payucart.R;
import com.example.payucart.fragment.PackageFragments;

public class AllPackageActivity extends AppCompatActivity {

    private ImageView imageViewBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_package);
        getSupportActionBar().hide();
        imageViewBackBtn=findViewById(R.id.all_packages_back_btn);
        imageViewBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AllPackageActivity.this,HomePageActivity.class));
                finish();
            }
        });
        PackageFragments packageFragments=new PackageFragments();
        setFragment(packageFragments);
    }
    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.all_package,fragment).commit();
    }

}