package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

// App main light color is RGB: 140 198 68
public class MainActivity extends AppCompatActivity {
    private static EditText username;
    private static EditText password;

    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(MainActivity.this,ProfilePageActivity.class);
        sharedpreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        if(sharedpreferences.contains("Name") && sharedpreferences.contains("Pass")){
            startActivity(intent);
        }
    }

    public void LoginButton(final View v){

        username = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        sharedpreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

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


                                JSONObject userData =  response.getJSONObject("data");
                                int userId = userData.getInt("id");

                                SharedPreferences.Editor editor = sharedpreferences.edit();

                                editor.putString("Name", username.getText().toString());
                                editor.putString("Pass", password.getText().toString());
                                editor.putInt("Id",userId);
                                editor.commit();

                                Intent intent = new Intent(v.getContext(), ProfilePageActivity.class);

                                intent.putExtra("userId",userId);
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
