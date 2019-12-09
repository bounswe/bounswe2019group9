package com.example.learningplatform;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CommentRateActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        boolean target = b.getBoolean("target");
        setContentView(R.layout.rating_comment_activity);
        LinearLayout rating = findViewById(R.id.rating_layout);
        if(!target){
            rating.setVisibility(View.GONE);
        }
        recyclerView = findViewById(R.id.recylerview_comment);
        CommentAdapter commentAdapter = new CommentAdapter(this);
        recyclerView.setAdapter(commentAdapter);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        intent = new Intent(CommentRateActivity.this, ExerciseListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_message:
                        intent = new Intent(CommentRateActivity.this, ChatsListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_profile:
                        intent = new Intent(CommentRateActivity.this, ProfilePageActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_search:
                        intent = new Intent(CommentRateActivity.this, SearchActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });
    }

    public void Rate(View v){
        int id = v.getId();
        if(id == R.id.rate_one){
            Toast.makeText(v.getContext(),"Rated 1", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.rate_two){
            Toast.makeText(v.getContext(),"Rated 2", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.rate_three){
            Toast.makeText(v.getContext(),"Rated 3", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.rate_four){
            Toast.makeText(v.getContext(),"Rated 4", Toast.LENGTH_SHORT).show();
        } else if(id == R.id.rate_five){
            Toast.makeText(v.getContext(),"Rated 5", Toast.LENGTH_SHORT).show();
        }
    }
}
