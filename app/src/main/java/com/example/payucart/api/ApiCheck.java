package com.example.payucart.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCheck {
    public static String BASE_URL="http://www.adsgrocy.com";
    //public static String BASE_URL="http://192.168.68.94:3000";
    //public static String BASE_URL="http://ecdb-122-160-47-222.ngrok.io";



//    public static String BASE_URL1="https://2a08-122-160-47-222.ngrok.io";

   public static Retrofit retrofit=null;
   private OkHttpClient.Builder okHttpClient=new OkHttpClient.Builder();
   private HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
    public static  Retrofit getApi(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
              return retrofit;
    }
    public static ApiInterface api = getApi().create(ApiInterface.class);
}
