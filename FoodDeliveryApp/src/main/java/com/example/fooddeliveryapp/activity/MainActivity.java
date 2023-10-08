package com.example.fooddeliveryapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.fooddeliveryapp.Adapter.CategoryAdapter;
import com.example.fooddeliveryapp.Adapter.PopularFoodAdapter;
import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.domain.CategoryDomain;
import com.example.fooddeliveryapp.domain.FoodDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
    }

    private void searchItemInit() {
        searchItem = findViewById(R.id.searchItem);
        searchItem.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("rony", "focusUpdated: " + hasFocus);
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
        popularFoodItem.add(new FoodDomain("Pepperoni Pizza", R.drawable.pop_1, "cheese, fresh oregano", 599.0, 5));
        popularFoodItem.add(new FoodDomain("Cheese Burger", R.drawable.pop_2, "cheese, special sauce", 350.0, 10));
        popularFoodItem.add(new FoodDomain("Vegetable Pizza", R.drawable.pop_3, "vegetable with chilly sauce", 799.0, 3));


        foodAdapter = new PopularFoodAdapter(popularFoodItem);
        recyclerViewPopularFoodList.setAdapter(foodAdapter);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}