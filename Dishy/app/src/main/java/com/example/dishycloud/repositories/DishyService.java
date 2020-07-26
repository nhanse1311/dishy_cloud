package com.example.dishycloud.repositories;

import com.example.dishycloud.models.Follower;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.User;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.utils.ConfigAPI;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface DishyService {

    @POST(ConfigAPI.Api.LOGIN)
    Call<ResponseBody> login(@Body RequestBody requestBody);

    @GET(ConfigAPI.Api.USER)
    Call<ResponseBody> getUser(@Header("Authorization") String header);

    @POST(ConfigAPI.Api.RECIPE)
    Call<ResponseBody> postRecipe(@Header("Authorization") String header, @Body Recipe requestBody);

    @POST(ConfigAPI.Api.REGISTER)
    Call<ResponseBody> register(@Body RequestBody requestBody);

    @Multipart
    @POST(ConfigAPI.Api.IMAGE)
    Call<ResponseBody> postImage(@Header("Authorization") String header, @Part List<MultipartBody.Part> files);

    @GET(ConfigAPI.Api.GET_RECIPE_AUTH)
    Call<ResponseBody> getRecipeByAuth(@Header("Authorization") String header);

    @GET(ConfigAPI.Api.GET_RECIPE_SAVE)
    Call<ResponseBody> getRecipeSave(@Header("Authorization") String header);

    @GET(ConfigAPI.Api.RECIPESUGGESTION)
    Call<ResponseBody> getAllRecipeSuggestion(@Header("Authorization") String header);

    @GET((ConfigAPI.Api.GET_RECIPE_BY_USER))
    Call<ResponseBody> getAllRecipebyUser(@Header("Authorization") String header, @Path("username") String username);

    @GET((ConfigAPI.Api.RECIPETOP))
    Call<ResponseBody> getAllRecipeTop(@Header("Authorization") String header);

    @GET((ConfigAPI.Api.RECIPEHISTORY))
    Call<ResponseBody> getAllRecipeHistory(@Header("Authorization") String header);

    @GET(ConfigAPI.Api.TOPCHEF)
    Call<ResponseBody> getTopChef(@Header("Authorization") String header);

    @GET((ConfigAPI.Api.FOLLOWER))
    Call<ResponseBody> getFollower(@Header("Authorization") String header);

    @POST(ConfigAPI.Api.ADFOLLOW)
    Call<ResponseBody> addFollower(@Header("Authorization") String header, @Body Follower requestBody);

    @POST(ConfigAPI.Api.UNFOLLOW)
    Call<ResponseBody> unFollower(@Header("Authorization") String header, @Body Follower requestBody);

    @PUT((ConfigAPI.Api.SAVE_RECIPE))
    Call<ResponseBody> saveRecipe(@Header("Authorization") String header, @Path("id") String id);

}
