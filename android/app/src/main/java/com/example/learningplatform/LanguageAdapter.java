package com.example.learningplatform;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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



public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {

    ArrayList<Language> languageArrayList = new ArrayList<Language>();
    LayoutInflater inflater;

    public LanguageAdapter(Context context) {
        inflater = LayoutInflater.from(context);
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
                                Log.i("api", ""+lang.getLanguageName());
                            }

                            //textView.setText(responseString);
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


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.languages, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Language selectedLanguage= languageArrayList.get(position);
        holder.setData(selectedLanguage, position);

    }

    @Override
    public int getItemCount() {
        return languageArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView languageName;

        public MyViewHolder(View itemView) {
            super(itemView);
            languageName = (TextView) itemView.findViewById(R.id.language);
        }

        public void setData(Language selectedLanguage, int position) {

            this.languageName.setText(selectedLanguage.getLanguageName());


        }


        @Override
        public void onClick(View v) {


        }


    }
}