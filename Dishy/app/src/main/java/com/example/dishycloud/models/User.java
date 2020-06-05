package com.example.dishycloud.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("roles")
    private String roles;
    @SerializedName("avarter")
    private String avarter;
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("access_token")
    private String access_token;
    @SerializedName("expires_in")
    private String expires_in;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAvarter() {
        return avarter;
    }

    public void setAvarter(String avarter) {
        this.avarter = avarter;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
