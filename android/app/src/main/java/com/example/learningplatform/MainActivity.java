package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

// App main light color is RGB: 140 198 68
public class MainActivity extends AppCompatActivity {
    private static EditText username;
    private static EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void LoginButton(final View v){

        username = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        JSONObject login_data = new JSONObject();
        try {
            login_data.put("email",username.getText().toString());
            login_data.put("password",password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(v.getContext());
        String url = "https://api.bounswe2019group9.tk/users/login";
        JsonObjectRequest loginJsonReq = new JsonObjectRequest(Request.Method.POST, url,login_data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int statusCode =  response.getInt("status");
                            if(statusCode == 200){
                                Toast.makeText(MainActivity.this,"Username and password is correct",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(v.getContext(), LanguageListDisplay.class);
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

        queue.add(loginJsonReq);
    }
    public void RegisterButton(View v){
        Intent registerIntent = new Intent(v.getContext(),RegisterActivity.class);
        v.getContext().startActivity(registerIntent);

    }

}
