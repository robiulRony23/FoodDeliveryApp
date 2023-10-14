package com.example.fooddeliveryapp.activity;

import static com.example.fooddeliveryapp.Utils.Constants.KEY_FOOD_ITEM;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.domain.FoodDomain;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView addToCartBtn, textTitle, priceTxt, descriptionTxt, orderAmountTxt;
    private ImageView plusBtn, minusBtn, foodImage;
    private FoodDomain foodItem;
    private int numberOfOrder;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        managementCart = new ManagementCart(this);

        intiViews();
        getBundle();
    }

    private void getBundle() {
        foodItem = (FoodDomain) getIntent().getSerializableExtra(KEY_FOOD_ITEM);

        textTitle.setText(foodItem.getTitle());
        priceTxt.setText("$" + foodItem.getPrice());
        descriptionTxt.setText(foodItem.getDescription());
        foodImage.setImageDrawable(getDrawable(foodItem.getImageResId()));
        orderAmountTxt.setText(String.valueOf(numberOfOrder));

        plusBtn.setOnClickListener(view -> {
            numberOfOrder++;
            orderAmountTxt.setText(String.valueOf(numberOfOrder));
        });

        minusBtn.setOnClickListener(view -> {
            if (numberOfOrder > 1) {
                numberOfOrder--;
                orderAmountTxt.setText(String.valueOf(numberOfOrder));
            }
        });

        addToCartBtn.setOnClickListener(view -> {
            foodItem.setNumberInCart(numberOfOrder);
            managementCart.insetFood(foodItem);
        });
    }

    private void intiViews() {
        addToCartBtn = findViewById(R.id.addToCardDetailsBtn);
        textTitle = findViewById(R.id.titleText);
        priceTxt = findViewById(R.id.priceText);
        descriptionTxt = findViewById(R.id.descriptionText);
        orderAmountTxt = findViewById(R.id.totalAmountText);

        plusBtn = findViewById(R.id.plusImageView);
        minusBtn = findViewById(R.id.minusImageView);
        foodImage = findViewById(R.id.foodImage);
    }
}