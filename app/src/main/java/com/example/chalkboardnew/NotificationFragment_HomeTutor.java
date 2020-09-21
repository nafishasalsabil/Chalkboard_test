package com.example.chalkboardnew;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NotificationFragment_HomeTutor extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications_hometutor,container, false);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.add_notice);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerDialog2 = new AlertDialog.Builder(getContext());
                View v = LayoutInflater.from(getContext()).inflate(R.layout.add_post_dialogbox, null);
                alerDialog2.setView(v);
                AlertDialog dialog = alerDialog2.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                Button post = (Button) v.findViewById(R.id.Post_UploadBtn);
             //   Button no = (Button) view.findViewById(R.id.no_button);
              TextView titleEt = v.findViewById(R.id.PostTittleET);
              TextView  descriptionEt = v.findViewById(R.id.PostDescriptionET);

                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String title = titleEt.getText().toString().trim();
                        String description = descriptionEt.getText().toString().trim();
                        if(TextUtils.isEmpty(title))
                        {
                            Toast.makeText(getContext(),"Enter Title",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(TextUtils.isEmpty(description))
                        {
                            Toast.makeText(getContext(),"Enter Description",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        dialog.dismiss();

                    }
                });



            }
        });
        return view;
    }
    private void uploadData(String title, String description){
       /* pd.setMessage("Publishing post");
        pd.show();
*/
        //for post-image name, post-id,post publish time

        String timestamp = String.valueOf(System.currentTimeMillis());

        //String filePathName = "Posts/" + "post_" + timestamp;

        //Data Base
    }

}
