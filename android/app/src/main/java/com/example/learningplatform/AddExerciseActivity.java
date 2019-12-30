package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class AddExerciseActivity extends AppCompatActivity {
    String imgDecodableString,encodedString,uploadedImageUrl;
    private static int RESULT_LOAD_IMAGE = 1;
    int id;

    SharedPreferences sharedPreferences;

    int typeOfExercise;
    EditText questionBody;
    EditText optA,optB,optC,optD;
    Spinner answerDropdown;
    Spinner dropdown;

    int currentExId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);


        Intent intent = getIntent();
        typeOfExercise = intent.getIntExtra("typeOfExercise",0);
        final int typeOfLang = 1;


        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        final String lang = sharedPreferences.getString("languageOfUser","");



        dropdown = findViewById(R.id.gradeSpinner);

        String[] items = new String[]{"A1", "A2", "B1","B2","C1","C2"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);


        answerDropdown = findViewById(R.id.correctAnswerSpinner);

        String[] answers = new String[]{"A", "B", "C","D"};

        ArrayAdapter<String> answerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, answers);

        answerDropdown.setAdapter(answerAdapter);



        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        id = sharedPreferences.getInt("Id",0);

        questionBody = findViewById(R.id.NewQuestionEditText);
        optA = findViewById(R.id.option1);
        optB = findViewById(R.id.option2);
        optC = findViewById(R.id.option3);
        optD = findViewById(R.id.option4);




        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        intent = new Intent(AddExerciseActivity.this, ExerciseListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_message:
                        intent = new Intent(AddExerciseActivity.this, ChatsListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_profile:
                        intent = new Intent(AddExerciseActivity.this, ProfilePageActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_search:
                        intent = new Intent(AddExerciseActivity.this, SearchActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });

    }

    public void SubmitButton(View v){

        JSONObject content_data = new JSONObject();

        String answer = answerDropdown.getSelectedItem().toString();
        int answerId=-1;

        if(answer.equals("A")){
            answerId = 1;
        }else if (answer.equals("B")){
            answerId = 2;
        }else if (answer.equals("C")){
            answerId = 3;
        }else if (answer.equals("D")){
            answerId = 4;
        }

        String grade = dropdown.getSelectedItem().toString();
        int gradeId =-1;

        if(grade.equals("A1")){
            gradeId = 1;
        }else if (grade.equals("A2")){
            gradeId = 2;
        }else if (grade.equals("B1")){
            gradeId = 3;
        }else if (grade.equals("B2")){
            gradeId = 4;
        }else if (grade.equals("C1")){
            gradeId = 5;
        }else if (grade.equals("C2")){
            gradeId = 6;
        }


    try{
        content_data.put("correctAnswer",answerId);
        content_data.put("grade",gradeId);
        content_data.put("languageId",1);
        content_data.put("optionA",optA.getText().toString());
        content_data.put("optionB",optB.getText().toString());
        content_data.put("optionC",optC.getText().toString());
        content_data.put("optionD",optD.getText().toString());
        content_data.put("questionBody",questionBody.getText().toString());
        content_data.put("typeId",typeOfExercise);
    }catch (JSONException e){
        e.printStackTrace();
    }

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bounswe2019group9.tk/contents/add";
        JsonObjectRequest solvedJsonReq = new JsonObjectRequest(Request.Method.POST, url,content_data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int statusCode =  response.getInt("status");

                            if(statusCode == 200){

                                JSONObject data = response.getJSONObject("data");
                                currentExId =  data.getInt("id");
                                Toast.makeText(AddExerciseActivity.this,"Your exercise has been uploaded successfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(AddExerciseActivity.this,"An error occurred while uploading exercise to server",
                                        Toast.LENGTH_LONG).show();
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

        queue.add(solvedJsonReq);

    }


    public void sendPhotoToServer(View v){
        encodeImagetoString();

        String base64data = "data:image/jpeg;base64," + encodedString;
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);


        JSONObject photo_data = new JSONObject();
        try {
            photo_data.put("authorId",id);
            photo_data.put("base64Data",base64data);
            photo_data.put("exerciseId",currentExId);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bounswe2019group9.tk/files/images/";
        JsonObjectRequest solvedJsonReq = new JsonObjectRequest(Request.Method.POST, url,photo_data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int statusCode =  response.getInt("status");

                            if(statusCode == 200){
                                uploadedImageUrl=response.getString("data");
                                Toast.makeText(AddExerciseActivity.this,"Your essay photo has been uploaded succesfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(AddExerciseActivity.this,"An error occurred while uploading image to server",
                                        Toast.LENGTH_LONG).show();
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

        queue.add(solvedJsonReq);

    }

    public void encodeImagetoString() {


        Bitmap imgBitmap;
        imgBitmap = BitmapFactory.decodeFile(imgDecodableString);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        // Must compress the Image to reduce image size to make upload easy
        imgBitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);

        byte[] byte_arr = stream.toByteArray();

        // Encode Image to String
        encodedString = Base64.encodeToString(byte_arr, 0);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        try {

            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();

                String[] filePathColumn = { MediaStore.Images.Media.DATA };



                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,

                        filePathColumn, null, null, null);

                // Move to first row
                cursor.moveToFirst();


                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                imgDecodableString = cursor.getString(columnIndex);

                cursor.close();




            } else {

                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();

            }

        } catch (Exception e) {

            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();

        }


    }
    public void photoUpload(View v) {


        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


        startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

    }
}
