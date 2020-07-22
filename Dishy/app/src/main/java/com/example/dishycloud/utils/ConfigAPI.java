package com.example.dishycloud.utils;

public class ConfigAPI {
    //https://myappapi20200527091033.azurewebsites.net/swagger/index.html
//    public static final String BASE_URL = "https://myappapi20200623011040.azurewebsites.net/api/";
    public static final String BASE_URL ="https://myappapi01.azurewebsites.net/api/";

    public interface Api {
        String LOGIN = "Auth/Login";
        String USER = "Register/GetInformation";
        String RECIPE = "Recipe";
        String REGISTER = "Auth/Register";
        String IMAGE = "image/";
        String GET_RECIPE_AUTH ="Recipe/getAllRecipebByAuthor";
        String GET_RECIPE_SAVE ="Recipe/getAllRecipebSaved";
    }

}
