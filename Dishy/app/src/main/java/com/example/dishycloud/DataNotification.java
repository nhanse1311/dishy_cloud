package com.example.dishycloud;

public class DataNotification {
    private String img;
    private String time;
    private String content;
    private String avatar;
    private String loginName;
    private String likeNumber;


    public DataNotification(String img, String time, String content, String avatar, String loginName, String likeNumber) {
        this.img = img;
        this.time = time;
        this.content = content;
        this.avatar = avatar;
        this.loginName = loginName;
        this.likeNumber = likeNumber;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
