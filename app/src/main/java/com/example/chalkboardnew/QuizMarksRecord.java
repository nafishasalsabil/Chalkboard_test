package com.example.chalkboardnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizMarksRecord extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();
    private QuizMarksRecordAdapter quizMarksRecordAdapter;
    private ClassAdapter classAdapter;
    private RecyclerView.LayoutManager layoutManager;
    CollectionReference studentcollection;
    List<QuizMarksClass> studentItems1 = new ArrayList<>();
    List<QuizNameClass> studentItems2 = new ArrayList<>();

    Toolbar toolbar_record;
    int total_marks = 0;
    RecyclerView quiz_rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_marks_record);
        quiz_rv = findViewById(R.id.quiz_record_recyclerview);
        toolbar_record = findViewById(R.id.toolbar_quiz_record);
        setSupportActionBar(toolbar_record);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_record.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setElevation(0);
        toolbar_record.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
       String title =  intent.getStringExtra("Title");
       String section =  intent.getStringExtra("Section");
       String quiz = intent.getStringExtra("quiz");
        System.out.println(quiz);
        System.out.println(title);
        System.out.println(section);
        quiz_rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        quiz_rv.setLayoutManager(layoutManager);
        quizMarksRecordAdapter = new QuizMarksRecordAdapter(getApplicationContext(), studentItems1);
        quiz_rv.setAdapter(quizMarksRecordAdapter);
     //   studentItems1.addAll(documentData);
        quizMarksRecordAdapter.notifyDataSetChanged();
        studentcollection = firestore.collection("users").document(userID)
                .collection("Courses").document(title).collection("Sections")
                .document(section)
                .collection("Students");

      /*  studentcollection.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                  for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                QuizMarksClass items = documentSnapshot.toObject(QuizMarksClass.class);

                    studentItems1.add(items);
                    System.out.println(studentItems1);
                    DocumentReference documentReference =  firestore.collection("users").document(userID)
                            .collection("Courses").document(title).collection("Sections")
                            .document(section)
                            .collection("Quizes").document(quiz).collection("Students").document(Integer.toString(items.getId()));
                    System.out.println(items.getId());
                    Log.d("checkO",Integer.toString(items.getId()));
                    Log.d("checkO",Integer.toString(studentItems1.size()));

                    for(int i=0;i<studentItems1.size();i++)
                    {
                        Map<String,Object> user = new HashMap<>();
                        user.put("id",items.getId());
                        user.put("name",items.getName());
                        documentReference.set(user);
                    }

                }


            }
        });
*/
        CollectionReference collectionReference =   firestore.collection("users").document(userID)
                .collection("Courses").document(title).collection("Sections")
                .document(section)
                .collection("Quizes").document(quiz).collection("Students");
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                studentItems1.clear();
                List<QuizMarksClass> documentData = queryDocumentSnapshots.toObjects(QuizMarksClass.class);
                quiz_rv.setAdapter(quizMarksRecordAdapter);
                quizMarksRecordAdapter.setTitle(title);
                quizMarksRecordAdapter.setSection(section);
                quizMarksRecordAdapter.setQuiz(quiz);
                studentItems1.addAll(documentData);
                quizMarksRecordAdapter.notifyDataSetChanged();
            }
        });



       /* studentcollection = firestore.collection("users").document(userID)
                .collection("Courses").document(title).collection("Sections")
                .document(section)
                .collection("Quizes").document(quiz).collection("Students");
        studentcollection.orderBy("id", Query.Direction.ASCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<StudentItems> documentData = queryDocumentSnapshots.toObjects(StudentItems.class);
                quizMarksRecordAdapter = new QuizMarksRecordAdapter(getApplicationContext(), studentItems1);
                quizMarksRecordAdapter.setTotal_marks(total_marks);
                quizMarksRecordAdapter.setTitle(title);
                quizMarksRecordAdapter.setSection(section);
                quizMarksRecordAdapter.setQuiz(quiz);

                quiz_rv.setAdapter(quizMarksRecordAdapter);
                studentItems1.addAll(documentData);
                quizMarksRecordAdapter.notifyDataSetChanged();
              *//*  for (int i = 0; i < studentItems1.size(); i++) {
                    System.out.println(studentItems1.get(i).toString());
                }
*//*
            }
        });*/
       /* CollectionReference collectionReference = firestore.collection("users").document(userID)
                .collection("Courses").document(title).collection("Sections")
                .document(section)
                .collection("Quizes").document(quiz).collection("Students");
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<QuizNameClass> doc = queryDocumentSnapshots.toObjects(QuizNameClass.class);
                quizMarksRecordAdapter = new QuizMarksRecordAdapter(getApplicationContext(), studentItems2);
                quizMarksRecordAdapter.setTotal_marks(total_marks);
                quizMarksRecordAdapter.setTitle(title);
                quizMarksRecordAdapter.setSection(section);
                quizMarksRecordAdapter.setQuiz(quiz);

                quiz_rv.setAdapter(quizMarksRecordAdapter);
                studentItems2.addAll(doc);
                quizMarksRecordAdapter.notifyDataSetChanged();


            }
        });
*/
      /*  documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                QuizNameClass quizNameClass = documentSnapshot.toObject(QuizNameClass.class);
                assert quizNameClass != null;
                total_marks = quizNameClass.getQuiz_total_marks();
                System.out.println(total_marks);
                quizMarksRecordAdapter = new QuizMarksRecordAdapter(getApplicationContext(), studentItems1);
                 quizMarksRecordAdapter.setTotal_marks(total_marks);
                quizMarksRecordAdapter.setTitle(title);
                quizMarksRecordAdapter.setSection(section);
                quizMarksRecordAdapter.setQuiz(quiz);
                quiz_rv.setAdapter(quizMarksRecordAdapter);
                quizMarksRecordAdapter.notifyDataSetChanged();
            }
        });*/
    }
}