package com.example.fooddeliveryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.Domain.FoodDomain;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.Interface.ItemsNumberChangeListener;
import com.example.fooddeliveryapp.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;
    private ManagementCart managementCart;
    private ItemsNumberChangeListener itemsNumberChangeListener;

    public CartListAdapter(Context context, ArrayList<FoodDomain> foodDomains, ItemsNumberChangeListener itemsNumberChangeListener) {
        this.foodDomains = foodDomains;
        this.managementCart = new ManagementCart(context);
        this.itemsNumberChangeListener = itemsNumberChangeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart_list, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDomain item = foodDomains.get(position);

        holder.title.setText(item.getTitle());
        holder.costPerUnit.setText(String.valueOf(item.getPrice()));
        holder.totalItem.setText(String.valueOf(item.getNumberInCart()));
        holder.totalCostPerItem.setText(String.valueOf(item.getPrice() * item.getNumberInCart()));
        holder.itemImage.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), item.getImageResId()));

        holder.plusItemBtn.setOnClickListener(view -> {
            managementCart.plusFoodItem(foodDomains, position, () -> {
                    notifyDataSetChanged();
                    itemsNumberChangeListener.changed();
                });
        });

        holder.minusItemBtn.setOnClickListener(view -> {
            managementCart.minusFoodItem(foodDomains, position, () -> {
                    notifyDataSetChanged();
                    itemsNumberChangeListener.changed();
            });
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, costPerUnit, totalItem, totalCostPerItem;
        ImageView itemImage, plusItemBtn, minusItemBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cartTitleTxt);
            costPerUnit = itemView.findViewById(R.id.costPerUnitTxt);
            totalCostPerItem = itemView.findViewById(R.id.costPerItemTxt);
            itemImage = itemView.findViewById(R.id.cartItemImage);
            plusItemBtn = itemView.findViewById(R.id.plusCardBtn);
            minusItemBtn = itemView.findViewById(R.id.minusCardBtn);
            totalItem = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
