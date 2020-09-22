package com.example.chalkboardnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MyNotes extends AppCompatActivity {

    public static final int REQEST_CODE_ADD_NOTE = 1;
    RecyclerView notes_recyclerview;
    public static final int NUM_COLUMN = 2;
    private LinearLayout layoutWebURL;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();
    String title = "";
    String sec = "";
    List<NotesClass> notesClassList = new ArrayList();
    Toolbar t;
    NotesAdapter notesAdapter;
    EditText search_notes;
    Toolbar toolbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        Intent intent  = getIntent();
         title  =intent.getStringExtra("title");
         sec = intent.getStringExtra("section");
        search_notes =findViewById(R.id.search_notes);
        toolbar1 = findViewById(R.id.toolbar_notes);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setElevation(0);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Materials.class);
                intent1.putExtra("title",title);
                intent1.putExtra("section",sec);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);

                finish();
            }
        });
        search_notes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                searchNotes(s.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        notes_recyclerview = findViewById(R.id.notesRecyclerView);
        notes_recyclerview.setHasFixedSize(true);

       StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(NUM_COLUMN,LinearLayoutManager.VERTICAL);
        notes_recyclerview.setLayoutManager(layoutManager);

        CollectionReference collectionReference =  firestore.collection("users").document(userID)
                .collection("Courses").document(title)
                .collection("Sections").document(sec).collection("MyNotes");
        Query query = collectionReference;
        FirestoreRecyclerOptions<NotesClass> options = new FirestoreRecyclerOptions.Builder<NotesClass>()
                .setQuery(query, NotesClass.class)
                .build();
        notesAdapter = new NotesAdapter(options);
        notes_recyclerview.setAdapter(notesAdapter);
        // classitems.addAll(options);
        notesAdapter.notifyDataSetChanged();

       /* collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<NotesClass> documentData = queryDocumentSnapshots.toObjects(NotesClass.class);
                 notesAdapter = new NotesAdapter(getApplicationContext(),notesClassList);
                notes_recyclerview.setAdapter(notesAdapter);
                notesClassList.addAll(documentData);
                notesAdapter.setTitle(title);
                notesAdapter.setSection(sec);
                notesAdapter.notifyDataSetChanged();
               *//* for(int i = 0;i<notesClassList.size();i++)
                {
                    System.out.println("Hi");
                    System.out.println(notesClassList.get(i).toString());
                    if((notesClassList.get(i).toString().equals("No url added")))
                {
                    notesAdapter.setState("invisible");

                }
                else
                {
                    notesAdapter.setState("visible");


                }
                    notesAdapter.notifyDataSetChanged();
                }
*//*

            }
        });
*/
        ImageView imageAddNoteMain = findViewById(R.id.addnotes);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(getApplicationContext(),CreateNoteActivity.class);
                intent1.putExtra("section",sec);
                intent1.putExtra("title",title);
                startActivity(intent1);

            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(notes_recyclerview);


    }

    private void searchNotes(String toLowerCase) {
        CollectionReference collectionReference =  firestore.collection("users").document(userID)
                .collection("Courses").document(title)
                .collection("Sections").document(sec).collection("MyNotes");
        Query query = collectionReference.orderBy("search").startAt(toLowerCase).endAt(toLowerCase+"\uf0ff");
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                notesClassList.clear();

                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    NotesClass notesClass = documentSnapshot.toObject(NotesClass.class);
                    assert notesClass!=null;
                    assert firebaseUser!=null;
                    if(notesClass.getNoteTitle()!=null)// && notesClass.getNoteTitle().equals())
                    {
                        notesClassList.add(notesClass);
                        System.out.println(notesClass);
                        System.out.println("hello" + notesClass.getNoteTitle());

                    }
                    else
                    {

                        System.out.println("edrftgyhujikl");
                    }
                /*   chatUsers.add(chatUser);
                   System.out.println(chatUsers);
                   System.out.println(chatUser.getid());
                    chatUserAdapter = new ChatUserAdapter(getContext(),chatUsers);
                    users_recycler_view.setAdapter(chatUserAdapter);
                    chatUserAdapter.notifyDataSetChanged();
              */ }


                notes_recyclerview.setAdapter(notesAdapter);
                notesAdapter.notifyDataSetChanged();

           /*    classAdapter = new ClassAdapter(getApplicationContext(), classitems);
               List<CourseInfo> documentData = queryDocumentSnapshots.toObjects(CourseInfo.class);
*/
            }

        });
    }
    @Override
    public void onStart() {
        super.onStart();
        notesAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        notesAdapter.stopListening();
    }

    List<String> archive = new ArrayList<>();
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //   classAdapter.deleteItem(viewHolder.getAdapterPosition());
            if(direction==ItemTouchHelper.LEFT)
            {
                Toast.makeText(getApplicationContext(), "Deleting", Toast.LENGTH_LONG).show();
                notesAdapter.deleteItem(viewHolder.getAdapterPosition());

            }

           /* int position = viewHolder.getAdapterPosition();
            switch(direction)
            {
                case ItemTouchHelper.LEFT:
                    String course_name = classitems.get(position).getCourseTitle();
                    archive.add(course_name);
                    classitems.remove(position);
                    classAdapter.notifyItemRemoved(position);
                    Snackbar.make(recyclerView,course_name+" Archived", Snackbar.LENGTH_LONG)
                           *//* .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    archive.remove(archive.lastIndexOf(course_name));
                                    classitems.;
                                }
                            }).show()*//*;


            }
*/

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                    .addActionIcon(R.drawable.ic_baseline_delete_24)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

}