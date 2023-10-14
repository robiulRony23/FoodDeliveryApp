package com.example.fooddeliveryapp.Helper;

import static com.example.fooddeliveryapp.Utils.Constants.KEY_CARD_LIST;

import android.content.Context;
import android.widget.Toast;

import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.Interface.ItemsNumberChangeListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        tinyDB = new TinyDB(context);
    }

    public void insetFood(FoodDomain item) {
        ArrayList<FoodDomain> foodList = getListCard();
        boolean alreadyInList = false;
        int itemIndex = 0;

        for (int i=0; i<foodList.size(); i++) {
            if (foodList.get(i).getTitle().equals(item.getTitle())) {
                itemIndex = i;
                alreadyInList = true;
                break;
            }
        }

        if (alreadyInList) {
            foodList.get(itemIndex).setNumberInCart(item.getNumberInCart());
        } else {
            foodList.add(item);
        }

        tinyDB.putListObject(KEY_CARD_LIST, foodList);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCard() {
        return tinyDB.getListObject(KEY_CARD_LIST);
    }

    public void plusFoodItem(ArrayList<FoodDomain> foodList, int position, ItemsNumberChangeListener itemsNumberChangeListener) {
        foodList.get(position).setNumberInCart(foodList.get(position).getNumberInCart() + 1);
        tinyDB.putListObject(KEY_CARD_LIST, foodList);
        itemsNumberChangeListener.changed();
    }

    public void minusFoodItem(ArrayList<FoodDomain> foodList, int position, ItemsNumberChangeListener itemsNumberChangeListener) {
        int numberOfItem = foodList.get(position).getNumberInCart();
        if (numberOfItem <= 1) {
            foodList.remove(position);
        } else {
            foodList.get(position).setNumberInCart(numberOfItem - 1);
        }
        tinyDB.putListObject(KEY_CARD_LIST, foodList);
        itemsNumberChangeListener.changed();
    }

    public double getTotalFee() {
        ArrayList<FoodDomain> foodList = getListCard();
        double totalCost = 0.0;

        for(FoodDomain item : foodList) {
            totalCost += item.getNumberInCart() * item.getPrice();
        }
        return totalCost;
    }


}
