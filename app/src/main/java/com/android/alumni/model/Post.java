package com.android.alumni.model;

public class Post {
    private String title,desc,time,username,imageurl;

    public Post() {
    }

    public Post(String title, String desc, String time, String username, String imageurl) {
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.username = username;
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}



