package com.betterskincare2;

import java.util.ArrayList;

public class Product {
    private String label;
    private String brand;
    private String name;
    private Integer price;
    private double rank;
    private String ingredients;
//    private int combination;
//    private int dry;
//    private int normal;
//    private int oily;
//    private int sensitive;
    public Product(String label, String brand, String name, int price, double rank, String ingredients) {
        this.label = label;
        this.brand = brand;
        this.name  = name;
        this.ingredients = ingredients;
        this.price = price;
        this.rank = rank;
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

    public String getIngredients(){
        return ingredients;
    }

    public void setLabel(String label) { this.label = label; }

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
}
