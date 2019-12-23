package com.example.learningplatform;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.InputStream;

public class SolvedEssayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writing_ex_solved);
        Bundle b = getIntent().getExtras();
        SolvedEssay essay = (SolvedEssay) b.getSerializable("SolvedEssay");
        ImageView imageView = findViewById(R.id.imgView);
        TextView textView = findViewById(R.id.essayAsText);
        TextView question = findViewById(R.id.questionTextSolved);
        question.setText(essay.essayQuestionLong);
        if(essay.imageUploaded){
            textView.setVisibility(View.GONE);
            new DownloadImageTask(imageView)
                    .execute(essay.source);
        } else{
            imageView.setVisibility(View.GONE);
            textView.setText(essay.source);
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.nav_bar_excercise:
                        intent = new Intent(SolvedEssayActivity.this, ExerciseListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_message:
                        intent = new Intent(SolvedEssayActivity.this, ChatsListDisplay.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_profile:
                        intent = new Intent(SolvedEssayActivity.this, ProfilePageActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_bar_search:
                        intent = new Intent(SolvedEssayActivity.this, SearchActivity.class);
                        startActivity(intent);
                        return true;
                }
                return true;
            }
        });
    }

    public void SeeRecommended(View v){
        Intent intent = new Intent(SolvedEssayActivity.this, RecommendationPageDisplay.class);
        startActivity(intent);
    }
}
class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
