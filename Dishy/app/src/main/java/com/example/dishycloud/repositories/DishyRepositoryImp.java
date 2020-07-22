package com.example.dishycloud.repositories;

import android.content.Context;

import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.ReponseData;
import com.example.dishycloud.models.ResponseGet;
import com.example.dishycloud.models.User;
import com.example.dishycloud.utils.BaseUtils;
import com.example.dishycloud.utils.CallBackData;
import com.example.dishycloud.utils.ClientApi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishyRepositoryImp implements DishyRepository{
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
    public void postRecipe(String token, Recipe recipe, final CallBackData<Recipe> callBackData) {
        ClientApi clientApi = new ClientApi();
        String header = "Bearer " + token;

//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), recipeJson.toString());

        Call<ResponseBody> bodyCall = clientApi.DishyService().postRecipe(header, recipe);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<Recipe>>() {
                        }.getType();
                        ReponseData<Recipe> data = new Gson().fromJson(result, type);
                        Recipe recipe = data.getData();
                        if (recipe != null) {
                            callBackData.onSucess(recipe);
                        } else {
                            callBackData.onFail("1");
                        }
                    } catch (Exception e) {
                        System.out.println("2");
                    }
                } else {
                    callBackData.onFail("Responese: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
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

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                callBackData.onFail("4");

                callBackData.onFail("Không thể đăng ký!");

            }
        });
    }

    @Override
    public void postImage(Context context, String token, List<String> imagePath, final CallBackData<List<String>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String header = "Bearer " + token;
        List<MultipartBody.Part> bodies = new ArrayList<>();
        for (int i = 0; i < imagePath.size(); i++) {
            File file = new File(imagePath.get(i));
            // create RequestBody instance from file
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            // MultipartBody.Part is used to send also the actual file name
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            //add to list MultipartBody.Part
            bodies.add(body);
        }
        Call<ResponseBody> bodyCall = clientApi.DishyService().postImage(header, bodies);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<List<String>>() {
                        }.getType();
                        List<String> data = new Gson().fromJson(result, type);
                        if (data != null) {
                            callBackData.onSucess(data);
                        } else {
                            callBackData.onFail("1");
                        }
                    } catch (Exception e) {
                        callBackData.onFail("2: " + e.getMessage());
                    }
                } else {
                    callBackData.onFail("Responese: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("3: " + t);
            }
        });
    }

    @Override
    public void getRecipeByAuth(Context context, String token, final CallBackData<List<Recipe>> callBackData) {
        ClientApi clientApi = new ClientApi();

        String header = "Bearer "+token;

        Call<ResponseBody> bodyCall = clientApi.DishyService().getRecipeByAuth(header);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<ResponseGet>>(){}.getType();
                        ReponseData<ResponseGet> data = new Gson().fromJson(result,type);
                        List<Recipe> recipes = data.getData().getResults();
                        if (data!=null){
                            callBackData.onSucess(recipes);
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
    public void getRecipeSave(Context context, String token, final CallBackData<List<Recipe>> callBackData) {
        ClientApi clientApi = new ClientApi();

        String header = "Bearer "+token;

        Call<ResponseBody> bodyCall = clientApi.DishyService().getRecipeSave(header);
        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code()==200){
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<ResponseGet>>(){}.getType();
                        ReponseData<ResponseGet> data = new Gson().fromJson(result,type);
                        List<Recipe> recipes = data.getData().getResults();
                        if (data!=null){
                            callBackData.onSucess(recipes);
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
}

