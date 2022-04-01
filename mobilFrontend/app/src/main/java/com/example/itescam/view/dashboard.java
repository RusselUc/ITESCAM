package com.example.itescam.view;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.itescam.R;

public class dashboard extends AppCompatActivity {

    ImageView logo;
    LinearLayout menu;
    LinearLayout layoutHorario, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //logo = findViewById(R.id.logoDashboard);
        menu = findViewById(R.id.llmenu);

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
        animation_view(menu);
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