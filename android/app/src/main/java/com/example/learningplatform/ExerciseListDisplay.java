package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

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
                            intent = new Intent(ExerciseListDisplay.this, ChatsListDisplay.class);
                            startActivity(intent);
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
            Intent intent;
            switch (v.getId()) {
                case R.id.listening:
                    Log.i("clickedTo","listening");
                    intent = new Intent(ExerciseListDisplay.this, StartExerciseActivity.class);
                    intent.putExtra("typeOfExercise",1);
                    startActivity(intent);
                    break;
                case R.id.reading:
                    Log.i("clickedTo","reading");
                    intent = new Intent(ExerciseListDisplay.this, StartExerciseActivity.class);
                    intent.putExtra("typeOfExercise",2);
                    startActivity(intent);
                    break;
                case R.id.writing:
                    Log.i("clickedTo","writing");
                    intent = new Intent(ExerciseListDisplay.this,WritingExActivity.class);
                    startActivity(intent);
                    break;
                case R.id.grammar:
                    Log.i("clickedTo","grammar");
                    intent = new Intent(ExerciseListDisplay.this, StartExerciseActivity.class);
                    intent.putExtra("typeOfExercise",4);
                    startActivity(intent);
                    break;
                case R.id.vocabulary:
                    Log.i("clickedTo","vocabulary");
                    intent = new Intent(ExerciseListDisplay.this, StartExerciseActivity.class);
                    intent.putExtra("typeOfExercise",3);

                    startActivity(intent);
                    break;
            }

        }

        public void AddExerciseType(View v){

            Intent intent;
            switch (v.getId()) {
                case R.id.add_listening:
                    Log.i("clickedTo","listening");
                    intent = new Intent(ExerciseListDisplay.this, AddExerciseActivity.class);
                    intent.putExtra("typeOfExercise",1);
                    startActivity(intent);
                    break;
                case R.id.add_reading:
                    Log.i("clickedTo","reading");
                    intent = new Intent(ExerciseListDisplay.this, AddExerciseActivity.class);
                    intent.putExtra("typeOfExercise",2);
                    startActivity(intent);
                    break;
                case R.id.add_writing:
                    Log.i("clickedTo","writing");
                    break;
                case R.id.add_grammar:
                    Log.i("clickedTo","grammar");
                    intent = new Intent(ExerciseListDisplay.this, AddExerciseActivity.class);
                    intent.putExtra("typeOfExercise",4);
                    startActivity(intent);
                    break;
                case R.id.add_vocabulary:
                    Log.i("clickedTo","vocabulary");
                    intent = new Intent(ExerciseListDisplay.this, AddExerciseActivity.class);
                    intent.putExtra("typeOfExercise",3);

                    startActivity(intent);
                    break;
            }


        }

}
