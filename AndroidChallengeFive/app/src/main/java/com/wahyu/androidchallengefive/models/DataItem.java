package com.wahyu.androidchallengefive.models;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("id")
	private int id;

	@SerializedName("avatar")
	private String avatar;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("email")
	private String email;

	public String getLastName(){
		return lastName;
	}

	public int getId(){
		return id;
	}

	public String getAvatar(){
		return avatar;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getEmail(){
		return email;
	}

//	@Override
// 	public String toString(){
//		return
//			"{" +
//			" \"last_name\" = '\"" + lastName + '\"' +
//			", \"id\" = '\"" + id + '\"' +
//			", \"avatar\" = '\"" + avatar + '\"' +
//			", \"first_name\" = '\"" + firstName + '\"' +
//			", \"email\" = '\"" + email + '\"' +
//			"}";
//		}
}