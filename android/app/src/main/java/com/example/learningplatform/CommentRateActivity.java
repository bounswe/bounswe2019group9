package com.example.learningplatform;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class CommentRateActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText editText;
    boolean target;
    int targetUser;
    int sourceUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        target = b.getBoolean("target");
        targetUser = b.getInt("targetUserId");
        sourceUser = b.getInt("sourceUserId");
        setContentView(R.layout.rating_comment_activity);
        LinearLayout rating = findViewById(R.id.rating_layout);
        LinearLayout commentButton = findViewById(R.id.comment_text_and_button);
        if(!target){
            rating.setVisibility(View.GONE);
            commentButton.setVisibility(View.GONE);
        }
        recyclerView = findViewById(R.id.recylerview_comment);
        editText = findViewById(R.id.comment_user);
        CommentAdapter commentAdapter = new CommentAdapter(this, targetUser);
        recyclerView.setAdapter(commentAdapter);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        intent = new Intent(CommentRateActivity.this, ExerciseListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_message:
                        intent = new Intent(CommentRateActivity.this, ChatsListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_profile:
                        intent = new Intent(CommentRateActivity.this, ProfilePageActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_search:
                        intent = new Intent(CommentRateActivity.this, SearchActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });
    }

    public void Rate(View v){
        int id = v.getId();
        if(id == R.id.rate_one){
            Toast.makeText(v.getContext(),"Rated 1", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.rate_two){
            Toast.makeText(v.getContext(),"Rated 2", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.rate_three){
            Toast.makeText(v.getContext(),"Rated 3", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.rate_four){
            Toast.makeText(v.getContext(),"Rated 4", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.rate_five){
            Toast.makeText(v.getContext(),"Rated 5", Toast.LENGTH_SHORT).show();
        }
    }

    public void Comment(View v){
        JSONObject comment_data = new JSONObject();

        try {
            comment_data.put("content",editText.getText().toString());
            comment_data.put("receiverId", targetUser);
            comment_data.put("sourceId", sourceUser);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(v.getContext());
        String url = "https://api.bounswe2019group9.tk/comments";

        JsonObjectRequest registerJsonReq = new JsonObjectRequest(Request.Method.POST, url, comment_data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int statusCode =  response.getInt("status");
                            if(statusCode == 200){
                                Toast.makeText(CommentRateActivity.this,"Comment successfully sent.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(CommentRateActivity.this,"An error has occurred.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("comment-rate-activity", "Error on post request.");
            }
        }
        );

        queue.add(registerJsonReq);
    }
}
