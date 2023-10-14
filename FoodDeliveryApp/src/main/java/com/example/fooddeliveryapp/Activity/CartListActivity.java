package com.example.fooddeliveryapp.Activity;

import static com.example.fooddeliveryapp.Utils.Constants.DELIVERY_CHARGE;
import static com.example.fooddeliveryapp.Utils.Constants.TAX_RATE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.Adapter.CartListAdapter;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.Interface.ItemsNumberChangeListener;
import com.example.fooddeliveryapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private static final String TAG = "CartListActivity";
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private ScrollView scrollView;
    private TextView itemCostTxt, taxTxt, deliverCostTxt, totalCostTxt, emptyCartTxt, checkoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        managementCart = new ManagementCart(this);

        intiViews();
        initList();
        calculateCartCost();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.home_button);

        homeBtn.setOnClickListener((view) -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    private void intiViews() {
        recyclerViewList = findViewById(R.id.cartRecycleView);
        scrollView = findViewById(R.id.cartScrollView);
        itemCostTxt = findViewById(R.id.itemCostTxt);
        deliverCostTxt =findViewById(R.id.deliveryCostTxt);
        taxTxt =findViewById(R.id.taxCostTxt);
        totalCostTxt =findViewById(R.id.totalCostTxt);
        emptyCartTxt =findViewById(R.id.emptyCartTxt);
        checkoutBtn = findViewById(R.id.checkoutBtn);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        adapter = new CartListAdapter(CartListActivity.this, managementCart.getListCard(), new ItemsNumberChangeListener() {
            @Override
            public void changed() {
                calculateCartCost();
                checkEmptyCart();
            }
        });

        recyclerViewList.setAdapter(adapter);
        checkoutBtn.setOnClickListener((view) -> {
            managementCart.clearList();
            Toast.makeText(this, "Enjoy Your Food", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        });

        checkEmptyCart();
    }

    private void checkEmptyCart() {
        int listSize = managementCart.getListCard().size();
        if (listSize == 0) {
            Log.d(TAG, "recycleViewList is empty()");
            emptyCartTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            Log.d(TAG, "recycleViewList size() : " + listSize);
            emptyCartTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCartCost() {
        double itemCost = managementCart.getTotalFee();
        double taxCost = itemCost * TAX_RATE;
        double totalCost = itemCost + taxCost + DELIVERY_CHARGE;

        itemCostTxt.setText(String.valueOf((itemCost*100)/100));
        taxTxt.setText(String.valueOf((taxCost * 100) / 100));
        deliverCostTxt.setText(String.valueOf(DELIVERY_CHARGE));
        totalCostTxt.setText(String.valueOf((totalCost*100)/100));
    }
}