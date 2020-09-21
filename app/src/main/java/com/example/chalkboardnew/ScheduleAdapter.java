package com.example.chalkboardnew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.chalkboardnew.Attendance_activity.clicked_courseTitle;
import static com.example.chalkboardnew.Attendance_activity.clicked_course_section;
import static com.example.chalkboardnew.Attendance_activity.detect1;
import static com.example.chalkboardnew.Attendance_activity.detect2;
import static com.example.chalkboardnew.StudentList.Lecture_s;
import static java.security.AccessController.getContext;

class ScheduleAdapter extends BaseAdapter {

    Context context;
    private List<ScheduleClass> scheduleClasses = new ArrayList<>();
    private DocumentReference documentReference;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();


    public static final String TAG = "check";

    public ScheduleAdapter(Context context, List<ScheduleClass> scheduleClasses) {
        this.scheduleClasses = scheduleClasses;
        this.context = context;
    }




    class studentViewHolder extends RecyclerView.ViewHolder {


        public studentViewHolder(@NonNull View itemView) {
            super(itemView);


            int pos = getAdapterPosition();


        }


    }

    @Override
    public int getCount() {
        return scheduleClasses.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, null);
      TextView  course = convertView.findViewById(R.id.course_schedule);
       TextView section = convertView.findViewById(R.id.section_schedule);
       TextView room = convertView.findViewById(R.id.room_no_schedule_s);
        TextView time = convertView.findViewById(R.id.time_class);
        View v = convertView.findViewById(R.id.view);

        course.setText(scheduleClasses.get(position).getCourse_name());
        section.setText(scheduleClasses.get(position).getSection());
        room.setText(scheduleClasses.get(position).getRoom());
        time.setText(scheduleClasses.get(position).getTime());



        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


}
