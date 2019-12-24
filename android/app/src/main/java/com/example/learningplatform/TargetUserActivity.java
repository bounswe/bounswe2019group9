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
import android.widget.Button;
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
import com.example.learningplatform.ProfilePageActivity;

public class TargetUserActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;

    private static TextView nameDisplay;
    private static TextView surnameDisplay;
    private static TextView mailDisplay;
    private static Button requestButton;
    private int TargetUserId;
    private int Userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_user);

        nameDisplay = findViewById(R.id.target_user_name_view);
        surnameDisplay = findViewById(R.id.target_user_surname_view);
        mailDisplay = findViewById(R.id.target_user_email_view);
        requestButton = findViewById(R.id.send_request_button);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Userid = sharedPreferences.getInt("Id",0);

        Intent intent = getIntent();
        TargetUserId = intent.getIntExtra("targetUserId",0);

        final TableLayout table = findViewById(R.id.profile_lang_table);
        final LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://api.bounswe2019group9.tk/users/profile?id="+TargetUserId;
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
                                    if(gradeOfUser.length()!= languagesOfUser.length() || gradeOfUser.length()!= progressLevelsOfUser.length()){
                                        Log.e("Error", "The number of languages user has and the grades user has doesn't match");
                                    }
                                    for(int i = 0; i<gradeOfUser.length();i++){
                                        LinearLayout row = (LinearLayout) layoutInflater.inflate(R.layout.profile_user_language_info,null);
                                        TextView rowLanguage = row.findViewById(R.id.language_name);
                                        TextView rowProgress = row.findViewById(R.id.progress_level);
                                        TextView rowGrade = row.findViewById(R.id.user_language_grade);
                                        TextView rowRating = row.findViewById(R.id.user_rating);
                                        rowLanguage.setText(languagesOfUser.getString(i));
                                        rowProgress.setText(Integer.toString(progressLevelsOfUser.getInt(i))+"%");
                                        rowGrade.setText(ProfilePageActivity.getGradeFromInt(gradeOfUser.getInt(i)));
                                        rowRating.setText("3.5");
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

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://api.bounswe2019group9.tk/invitations/state?userId1="+Userid+"&userId2="+TargetUserId;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject data = response.getJSONObject("data");
                                    boolean pendingReqFrom1To2 = data.getBoolean("pendingRequestFromOneToTwo");
                                    boolean pendingReqFrom2To1 = data.getBoolean("pendingRequestFromTwoToOne");
                                    boolean startedConversation = data.getBoolean("startedConversation");
                                    if(pendingReqFrom1To2){
                                        requestButton.setText("Request Pending");
                                    } else if(pendingReqFrom2To1){
                                        requestButton.setText("Accept message request");
                                    } else if(startedConversation){
                                        requestButton.setText("Go to conversatıon");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        , new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("profile_view", "Error on request to get message request details");

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
                        intentx = new Intent(TargetUserActivity.this, ExerciseListDisplay.class);
                        startActivity(intentx);
                        return true;
                    case R.id.nav_bar_message:
                        intentx = new Intent(TargetUserActivity.this, ChatsListDisplay.class);
                        startActivity(intentx);
                        return true;
                    case R.id.nav_bar_profile:
                        intentx = new Intent(TargetUserActivity.this, ProfilePageActivity.class);
                        startActivity(intentx);
                        return true;
                    case R.id.nav_bar_search:
                        intentx = new Intent(TargetUserActivity.this, SearchActivity.class);
                        startActivity(intentx);
                        return true;
                }
                return true;
            }
        });


    }

    public void SendRequest(final View v){
        TextView t = (TextView) v;
        String buttonText = (String) t.getText();
        if(buttonText.equals("Send Message Request")){
            JSONObject request_data = new JSONObject();
            try {
                request_data.put("receiverId",TargetUserId);
                request_data.put("sourceId",Userid);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            RequestQueue queue = Volley.newRequestQueue(v.getContext());
            String url = "https://api.bounswe2019group9.tk/invitations/add";
            JsonObjectRequest loginJsonReq = new JsonObjectRequest(Request.Method.POST, url,request_data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int statusCode =  response.getInt("status");
                                if(statusCode == 200){
                                    Toast.makeText(TargetUserActivity.this,"Message request is sent",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(TargetUserActivity.this,"Error sending message request",
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
            queue.add(loginJsonReq);
        } else if(buttonText.equals("Request Pending")){
            Toast.makeText(TargetUserActivity.this,"Request already sent",
                    Toast.LENGTH_SHORT).show();
        } else if(buttonText.equals("Accept message request")){
            JSONObject request_data = new JSONObject();
            try {
                request_data.put("receiverId",TargetUserId);
                request_data.put("sourceId",Userid);
                request_data.put("approved", true);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            RequestQueue queue = Volley.newRequestQueue(v.getContext());
            String url = "https://api.bounswe2019group9.tk/invitations/add";
            JsonObjectRequest loginJsonReq = new JsonObjectRequest(Request.Method.POST, url,request_data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int statusCode =  response.getInt("status");
                                if(statusCode == 200){
                                    Toast.makeText(TargetUserActivity.this,"Message request is approved",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(v.getContext(), MessageListActivity.class);
                                    intent.putExtra("userId2", TargetUserId);
                                    String name = nameDisplay.getText() + " "+surnameDisplay.getText();
                                    intent.putExtra("name", name);
                                    v.getContext().startActivity(intent);
                                }
                                else{
                                    Toast.makeText(TargetUserActivity.this,"Error accepting message request",
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
            queue.add(loginJsonReq);
        }else if(buttonText.equals("Go to conversatıon")){
            Intent intent = new Intent(v.getContext(), MessageListActivity.class);
            intent.putExtra("userId2", TargetUserId);
            String name = nameDisplay.getText() + " "+surnameDisplay.getText();
            intent.putExtra("name", name);
            v.getContext().startActivity(intent);
        }

    }
}


