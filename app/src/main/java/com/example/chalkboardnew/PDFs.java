package com.example.chalkboardnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PDFs extends AppCompatActivity {

    ListView mypdfListView;
    DatabaseReference databaseReference;
    List<PDFs> PDFs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_fs);

        mypdfListView = (ListView)findViewById(R.id.myListView_pdf);
        PDFs= new ArrayList<>();


        viewAllFiles();
    }

    private void viewAllFiles() {
        databaseReference = FirebaseDatabase.getInstance().getReference("PDFs");

    }
}