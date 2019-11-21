package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ChatsListDisplay extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_selection);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        intent = new Intent(ChatsListDisplay.this, ExerciseListDisplay.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_bar_profile:
                        intent = new Intent(ChatsListDisplay.this, ProfilePageActivity.class);
                        startActivity(intent);
                        return true;

                    case R.id.nav_bar_message:
                        return true;

                    case R.id.nav_bar_search:
                        intent = new Intent(ChatsListDisplay.this, SearchActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });
    }


}
