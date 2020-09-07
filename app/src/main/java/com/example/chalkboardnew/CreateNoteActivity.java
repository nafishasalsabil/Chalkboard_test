package com.example.chalkboardnew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText inputNoteTitle,inputNoteSubtitle,inputNoteText;
    private TextView textDateTime;
    private TextView textWebURL;
    private LinearLayout layoutWebURL;

    public String input_note = "";
    private AlertDialog dialogAddURL;
    ImageView image_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        ImageView imageBack = findViewById(R.id.ImageBack);
        ImageView imageSave = findViewById(R.id.ImageSave);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteSubtitle = findViewById(R.id.inputNoteSubtitle);
        inputNoteText = findViewById(R.id.inputNote);
        textDateTime = findViewById(R.id.textDateTime);
        image_url = findViewById(R.id.image_url);

        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                .format(new Date())
        );
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        image_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUrlDialog();
            }
        });
    }

    private void showUrlDialog() {
        AlertDialog.Builder alerDialog2 = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_add_url, null);
        alerDialog2.setView(view);
        AlertDialog dialog = alerDialog2.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();

        Button add_button = (Button) view.findViewById(R.id.textAdd);
        Button cancel_button = (Button) view.findViewById(R.id.textCancel);

        EditText input_url = view.findViewById(R.id.inputURL);
        input_url.requestFocus();
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = input_url.getText().toString().trim();
                input_note = inputNoteText.getText().toString();

                System.out.println(input_note);

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
                    String u = inputNoteText.getText().toString() + " " + input_url.getText().toString();
                    inputNoteText.setText(u);
                    dialog.dismiss();
                }






            }
        });

    }

    private void saveNote(){
        if(inputNoteTitle.getText().toString().trim().isEmpty()
         && inputNoteText.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Note can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            String note_title = inputNoteTitle.getText().toString().trim();
            String note_subtitle = inputNoteSubtitle.getText().toString().trim();
            String my_note = inputNoteText.getText().toString().trim();


            startActivity(new Intent(getApplicationContext(),MyNotes.class));
            /*Map<String, Object> user = new HashMap<>();
            user.put("noteTitle", note_title);
            user.put("subtitle", note_subtitle);
            user.put("mynote", my_note);
         */

        }
        //ekhane hobe kaaj wait

       /* final ContactsContract.CommonDataKinds.Note note = new ContactsContract.CommonDataKinds.Note();
        note.setTitle(inputNoteTitle.getText().toString());

*/
    }
}