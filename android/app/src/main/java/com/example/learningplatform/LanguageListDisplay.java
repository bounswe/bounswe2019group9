package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class LanguageListDisplay extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.language_selection);
        recyclerView = findViewById(R.id.recylerview);
        LanguageAdapter languageAdapter = new LanguageAdapter(this);
        recyclerView.setAdapter(languageAdapter);

    }

}
