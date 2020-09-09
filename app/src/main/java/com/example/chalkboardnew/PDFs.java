package com.example.chalkboardnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PDFs extends AppCompatActivity {

    ListView mypdfListView;
    DatabaseReference databaseReference;
    List<PDFs> PDFs;
Toolbar pdf_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_fs);
    pdf_toolbar = findViewById(R.id.toolbar_pdf);
        mypdfListView = (ListView)findViewById(R.id.myListView_pdf);
        PDFs= new ArrayList<>();
        setSupportActionBar(pdf_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pdf_toolbar.setNavigationIcon(R.drawable.ic_back);
        pdf_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        viewAllFiles();
    }

    private void viewAllFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference("PDFs");

    }
}