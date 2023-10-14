package com.example.fooddeliveryapp.Helper;

import static com.example.fooddeliveryapp.Utils.Constants.KEY_CARD_LIST;

import android.content.Context;
import android.widget.Toast;

import com.example.fooddeliveryapp.domain.FoodDomain;

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

    private ArrayList<FoodDomain> getListCard() {
        return tinyDB.getListObject(KEY_CARD_LIST);
    }


}
