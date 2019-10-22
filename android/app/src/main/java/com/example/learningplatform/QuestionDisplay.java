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
import android.widget.Toast;

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
    int userID=0;

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
            double successRate = 100 * (1.0*correctAnswers / nofQuestions);
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

            JSONObject grade_data = new JSONObject();
            try {
                grade_data.put("grade",gradeOfStudent);
                grade_data.put("languageId",1);
                grade_data.put("userId",userID);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RequestQueue queue = Volley.newRequestQueue(v.getContext());
            String url = "https://api.bounswe2019group9.tk//grades/add";
            JsonObjectRequest gradeJsonReq = new JsonObjectRequest(Request.Method.POST, url,grade_data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int statusCode =  response.getInt("status");
                                if(statusCode == 200){
                                    Toast.makeText(QuestionDisplay.this,"Your grade is calculated successfully",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(QuestionDisplay.this,"An error occurred while calculating your grade",
                                            Toast.LENGTH_SHORT).show();
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

            //queue.add(gradeJsonReq);

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
