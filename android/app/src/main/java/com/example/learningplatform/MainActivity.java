package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static EditText username;
    private static EditText password;
    private static Button login_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginButton();
    }

    public void LoginButton(){

        username = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);


        login_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                            JSONObject data = new JSONObject();
                            try {
                                data.put("email",username.getText().toString());
                                data.put("password",password.getText().toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            RequestQueue queue = Volley.newRequestQueue(v.getContext());
                            String url = "https://api.bounswe2019group9.tk/users/login";
                            JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST, url,data,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                int statusCode =  response.getInt("status");
                                                if(statusCode == 200){
                                                    Toast.makeText(MainActivity.this,"Username and password is correct",
                                                            Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(v.getContext(),DisplayMessage.class);
                                                    v.getContext().startActivity(intent);
                                                }
                                                else{
                                                    Toast.makeText(MainActivity.this,"Username and password is NOT correct",
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

                        queue.add(json);
                    }
                }
        );


    }

}
