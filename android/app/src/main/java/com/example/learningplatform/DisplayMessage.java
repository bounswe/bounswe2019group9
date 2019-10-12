package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class DisplayMessage extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_welcome);
        setContentView(R.layout.language_selection);
        recyclerView = findViewById(R.id.recylerview);

        LanguageAdapter languageAdapter = new LanguageAdapter(this);
        recyclerView.setAdapter(languageAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*final TextView textView;
        textView = findViewById(R.id.textView);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://api.bounswe2019group9.tk/greeting?name=" + "Group9" ;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            String responseString = (String) response.get("content");

                            textView.setText(responseString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());

            }
        });

        queue.add(jsonObjectRequest);*/

    }
}
