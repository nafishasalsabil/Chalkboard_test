package com.example.chalkboardnew;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.grpc.internal.JsonUtil;


import static com.example.chalkboardnew.Attendance_activity.clicked_courseTitle;
import static com.example.chalkboardnew.Attendance_activity.clicked_course_section;
import static com.example.chalkboardnew.Attendance_activity.detect1;
import static com.example.chalkboardnew.Attendance_activity.detect2;

import static java.security.AccessController.getContext;

class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.studentinfoViewHolder> {

    Context context;
    List<StudentItems> studentItems1 = new ArrayList<>();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();
    private DocumentReference documentReference;
    CollectionReference collectionReference;
    StudentItems studentItems_object = new StudentItems();
    String ui="";
    String lectureName,title,section;
    List<PerformanceClass> quizitems1= new ArrayList<>();
    List<PerformanceClass> quizitems2= new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public static final String TAG = "check";
    SharedPreferences sharedPreferences1, sharedPreferences2, sharedPreferences3;


    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {

        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public StudentInfoAdapter(Context context, List<StudentItems> studentItems1) {
        this.studentItems1 = studentItems1;
//        this.context = context;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public static class studentinfoViewHolder extends RecyclerView.ViewHolder {
        TextView roll;
        TextView name;
        CardView cardView;
        ImageView info;

        public studentinfoViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            roll = itemView.findViewById(R.id.roll_info);
            name = itemView.findViewById(R.id.name_info);
            cardView = itemView.findViewById(R.id.cardview);
            info = itemView.findViewById(R.id.info_image);
//            pres = itemView.findViewById(R.id.present_status);
//            abs = itemView.findViewById(R.id.absent_status);
//            la = itemView.findViewById(R.id.late_status);



        }
    }

    @NonNull
    @Override
    public studentinfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_info_item, parent, false);
        context = parent.getContext();
        return new studentinfoViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull studentinfoViewHolder holder, int position) {
        holder.roll.setText(Integer.toString(studentItems1.get(position).getId()));
        holder.name.setText(studentItems1.get(position).getName());

        holder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*StudentInfoDialog studentInfoDialog = new StudentInfoDialog();
                StudentInfo studentInfo = (StudentInfo) context;
                studentInfoDialog.show( studentInfo.getSupportFragmentManager(), "studentinfodialog");
*/
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.student_info_bottomsheet);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView t1 = dialog.findViewById(R.id.days);
                TextView t2 = dialog.findViewById(R.id.grades);
                TextView t3 = dialog.findViewById(R.id.cg_info);
           //     dialog.create();
                dialog.show();
                CollectionReference collectionReference =  firestore.collection("users").document(userID)
                        .collection("Courses").document(title)
                        .collection("Batches").document(section)
                        .collection("Class_Performance").document(holder.roll.getText().toString())
                        .collection("Backup");
                collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<PerformanceClass> doc = queryDocumentSnapshots.toObjects(PerformanceClass.class);
                        quizitems1.addAll(doc);
                        for(PerformanceClass performanceClass : doc)
                        {
                            t3.setText(Double.toString(performanceClass.getCgpa()));
                           t2.setText(performanceClass.getGrade());
                            System.out.println(quizitems1.get(position).getCgpa());
                            System.out.println(quizitems1.get(position).getGrade());

                        }

                    }
                });
                CollectionReference collectionReference2 =  firestore.collection("users").document(userID)
                        .collection("Courses").document(title)
                        .collection("Batches").document(section)
                        .collection("Class_Performance");
                collectionReference2.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        List<PerformanceClass> doc = queryDocumentSnapshots.toObjects(PerformanceClass.class);
                        quizitems2.addAll(doc);
                        for(PerformanceClass performanceClass : doc)
                        {
                            System.out.println(quizitems2.get(position).getCount());
                            t1.setText(Integer.toString(quizitems2.get(position).getCount()));

                        }

                    }
                });



            }
        });

    }

  /*  private int getcolor(int position) {
        String status = studentItems.get(position).getStatus();
        if(status.equals("P"))
        {
            return Color.parseColor("#"+ Integer.toHexString(ContextCompat.getColor(context,R.color.present)));
        }
        else if(status.equals("A"))
        {
            return Color.parseColor("#"+ Integer.toHexString(ContextCompat.getColor(context,R.color.absent)));

        }
        return Color.parseColor("#"+ Integer.toHexString(ContextCompat.getColor(context,R.color.normal)));


    }*/

    @Override
    public int getItemCount() {
        return studentItems1.size();
    }
}
