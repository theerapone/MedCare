package com.example.pone.medcare;

import java.util.Date;

public class User {
    private int id;
    private String fname;
    private String lname;
    private Date birth;
    private int gender;
    private double height;
    private double weight;

    public static User user;
    public User(){}
    public User(int id, String fname, String lname, Date birth, int gender, double height, double weight){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.birth = birth;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }
    public static User init(){
        if(user==null) user = new User();
        return user;
    }
    public User setId(int id){
        this.id = id;
        return this;
    }
    public User setFname(String fname){
        this.fname = fname;
        return this;
    }
    public  User setLname(String lname){
        this.lname = lname;
        return  this;
    }
    public User setBirth(Date date){
        this.birth = date;
        return  this;
    }
    public User setGender(int gender){
        this.gender = gender;
        return this;
    }
    public User setHeight(double height){
        this.height = height;
        return  this;
    }
    public User setWeight(double weight){
        this.weight = weight;
        return  this;
    }

    public int getId(){
        return this.id;
    }
    public String getFname(){
        return this.fname;
    }
    public String getLname(){
        return this.lname;
    }
    public Date getBirth() { return birth;  }
    public int getGender() { return gender;  }
    public double getHeight() {  return height;  }
    public double getWeight() {  return weight;  }
}
