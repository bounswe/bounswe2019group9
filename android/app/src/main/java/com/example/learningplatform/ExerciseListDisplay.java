package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseListDisplay extends AppCompatActivity {




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.exercise_type_selection);


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
