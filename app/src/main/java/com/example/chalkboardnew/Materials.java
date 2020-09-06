package com.example.chalkboardnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Materials extends AppCompatActivity {

    RelativeLayout pdf_layout,tutorials_layout,exam_ques_layout,my_notes_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
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
