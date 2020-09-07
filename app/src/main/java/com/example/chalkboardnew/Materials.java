package com.example.chalkboardnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class Materials extends AppCompatActivity {

    Toolbar toolbar_materials;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        toolbar_materials = findViewById(R.id.toolbar_materials);
        setSupportActionBar(toolbar_materials);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_materials.setNavigationIcon(R.drawable.ic_back);
        getSupportActionBar().setElevation(0);
        toolbar_materials.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
