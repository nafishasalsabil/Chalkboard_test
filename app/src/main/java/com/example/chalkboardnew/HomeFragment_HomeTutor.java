package com.example.chalkboardnew;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment_HomeTutor extends Fragment {
    private TextView dayname,datetoday;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();
    List<ScheduleClass> scheduleClassList = new ArrayList<>();
    List<ScheduleHomeTutorClass> scheduleClassList1 = new ArrayList<>();

    RecyclerView home_recyleview;
    String class_name = "";
    HomeHomeTutorAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_hometutor,container, false);
        home_recyleview = view.findViewById(R.id.home_recycler_view_ht);
        home_recyleview.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        home_recyleview.setLayoutManager(layoutManager);
        dayname = view.findViewById(R.id.day_name_ht);
        datetoday = view.findViewById(R.id.date_today_ht);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);
        Log.d("checkDate",dayOfTheWeek);
        dayname.setText(dayOfTheWeek);
       /* Date currentTime = Calendar.getInstance().getTime();
        Log.d("checkDate",currentTime.toString());
       */ calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        datetoday.setText(date);
        Log.d("checkDate",date);
        CollectionReference collectionReference = firestore.collection("users").document(userID)
                .collection("Schedules");
        collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                //  List<ScheduleClass> doc = queryDocumentSnapshots.toObjects(ScheduleClass.class);
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    ScheduleHomeTutorClass scheduleClass = documentSnapshot.toObject(ScheduleHomeTutorClass.class);
//                    System.out.println(scheduleClass.getDay());
                    if(scheduleClass.getDay().equals(dayOfTheWeek))
                    {
                        // List<ScheduleClass> doc = queryDocumentSnapshots.toObjects(ScheduleClass.class);
                        scheduleClassList1.add(scheduleClass);
                        homeAdapter = new HomeHomeTutorAdapter(getContext(),scheduleClassList1);

                        System.out.println("HIII");
                        System.out.println(scheduleClassList1);
                        Log.d("checkDate",scheduleClassList1.toString());

                        home_recyleview.setVisibility(View.VISIBLE);



                    }
                }
                homeAdapter = new HomeHomeTutorAdapter(getContext(),scheduleClassList1);

                home_recyleview.setAdapter(homeAdapter);
                homeAdapter.notifyDataSetChanged();

            }
        });





        return view;
    }
}
