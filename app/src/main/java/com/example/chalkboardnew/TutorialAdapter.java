package com.example.chalkboardnew;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.TutorialViewHolder> {

    Context context;
    List<TutorialsClass> tutorialsClassList;
       private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TutorialAdapter(Context context, List<TutorialsClass> tutorialsClassList) {
        this.tutorialsClassList = tutorialsClassList;
        this.context = context;


    }


    public static class TutorialViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView link;


        public TutorialViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            link = itemView.findViewById(R.id.link);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),InsideClassActivity.class);
                    intent.putExtra("section",section_name.getText());
                    intent.putExtra("Title",Sections_Inside_Courses.title);
                    v.getContext().startActivity(intent);

                }
            });
*/        }
    }

    @NonNull
    @Override
    public TutorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutorials_item, parent, false);
        return new TutorialViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorialViewHolder holder, int position) {
        holder.link.setText(tutorialsClassList.get(position).getLink());


    }


    @Override
    public int getItemCount() {
        return tutorialsClassList.size();
    }
}
