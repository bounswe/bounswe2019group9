package com.example.learningplatform;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

import java.io.Serializable;
import java.util.ArrayList;

public class ContentSearchFragment extends Fragment {
    SearchView searchView;
    ListView listView;
    ArrayList<ContentDataClass> list;
    ArrayAdapter<ContentDataClass> adapter;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_search, container, false);
        searchView = (SearchView) v.findViewById(R.id.searchView);
        listView = (ListView) v.findViewById(R.id.lv1);
        sharedPreferences = v.getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);

        list = new ArrayList<ContentDataClass>();

        adapter = new ContentAdapter(v.getContext(),list);
        listView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                JSONObject search_data = new JSONObject();
                try {
                    int grade = sharedPreferences.getInt("currentGrade",0);
                    search_data.put("grade",grade);
                    search_data.put("languageId", 1);
                    search_data.put("tag",query);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestQueue queue = Volley.newRequestQueue(searchView.getContext());
                String url = "https://api.bounswe2019group9.tk/search/exercises";
                JsonObjectRequest searchJsonReq = new JsonObjectRequest(Request.Method.POST, url,search_data,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray data = (JSONArray) response.get("data");
                                    list.clear();
                                    for (int i = 0; i < data.length(); i++) {

                                        String question = data.getJSONObject(i).getString("questionBody");
                                        JSONArray tags = data.getJSONObject(i).getJSONArray("tags");
                                        int exerciseId = data.getJSONObject(i).getInt("id");
                                        String[] tagsArray = new String[tags.length()];
                                        for(int j = 0; j<tags.length(); j++){
                                            String tag = tags.getJSONObject(j).getString("tagText");
                                            tagsArray[j] = tag;
                                        }
                                        ContentDataClass newContent = new ContentDataClass(exerciseId, question, tagsArray);
                                        list.add(newContent);
                                    }

                                    adapter.notifyDataSetChanged();

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

                queue.add(searchJsonReq);


                if(list.contains(query)) {
                    adapter.getFilter().filter(query);
                }else {
                    //Toast.makeText(SearchActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String person = String.valueOf(parent.getItemAtPosition(position));
                ContentDataClass selectedUser = (ContentDataClass) parent.getItemAtPosition(position);
                int selectedID = selectedUser.userID;
                String person = selectedUser.firstName;
                Toast.makeText(getContext(),"yeeey clicked well on "+person+" with ID "+ selectedID,Toast.LENGTH_LONG).show();
                Intent intent;
                intent = new Intent(view.getContext(),TargetUserActivity.class);
                intent.putExtra("targetUserId",selectedID);
                startActivity(intent);
            }
        });*/


        BottomNavigationView bottomNavigationView = (BottomNavigationView) v.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        intent = new Intent(getContext(), ExerciseListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_message:
                        intent = new Intent(getContext(), ChatsListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_profile:
                        intent = new Intent(getContext(), ProfilePageActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_search:
                        return true;
                }
                return true;
            }
        });
        return v;
    }
}

class ContentDataClass implements Serializable {

    public int exerciseID;
    public String firstCharactersOfExercise;
    public String tagList;

    public ContentDataClass(int id, String exercise, String[] tagList){
        this.exerciseID = id;
        if(exercise.length()>100){
            exercise = exercise.substring(0,100);
        }
        this.firstCharactersOfExercise = exercise + "....";
        String tags = tagList[0];
        for(int i = 1; i<tagList.length; i++){
            tags = tags + ", "+tagList[i];
        }
        this.tagList = tags;
    }

    public String toString(){
        return "";
    }
}

class ContentAdapter extends ArrayAdapter<ContentDataClass> {
    public ContentAdapter(Context context, ArrayList<ContentDataClass> contents) {
        super(context, 0, contents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ContentDataClass content = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.content_search_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvExercise = (TextView) convertView.findViewById(R.id.tvExercise);
        TextView tvTags = (TextView) convertView.findViewById(R.id.tvTags);
        // Populate the data into the template view using the data object
        tvExercise.setText(content.firstCharactersOfExercise);
        tvTags.setText(content.tagList);
        // Return the completed view to render on screen
        return convertView;
    }
}
