package com.example.itescam.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.itescam.R;

public class dashboard extends AppCompatActivity {

    ImageView logo;
    LinearLayout menu;
    LinearLayout layoutHorario, btnProfile;

    LinearLayout syllabus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //logo = findViewById(R.id.logoDashboard);
        menu = findViewById(R.id.llmenu);

<<<<<<< HEAD
        layoutHorario = findViewById(R.id.layoutHorario);
        btnProfile = findViewById(R.id.btnProfile);

        layoutHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horario();
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfil();
            }
        });

        //animation_view(logo);
=======
        syllabus = findViewById(R.id.btnSyllabus);

        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSyllabus();
            }
        });

        animation_view(logo);
>>>>>>> Miguel
        animation_view(menu);
    }

    public void goToSyllabus() {
        Intent i = new Intent(this, ControlEscolar.class);
        startActivity(i);
    }

    public void animation_view(View view){
        view.animate().alpha(1).setDuration(1500).translationY(0);
    }

    public void horario(){
        Intent intent = new Intent(dashboard.this, HorarioActivity.class);
        startActivity(intent);
    }

    public void perfil(){
        Intent intent = new Intent(dashboard.this, ProfileActivity.class);
        startActivity(intent);
    }
}