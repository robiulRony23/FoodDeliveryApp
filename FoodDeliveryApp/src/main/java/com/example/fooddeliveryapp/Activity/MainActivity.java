package com.example.fooddeliveryapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.Adapter.CategoryAdapter;
import com.example.fooddeliveryapp.Adapter.PopularFoodAdapter;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.Domain.CategoryDomain;
import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView.Adapter categoryAdapter;
    private RecyclerView.Adapter foodAdapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewPopularFoodList;

    private EditText searchItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchItemInit();
        recyclerViewCategory();
        recyclerViewPopularFood();
        bottomNavigation();
    }

    private void searchItemInit() {
        searchItem = findViewById(R.id.searchItem);
        searchItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d(TAG, "focusUpdated: " + hasFocus);
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.category_recycler_view);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryItem = new ArrayList<>();
        categoryItem.add(new CategoryDomain("Pizza", R.drawable.cat_1, R.drawable.category_backgroud_1));
        categoryItem.add(new CategoryDomain("Burger", R.drawable.cat_2, R.drawable.category_backgroud_2));
        categoryItem.add(new CategoryDomain("Hotdog", R.drawable.cat_3, R.drawable.category_backgroud_3));
        categoryItem.add(new CategoryDomain("Drink", R.drawable.cat_4, R.drawable.category_backgroud_4));
        categoryItem.add(new CategoryDomain("Donut", R.drawable.cat_5, R.drawable.category_backgroud_5));

        categoryAdapter = new CategoryAdapter(categoryItem);
        recyclerViewCategoryList.setAdapter(categoryAdapter);
    }

    private void recyclerViewPopularFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularFoodList = findViewById(R.id.popular_recycler_view);
        recyclerViewPopularFoodList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> popularFoodItem = new ArrayList<>();
        popularFoodItem.add(new FoodDomain("Pepperoni Pizza", R.drawable.pop_1, "cheese, fresh oregano pizza, dish of Italian origin consisting of a flattened disk of bread dough topped with some combination of olive oil, oregano, tomato, olives", 500.0, 5));
        popularFoodItem.add(new FoodDomain("Cheese Burger", R.drawable.pop_2, "The Beef Burger, Juicy, big, loaded with toppings of my choice.High quality beef medium to well with cheese and bacon on a multigrain bun. cheese, special sauce", 250.0, 10));
        popularFoodItem.add(new FoodDomain("Vegetable Pizza", R.drawable.pop_3, "vegetable with chilly sauce, White pizza with garlic infused olive oil, goat cheese, fresh mozzarella fior di latte, prosciutto fired off then topped with arugula and shaved parmesan", 750.0, 3));


        foodAdapter = new PopularFoodAdapter(popularFoodItem);
        recyclerViewPopularFoodList.setAdapter(foodAdapter);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.home_button);
        floatingActionButton.setOnClickListener((view) -> {
            startActivity(new Intent(this, CartListActivity.class));
        });
    }
}