package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DisplayMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);



        final TextView textView;
        textView = findViewById(R.id.textView);

        RequestQueue queue = Volley.newRequestQueue(this);

        Intent intent = getIntent();
        String person = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String url ="https://api.bounswe2019group9.tk/greeting?name="+ person ;

        //String url2 ="http://api.openweathermap.org/data/2.5/weather?id=745044&APPID=ea1a1745f60c4b98c789f39c611dcf19&lang=tr";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //textView.setText("Response: " + response.toString());
                        try {

                            String responseString = (String) response.get("content");

                            //JSONObject main = (JSONObject) response.get("main");      this code block for getting
                            //Double temp = (Double) main.get("temp");                  the json data inside a jsonObject
                            //String responseString = String.valueOf(temp);

                            textView.setText(responseString);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
/*      StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        textView.setText("Response is: "+ response);
                    }
                }*/, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText(error.toString());
                System.out.println("That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
}
