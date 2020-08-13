package com.wahyu.challengefourmaterialdesign.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

public class Products{

	@SerializedName("image")
	private String image;

	@SerializedName("price")
	private double price;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("category")
	private String category;

	@BindingAdapter("profileImage")
	public static void loadImage(ImageView view, String imageUrl) {
		Glide.with(view.getContext())
				.load(imageUrl).apply(new RequestOptions().centerInside())
				.into(view);
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}
}