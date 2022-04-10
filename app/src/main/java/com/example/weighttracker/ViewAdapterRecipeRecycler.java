package com.example.weighttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ViewAdapterRecipeRecycler extends  RecyclerView.Adapter<ViewAdapterRecipeRecycler.RecipeViewHolder>{

    private ClassRecipe mData;
    private LayoutInflater mInflater;
    private FoodTypeRecyclerViewAdapter.ItemClickListener mClickListener;
    private int selectedPos = RecyclerView.NO_POSITION;

    // data is passed into the constructor
    ViewAdapterRecipeRecycler(Context context, ClassRecipe data) {
        this.mInflater = LayoutInflater.from(context);
        setHasStableIds(false);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewAdapterRecipeRecycler.RecipeViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recipe_recyclerview_row, parent, false);

        return new ViewAdapterRecipeRecycler.RecipeViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewAdapterRecipeRecycler.RecipeViewHolder holder, int position) {
        holder.itemView.setSelected(selectedPos == position);
        ClassIngredient ingredient = mData.getIngredientDataAtIndex(position);

        String units = ingredient.getUnits();
       if (units.equals("grams"))
       {
           holder.weightTextView.setText(String.valueOf(ingredient.getAmount())+ "g");
       }
       else
       {
           holder.weightTextView.setText(String.valueOf(ingredient.getAmount())+ "mL");
       }
        holder.nameTextView.setText(ingredient.getFoodName());

        holder.energyTextView.setText(String.valueOf(ingredient.getEnergy()) + "Kj");
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
    ClassIngredient getItem(int id) {
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

    public ClassRecipe getRecipe()
    {
        return mData;
    }
}
