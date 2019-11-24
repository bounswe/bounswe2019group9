package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

public class ExerciseViewActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    Button answer1,answer2,answer3,answer4, nextQuestion;
    TextView questionText;

    ArrayList<String> exerciseList = new ArrayList<String>();
    ArrayList<String> choices= new ArrayList<String>();
    ArrayList<String> answers= new ArrayList<String>();
    ArrayList<String> solutions= new ArrayList<String>();
    int questionCount;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_view);



        questionCount = 0;

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        questionText = findViewById(R.id.questionText);

        nextQuestion = findViewById(R.id.finish);


        //nextQuestion.getBackground().setColorFilter(Color.BLUE,PorterDuff.Mode.MULTIPLY);


        Intent intent = getIntent();
        exerciseList = intent.getStringArrayListExtra("exerciseList");
        choices = intent.getStringArrayListExtra("choices");
        solutions = intent.getStringArrayListExtra("solutions");

/*
        exerciseList.add("Aunt is my mother's ?");

        choices.add("uncle");
        choices.add("father");
        choices.add("sister");
        choices.add("friend");

        solutions.add(3);

        exerciseList.add("Dog is a/an");

        choices.add("plant");
        choices.add("animal");
        choices.add("bacteria");
        choices.add("archea");

        solutions.add(2);

*/

       // Bundle b = getIntent().getExtras();
       // exerciseList = b.getStringArrayList("questions");
       // choices = b.getStringArrayList("choices");
       // solutions = b.getStringArrayList("solutions");

        questionText.setText(exerciseList.get(questionCount));
        answer1.setText(choices.get(questionCount));
        answer2.setText(choices.get(questionCount+1));
        answer3.setText(choices.get(questionCount+2));
        answer4.setText(choices.get(questionCount+3));
        questionCount++;


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        intent = new Intent(ExerciseViewActivity.this, ExerciseListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_message:
                        return true;
                    case R.id.nav_bar_profile:
                        intent = new Intent(ExerciseViewActivity.this, ProfilePageActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_search:
                        intent = new Intent(ExerciseViewActivity.this, SearchActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });

    }

    int choiceClicked=0;

    public void ChoiceSelected(View v){


        if(choiceClicked !=0)
            return;

        choiceClicked++;
        TextView t = (TextView) v;
        String answer = t.getText().toString();
        Log.i("logofanswer",""+answer);

        answers.add(answer);


        int indexForArrayList = questionCount -1 ;
        if(answer.compareTo(solutions.get(indexForArrayList))==0){ //if the answer is true, set others as red
            if(choices.get(4*indexForArrayList).compareTo(answer)==0){
                answer1.getBackground().setColorFilter(Color.parseColor("#00574B"),PorterDuff.Mode.MULTIPLY);
                answer1.setTextColor(Color.WHITE);
                answer2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                answer3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                answer4.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }else if(choices.get(4*indexForArrayList + 1).compareTo(answer)==0){
                answer1.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
                answer2.getBackground().setColorFilter(Color.parseColor("#00574B"),PorterDuff.Mode.MULTIPLY);
                answer2.setTextColor(Color.WHITE);
                answer3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                answer4.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            } else if(choices.get(4*indexForArrayList + 2).compareTo(answer)==0){
                answer1.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
                answer2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                answer3.getBackground().setColorFilter(Color.parseColor("#00574B"),PorterDuff.Mode.MULTIPLY);
                answer3.setTextColor(Color.WHITE);
                answer4.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
            }else{
                answer1.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
                answer2.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                answer3.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                answer4.getBackground().setColorFilter(Color.parseColor("#00574B"),PorterDuff.Mode.MULTIPLY);
                answer4.setTextColor(Color.WHITE);
            }
        }else{ // if the answer is false


            if(choices.get(4*indexForArrayList).compareTo(answer)==0){ // set selected button as yellow
                answer1.getBackground().setColorFilter(Color.YELLOW,PorterDuff.Mode.MULTIPLY);
            } else if(choices.get(4*indexForArrayList+1).compareTo(answer)==0){
                answer2.getBackground().setColorFilter(Color.YELLOW,PorterDuff.Mode.MULTIPLY);
            } else if(choices.get(4*indexForArrayList+2).compareTo(answer)==0){
                answer3.getBackground().setColorFilter(Color.YELLOW,PorterDuff.Mode.MULTIPLY);
            } else {
                answer4.getBackground().setColorFilter(Color.YELLOW,PorterDuff.Mode.MULTIPLY);
            }

            //and others as red
            if(choices.get(4*indexForArrayList).compareTo(solutions.get(indexForArrayList))!=0 && choices.get(4*indexForArrayList).compareTo(answer)!=0){ // if the nor selected nor the true answer, set them as red
                answer1.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
            }
            if(choices.get(4*indexForArrayList+1).compareTo(solutions.get(indexForArrayList))!=0 && choices.get(4*indexForArrayList+1).compareTo(answer)!=0){
                answer2.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
            }
            if(choices.get(4*indexForArrayList+2).compareTo(solutions.get(indexForArrayList))!=0 && choices.get(4*indexForArrayList+2).compareTo(answer)!=0){
                answer3.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
            }
            if(choices.get(4*indexForArrayList+3).compareTo(solutions.get(indexForArrayList))!=0 && choices.get(4*indexForArrayList+3).compareTo(answer)!=0){
                answer4.getBackground().setColorFilter(Color.RED,PorterDuff.Mode.MULTIPLY);
            }

            //true answer as green
            if(choices.get(4*indexForArrayList).compareTo(solutions.get(indexForArrayList))==0 && choices.get(4*indexForArrayList).compareTo(answer)!=0){
                answer1.getBackground().setColorFilter(Color.parseColor("#00574B"),PorterDuff.Mode.MULTIPLY);
                answer1.setTextColor(Color.WHITE);
            } else if(choices.get(4*indexForArrayList+1).compareTo(solutions.get(indexForArrayList))==0 && choices.get(4*indexForArrayList+1).compareTo(answer)!=0){
                answer2.getBackground().setColorFilter(Color.parseColor("#00574B"),PorterDuff.Mode.MULTIPLY);
                answer2.setTextColor(Color.WHITE);
            }else if(choices.get(4*indexForArrayList+2).compareTo(solutions.get(indexForArrayList))==0 && choices.get(4*indexForArrayList+2).compareTo(answer)!=0){
                answer3.getBackground().setColorFilter(Color.parseColor("#00574B"),PorterDuff.Mode.MULTIPLY);
                answer3.setTextColor(Color.WHITE);
            } else if(choices.get(4*indexForArrayList+3).compareTo(solutions.get(indexForArrayList))==0 && choices.get(4*indexForArrayList+3).compareTo(answer)!=0){
                answer4.getBackground().setColorFilter(Color.parseColor("#00574B"),PorterDuff.Mode.MULTIPLY);
                answer4.setTextColor(Color.WHITE);
            }





        }



    }

    public void NextQuestion(View v){


        if(questionCount==exerciseList.size()){
            Toast.makeText(v.getContext(),"Your exercise is finished !",
                    Toast.LENGTH_SHORT).show();
           return;
        }

        choiceClicked =0;

        answer1.getBackground().clearColorFilter();
        answer1.setTextColor(Color.parseColor("#FF0C360B"));
        answer2.getBackground().clearColorFilter();
        answer2.setTextColor(Color.parseColor("#FF0C360B"));
        answer3.getBackground().clearColorFilter();
        answer3.setTextColor(Color.parseColor("#FF0C360B"));
        answer4.getBackground().clearColorFilter();
        answer4.setTextColor(Color.parseColor("#FF0C360B"));


        questionText.setText(exerciseList.get(questionCount));
        answer1.setText(choices.get(4 * questionCount));
        answer2.setText(choices.get(4 * questionCount + 1));
        answer3.setText(choices.get(4 * questionCount + 2));
        answer4.setText(choices.get(4 * questionCount + 3));
        questionCount++;
    }



    public void FinishSelected(View v){

        Toast.makeText(v.getContext(),"Your answers have been submitted",
                Toast.LENGTH_SHORT).show();
    }
}
