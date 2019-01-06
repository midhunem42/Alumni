package com.android.alumni.model;

public class AdminModel {
    private String UserName,Department,Name,Password,imgUrl;



    public AdminModel() {
    }

    public AdminModel(String userName, String department, String name, String password, String imgUrl) {
        UserName = userName;
        Department = department;
        Name = name;
        Password = password;
        this.imgUrl = imgUrl;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
