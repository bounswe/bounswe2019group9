package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
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

import java.util.ArrayList;

public class StartExerciseActivity extends AppCompatActivity {

    ArrayList<String> exerciseList = new ArrayList<String>();
    ArrayList<String> choices= new ArrayList<String>();
    ArrayList<String> answers= new ArrayList<String>();
    ArrayList<String> solutions= new ArrayList<String>();
    ArrayList<Integer> exerciseIdList = new ArrayList<>();

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exercise);



        exerciseList = new ArrayList<>();
        choices = new ArrayList<>();
        answers = new ArrayList<>();
        solutions = new ArrayList<>();

        Intent intent = getIntent();
        final int typeOfExercise = intent.getIntExtra("typeOfExercise",0);
        final int typeOfLang = 1;


        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        final String lang = sharedPreferences.getString("languageOfUser","");
        final String progressForLang = sharedPreferences.getString("progressOfUser","");
        final String grade = sharedPreferences.getString("grade","");
        final int userId = sharedPreferences.getInt("Id", 0);


        final TableLayout table = findViewById(R.id.profile_lang_table);
        final LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout row = (LinearLayout) layoutInflater.inflate(R.layout.start_exercise_activity_info,null);
        TextView rowLanguage = row.findViewById(R.id.language_name);
        TextView rowProgress = row.findViewById(R.id.progress_level);
        TextView rowGrade = row.findViewById(R.id.user_language_grade);

        rowLanguage.setText(lang);
        rowProgress.setText(progressForLang);
        rowGrade.setText(grade);
        table.addView(row,1);



        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                String url = "https://api.bounswe2019group9.tk/search/exercises";
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


                JSONObject search_data = new JSONObject();
                try {
                    search_data.put("languageId",typeOfLang);
                    search_data.put("typeId",typeOfExercise);
                    search_data.put("userId", userId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,search_data,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    int statusCode =  response.getInt("status");
                                    if(statusCode == 200){

                                        JSONArray exerciseData =  response.getJSONArray("data");

                                        for(int i=0;i<exerciseData.length();i++){
                                            JSONObject exercise = exerciseData.getJSONObject(i);

                                            String questionBody = exercise.getString("questionBody");
                                            String option1 = exercise.getString("optionA");
                                            String option2 = exercise.getString("optionB");
                                            String option3 = exercise.getString("optionC");
                                            String option4 = exercise.getString("optionD");

                                            int exerciseId = exercise.getInt("id");

                                            exerciseIdList.add(exerciseId);

                                            int answer = exercise.getInt("correctAnswer");


                                            exerciseList.add(questionBody);

                                            choices.add(option1);
                                            choices.add(option2);
                                            choices.add(option3);
                                            choices.add(option4);

                                            if(answer == 1){
                                                solutions.add(option1);
                                            }else if(answer == 2){
                                                solutions.add(option2);
                                            }else if(answer == 3){
                                                solutions.add(option3);
                                            }else if(answer ==4){
                                                solutions.add(option4);
                                            }


                                        }

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"An error occurred while getting exercise data",
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
                queue.add(jsonObjectRequest);
            }
        });




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


    public void StartSelected(View v){



        Intent intent;
        intent = new Intent(getApplicationContext(), ExerciseViewActivity.class);
        intent.putStringArrayListExtra("exerciseList",exerciseList);
        intent.putStringArrayListExtra("choices",choices);
        intent.putStringArrayListExtra("solutions",solutions);
        intent.putIntegerArrayListExtra("exerciseIdList",exerciseIdList);
        startActivity(intent);

    }
}