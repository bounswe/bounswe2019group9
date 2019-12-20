package com.example.learningplatform;

import android.content.Context;
import android.os.AsyncTask;
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

public class SolvedEssayAdapter extends RecyclerView.Adapter<SolvedEssayAdapter.MyViewHolder>{
    ArrayList<SolvedEssay> essayArrayList;
    Context context;
    int userId;

    public SolvedEssayAdapter(final Context context, final int userID) {
        this.context = context;
        this.essayArrayList = new ArrayList<>();
        this.userId = userID;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code
                RequestQueue queue = Volley.newRequestQueue(context);
                String url ="https://api.bounswe2019group9.tk/essays/user?id=" + userId;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray data = (JSONArray) response.get("data");
                                    for(int i=0; i<data.length();i++){
                                        JSONObject assignment = data.getJSONObject(i).getJSONObject("assignment");
                                        String question = assignment.getString("question");
                                        SolvedEssay essay = new SolvedEssay(question);
                                        essayArrayList.add(essay);
                                    }
                                    notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("solved_essay_adapter", "Error on request to get essay list");

                    }
                });
                queue.add(jsonObjectRequest);
            }
        });
    }


    @Override
    public SolvedEssayAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.solved_essay_item, parent, false);
        SolvedEssayAdapter.MyViewHolder holder = new SolvedEssayAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SolvedEssayAdapter.MyViewHolder holder, final int position) {
        holder.essayQuestion.setText(essayArrayList.get(position).essayQuestion);
    }

    @Override
    public int getItemCount() {
        return essayArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView essayQuestion;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.essayQuestion = itemView.findViewById(R.id.essay_text);
        }

        @Override
        public void onClick(View v) {
        }
    }

}

class SolvedEssay{
    String essayQuestion;
    public SolvedEssay(String question){
        if(question.length() > 150){
            question = question.substring(0,150);
        }
        this.essayQuestion = question;
    }
}
