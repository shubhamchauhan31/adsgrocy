package com.example.payucart.api;

import com.example.payucart.model.addmoney.AddMoneyReq;
import com.example.payucart.model.addmoney.AddMoneyResponse;
import com.example.payucart.model.benificial.BenificalReq;
import com.example.payucart.model.benificial.BenificialResponse;
import com.example.payucart.model.benificial.UserBeneficiary;
import com.example.payucart.model.buy.BuyBody;
import com.example.payucart.model.buy.BuyModel;
import com.example.payucart.model.buy.getPackages.PackgesBody;
import com.example.payucart.model.buy.getPackages.PackgesModel;
import com.example.payucart.model.changePassword.ChangePassReq;
import com.example.payucart.model.changePassword.ChangePassRes;
import com.example.payucart.model.checkBenificalAccount.CheckBenifiaclAccountReq;
import com.example.payucart.model.checkBenificalAccount.CheckBenificalResponse;
import com.example.payucart.model.forget.ForgetReq;
import com.example.payucart.model.forget.ForgetRes;
import com.example.payucart.model.forget.OTPReq;
import com.example.payucart.model.forget.OTPRes;
import com.example.payucart.model.howTouseApp.HowTOUseResponse;
import com.example.payucart.model.instant_deposit.InstanrDepositModel;
import com.example.payucart.model.login.LoginReq;
import com.example.payucart.model.login.LoginRes;
import com.example.payucart.model.mobile.MobileModel;
import com.example.payucart.model.profile.EditProfileReq;
import com.example.payucart.model.profile.EditProfileResponse;
import com.example.payucart.model.profile.UserReq;
import com.example.payucart.model.profile.UserResModel;
import com.example.payucart.model.referCode.ReferCodeResponse;
import com.example.payucart.model.referandearn.ReferReq;
import com.example.payucart.model.referandearn.ReferRes;
import com.example.payucart.model.rewards.Check;
import com.example.payucart.model.rewards.RewardResponse;
import com.example.payucart.model.scratchCard.ScratchCardResponse;
import com.example.payucart.model.share.SharedResponse;
import com.example.payucart.model.shareSocialMedia.ShareFriendResponse;
import com.example.payucart.model.signup.SignUpBody;
import com.example.payucart.model.signup.SignUpModel;
import com.example.payucart.model.slider.SliderImageBody;
import com.example.payucart.model.slider.SliderImageModel;
import com.example.payucart.model.transfer.TransferResponse;
import com.example.payucart.model.video.VedioResponse;
import com.example.payucart.model.video.VideoData;
import com.example.payucart.model.withdraw.WithDrawReq;
import com.example.payucart.model.withdraw.WithDrawRes;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface ApiInterface {
            // 1) user account create
            @POST("/auth/createuser")
            Call<SignUpBody> signUp(@Body SignUpModel signUpModel);

             // 2) user login
            @POST("/auth/loginuser")
            Call<LoginRes> loginSucessFully(@Header("fcm_token") String fcmToken,@Body LoginReq loginReq);

            // 3) get Packages
            @Headers("Content-Type: application/json")
            @GET("/admindata/getpackages")
            Call<BuyModel>  getPackage(@Header("auth-token") String token);

            // 4) user get Profile
            @GET("/auth/getuser")
            Call<UserResModel> getProfile(@Header("auth-token") String token);

            // 5) user edit profile
            @Multipart
            @POST("/auth/editProfile")
            Call<EditProfileResponse> editProfile(@Header("auth-token") String token, @Part("img") RequestBody type, @Part MultipartBody.Part  file );

            // 6) user buy packages
            @POST("/auth/payorder")
            Call<PackgesBody> buyPackages(@Header("auth-token") String token,@Body PackgesModel packgesModel);

             // 7) get Banner
            @GET("/admindata/userBanners")
            Call<SliderImageModel> getBanner(@Header("auth-token") String token);

            // 8) for get password not working

            @PUT("/admindata/user/forget")
            Call<ForgetRes> getMobile(@Body ForgetReq forgetReq);

            // 9) refer code and earn

           @POST("/auth/refer")
            Call<ReferRes> getRefer(@Header("auth-token") String token,@Body ReferReq referReq);

            // 10) Wallet

            @POST("/auth/withdrawWalletMoney")
            Call<BenificialResponse> getWalletMoney(@Header("auth-token") String token,@Body BenificalReq benificalReq);

            // 11) add money

            @POST("/auth/payorder")
            Call<AddMoneyResponse> getMoney(@Header("auth-token") String token, @Body AddMoneyReq addMoneyReq);

            // 12) with draw money

            @POST("/auth/withdrawMoney")
            Call<WithDrawRes> getWithDrawMoney(@Header("auth-token") String token, @Body WithDrawReq withDrawReq);

            // 13) Transaction

            @GET("/auth/getwallet")
            Call<List<TransferResponse>> getTransaction(@Header("auth-token") String token);

            // 14) get Task To Wallet

            @POST("/auth/getReward")
            Call<Check> getTask(@Header("auth-token")String token);

            // 15)  get Money

            @POST("/auth/getReward")
            Call<RewardResponse> getRewards(@Header("auth-token")String token, @Body VideoData videoData);

            // 16) getInstant Deposit

            @POST("/auth/getUserPlan")
            Call<InstanrDepositModel>  getInstant(@Header("auth-token") String token);

            // 17) verify OTP
            @PUT("/admindata/user/checkOtp")
            Call<OTPRes> verifyOtp(@Body OTPReq otpReq);

            // 18) change Password
            @PUT("/admindata/user/createNewPassword")
            Call<ChangePassRes> changePassword(@Body ChangePassReq changePassReq);

            // 19) Get User Profile

            @GET("/auth/getbank")
            Call<BenificalReq> getBankDetail(@Header("auth-token") String token);

            // 20 ) Check Benifical Account

            @POST("/auth/getbankbynumber")
            Call<CheckBenificalResponse> CheckBankAccount(@Header("auth-token") String token,@Body CheckBenifiaclAccountReq checkBenifiaclAccountReq);

            // 21) Refer code Api

            @GET("/auth/fetchReferText")
            Call<ReferCodeResponse> getReferData(@Header("auth-token") String token);

            // 22) Video
                @GET("/admindata/UserVideo")
                Call<VedioResponse> getViedo(@Header("auth-token") String token);

                // 23) scratch card data
                @GET("/api/scratch-card")
                Call<ScratchCardResponse> getScratchCardDetails();

                // 24) How to use watch
                    @GET("/api/how-to-use")
                    Call<HowTOUseResponse> howToUSeApp();

                    // 25) share data and image
                    @GET("/api/shared-app-image")
                    Call<ShareFriendResponse> getFriendResponse();

}
