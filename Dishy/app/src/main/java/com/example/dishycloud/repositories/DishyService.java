package com.example.dishycloud.repositories;

import com.example.dishycloud.models.Recipe;
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

    @GET(ConfigAPI.Api.GET_RECIPE_AUTH)
    Call<ResponseBody> getRecipeSave(@Header("Authorization") String header);
}
