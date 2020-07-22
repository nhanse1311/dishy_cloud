package com.example.dishycloud.views;

import java.util.List;

public interface PostImageView {
    void onPostImageSucces(List<String> imageNames);
    void onPostImageFail(String fail);
}
