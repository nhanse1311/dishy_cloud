package com.example.dishycloud.utils;


import com.example.dishycloud.repositories.DishyService;

public class ClientApi extends  BaseApi {
    public DishyService DishyService(){
        return this.getService(DishyService.class,ConfigAPI.BASE_URL);
    }
}
