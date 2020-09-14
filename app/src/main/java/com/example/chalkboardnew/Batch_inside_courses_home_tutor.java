package com.example.chalkboardnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Batch_inside_courses_home_tutor extends AppCompatActivity {

    TextView t1;
    private RecyclerView recyclerView;
    private BatchAdapter batchAdapter;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    EditText section_name;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();
    public static String title  = "";
    List<BatchClass> batchClassList = new ArrayList<>();
    Toolbar batch_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch_inside_home_tutor);
        batch_toolbar = findViewById(R.id.toolbar_batch);
        setSupportActionBar(batch_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        t1 = findViewById(R.id.abh12);
        //    getSupportActionBar().setDisplayShowHomeEnabled(true);
        batch_toolbar.setNavigationIcon(R.drawable.ic_back);
        batch_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.batch_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        title = intent.getStringExtra("className");
        CollectionReference collectionReference = firestore.collection("users").document(userID).collection("Courses").document(title).collection("Batches");
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<BatchClass> documentData = queryDocumentSnapshots.toObjects(BatchClass.class);
                batchAdapter = new BatchAdapter(getApplicationContext(), batchClassList);
                recyclerView.setAdapter(batchAdapter);
                batchClassList.addAll(documentData);
                batchAdapter.notifyDataSetChanged();
                t1.setVisibility(View.GONE);
                //    System.out.println();
               /* classAdapter.setOnItemClickListener(new ClassAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        gotoinsideclass(position);
                    }
                });*/


            }
        });
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.add_batches_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBatchDialog();
            }
        });




    }

    private void showBatchDialog() {
        AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.batch_dialogbox, null);
        alerDialog.setView(view);
        AlertDialog dialog = alerDialog.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
        Button add_batch =(Button) view.findViewById(R.id.batch_add_button);
        EditText batch_name =(EditText) view.findViewById(R.id.batch_name_edittext);
        EditText no_of_days =(EditText) view.findViewById(R.id.batch_no_of_days_edittext);
        EditText batch_days =(EditText) view.findViewById(R.id.batch_days_edittext);
        EditText batch_time =(EditText) view.findViewById(R.id.batch_time_edittext);
        EditText payment_per_student =(EditText) view.findViewById(R.id.payment_edittext);

        add_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b_name = batch_name.getText().toString();
                String b_no_of_days = no_of_days.getText().toString();
                String b_days = batch_days.getText().toString();
                String b_time = batch_time.getText().toString();
                String b_payment = payment_per_student.getText().toString();

                System.out.println(b_name);

                if (TextUtils.isEmpty(b_name))
                {
                    batch_name.setError("required");
                    return;
                }
                if (TextUtils.isEmpty(b_no_of_days))
                {
                    no_of_days.setError("required");
                    return;
                }   if (TextUtils.isEmpty(b_days))
                {
                    batch_days.setError("required");
                    return;
                }   if (TextUtils.isEmpty(b_time))
                {
                    batch_time.setError("required");
                    return;
                }   if (TextUtils.isEmpty(b_payment))
                {
                    payment_per_student.setError("required");
                    return;
                }
                System.out.println(title);
                DocumentReference documentReference = firestore.collection("users").document(userID)
                        .collection("Courses").document(title).collection("Batches").document(b_name);

                BatchClass batchClass = new BatchClass(b_name,b_no_of_days,b_days,b_time,b_payment);
                documentReference.set(batchClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Batch is added!",Toast.LENGTH_SHORT);
                    }
                });

                dialog.dismiss();

            }
        });

    }
}