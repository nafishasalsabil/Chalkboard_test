package com.example.chalkboardnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Tutorials extends AppCompatActivity {
    private VideoView videoview;

    public static final int REQEST_CODE_ADD_LINK = 1;
    RecyclerView tutorials_recyclerview;
    public static final int NUM_COLUMN = 2;
    private LinearLayout layoutWebLINK;

    List<TutorialsClass> tutorialsClassList = new ArrayList();
    TutorialAdapter tutorialAdapter;
    FloatingActionButton imageAddLinkMain;
    public String url  ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);
         imageAddLinkMain = findViewById(R.id.addlinks);

        Intent intent  = getIntent();

        tutorials_recyclerview = findViewById(R.id.tutorialsRecyclerView);
        tutorials_recyclerview.setHasFixedSize(true);
        tutorials_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

       // videoview = findViewById(R.id.viewVideo);
        imageAddLinkMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerDialog2 = new AlertDialog.Builder(Tutorials.this);
                View view = LayoutInflater.from(Tutorials.this).inflate(R.layout.layout_add_tutorial_links, null);
                alerDialog2.setView(view);
                AlertDialog dialog = alerDialog2.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                Button add_button = (Button) view.findViewById(R.id.textAdd);
                Button cancel_button = (Button) view.findViewById(R.id.textCancel);

                EditText input_url = view.findViewById(R.id.inputLink);
             /*   tutorialAdapter = new TutorialAdapter(getApplicationContext());
                users_recycler_view.setAdapter(chatUserAdapter);
                chatUserAdapter.notifyDataSetChanged();
*/
//                input_url.requestFocus();
                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        url = input_url.getText().toString().trim();


                        // inputNoteText.setText(inputNoteText + "" + url);

                        System.out.println(url);

                        if(TextUtils.isEmpty(url))
                        {
                            input_url.setError("url is required");
                            //  Toast.makeText(getApplicationContext(),"Empty!",Toast.LENGTH_LONG);
                        }
                        else if(!Patterns.WEB_URL.matcher(input_url.getText().toString()).matches())
                        {
                            //  Toast.makeText(getApplicationContext(),"Enter valid url!",Toast.LENGTH_LONG);
                            input_url.setError("Enter valid url!");


                        }
                        else
                        {
                   /* String u = inputNoteText.getText().toString() + " " + input_url.getText().toString();
                    inputNoteText.setText(u);
                   */
                            dialog.dismiss();
                        }






                    }
                });

            }
        });
    }




}