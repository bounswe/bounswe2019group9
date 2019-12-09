package com.example.learningplatform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>{
    ArrayList<Comment> commentArrayList;
    Context context;

    public CommentAdapter(final Context context) {
        this.context = context;
        this.commentArrayList = new ArrayList<>();
        commentArrayList.add(new Comment("comment sender1", "was a good comment", 3.876));
        commentArrayList.add(new Comment("comment sender2", "was a good comment", 2.876));
        commentArrayList.add(new Comment("comment sender3", "was a good comment", 1.876));
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.comment_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.senderName.setText(commentArrayList.get(position).senderName);
        holder.rating.setText(commentArrayList.get(position).rate);
        holder.comment.setText(commentArrayList.get(position).comment);
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView senderName;
        public TextView rating;
        public TextView comment;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.senderName = itemView.findViewById(R.id.senderName);
            this.rating = itemView.findViewById(R.id.commentRating);
            this.comment = itemView.findViewById(R.id.commentItemComment);
        }

        @Override
        public void onClick(View v) {
        }
    }
}

class Comment{
    int senderId;
    int receiverId;
    String senderName;
    String date;
    String comment;
    String rate;
    public Comment(String sender, String comment, double rate){
        this.senderName = sender;
        this.comment = comment;
        this.rate = String.format("%.1f", rate);
    }
}
