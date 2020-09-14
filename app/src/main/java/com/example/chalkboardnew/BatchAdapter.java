package com.example.chalkboardnew;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

class BatchAdapter extends RecyclerView.Adapter<BatchAdapter.BatchViewHolder> {

    Context context;
    List<BatchClass> batch;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();
    DocumentReference documentReference;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BatchAdapter(Context context, List<BatchClass> batch) {
        this.batch = batch;
        this.context = context;


    }


    public static class BatchViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView batch_title,batch_days,batch_time;


        public BatchViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            batch_title = itemView.findViewById(R.id.batchname);
            batch_days = itemView.findViewById(R.id.batch_days);
            batch_time = itemView.findViewById(R.id.batch_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),Inside_class_home_tutor.class);
                    intent.putExtra("section",batch_title.getText());
                    v.getContext().startActivity(intent);

                }
            });
        }
    }

    @NonNull
    @Override
    public BatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.batch_item, parent, false);
        return new BatchViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchViewHolder holder, int position) {
        holder.batch_title.setText(batch.get(position).getBatchName());
        holder.batch_days.setText(batch.get(position).getBatchDays());
        holder.batch_time.setText(batch.get(position).getBatchTime());



    }


    @Override
    public int getItemCount() {
        return batch.size();
    }
}
