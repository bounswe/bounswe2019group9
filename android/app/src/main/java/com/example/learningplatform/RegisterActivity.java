package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {

    private static EditText name;
    private static EditText surname;
    private static EditText email;
    private static EditText password;
    private static Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RegisterButton();
    }

    public void RegisterButton() {

        name = findViewById(R.id.register_name);
        surname = findViewById(R.id.register_surname);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        JSONObject register_data = new JSONObject();

                        try {
                            register_data.put("email",email.getText().toString());
                            register_data.put("password",password.getText().toString());
                            register_data.put("firstName",name.getText().toString());
                            register_data.put("lastName",surname.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        RequestQueue queue = Volley.newRequestQueue(v.getContext());
                        String url = "https://api.bounswe2019group9.tk/users/register";

                        JsonObjectRequest registerJsonReq = new JsonObjectRequest(Request.Method.POST, url, register_data,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            int statusCode =  response.getInt("status");
                                            if(statusCode == 200){
                                                Toast.makeText(RegisterActivity.this,"User successfully created.",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(v.getContext(),MainActivity.class);
                                                v.getContext().startActivity(intent);
                                            }
                                            else if(statusCode == 400){
                                                Toast.makeText(RegisterActivity.this,"You entered an invalid email.",
                                                        Toast.LENGTH_SHORT).show();
                                                                                            }
                                            else{
                                                Toast.makeText(RegisterActivity.this,"An error has occurred due to network.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("That didn't work!");
                            }
                        }
                        );

                        queue.add(registerJsonReq);
                    }
                }
        );
    }
    }
