package com.betterskincare;

import java.util.List;

public class Product {
    private final Integer id;
    private final String label;
    private final String brand;
    private final String name;
    private final Integer price;
    private final double rank;
    private final String ingredients;

//skin type
    private final boolean combination;
    private final boolean dry;
    private final boolean normal;
    private final boolean oily;
    private final boolean sensitive;

//Skintype matched
    private final List<String> skintypesMatched;
    public Product(Integer id, String label, String brand, String name, int price, double rank, String ingredients, boolean combination, boolean dry, boolean normal, boolean oily, boolean sensitive, List<String> skintypesMatched) {
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
        this.skintypesMatched = skintypesMatched;
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

    public String getIngredients(){return ingredients;}

    public List<String> getSkinTypesMatched() {return skintypesMatched;}
}
