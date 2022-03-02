package com.example.payucart.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.payucart.MainActivity;
import com.example.payucart.R;
import com.example.payucart.adapter.MyPagerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomePageActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int Tabs[]={
      R.drawable.ic_baseline_home_24,
      R.drawable.ic_reply_24px,
      R.drawable.packages,
      R.drawable.ic_account_circle_24px
    };

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private ActionBarDrawerToggle drawerToggle;

    private Toolbar toolbar;

    private SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_home_page);
        toolbar = findViewById(R.id.balkand_toolbar);

        // getSupportActionBar().setIcon(R.drawable.user);
    //    getSupportActionBar().setTitle("Payucart");
  //      setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        drawerToggle = setupDrawerToggle();


        // Setup toggle to display hamburger icon with nice animation

        drawerToggle.setDrawerIndicatorEnabled(true);

        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle

        drawerLayout.addDrawerListener(drawerToggle);

        navigationView = findViewById(R.id.home_page_nav);

        setupDrawerContent(navigationView);


        tabLayout=findViewById(R.id.tab_layout);

        viewPager=findViewById(R.id.viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("Home"));

        tabLayout.addTab(tabLayout.newTab().setText("Share"));

        tabLayout.addTab(tabLayout.newTab().setText("Buy"));

        tabLayout.addTab(tabLayout.newTab().setText("Profile"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final MyPagerAdapter myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());

        viewPager.setAdapter(myPagerAdapter);

        setupTabIcons();

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private void setupTabIcons(){

        tabLayout.getTabAt(0).setIcon(Tabs[0]);

        tabLayout.getTabAt(1).setIcon(Tabs[1]);

        tabLayout.getTabAt(2).setIcon(Tabs[2]);

        tabLayout.getTabAt(3).setIcon(Tabs[3]);


    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked

        switch(menuItem.getItemId()) {


                case R.id.nav_home:
                     startActivity(new Intent(HomePageActivity.this,HomePageActivity.class));
                     finish();
                break;
//                case R.id.nav_buy:
//                     startActivity(new Intent(HomePageActivity.this,BuyActivity.class));
//                break;

//
//                case  R.id.nav_notification:
//
//                break;

//                case R.id.nav_profile:
//
//                break;
                case R.id.nav_about:
                    startActivity(new Intent(HomePageActivity.this,AboutAcivity.class));
                    finish();
                break;

//                case R.id.nav_customer_support:
//
//                    break;
            case R.id.nav_privacy_policy:
                startActivity(new Intent(HomePageActivity.this,PrivercyAcivity.class));
                finish();

                break;
            case R.id.nav_term_condition:
                startActivity(new Intent(HomePageActivity.this,TearmAndConditionActivity.class));
                finish();

                break;
            case R.id.nav_logout:
//                setSharedPrefBoolean(getApplicationContext(),"hashLogin",false);
                 sharedPreferences=getSharedPreferences("hasLogin",MODE_PRIVATE);
//                 sharedPreferences=getSharedPreferences("TOKEN",MODE_PRIVATE);
                 editor = sharedPreferences.edit();
                 editor.clear();
                 editor.commit();
                 editor.apply();

//                 clearToken();


               // setSharedPrefBoolean(HomePageActivity.this,"hasLogin",false);
           //     navigateWithClearAllTop(HomePageActivity.this,LoginActivity.class);
                startActivity(new Intent(HomePageActivity.this,LoginActivity.class));
                finish();

                break;

            default:
                break;
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(HomePageActivity.this, drawerLayout, toolbar, R.string.open, R.string.closed);
    }

    public static String getSharedPref(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("payucart", Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static void setSharedPref(Context context, String key, String value){
        SharedPreferences prefs = context.getSharedPreferences("payukart", Context.MODE_PRIVATE);
        prefs.edit().putString(key,value).commit();
    }


    public static void setSharedPrefBoolean(Context context, String key, boolean value){
        SharedPreferences prefs = context.getSharedPreferences("payucart", Context.MODE_PRIVATE);
        prefs.edit().putBoolean(key,value).commit();
    }

    public void navigateWithClearAllTop(AppCompatActivity from, Class<? extends AppCompatActivity> to) {
        Intent intent = new Intent(from, to);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        from.startActivity(intent);
//        from.overridePendingTransition(R.anim.fade_in, R.anim.slide_right_out);
        from.finishAffinity();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void clearToken(){
        SharedPreferences sharedPreferences=getSharedPreferences("TOKEN",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}