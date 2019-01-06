package com.android.alumni.model;

public class StudentModel {
    private String name,email,rollno,department,semester,yearofjoining,mobileno,address,username,password,profilepicurl,batch,gender;

    public StudentModel() {
    }

    public StudentModel(String name, String email, String rollno, String department, String semester, String yearofjoining, String mobileno, String address, String username, String password, String profilepicurl, String batch, String gender) {
        this.name = name;
        this.email = email;
        this.rollno = rollno;
        this.department = department;
        this.semester = semester;
        this.yearofjoining = yearofjoining;
        this.mobileno = mobileno;
        this.address = address;
        this.username = username;
        this.password = password;
        this.profilepicurl = profilepicurl;
        this.batch = batch;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYearofjoining() {
        return yearofjoining;
    }

    public void setYearofjoining(String yearofjoining) {
        this.yearofjoining = yearofjoining;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilepicurl() {
        return profilepicurl;
    }

    public void setProfilepicurl(String profilepicurl) {
        this.profilepicurl = profilepicurl;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
