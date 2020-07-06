package com.example.dishycloud.repositories;

import android.content.Context;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.ReponseData;
import com.example.dishycloud.models.User;
import com.example.dishycloud.utils.BaseUtils;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.utils.ClientApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishyRepositoryImp implements DishyRepository {
    @Override
    public void login(final String username, final String password, final Context context, final CallBackData<User> callBackData) {
        ClientApi clientApi = new ClientApi();
        // json của Credentials
        JSONObject userLogin = new JSONObject();
        try {
            userLogin.put("username", username);
            userLogin.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), userLogin.toString());
        Call<ResponseBody> serviceCall = clientApi.DishyService().login(body);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<User>>() {
                        }.getType();
                        ReponseData<User> data = new Gson().fromJson(result, type);
                        User user = data.getData();
                        if (user != null) {
                            callBackData.onSucess(user);
                        } else {
                            callBackData.onFail("Không thể đăng nhập!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        callBackData.onFail("Không thể đăng nhập!");
                    }
                } else {
                    callBackData.onFail("Không thể đăng nhập!");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("Không thể đăng nhập!");
            }
        });
    }

    @Override
<<<<<<< HEAD
    public void getUser(String token, final CallBackData<User> callBackData) {
        ClientApi clientApi = new ClientApi();

        String header = "Bearer "+token;

        Call<ResponseBody> bodyCall = clientApi.DishyService().getUser(header);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<User>>(){}.getType();
                        ReponseData<User> data = new Gson().fromJson(result,type);
                        User user = data.getData();
                        if (user!=null){
                            callBackData.onSucess(user);
                        }else{
                            callBackData.onFail("Không có dữ liệu!");
                        }
                    }catch (Exception e){
                        System.out.println("Lỗi quá trình parse dữ liệu");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("Không thể lấy thông tin");
            }
        });
    }

    @Override
    public void postRecipe(String token, final CallBackData<Recipe> callBackData) {
        ClientApi clientApi = new ClientApi();

        String header = "Bearer "+token;


//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), recipeJson.toString());

        Call<ResponseBody> bodyCall = clientApi.DishyService().postRecipe(header, BaseUtils.recipe);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<Recipe>>(){}.getType();
                        ReponseData<Recipe> data = new Gson().fromJson(result,type);
                        Recipe recipe = data.getData();
                        if (recipe!=null){
                            callBackData.onSucess(recipe);
                        }else{
                            callBackData.onFail("1");
                        }
                    }catch (Exception e){
                        System.out.println("2");
                    }
                }
                else{
                    callBackData.onFail("Responese: "+response.code()+" - "+response.message());
=======
    public void register(final String username, final String password, final String fullname, Context context, final CallBackData<User> callBackData) {
        ClientApi clientApi = new ClientApi();

        JSONObject newUser = new JSONObject();
        try {
            newUser.put("username", username);
            newUser.put("password", password);
            newUser.put("fullname", fullname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), newUser.toString());
        Call<ResponseBody> serviceCall = clientApi.DishyService().register(body);
        serviceCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<User>>() {
                        }.getType();
                        ReponseData<User> data = new Gson().fromJson(result, type);
                        User user = data.getData();
                        if (user != null) {
                            callBackData.onSucess(user);
                        } else {
                            callBackData.onFail("Không thể đăng ký!");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        callBackData.onFail("Không thể đăng ký!");
                    }

>>>>>>> e9987c503e38607b70d19ab5ef2f2a750facb7ef
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
<<<<<<< HEAD
                callBackData.onFail("4");
=======
                callBackData.onFail("Không thể đăng ký!");
>>>>>>> e9987c503e38607b70d19ab5ef2f2a750facb7ef
            }
        });
    }
}

