package com.example.fooddeliveryapp.domain;

import android.graphics.drawable.Drawable;

public class CategoryDomain {
    String title;
    int image;



    int background;

    public CategoryDomain(String title, int image, int background) {
        this.title = title;
        this.image = image;
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
