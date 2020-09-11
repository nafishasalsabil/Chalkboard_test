package com.example.chalkboardnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity_HomeTutor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__home_tutor);

        BottomNavigationView bottomNavHomeTutor = findViewById(R.id.bottomNavigationHomeTutor);
        bottomNavHomeTutor.setOnNavigationItemSelectedListener(navListenerHomeTutor);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_hometutor,new HomeFragment_HomeTutor()).commit();
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
}