package com.wahyu.androidchallengefive.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UsersModel {

	@SerializedName("per_page")
	private int perPage;

	@SerializedName("total")
	private int total;

	@SerializedName("ad")
	private Ad ad;

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	public int getPerPage(){
		return perPage;
	}

	public int getTotal(){
		return total;
	}

	public Ad getAd(){
		return ad;
	}

	public List<DataItem> getData(){
		return data;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

//	@Override
// 	public String toString(){
//		return
//			"{" +
//			" \"per_page\" = '\"" + perPage + '\"' +
//			", \"total\"= '\"" + total + '\"' +
//			", \"ad\" = '\"" + ad + '\"' +
//			", \"data\" = '\"" + data + '\"' +
//			", \"page\" = '\"" + page + '\"' +
//			", \"total_pages\" = '\"" + totalPages + '\"' +
//			"}";
//		}
}