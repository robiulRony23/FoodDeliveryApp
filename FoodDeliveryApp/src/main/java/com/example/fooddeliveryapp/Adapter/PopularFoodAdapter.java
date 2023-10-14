package com.example.fooddeliveryapp.Adapter;

import static com.example.fooddeliveryapp.Utils.Constants.KEY_FOOD_ITEM;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.R;
import com.example.fooddeliveryapp.activity.ShowDetailActivity;
import com.example.fooddeliveryapp.domain.FoodDomain;

import java.util.ArrayList;

public class PopularFoodAdapter extends RecyclerView.Adapter<PopularFoodAdapter.ViewHolder> {

    private ArrayList<FoodDomain> foodDomains;

    public PopularFoodAdapter(ArrayList<FoodDomain> foodDomains) {
        this.foodDomains = foodDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular_food, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularFoodAdapter.ViewHolder holder, int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.image.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), foodDomains.get(position).getImageResId()));
        holder.price.setText(foodDomains.get(position).getPrice().toString());
        holder.addToCartBtn.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra(KEY_FOOD_ITEM, foodDomains.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, addToCartBtn;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.categoryTitle);
            image = itemView.findViewById(R.id.categoryImage);
            price = itemView.findViewById(R.id.categoryPrice);
            addToCartBtn = itemView.findViewById(R.id.addToCardBtn);
        }
    }
}
