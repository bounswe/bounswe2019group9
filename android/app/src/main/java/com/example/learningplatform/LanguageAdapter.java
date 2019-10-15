package com.example.learningplatform;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    ArrayList<Language> languageArrayList;

    public LanguageAdapter(Context context) {
        this.languageArrayList = new ArrayList<Language>();
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.languages, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.languageName.setText(languageArrayList.get(position).getLanguageName());
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
            this.languageName = (TextView) itemView.findViewById(R.id.language);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }

        @Override
        public void onClick(View v) {

        }


    }
}