package com.example.chalkboardnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MonthlyIncomeHomeTutor extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();
    String clicked_courseTitle = "";
    String clicked_course_section = "";
    private RecyclerView recyclerView;
    private StudentListAdapter studentListAdapter;
    private ClassAdapter classAdapter;
    private RecyclerView.LayoutManager layoutManager;
    IncomeAdapter incomeAdapter;
    Toolbar toolbar_cp;
    List<IncomeClass> incomeClassList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_income_home_tutor);
        Intent intent = getIntent();
        clicked_courseTitle = intent.getStringExtra("title");
        clicked_course_section = intent.getStringExtra("section");
        System.out.println(clicked_courseTitle);
        System.out.println(clicked_course_section);
        recyclerView = (RecyclerView) findViewById(R.id.income_recyclerview_ht);
        toolbar_cp = findViewById(R.id.toolbar_income_ht);
        setSupportActionBar(toolbar_cp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_cp.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_cp.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CollectionReference collectionReference = firestore.collection("users").document(userID)
                .collection("Courses").document(clicked_courseTitle)
                .collection("Batches").document(clicked_course_section)
                .collection("Class_Performance");
        collectionReference.orderBy("id", Query.Direction.ASCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //   list.clear();
                List<IncomeClass> documentData = queryDocumentSnapshots.toObjects(IncomeClass.class);
                incomeAdapter = new IncomeAdapter(getApplicationContext(), incomeClassList);
                recyclerView.setAdapter(incomeAdapter);
                incomeClassList.addAll(documentData);
                incomeAdapter.setTitle(clicked_courseTitle);
              //  incomeAdapter.setSection(clicked_course_section);
                //   classPerformanceAdapter.setListSize(student.size());
                incomeAdapter.setSec(clicked_course_section);
                incomeAdapter.setTitle(clicked_courseTitle);
                incomeAdapter.notifyDataSetChanged();

            }
        });

    }
}