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

import java.util.Locale;

public class ProfilePageActivity extends AppCompatActivity {

    SharedPreferences  sharedPreferences;
    private static TextView nameDisplay;
    private static TextView surnameDisplay;
    private static TextView mailDisplay;
    int id;
    String rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        nameDisplay = findViewById(R.id.user_name_view);
        surnameDisplay = findViewById(R.id.user_surname_view);
        mailDisplay = findViewById(R.id.user_email_view);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        id = sharedPreferences.getInt("Id",0);
        final TableLayout table = findViewById(R.id.profile_lang_table);
        final LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
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
                                    JSONArray languagesOfUser = data.getJSONArray("languages");
                                    JSONArray progressLevelsOfUser = data.getJSONArray("progressLevels");
                                    double rating_double = data.getDouble("rating");
                                    if(gradeOfUser.length()!= languagesOfUser.length() || gradeOfUser.length()!= progressLevelsOfUser.length()){
                                        Log.e("Error", "The number of languages user has and the grades user has doesn't match");
                                    }
                                    for(int i = 0; i<gradeOfUser.length();i++){
                                        if(i == 0){
                                            editor.putInt("currentLanguage",1);
                                            editor.putInt("currentGrade", gradeOfUser.getInt(0));
                                        }
                                        LinearLayout row = (LinearLayout) layoutInflater.inflate(R.layout.profile_user_language_info,null);
                                        TextView rowLanguage = row.findViewById(R.id.language_name);
                                        TextView rowProgress = row.findViewById(R.id.progress_level);
                                        TextView rowGrade = row.findViewById(R.id.user_language_grade);
                                        TextView rowRating = row.findViewById(R.id.user_rating);
                                        rowLanguage.setText(languagesOfUser.getString(i));

                                        // adding language progress to sharedPreferences
                                        SharedPreferences.Editor editor = sharedPreferences.edit();

                                        editor.putString("languageOfUser",languagesOfUser.getString(i));
                                        editor.putString("progressOfUser",Integer.toString(progressLevelsOfUser.getInt(i))+"%");
                                        editor.putString("grade",getGradeFromInt(gradeOfUser.getInt(i)));
                                        editor.commit();


                                        rowProgress.setText(Integer.toString(progressLevelsOfUser.getInt(i))+"%");
                                        rowGrade.setText(getGradeFromInt(gradeOfUser.getInt(i)));
                                        rating = String.format(Locale.getDefault(),"%.1f", rating_double);
                                        rowRating.setText(rating);
                                        table.addView(row,i+1);
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
            }
        });

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
                        intentx = new Intent(ProfilePageActivity.this, ChatsListDisplay.class);
                        startActivity(intentx);
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

    public void GoComment(View v){
        Intent intent = new Intent(v.getContext(), CommentRateActivity.class);
        intent.putExtra("target", false);
        intent.putExtra("targetUserId", id);
        intent.putExtra("sourceUserId", -1);
        intent.putExtra("rating", rating);
        v.getContext().startActivity(intent);
    }

    public static String getGradeFromInt(int userGrade){
        if (userGrade == 1) {
           return "A1";
        } else if (userGrade == 2) {
            return "A2";
        } else if (userGrade == 3) {
            return "B1";
        } else if (userGrade == 4) {
            return "B2";
        } else if (userGrade == 5) {
            return "C1";
        } else if (userGrade == 6) {
            return "C2";
        } else{
            return "Please take exam.";
        }
    }
}
