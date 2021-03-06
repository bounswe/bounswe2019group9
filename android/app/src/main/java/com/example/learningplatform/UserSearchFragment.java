package com.example.learningplatform;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
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

public class UserSearchFragment extends Fragment {
    SearchView searchView;
    ListView listView;
    ArrayList<UserDataClass> list;
    ArrayAdapter<UserDataClass> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_search, container, false);
        searchView = (SearchView) v.findViewById(R.id.searchView);
        listView = (ListView) v.findViewById(R.id.lv1);

        list = new ArrayList<UserDataClass>();

        adapter = new ArrayAdapter<UserDataClass>(v.getContext(),android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                JSONObject search_data = new JSONObject();
                try {
                    search_data.put("firstName",query);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                RequestQueue queue = Volley.newRequestQueue(searchView.getContext());
                String url = "https://api.bounswe2019group9.tk/search/users";
                JsonObjectRequest searchJsonReq = new JsonObjectRequest(Request.Method.POST, url,search_data,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray data = (JSONArray) response.get("data");
                                    list.clear();
                                    for (int i = 0; i < data.length(); i++) {

                                        String firstName = data.getJSONObject(i).getString("firstName");
                                        String lastName = data.getJSONObject(i).getString("lastName");
                                        int userId = data.getJSONObject(i).getInt("userId");

                                        UserDataClass newUser = new UserDataClass();
                                        newUser.firstName = firstName;
                                        newUser.lastName = lastName;
                                        newUser.userID = userId;

                                        if(!list.isEmpty()) {
                                            boolean exist=false;
                                            for (int j = 0; j < list.size(); j++) {
                                                if (list.get(j).userID == userId) {
                                                    exist = true;
                                                }
                                            }
                                            if(!exist){
                                                list.add(newUser);
                                            }
                                        }else{
                                            list.add(newUser);
                                        }


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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String person = String.valueOf(parent.getItemAtPosition(position));
                UserDataClass selectedUser = (UserDataClass) parent.getItemAtPosition(position);
                int selectedID = selectedUser.userID;
                String person = selectedUser.firstName;
                //Toast.makeText(getContext(),"yeeey clicked well on "+person+" with ID "+ selectedID,Toast.LENGTH_LONG).show();
                Intent intent;
                intent = new Intent(view.getContext(),TargetUserActivity.class);
                intent.putExtra("targetUserId",selectedID);
                startActivity(intent);
            }
        });


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

class UserDataClass implements Serializable {

    public int userID;
    public String firstName;
    public String lastName;

    public String toString(){
        return firstName+ " "+lastName;
    }
}