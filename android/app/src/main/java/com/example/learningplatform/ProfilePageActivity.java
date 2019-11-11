package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

public class ProfilePageActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static TextView nameDisplay;
    private static TextView surnameDisplay;
    private static TextView mailDisplay;
    private static TextView gradeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        nameDisplay = findViewById(R.id.user_name_view);
        surnameDisplay = findViewById(R.id.user_surname_view);
        mailDisplay = findViewById(R.id.user_email_view);
        gradeDisplay = findViewById(R.id.user_grade);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("Id",0);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bounswe2019group9.tk/users/profile?id="+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            String firstName = data.getString("firstName");
                            String lastName = data.getString("lastName");
                            String email = data.getString("email");

                            nameDisplay.setText(firstName);
                            surnameDisplay.setText(lastName);
                            mailDisplay.setText(email);


                            JSONArray gradeOfUser = data.getJSONArray("grades");
                            int userGrade = gradeOfUser.getInt(0);
                            if (userGrade == 1) {
                                gradeDisplay.setText("A1");
                            } else if (userGrade == 2) {
                                gradeDisplay.setText("A2");
                            } else if (userGrade == 3) {
                                gradeDisplay.setText("B1");
                            } else if (userGrade == 4) {
                                gradeDisplay.setText("B2");
                            } else if (userGrade == 5) {
                                gradeDisplay.setText("C1");
                            } else if (userGrade == 6) {
                                gradeDisplay.setText("C2");
                            }


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

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intentx;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        intentx = new Intent(ProfilePageActivity.this, ExerciseListDisplay.class);
                        startActivity(intentx);
                        return true;
                    case R.id.nav_bar_message:
                        return true;
                    case R.id.nav_bar_profile:
                        return true;
                    case R.id.nav_bar_search:
                        intentx = new Intent(ProfilePageActivity.this, SearchActivity.class);
                        startActivity(intentx);
                        return true;
                }
                return true;
            }
        });

    }
    public void Logout(View v){
        SharedPreferences sharedpreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();

        Intent intent = new Intent(v.getContext(), MainActivity.class);
        v.getContext().startActivity(intent);

    }

    public void GoProf(View v){
        Intent intent = new Intent(v.getContext(), LanguageListDisplay.class);
        v.getContext().startActivity(intent);
    }


    public void SelectExercise(View v) {
        String s = v.getContext().toString();
        Intent intent = new Intent(v.getContext(), ExerciseListDisplay.class);
        v.getContext().startActivity(intent);
    }
    public void searchButton(View v){
        Intent intent = new Intent(v.getContext(), SearchActivity.class);
        v.getContext().startActivity(intent);
        Log.i("searchButton","buttonClicked");
    }
}
