package com.wahyu.androidchallengefive.models;

import com.google.gson.annotations.SerializedName;

public class Ad{

	@SerializedName("company")
	private String company;

	@SerializedName("text")
	private String text;

	@SerializedName("url")
	private String url;

	public String getCompany(){
		return company;
	}

	public String getText(){
		return text;
	}

	public String getUrl(){
		return url;
	}

//	@Override
// 	public String toString(){
//		return
//			"{" +
//			" \"company\" = '\"" + company + '\"' +
//			", \"text\" = '\"" + text + '\"' +
//			", \"url\" = '\"" + url + '\"' +
//			"}";
//		}
}