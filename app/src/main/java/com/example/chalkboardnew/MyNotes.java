package com.example.chalkboardnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MyNotes extends AppCompatActivity {

    public static final int REQEST_CODE_ADD_NOTE = 1;
    RecyclerView notes_recyclerview;
    RecyclerView.LayoutManager layoutManager;
    List<NotesClass> notesClassList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        notes_recyclerview = findViewById(R.id.notesRecyclerView);
        notes_recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        notes_recyclerview.setLayoutManager(layoutManager);
        NotesAdapter notesAdapter = new NotesAdapter(getApplicationContext(),notesClassList);
        notes_recyclerview.setAdapter(notesAdapter);

        notesAdapter.notifyDataSetChanged();

        ImageView imageAddNoteMain = findViewById(R.id.addclass);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(getApplicationContext(), CreateNoteActivity.class),
                        REQEST_CODE_ADD_NOTE
                );
            }
        });

    }
}