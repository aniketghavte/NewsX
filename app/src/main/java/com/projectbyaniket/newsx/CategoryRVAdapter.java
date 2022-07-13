package com.projectbyaniket.newsx;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectbyaniket.newsx.Models.CategoryRVModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.viewHolder> {
    private List<CategoryRVModel> categoryRVModels;
    private CategoryClickInterface categoryClickInterface;
    private Context context;

    public CategoryRVAdapter(List<CategoryRVModel> categoryRVModels, CategoryClickInterface categoryClickInterface, Context context) {
        this.categoryRVModels = categoryRVModels;
        this.categoryClickInterface = categoryClickInterface;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item,parent,false);
        return new CategoryRVAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryRVModel categoryRVModel = categoryRVModels.get(position);
        holder.categoryTV.setText(categoryRVModel.getCategory());
        Picasso.get().load(categoryRVModel.getCategoryImageUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryRVModels.size();
    }

    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        private TextView categoryTV;
        private ImageView categoryIV;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTV =itemView.findViewById(R.id.idTVCategories);
            categoryIV = itemView.findViewById(R.id.idIVCategory);
        }
    }
}

