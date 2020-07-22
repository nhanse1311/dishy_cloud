package com.example.dishycloud.repositories;

import android.content.Context;

import com.example.dishycloud.models.Chef;
import com.example.dishycloud.models.Recipe;
import com.example.dishycloud.models.ReponseData;
import com.example.dishycloud.models.ResponseGet;
import com.example.dishycloud.models.ResponseGetChef;
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
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

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
    public void getUser(String token, final CallBackData<User> callBackData) {
        ClientApi clientApi = new ClientApi();

        String header = "Bearer " + token;

        Call<ResponseBody> bodyCall = clientApi.DishyService().getUser(header);
        bodyCall.enqueue(new Callback<ResponseBody>() {
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
                            callBackData.onFail("Không có dữ liệu!");
                        }
                    } catch (Exception e) {
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

        String header = "Bearer " + token;


//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), recipeJson.toString());

        Call<ResponseBody> bodyCall = clientApi.DishyService().postRecipe(header, BaseUtils.recipe);
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
                callBackData.onFail("Không thể tạo món ăn");

            }
        });
    }

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
    public void getAllRecipeSuggestion(String token, final CallBackData<List<Recipe>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String header = "Bearer " + token;

        Call<ResponseBody> bodyCall = clientApi.DishyService().getAllRecipeSuggestion(header);

        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<ResponseGet>>() {
                        }.getType();
                        ReponseData<ResponseGet> data = new Gson().fromJson(result, type);
                        List<Recipe> recipeList = data.getData().getResults();
                        if (recipeList != null) {
                            callBackData.onSucess(recipeList);
                        } else {
                            callBackData.onFail("Không có dữ liệu!");
                        }
                    } catch (Exception e) {
                        System.out.println("Lỗi quá trình parse dữ liệu!");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("Không có thông tin về công thức");
            }
        });
    }

    @Override
    public void getAllRecipeTop(String token, final CallBackData<List<Recipe>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String header = "Bearer " + token;

        Call<ResponseBody> bodyCall = clientApi.DishyService().getAllRecipeTop(header);

        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<ResponseGet>>() {
                        }.getType();
                        ReponseData<ResponseGet> data = new Gson().fromJson(result, type);
                        List<Recipe> recipeList = data.getData().getResults();
                        if (recipeList != null) {
                            callBackData.onSucess(recipeList);
                        } else {
                            callBackData.onFail("Không có dữ liệu");
                        }
                    } catch (Exception e) {
                        System.out.println("Lối trong quá trình parse dữ liệu");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBackData.onFail("Không có thông tin về công thức");
            }
        });
    }

    @Override
    public void getAllRecipeHistory(String token, final CallBackData<List<Recipe>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String header = "Bearer " + token;

        Call<ResponseBody> bodyCall = clientApi.DishyService().getAllRecipeHistory(header);

        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<ResponseGet>>() {
                        }.getType();
                        ReponseData<ResponseGet> data = new Gson().fromJson(result, type);
                        List<Recipe> recipeList = data.getData().getResults();
                        if (recipeList != null) {
                            callBackData.onSucess(recipeList);
                        } else {
                            callBackData.onFail("Không có dữ liệu");
                        }
                    } catch (Exception e) {
                        System.out.println("Lỗi trong quá trình parse dữ liệu");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void getTopChef(String token, final CallBackData<List<User>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String header = "Bearer " + token;

        Call<ResponseBody> bodyCall = clientApi.DishyService().getTopChef(header);

        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<ResponseGetChef>>() {
                        }.getType();
                        ReponseData<ResponseGetChef> data = new Gson().fromJson(result, type);
                        List<User> chefList = data.getData().getResults();
                        if (chefList != null) {
                            callBackData.onSucess(chefList);
                        } else {
                            callBackData.onFail("Không có dữ liệu");
                        }
                    } catch (Exception e) {
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
    public void getFollower(String token, final CallBackData<List<User>> callBackData) {
        ClientApi clientApi = new ClientApi();
        String header = "Bearer " + token;

        Call<ResponseBody> bodyCall = clientApi.DishyService().getFollower(header);

        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        Type type = new TypeToken<ReponseData<ResponseGetChef>>() {
                        }.getType();
                        ReponseData<ResponseGetChef> data = new Gson().fromJson(result, type);
                        List<User> followerList = data.getData().getResults();
                        if (followerList != null) {
                            callBackData.onSucess(followerList);
                        } else {
                            callBackData.onFail("Không có dữ liệu");
                        }
                    } catch (Exception e) {
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

