package com.example.learningplatform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class MessageListActivity extends AppCompatActivity {
    private static final String TAG = "MessageListActivity";
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    SharedPreferences sharedPreferences;
    int id;
    int id2;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        Bundle b = getIntent().getExtras();
        id2 = b.getInt("userId2");
        name = b.getString("name");
        final List<Message> messageList = new LinkedList<>();
        mMessageAdapter = new MessageListAdapter(MessageListActivity.this, messageList, name);
        mMessageRecycler.setAdapter(mMessageAdapter);
        Log.d(TAG, "onCreate: wow");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                id = sharedPreferences.getInt("Id",0);

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://api.bounswe2019group9.tk/messages/chat?userId1="+ id + "&userId2=" + id2 ;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray data = (JSONArray)response.get("data");
                                    for(int i = 0; i<data.length(); i++){
                                        String content = data.getJSONObject(i).getString("content");
                                        String createdAt = data.getJSONObject(i).getString("createdAt");
                                        int senderId = data.getJSONObject(i).getInt("sourceId");
                                        int receiverId = data.getJSONObject(i).getInt("receiverId");
                                        Message message = new Message(senderId, receiverId, content, createdAt);
                                        messageList.add(message);
                                    }
                                    //mMessageAdapter.mMessageList.addAll(messageList);
                                    mMessageAdapter.notifyDataSetChanged();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        , new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("profile_view", "Error on request to get profile details");

                    }
                });
                queue.add(jsonObjectRequest);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        intent = new Intent(MessageListActivity.this, ExerciseListDisplay.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_bar_profile:
                        intent = new Intent(MessageListActivity.this, ProfilePageActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_bar_message:
                        intent = new Intent(MessageListActivity.this, ChatsListDisplay.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_bar_search:
                        intent = new Intent(MessageListActivity.this, SearchActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });

    }
    public void sendMessage(final View v){
        EditText editText = findViewById(R.id.edittext_chatbox);
        String content = editText.getText().toString();

        JSONObject message_data = new JSONObject();
        try {
            message_data.put("content", content);
            message_data.put("receiverId", id2);
            message_data.put("sourceId",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(v.getContext());
        String url = "https://api.bounswe2019group9.tk/messages";
        JsonObjectRequest loginJsonReq = new JsonObjectRequest(Request.Method.POST, url,message_data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int statusCode =  response.getInt("status");
                            if(statusCode == 200){
                                Toast.makeText(MessageListActivity.this,"Message is sent",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(v.getContext(), MessageListActivity.class);
                                intent.putExtra("userId2", id2 );
                                intent.putExtra("name", name);
                                v.getContext().startActivity(intent);

                            }
                            else{
                                Toast.makeText(MessageListActivity.this,"Ups... Something is wrong.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("That didn't work!");
                    }
                });

        queue.add(loginJsonReq);


    }
}