package com.wahyu.androidchallengefive.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wahyu.androidchallengefive.R;
import com.wahyu.androidchallengefive.models.DataItem;
import com.wahyu.androidchallengefive.models.PostModel;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {

    private List<DataItem> userList;
    private List<DataItem> userListFiltered;
    private Context context;


    public void setUserList(Context context,final List<DataItem> userList){
        this.context = context;
        if(this.userList == null){
            this.userList = userList;
            this.userListFiltered = userList;
            notifyItemChanged(0, userListFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return UserAdapter.this.userList.size();
                }

                @Override
                public int getNewListSize() {
                    return userList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return UserAdapter.this.userList.get(oldItemPosition).getFirstName() == userList.get(newItemPosition).getFirstName();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    DataItem newUser = UserAdapter.this.userList.get(oldItemPosition);

                    DataItem oldMovie = userList.get(newItemPosition);

                    return newUser.getFirstName() == oldMovie.getFirstName() ;
                }
            });
            this.userList = userList;
            this.userListFiltered = userList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_items, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        DataItem user = userList.get(position);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.notfound);
        Glide.with(holder.itemView.getContext()).load(user.getAvatar()).apply(options).into(holder.photo);
        holder.username.setText(user.getEmail());
        holder.fullname.setText(user.getFirstName() + " " + user.getLastName());
        holder.itemView.setOnClickListener((View v) -> {
            onItemClickCallback.onItemClicked(userList.get(holder.getAdapterPosition()));
        });
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public int getItemCount() {
        if(userList != null){
            return userListFiltered.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    userListFiltered = userList;
                } else {
                    List<DataItem> filteredList = new ArrayList<>();
                    for (DataItem user : userList) {
                        if (user.getFirstName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(user);
                        }
                    }
                    userListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = userListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userListFiltered = (ArrayList<DataItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView username;
        TextView fullname;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.avatar);
            username = itemView.findViewById(R.id.tv_username);
            fullname = itemView.findViewById(R.id.full_name);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(DataItem data);
    }
}
