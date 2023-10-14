package com.example.fooddeliveryapp.domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {
    String title;
    int imageResId;
    String description;
    Double price;
    int numberInCart;

    public FoodDomain(String title, int imageResId, String description, Double price, int numberInCart) {
        this.title = title;
        this.imageResId = imageResId;
        this.description = description;
        this.price = price;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

}
