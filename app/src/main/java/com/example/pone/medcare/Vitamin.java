package com.example.pone.medcare;

public class Vitamin {
    private int id;
    private String name;
    private String food;
    private String effect;

    public int getId() {   return id;}
    public Vitamin setId(int id) { this.id = id; return  this;}
    public String getName() { return name; }
    public Vitamin setName(String name) { this.name = name; return  this; }
    public String getFood() {  return food;  }
    public Vitamin setFood(String food) { this.food = food; return  this; }
    public String getEffect() { return effect;  }
    public Vitamin setEffect(String effect) {  this.effect = effect; return  this; }
}
