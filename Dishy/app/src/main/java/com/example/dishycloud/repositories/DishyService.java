package com.example.dishycloud.repositories;

import com.example.dishycloud.utils.ConfigAPI;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface DishyService {

    @POST(ConfigAPI.Api.LOGIN)
    Call<ResponseBody> login(@Body RequestBody requestBody);
}
