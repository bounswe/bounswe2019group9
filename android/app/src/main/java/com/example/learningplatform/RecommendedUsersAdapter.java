package com.example.learningplatform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.Locale;

public class RecommendedUsersAdapter extends RecyclerView.Adapter<RecommendedUsersAdapter.MyViewHolder>{
    ArrayList<recommendedUser> recommendedUsersArray;
    Context context;
    SharedPreferences sharedPreferences;

    public RecommendedUsersAdapter(final Context context, final JSONObject req_data) {
        this.context = context;
        this.recommendedUsersArray = new ArrayList<>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //TODO your background code

                RequestQueue queue = Volley.newRequestQueue(context);
                String url ="https://api.bounswe2019group9.tk/recommendations" ;
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,req_data,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {

                                    if(response.has("data")) {
                                        JSONArray data = (JSONArray) response.get("data");
                                        for(int i=0; i<data.length();i++) {
                                            if (data.getJSONObject(i).has("firstName") & data.getJSONObject(i).has("lastName")) {
                                                String nameOfUser = (String) data.getJSONObject(i).get("firstName") + " " +  (String) data.getJSONObject(i).get("lastName") ;
                                                JSONArray gradeOfUser = data.getJSONObject(i).getJSONArray("grades");
                                                JSONArray languagesOfUser = data.getJSONObject(i).getJSONArray("languages");
                                                int id = data.getJSONObject(i).getInt("userId");
                                                int rating = data.getJSONObject(i).getInt("rating");

                                                for(int j = 0; j<gradeOfUser.length();j++){
                                                    String lang = languagesOfUser.getString(j);
                                                    int grade = gradeOfUser.getInt(j);
                                                    recommendedUsersArray.add(new recommendedUser(nameOfUser, lang, id , rating, grade));
                                                }

                                                Log.i("nameofuser", nameOfUser);
                                                if(i==4) break;
                                            }
                                        }
                                        notifyDataSetChanged();

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
                        Log.e("tag2", "here");

                    }
                });
                queue.add(jsonObjectRequest);
            }
        });
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

        @Override
        public RecommendedUsersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.recommended_users, parent, false);
            RecommendedUsersAdapter.MyViewHolder holder = new RecommendedUsersAdapter.MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final RecommendedUsersAdapter.MyViewHolder holder, final int position) {

            holder.nameOfUser.setText(recommendedUsersArray.get(position).name);
            holder.rating.setText(String.format(Locale.getDefault(), "Rate :%d ,  %s",
                    recommendedUsersArray.get(position).rating,
                    getGradeFromInt(recommendedUsersArray.get(position).grade)));

        }

        @Override
        public int getItemCount(){
            return recommendedUsersArray.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView nameOfUser;
            TextView rating;
            ConstraintLayout constraintLayout;

            MyViewHolder(View itemView) {
                super(itemView);
                this.nameOfUser = itemView.findViewById(R.id.recommended_user);
                this.rating = itemView.findViewById(R.id.rating);
                this.constraintLayout = itemView.findViewById(R.id.constraintLayout);
            }

            @Override
            public void onClick(View v) {
            }
        }
}
class recommendedUser {
    String name, lang;
    int grade;
    int id;
    int rating;

    public recommendedUser(String name, String lang, int id, int rating, int grade) {
        this.name = name;
        this.lang = lang;
        this.id = id;
        this.rating = rating;
        this.grade = grade;



    }

}