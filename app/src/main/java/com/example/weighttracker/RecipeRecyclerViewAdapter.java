package com.example.weighttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class RecipeRecyclerViewAdapter extends  RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder>{

    private Recipe mData;
    private LayoutInflater mInflater;
    private FoodTypeRecyclerViewAdapter.ItemClickListener mClickListener;
    private int selectedPos = RecyclerView.NO_POSITION;

    // data is passed into the constructor
    RecipeRecyclerViewAdapter(Context context, Recipe data) {
        this.mInflater = LayoutInflater.from(context);
        setHasStableIds(false);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public RecipeRecyclerViewAdapter.RecipeViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recipe_recyclerview_row, parent, false);

        return new RecipeRecyclerViewAdapter.RecipeViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(RecipeRecyclerViewAdapter.RecipeViewHolder holder, int position) {
        holder.itemView.setSelected(selectedPos == position);
        String[] foodItem = mData.getIngredientDataAtIndex(position);
        holder.nameTextView.setText(foodItem[0]);
        holder.weightTextView.setText(foodItem[1]);
        holder.energyTextView.setText(foodItem[2]);
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView weightTextView;
        TextView energyTextView;

        RecipeViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.recipeFoodItemName);
            weightTextView = itemView.findViewById(R.id.recipeFoodItemWeight);
            energyTextView = itemView.findViewById(R.id.recipeFoodItemEnergy);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            notifyItemChanged(selectedPos);
            selectedPos = getLayoutPosition();
            notifyItemChanged(selectedPos);
        }
    }
    // convenience method for getting data at click position
    String[] getItem(int id) {
        return mData.getIngredientDataAtIndex(id);
    }

    // allows clicks events to be caught
    void setClickListener(FoodTypeRecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }
}
