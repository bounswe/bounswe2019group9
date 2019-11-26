package com.example.learningplatform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;

public class UsersInChatsAdapter extends RecyclerView.Adapter<UsersInChatsAdapter.MyViewHolder>{

    ArrayList<User> usersArrayList;
    Context context;
    SharedPreferences sharedPreferences;

    public UsersInChatsAdapter(final Context context, final int id) {
        this.context = context;
        this.usersArrayList = new ArrayList<>();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code
                RequestQueue queue = Volley.newRequestQueue(context);
                String url ="https://api.bounswe2019group9.tk/conversations?id=" + id;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    if(response.has("data")) {
                                        JSONArray data = (JSONArray) response.get("data");
                                        for(int i=0; i<data.length();i++) {
                                            if (data.getJSONObject(i).has("firstName") & data.getJSONObject(i).has("lastName")) {
                                                String nameOfUser = (String) data.getJSONObject(i).get("firstName") + " " +  (String) data.getJSONObject(i).get("lastName") ;
                                                int id2 = data.getJSONObject(i).getInt("userId");
                                                Log.i("nameofuser", nameOfUser);
                                                usersArrayList.add(new User(nameOfUser,id2));
                                            }
                                        }
                                        notifyDataSetChanged();

                                    }

                                } catch (JSONException e) {
                                    Log.i("catch","ctachhh");
                                    e.printStackTrace();
                                }

                            }
                        }
                        , new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("tag2", "here");

                    }
                });
                queue.add(jsonObjectRequest);
            }
        });

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chat_users, parent, false);
        UsersInChatsAdapter.MyViewHolder holder = new UsersInChatsAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UsersInChatsAdapter.MyViewHolder holder, final int position) {

        holder.nameOfUser.setText(usersArrayList.get(position).name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MessageListActivity.class);
                intent.putExtra("userId2", usersArrayList.get(position).id );
                intent.putExtra("name",usersArrayList.get(position).name);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nameOfUser;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.nameOfUser = itemView.findViewById(R.id.language);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }

        @Override
        public void onClick(View v) {
        }
    }
}

class User{
    String name;
    int id;
    public  User(String name, int id){
        this.name= name;
        this.id = id;
    }
}
