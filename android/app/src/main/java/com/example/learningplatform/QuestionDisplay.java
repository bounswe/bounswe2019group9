package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class QuestionDisplay extends AppCompatActivity {

    Button answer1,answer2,answer3,answer4;
    TextView questionText;
    ArrayList<String> questionList;
    ArrayList<String> choices;
    int questionCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_displaying);

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        questionText = findViewById(R.id.questionText);

        questionList = new ArrayList<>();
        choices = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        questionList = b.getStringArrayList("questions");
        questionList.add("How are you today ? ");
        choices = b.getStringArrayList("choices");
        choices.add("fine");
        choices.add("thanks");
        choices.add("and");
        choices.add("you");

        questionText.setText(questionList.get(questionCount));
        answer1.setText(choices.get(questionCount));
        answer2.setText(choices.get(questionCount+1));
        answer3.setText(choices.get(questionCount+2));
        answer4.setText(choices.get(questionCount+3));
        questionCount++;

    }


    public void NextQuestion(View v){
        TextView t = (TextView) v;
        t.getText().toString();
        questionText.setText(questionList.get(questionCount));
        answer1.setText(choices.get(4*questionCount));
        answer2.setText(choices.get(4*questionCount+1));
        answer3.setText(choices.get(4*questionCount+2));
        answer4.setText(choices.get(4*questionCount+3));
        questionCount++;
    }


}
