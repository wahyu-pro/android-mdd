package com.wahyu.androidchallengefive.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PostModel implements Parcelable {

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private int userId;

	protected PostModel(Parcel in) {
		id = in.readInt();
		title = in.readString();
		body = in.readString();
		userId = in.readInt();
	}

	public static final Creator<PostModel> CREATOR = new Creator<PostModel>() {
		@Override
		public PostModel createFromParcel(Parcel in) {
			return new PostModel(in);
		}

		@Override
		public PostModel[] newArray(int size) {
			return new PostModel[size];
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(body);
		dest.writeInt(userId);
	}
}