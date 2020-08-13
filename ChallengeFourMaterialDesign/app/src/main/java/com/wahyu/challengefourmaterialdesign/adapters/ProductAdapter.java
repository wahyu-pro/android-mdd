package com.wahyu.challengefourmaterialdesign.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wahyu.challengefourmaterialdesign.R;
import com.wahyu.challengefourmaterialdesign.databinding.CardItemsBinding;
import com.wahyu.challengefourmaterialdesign.models.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Products> products = new ArrayList<>();

    public void setProducts(List<Products> products) {
        this.products = products;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardItemsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.card_items,
                parent,
                false);

        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        holder.bindData(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        CardItemsBinding binding;
        public ProductViewHolder(@NonNull CardItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(Products products) {
            binding.setProd(products);
            binding.setImageurl(products.getImage());
        }
    }
}
