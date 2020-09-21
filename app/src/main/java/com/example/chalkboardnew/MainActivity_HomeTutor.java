package com.example.chalkboardnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity_HomeTutor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
     Button signout;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    private NavController navController;
    BottomNavigationView bottomNavigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageView profilepic_tutor;
    public TextView username_tutor;
     public String u = "";
    private MenuItem mActiveBottomNavigationViewMenuItem;
    String courseTitle="";
    Bitmap bitmap;
    Toolbar toolbar_features_hometutor;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String userID = firebaseAuth.getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__home_tutor);
        drawerLayout = findViewById(R.id.dlayout_hometutor);
        navigationView = findViewById(R.id.navigationView_hometutor);
toolbar_features_hometutor = findViewById(R.id.toolbar_features_hometutor);
        username_tutor = navigationView.getHeaderView(0).findViewById(R.id.username_tutor);
        profilepic_tutor = navigationView.getHeaderView(0).findViewById(R.id.profilepic_tutor);
        DocumentReference documentReference = firestore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder fields = new StringBuilder("");
                    u = fields.append(doc.get("username")).toString();
                    //  fields.append("\nEmail: ").append(doc.get("email"));
                    //   fields.append("\nPhone: ").append(doc.get("phone"));
                    username_tutor.setText(u);
                    if (u.startsWith("a") || u.startsWith("A")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.a_un);
                        profilepic_tutor.setImageResource(R.drawable.a_un);

                    }
                    if (u.startsWith("b") || u.startsWith("B")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.b_letter);
                        profilepic_tutor.setImageResource(R.drawable.b_letter);

                    }
                    if (u.startsWith("c") || u.startsWith("C")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.c);
                        profilepic_tutor.setImageResource(R.drawable.c);

                    }
                    if (u.startsWith("d") || u.startsWith("D")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.d);
                        profilepic_tutor.setImageResource(R.drawable.d);

                    }
                    if (u.startsWith("e") || u.startsWith("E")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.e);
                        profilepic_tutor.setImageResource(R.drawable.e);

                    }
                    if (u.startsWith("f") || u.startsWith("F")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.f);
                        profilepic_tutor.setImageResource(R.drawable.f);

                    }
                    if (u.startsWith("g") || u.startsWith("G")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.g);
                        profilepic_tutor.setImageResource(R.drawable.g);

                    }
                    if (u.startsWith("h") || u.startsWith("H")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.h);
                        profilepic_tutor.setImageResource(R.drawable.h);

                    }
                    if (u.startsWith("i") || u.startsWith("I")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.i);
                        profilepic_tutor.setImageResource(R.drawable.i);

                    }
                    if (u.startsWith("j") || u.startsWith("J")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.j);
                        profilepic_tutor.setImageResource(R.drawable.j);

                    }
                    if (u.startsWith("k") || u.startsWith("K")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.k);
                        profilepic_tutor.setImageResource(R.drawable.k);

                    }
                    if (u.startsWith("l") || u.startsWith("L")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.l_un);
                        profilepic_tutor.setImageResource(R.drawable.l_un);

                    }
                    if (u.startsWith("m") || u.startsWith("M")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.m);
                        profilepic_tutor.setImageResource(R.drawable.m);

                    }
                    if (u.startsWith("n") || u.startsWith("N")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.ic_n);
                        profilepic_tutor.setImageResource(R.drawable.ic_n);

                    }
                    if (u.startsWith("o") || u.startsWith("O")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.o);
                        profilepic_tutor.setImageResource(R.drawable.o);

                    }
                    if (u.startsWith("p") || u.startsWith("P")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.p_un);
                        profilepic_tutor.setImageResource(R.drawable.p_un);

                    }
                    if (u.startsWith("q") || u.startsWith("Q")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.q);
                        profilepic_tutor.setImageResource(R.drawable.q);

                    }
                    if (u.startsWith("r") || u.startsWith("R")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.r);
                        profilepic_tutor.setImageResource(R.drawable.r);

                    }
                    if (u.startsWith("s") || u.startsWith("S")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.s);
                        profilepic_tutor.setImageResource(R.drawable.s);

                    }
                    if (u.startsWith("t") || u.startsWith("T")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.t);
                        profilepic_tutor.setImageResource(R.drawable.t);

                    }
                    if (u.startsWith("u") || u.startsWith("U")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.u);
                        profilepic_tutor.setImageResource(R.drawable.u);

                    }
                    if (u.startsWith("v") || u.startsWith("V")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.v);
                        profilepic_tutor.setImageResource(R.drawable.v);

                    }
                    if (u.startsWith("w") || u.startsWith("W")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.w);
                        profilepic_tutor.setImageResource(R.drawable.w);

                    }  if (u.startsWith("x") || u.startsWith("X")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.x);
                        profilepic_tutor.setImageResource(R.drawable.x);

                    }
                    if (u.startsWith("y") || u.startsWith("Y")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.y);
                        profilepic_tutor.setImageResource(R.drawable.y);

                    }
                    if (u.startsWith("z") || u.startsWith("Z")) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.z);
                        profilepic_tutor.setImageResource(R.drawable.z);

                    }
                    if (u.startsWith("0") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no0);
                        profilepic_tutor.setImageResource(R.drawable.no0);

                    }
                    if (u.startsWith("1") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no1);
                        profilepic_tutor.setImageResource(R.drawable.no1);

                    }
                    if (u.startsWith("2") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no2);
                        profilepic_tutor.setImageResource(R.drawable.no2);

                    }
                    if (u.startsWith("3") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no3);
                        profilepic_tutor.setImageResource(R.drawable.no3);

                    }
                    if (u.startsWith("4") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no4);
                        profilepic_tutor.setImageResource(R.drawable.no4);

                    }
                    if (u.startsWith("5") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no5);
                        profilepic_tutor.setImageResource(R.drawable.no5);

                    }
                    if (u.startsWith("6") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no6);
                        profilepic_tutor.setImageResource(R.drawable.no6);

                    }
                    if (u.startsWith("8") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no8);
                        profilepic_tutor.setImageResource(R.drawable.no8);

                    }
                    if (u.startsWith("7") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no7);
                        profilepic_tutor.setImageResource(R.drawable.no7);

                    }
                    if (u.startsWith("9") ) {
                        toolbar_features_hometutor.setNavigationIcon(R.drawable.no9);
                        profilepic_tutor.setImageResource(R.drawable.no9);

                    }

                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
        profilepic_tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserProfileHomeTutor.class);
                intent.putExtra("username",u);
                startActivity(intent);
            }
        });

        setSupportActionBar(toolbar_features_hometutor);
        //  getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EFF3FB")));
        getSupportActionBar().setElevation(0);

        BottomNavigationView bottomNavHomeTutor = findViewById(R.id.bottomNavigationHomeTutor);
        bottomNavHomeTutor.setOnNavigationItemSelectedListener(navListenerHomeTutor);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_hometutor,new HomeFragment_HomeTutor()).commit();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar_features_hometutor, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
//        toolbar.setNavigationIcon(R.drawable.ic_person_outline_black_24dp);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });

        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_profile);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListenerHomeTutor =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_home_homeTutor:
                            selectedFragment = new HomeFragment_HomeTutor();
                            break;
                        case R.id.nav_classes_homeTutor:
                            selectedFragment = new ClassesFragment_HomeTutor();
                            break;
                        case R.id.nav_schedule_homeTutor:
                            selectedFragment = new ScheduleFragment_HomeTutor();
                            break;
                        case R.id.nav_chats_homeTutor:
                            selectedFragment = new ChatsFragment_HomeTutor();
                            break;
                        case R.id.nav_notifications_homeTutor:
                            selectedFragment = new NotificationFragment_HomeTutor();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_hometutor,selectedFragment).commit();
                    return true;
                }

            };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(MainActivity_HomeTutor.this, Main2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
               /* Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                if (status.isSuccess()) {
                                    //gotoMainActivity();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Session not close", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
*/

                break;
        }
        switch (item.getItemId()) {
            case R.id.update_profile:
                startActivity(new Intent(getApplicationContext(), Update_profile_homeTutor.class));
                break;
        }
        return true;
    }
}