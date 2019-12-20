package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;


public class RecommendationPageDisplay extends AppCompatActivity {
    RecyclerView recyclerView;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_page);
        recyclerView = findViewById(R.id.recylerview3);
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        final int grade = sharedPreferences.getInt("currentGrade",0);
        final int typeOfLang = 1;
        JSONObject req_data = new JSONObject();
        try {
            req_data.put("languageId",typeOfLang);
            req_data.put("grade",grade);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecommendedUsersAdapter recommendedUsers = new RecommendedUsersAdapter(this, req_data);
        recyclerView.setAdapter(recommendedUsers);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        return true;
                    case R.id.nav_bar_message:
                        intent = new Intent(getApplicationContext(), ChatsListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_profile:
                        intent = new Intent(getApplicationContext(), ProfilePageActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_search:
                        intent = new Intent(getApplicationContext(), SearchActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });
    }
    public void ReviewRequest(View v) {
        Intent intent;
        intent = new Intent(getApplicationContext(), RecommendationPageDisplay.class);

        startActivity(intent);

    }
}
