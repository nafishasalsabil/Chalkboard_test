package com.example.chalkboardnew;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

class QuizMarksRecordAdapter extends RecyclerView.Adapter<QuizMarksRecordAdapter.QuizMarksRecordViewHolder> {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();

    Context context;
   // List<QuizNameClass> quizmakrsitems;
    List<QuizMarksClass> studentItems;
    List<StudentItems> studentItems1;
    String a="",b="";

    private OnItemClickListener onItemClickListener;
    int total_marks;
    int marks_total=0;
    String section = "";
    String title = "";
    String quiz = "";

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }

    public interface OnItemClickListener {

        void onClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public QuizMarksRecordAdapter(Context context,List<QuizMarksClass> studentItems) {
     //   this.quizmakrsitems = quizmakrsitems;
        this.context = context;
        this.studentItems = studentItems;
        setHasStableIds(true);

    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public static class QuizMarksRecordViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView quiz_marks,name,id;
        ImageView add_marks;

        public QuizMarksRecordViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            quiz_marks = itemView.findViewById(R.id.quiz_marks_text);
            id  = itemView.findViewById(R.id.roll_quiz_record);
            name = itemView.findViewById(R.id.name_quiz_record);
            add_marks = itemView.findViewById(R.id.add_marks);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    onItemClickListener.onClick(ClassViewHolder.this.getAdapterPosition());
                   /* Intent intent = new Intent(v.getContext(),QuizMarksRecord.class);
                    //   intent.putExtra("Title",classname.getText());
                    // System.out.println(classname.getText());
                    v.getContext().startActivity(intent);
*/
                }
            });
        }
    }

    @NonNull
    @Override
    public QuizMarksRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_record_item, parent, false);
        context = parent.getContext();

        return new QuizMarksRecordViewHolder(itemView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizMarksRecordViewHolder holder, int position) {
        holder.id.setText(Integer.toString(studentItems.get(position).getId()));
        holder.name.setText(studentItems.get(position).getName());
        holder.quiz_marks.setText(Integer.toString(studentItems.get(position).getMarks()));
      //  String marks = holder.quiz_marks.getText().toString();



        //   holder.quiz_marks.setText(quizmakrsitems.get(position).getQuiz_marks());
      /* CollectionReference collectionReference = firestore.collection("users").document(userID)
                .collection("Courses").document(title).collection("Sections")
                .document(section)
                .collection("Students");
       collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
           @Override
           public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
               for(StudentItems studentItems : studentItems1)
               {
                   holder.id.setText(Integer.toString(studentItems.getId()));
                   holder.name.setText(studentItems.getName());
                   a=Integer.toString(studentItems.getId());
                   b=studentItems.getName();
                   System.out.println(a);
                   System.out.println(b);

               }
           }
       });*/

         System.out.println(studentItems.get(position).getId());
        System.out.println(studentItems.get(position).getName());
        Log.d("checktag",title);
       /* DocumentReference documentReference = firestore.collection("users").document(userID)
                .collection("Courses").document(title).collection("Sections")
                .document(section)
                .collection("Quizes").document(quiz);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                QuizNameClass quizNameClass = documentSnapshot.toObject(QuizNameClass.class);
                assert quizNameClass != null;
                marks_total = quizNameClass.getQuiz_total_marks();
                System.out.println(marks_total);

            }
        });
       */ /*DocumentReference documentReference1 = firestore.collection("users").document(userID)
                .collection("Courses").document(title).collection("Sections")
                .document(section)
                .collection("Quizes").document(quiz)
                .collection("Students").document(Integer.toString(studentItems.get(position).getId()));
        Map<String,Object> user = new HashMap<>();
        user.put("quiz_marks","0");
        user.put("id",Integer.toString(studentItems.get(position).getId()));
        user.put("name",studentItems.get(position).getName());
*/
        DocumentReference documentReference = firestore.collection("users").document(userID)
                .collection("Courses").document(title).collection("Sections")
                .document(section)
                .collection("Quizes").document(quiz);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                QuizNameClass quizNameClass = documentSnapshot.toObject(QuizNameClass.class);
                assert quizNameClass != null;
                marks_total =  quizNameClass.getQuiz_total_marks();
                System.out.println(marks_total);

            }
            });
        holder.add_marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.quiz_marks_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                EditText marks = dialog.findViewById(R.id.quiz_marks_edittext);
                Button ok = dialog.findViewById(R.id.done_quiz_marks_add);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int marks_quiz =Integer.parseInt( marks.getText().toString());
                        System.out.println(marks_total);
                        if(marks_quiz>marks_total)
                        {
                          //  System.out.println("dfghjkl");
                         //   marks.requestFocus();
                            marks.setError("Invalid marks");
                        }
                        else
                        {
                            System.out.println(marks_quiz);
                            holder.quiz_marks.setText(Integer.toString(marks_quiz));
                          //  holder.add_marks.setImageResource(R.drawable.ic_pencil_edit);

                            DocumentReference documentReference =  firestore.collection("users").document(userID)
                                    .collection("Courses").document(title).collection("Sections")
                                    .document(section)
                                    .collection("Quizes").document(quiz).collection("Students")
                                    .document(Integer.toString(studentItems.get(position).getId()));

                            Map<String,Object> user = new HashMap<>();
                            user.put("marks",marks_quiz);
                            documentReference.set(user, SetOptions.merge());
                          /*  DocumentReference documentReference1 = firestore.collection("users").document(userID)
                                    .collection("Courses").document(title_course).collection("Sections")
                                    .document(sec)
                                    .collection("Class_Performance")
                                    .document(Integer.toString(items.getId()))
                                    .collection("quizes").document(q_n);
*/
                            DocumentReference documentReference1 =  firestore.collection("users").document(userID)
                                    .collection("Courses").document(title).collection("Sections")
                                    .document(section)
                                    .collection("Class_Performance")
                                    .document(Integer.toString(studentItems.get(position).getId()))
                                    .collection("quizes").document(quiz);

                            Map<String,Object> user1 = new HashMap<>();
                            user1.put("marks",marks_quiz);
                            documentReference1.update(user1);

                            dialog.dismiss();
                        }

                    }

                });
                dialog.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return studentItems.size();
    }
}
