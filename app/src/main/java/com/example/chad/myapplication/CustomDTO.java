package com.example.chad.myapplication;

/**
 * Created by chad on 2017-09-23.
 */

// DTO : data transfer object, 계층간 데이터교환을 위한 class
public class CustomDTO {
    private int resId;
    private String title;
    private String content;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
