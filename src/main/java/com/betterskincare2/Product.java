package com.betterskincare2;

import java.util.ArrayList;

public class Product {
    private String label;
    private String brand;
    private String name;
    private int price;
    private double rank;
    private ArrayList<String> ingredient;
//    private int combination;
//    private int dry;
//    private int normal;
//    private int oily;
//    private int sensitive;
    public Product(String label, String brand, String name, ArrayList<String> ingredient, int price, double rank) {
        this.label = label;
        this.brand = brand;
        this.name  = name;
        this.ingredient = ingredient;
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

    public int getPrice(){
        return price;
    }

    public double getRank(){
        return rank;
    }

    public ArrayList<String> getIngredient(){
        return ingredient;
    }
}
