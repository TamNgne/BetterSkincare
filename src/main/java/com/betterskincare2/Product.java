package com.betterskincare2;

import java.util.ArrayList;

public class Product {
    private  Integer id;
    private String label;
    private String brand;
    private String name;
    private Integer price;
    private double rank;
    private String ingredients;

//skin type
    private boolean combination;
    private boolean dry;
    private boolean normal;
    private boolean oily;
    private boolean sensitive;

    public Product(Integer id, String label, String brand, String name, int price, double rank, String ingredients, boolean combination, boolean dry, boolean normal, boolean oily, boolean sensitive) {
        this.id = id;
        this.label = label;
        this.brand = brand;
        this.name  = name;
        this.ingredients = ingredients;
        this.price = price;
        this.rank = rank;
        this.combination = combination;
        this.dry = dry;
        this.normal = normal;
        this.oily = oily;
        this.sensitive = sensitive;
    }

    public String getLabel(){
        return label;
    }

    public String getBrand(){
        return brand;
    }

    public String getName(){
        return name;
    }

    public Integer getPrice() { return price;}

    public double getRank(){
        return rank;
    }
    public Integer getId() {return id;}

    public boolean isCombination() {return combination;}

    public boolean isDry() {return dry;}

    public boolean isNormal() {return normal;}

    public boolean isOily() {return oily;}

    public boolean isSensitive() {return sensitive;}

    public String getIngredients(){
        return ingredients;
    }

    public void setId(Integer id) {  this.id = id; }
    public void setLabel(String label) { this.label = label; }

    public void setBrand(String brand) { this.brand = brand; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setCombination(boolean combination) {this.combination = combination;}

    public void setDry(boolean dry) {this.dry = dry;}

    public void setNormal(boolean normal) {this.normal = normal;}

    public void setOily(boolean oily) {this.oily = oily;}

    public void setSensitive(boolean sensitive) {this.sensitive = sensitive; }
}
