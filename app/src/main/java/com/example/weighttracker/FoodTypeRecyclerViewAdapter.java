package com.example.weighttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class FoodTypeRecyclerViewAdapter extends RecyclerView.Adapter<FoodTypeRecyclerViewAdapter.ViewHolder> {

    private ClassFoodTypes mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int selectedPos = RecyclerView.NO_POSITION;

    // data is passed into the constructor
    FoodTypeRecyclerViewAdapter(Context context, ClassFoodTypes data) {
        this.mInflater = LayoutInflater.from(context);
        setHasStableIds(false);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.foodtype_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setSelected(selectedPos == position);
        String[] foodItem = mData.getNameAndBrand(position);
        holder.nameTextView.setText(foodItem[0]);
        holder.brandTextView.setText(foodItem[1]);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameTextView;
        TextView brandTextView;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.foodItemName);
            brandTextView = itemView.findViewById(R.id.foodItemBrand);
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
        return mData.getNameAndBrand(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}