package com.example.learningplatform;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>{
    ArrayList<Comment> commentArrayList;
    Context context;

    public CommentAdapter(final Context context, final int targetUserID) {
        this.context = context;
        this.commentArrayList = new ArrayList<>();
       AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code
                RequestQueue queue = Volley.newRequestQueue(context);
                String url ="https://api.bounswe2019group9.tk/comments/getCommentsByReceiverId?userId=" + targetUserID;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray data = (JSONArray) response.get("data");
                                    for(int i=0; i<data.length();i++){
                                        String senderName = data.getJSONObject(i).getString("sourceFirstName")+ " " + data.getJSONObject(i).getString("sourceLastName");
                                        JSONObject commentObject = data.getJSONObject(i).getJSONObject("comment");
                                        String commentContent = commentObject.getString("content");
                                        String date = commentObject.getString("createdAt").substring(0,10);
                                        Comment comment = new Comment(senderName, commentContent,date);
                                        commentArrayList.add(comment);
                                    }
                                    notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("comment_adapter", "Error on request to get comment list");

                    }
                });
                queue.add(jsonObjectRequest);
            }
        });
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
        holder.comment.setText(commentArrayList.get(position).comment);
        holder.commentDate.setText(commentArrayList.get(position).date);
    }

    @Override
    public int getItemCount() {
        return commentArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView senderName;
        public TextView comment;
        public TextView commentDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.senderName = itemView.findViewById(R.id.senderName);
            this.comment = itemView.findViewById(R.id.commentItemComment);
            this.commentDate = itemView.findViewById(R.id.comment_date);
        }

        @Override
        public void onClick(View v) {
        }
    }
}

class Comment{
    String senderName;
    String date;
    String comment;
    public Comment(String sender, String comment, String date){
        this.senderName = sender;
        this.comment = comment;
        this.date = date;
    }
}
