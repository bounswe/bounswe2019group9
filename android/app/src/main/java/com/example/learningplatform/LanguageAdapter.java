package com.example.learningplatform;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

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


public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {

    ArrayList<Language> languageArrayList;
    Context context;
    ArrayList<String> questionList;
    ArrayList<String> choices;
    ArrayList<String> solutions;

    public LanguageAdapter(final Context context) {
        this.context = context;
        this.languageArrayList = new ArrayList<>();

        questionList = new ArrayList<>();
        choices = new ArrayList<>();
        solutions = new ArrayList<>();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code
                RequestQueue queue = Volley.newRequestQueue(context);
                String url ="https://api.bounswe2019group9.tk/contents/languages";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray data = (JSONArray) response.get("data");
                                    for(int i=0; i<data.length();i++){
                                        Language lang = new Language((String) data.get(i));
                                        languageArrayList.add(lang);
                                        Log.i("lang_list_api", ""+lang.getLanguageName());
                                    }
                                    notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        , new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("language_selection", "Error on request to get language list");

                    }
                });
                queue.add(jsonObjectRequest);
            }
        });

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

                                    if(response.has("data")) {
                                        JSONArray data = (JSONArray) response.get("data");
                                        if (data.getJSONObject(0).has("questionBody")) {

                                            String question = (String) data.getJSONObject(0).get("questionBody");
                                            Log.i("questionlist", question);
                                            questionList.add(question);

                                        }

                                    }

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


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final RequestQueue choiceQueue = Volley.newRequestQueue(context);
                String url ="https://api.bounswe2019group9.tk/contents/prof?language=English";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray data = (JSONArray) response.get("data");

                                    for (int i = 0; i < data.length(); i++) {

                                        String option1 = data.getJSONObject(i).getString("optionA");
                                        String option2 = data.getJSONObject(i).getString("optionB");
                                        String option3 = data.getJSONObject(i).getString("optionC");
                                        String option4 = data.getJSONObject(i).getString("optionD");

                                        choices.add(option1);
                                        choices.add(option2);
                                        choices.add(option3);
                                        choices.add(option4);

                                        int answer = data.getJSONObject(i).getInt("correctAnswer");

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

                                } catch (JSONException e) {
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

                choiceQueue.add(jsonObjectRequest);
            }
        });

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.languages, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.languageName.setText(languageArrayList.get(position).getLanguageName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("entered", "mylanguage");
                Intent intent = new Intent(holder.itemView.getContext(), QuestionDisplay.class);
                intent.putStringArrayListExtra("questions", questionList);
                intent.putStringArrayListExtra("choices", choices);
                intent.putStringArrayListExtra("solutions",solutions);
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return languageArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView languageName;
        public RelativeLayout relativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.languageName = itemView.findViewById(R.id.language);
            this.relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }

        @Override
        public void onClick(View v) {


        }
    }
}