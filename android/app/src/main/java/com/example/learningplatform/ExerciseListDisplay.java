package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExerciseListDisplay extends AppCompatActivity {




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.exercise_type_selection);

            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.nav_bar_excercise:
                            return true;
                        case R.id.nav_bar_message:
                            return true;
                        case R.id.nav_bar_profile:
                            intent = new Intent(ExerciseListDisplay.this, ProfilePageActivity.class);
                            startActivity(intent);
                            return true;
                        case R.id.nav_bar_search:
                            intent = new Intent(ExerciseListDisplay.this, SearchActivity.class);
                            startActivity(intent);
                            return true;
                    }
                    return true;
                }
            });


        }

        public void SelectExerciseType(View v){
            //Toast.makeText(ExerciseListDisplay.this,"clicked to listening",Toast.LENGTH_LONG);
            switch (v.getId()) {
                case R.id.listening:
                    Log.i("clickedTo","listening");
                    break;
                case R.id.reading:
                    Log.i("clickedTo","reading");
                    break;
                case R.id.writing:
                    Log.i("clickedTo","writing");
                    break;
                case R.id.grammar:
                    Log.i("clickedTo","grammar");
                    break;
                case R.id.vocabulary:
                    Log.i("clickedTo","vocabulary");
                    break;
            }

        }

}
