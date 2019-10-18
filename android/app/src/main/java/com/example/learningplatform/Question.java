package com.example.learningplatform;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Question {


    ArrayList<String> questionList;
    ArrayList<ArrayList<String>> choices = new ArrayList<>();

    public Question(final Context context){

        Log.i("cons","asfasfasf");

        questionList = new ArrayList<String>();

    AsyncTask.execute(new Runnable() {
        @Override
        public void run() {
            RequestQueue queue = Volley.newRequestQueue(context);
            String url ="https://api.bounswe2019group9.tk/contents/prof?language=English";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {


                                JSONArray data = (JSONArray) response.get("data");

                                //for (int i = 0; i < data.length(); i++) {
                                String question = (String) data.getJSONObject(0).get("questionBody");
                                Log.i("questionlist",question);
                                questionList.add(question);
                                //}

                            } catch (JSONException e) {
                                Log.i("catch","ctachhh");
                                e.printStackTrace();
                            }

                        }
                    }
                    , new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("question_list", "Error on request to get question list");

                }
            });

            queue.add(jsonObjectRequest);
        }
    });

    Log.i("empty",""+questionList.isEmpty());


    }

    public String getQuestion(int a){
        String question = questionList.get(0);
        return question;
    }

    public String getchoice1(int a){
        String choice = choices.get(a).get(0);
        return choice;
    }

    public String getchoice2(int a){
        String choice = choices.get(a).get(1);
        return choice;
    }

    public String getchoice3(int a){
        String choice = choices.get(a).get(2);
        return choice;
    }

    public String getchoice4(int a){
        String choice = choices.get(a).get(3);
        return choice;
    }

    public int getSize(){
        return questionList.size();
    }


}
