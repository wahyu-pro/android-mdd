package com.wahyu.androidchallengefive.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wahyu.androidchallengefive.R;
import com.wahyu.androidchallengefive.databinding.ActivityPostBinding;
import com.wahyu.androidchallengefive.models.DataItem;
import com.wahyu.androidchallengefive.models.PostModel;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> implements Filterable {

    private List<PostModel> postList;
    private List<PostModel> postListFiltered;
    private Context context;

    public void setPostList(Context context,final List<PostModel> postList){
        this.context = context;
        if(this.postList == null){
            this.postList = postList;
            this.postListFiltered = postList;
            notifyItemChanged(0, postListFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return PostAdapter.this.postList.size();
                }

                @Override
                public int getNewListSize() {
                    return postList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return PostAdapter.this.postList.get(oldItemPosition).getTitle() == postList.get(newItemPosition).getTitle();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    PostModel newUser = PostAdapter.this.postList.get(oldItemPosition);

                    PostModel oldMovie = postList.get(newItemPosition);

                    return newUser.getTitle() == oldMovie.getTitle() ;
                }
            });
            this.postList = postList;
            this.postListFiltered = postList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_items, parent, false);
        return new PostAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        PostModel post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(postList.get(holder.getAdapterPosition()));
            }
        });

    }

    private PostAdapter.OnItemClickCallbackPost onItemClickCallback;
    public void setOnItemClickCallback(PostAdapter.OnItemClickCallbackPost onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public int getItemCount() {
        if (postList != null){
            return postListFiltered.size();
        }else {
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
                    postListFiltered = postList;
                } else {
                    List<PostModel> filteredList = new ArrayList<>();
                    for (PostModel post : postList) {
                        if (post.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(post);
                        }
                    }
                    postListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = postListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                postListFiltered = (ArrayList<PostModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView title, body;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.judul);
            body = itemView.findViewById(R.id.body);
        }
    }

    public interface OnItemClickCallbackPost {
        void onItemClicked(PostModel data);
    }
}
