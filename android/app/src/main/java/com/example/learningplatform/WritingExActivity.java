package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;


public class WritingExActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    String imgDecodableString;
    ProgressDialog prgDialog;
    String encodedString;
    String imgPath, fileName;
    Bitmap bitmap;
    SharedPreferences sharedPreferences;
    int id;
    String textInput = null;

    String uploadedImageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_ex);


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

                ImageView imgView = findViewById(R.id.imgView);

                //Set the Image in ImageView after decoding the String

                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));






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

    @Override
    protected void onDestroy() {

        // TODO Auto-generated method stub

        super.onDestroy();

        // Dismiss the progress bar when application is closed

        if (prgDialog != null) {
            prgDialog.dismiss();
        }

    }

    public void encodeImagetoString() {

        new AsyncTask<Void, Void, String>() {

            protected void onPreExecute() {



            };

            @Override

            protected String doInBackground(Void... params) {

                BitmapFactory.Options options = null;

                options = new BitmapFactory.Options();

                options.inSampleSize = 3;

                bitmap = BitmapFactory.decodeFile(imgPath,
                        options);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                // Must compress the Image to reduce image size to make upload easy

                bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);

                byte[] byte_arr = stream.toByteArray();

                // Encode Image to String

                encodedString = Base64.encodeToString(byte_arr, 0);

                return "";

            }

            @Override
            protected void onPostExecute(String msg) {

                prgDialog.setMessage("Calling Upload");

                //triggerImageUpload();

            }

        }.execute(null, null, null);

    }

    public void triggerImageUpload(View v) {

        makeAPICall();

    }

    public void makeAPICall(){


        String base64data = "data:image/jpeg;base64," + encodedString;
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        id = sharedPreferences.getInt("Id",0);

        JSONObject photo_data = new JSONObject();
        try {
            photo_data.put("authorId",id);
            photo_data.put("base64Data",base64data);
            photo_data.put("exerciseId","");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bounswe2019group9.tk/files/images/essay";
        JsonObjectRequest solvedJsonReq = new JsonObjectRequest(Request.Method.POST, url,photo_data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int statusCode =  response.getInt("status");

                            if(statusCode == 200){
                                uploadedImageUrl=response.getString("data");
                                Toast.makeText(WritingExActivity.this,"Your essay photo has been uploaded succesfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(WritingExActivity.this,"An error occurred while uploading image to server",
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

    public void submit(View v){


        JSONObject essay_data = new JSONObject();

        if(textInput == null) {
            try {
                essay_data.put("authorId",id);
                essay_data.put("source",uploadedImageUrl);
                essay_data.put("assignmentId","");
                essay_data.put("sourceType",0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                essay_data.put("authorId",id);
                essay_data.put("source",textInput);
                essay_data.put("assignmentId","");
                essay_data.put("sourceType",1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.bounswe2019group9.tk/essays";
        JsonObjectRequest solvedJsonReq = new JsonObjectRequest(Request.Method.POST, url,essay_data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int statusCode =  response.getInt("status");

                            if(statusCode == 200){
                                Toast.makeText(WritingExActivity.this,"Your essay has been uploaded to system succesfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(WritingExActivity.this,"An error occurred while sending your essay to server",
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

    public void NextQuestion(View v){

    }
}
