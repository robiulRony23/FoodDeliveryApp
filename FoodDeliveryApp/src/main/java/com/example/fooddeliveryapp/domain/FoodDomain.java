package com.example.fooddeliveryapp.domain;

public class FoodDomain {
    String title;
    int image;
    String description;
    Double price;
    int stockCount;

    public FoodDomain(String title, int image, String description, Double price, int stockCount) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.price = price;
        this.stockCount = stockCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

}
