package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GradeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_view);

        Bundle b = getIntent().getExtras();
        String grade = b.getString("grade");

        TextView gradeView = findViewById(R.id.gradeView);
        gradeView.setText(grade);
    }
}
