package com.example.itescam.view;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.itescam.R;

public class dashboard extends AppCompatActivity {

    ImageView logo;
    LinearLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        logo = findViewById(R.id.logoDashboard);
        menu = findViewById(R.id.llmenu);

        animation_view(logo);
        animation_view(menu);
    }

    public void animation_view(View view){
        view.animate().alpha(1).setDuration(1500).translationY(0);
    }
}