package com.example.dishycloud.utils;

public class ConfigAPI {
    //https://myappapi20200527091033.azurewebsites.net/swagger/index.html
    public static final String BASE_URL = "https://myappapi01.azurewebsites.net/api/";

    public interface Api {
        String LOGIN = "Auth/Login";
        String USER = "Register/GetInformation";
        String RECIPE = "Recipe";
        String REGISTER = "Auth/Register";
        String RECIPESUGGESTION = "Recipe/getAllRecipeSuggestion";
        String RECIPETOP = "Recipe/getAllRecipeTop";
        String RECIPEHISTORY = "Recipe/getAllRecipeHistory";
        String TOPCHEF = "Register/GetTopChef";
        String FOLLOWER = "Follower";
    }

}
