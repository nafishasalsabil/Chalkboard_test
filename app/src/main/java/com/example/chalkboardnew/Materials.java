package com.example.chalkboardnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.widget.RelativeLayout;

public class Materials extends AppCompatActivity {

    Toolbar toolbar_materials;
    RelativeLayout pdf_layout,tutorials_layout,exam_ques_layout,my_notes_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        toolbar_materials = findViewById(R.id.toolbar_materials);
        setSupportActionBar(toolbar_materials);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_materials.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setElevation(0);
        toolbar_materials.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pdf_layout = findViewById(R.id.pdf_layout);
        tutorials_layout = findViewById(R.id.tutorilas_layout);
        exam_ques_layout = findViewById(R.id.exam_questions_layout);
        my_notes_layout = findViewById(R.id.my_notes_layout);


        pdf_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PDFs.class));
            }
        });
        exam_ques_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Exam_Questions.class));

            }
        });
        my_notes_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MyNotes.class));

            }
        });
    }
}
