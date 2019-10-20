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
    ArrayList<String> answers;
    ArrayList<String> solutions;
    int questionCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_displaying);

        questionCount = 0;

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        questionText = findViewById(R.id.questionText);

        questionList = new ArrayList<>();
        choices = new ArrayList<>();
        answers = new ArrayList<>();

        Bundle b = getIntent().getExtras();
        questionList = b.getStringArrayList("questions");
        choices = b.getStringArrayList("choices");
        solutions = b.getStringArrayList("solutions");

        questionText.setText(questionList.get(questionCount));
        answer1.setText(choices.get(questionCount));
        answer2.setText(choices.get(questionCount+1));
        answer3.setText(choices.get(questionCount+2));
        answer4.setText(choices.get(questionCount+3));
        questionCount++;

    }


    public void NextQuestion(View v){
        TextView t = (TextView) v;
        String answer = t.getText().toString();
        Log.i("logofanswer",""+answer);
        answers.add(answer);

        if(questionCount==questionList.size()) {

            int nofQuestions = questionList.size();
            int correctAnswers = 0;
            for (int i = 0; i < nofQuestions; i++) {
                if (answers.get(i).equals(solutions.get(i)))
                    correctAnswers++;
            }
            double successRate = 100 * (correctAnswers / nofQuestions);
            int gradeOfStudent;
            String letterGradeOfStudent;

            if (successRate > 90) {
                gradeOfStudent = 6;
                letterGradeOfStudent = "C2";
            } else if (successRate > 85) {
                gradeOfStudent = 5;
                letterGradeOfStudent = "C1";
            } else if (successRate > 75) {
                gradeOfStudent = 4;
                letterGradeOfStudent = "B2";
            } else if (successRate > 60){
                gradeOfStudent = 3;
                letterGradeOfStudent = "B1";
            } else if (successRate>40) {
                gradeOfStudent = 2;
                letterGradeOfStudent = "A2";
            }
            else {
                gradeOfStudent = 1;
                letterGradeOfStudent = "A1";
            }


            Intent intent = new Intent(this,GradeView.class);
            intent.putExtra("grade",letterGradeOfStudent);
            this.startActivity(intent);

        } else {

            questionText.setText(questionList.get(questionCount));
            answer1.setText(choices.get(4 * questionCount));
            answer2.setText(choices.get(4 * questionCount + 1));
            answer3.setText(choices.get(4 * questionCount + 2));
            answer4.setText(choices.get(4 * questionCount + 3));
            questionCount++;
        }
    }

}
