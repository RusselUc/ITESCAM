package com.example.itescam.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itescam.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation animationUp = AnimationUtils.loadAnimation(this, R.anim.up);
        Animation animationDown = AnimationUtils.loadAnimation(this, R.anim.down);

        tv = (TextView)findViewById(R.id.tv);
        logo = (ImageView)findViewById(R.id.logo);

        logo.setAnimation(animationUp);
        tv.setAnimation(animationDown);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            //Pair[] pair = new Pair[1];
            //pair[0] = new Pair<View, String>(logo, "logoImage");
            //pair[1] = new Pair<View, String>(tv, "textTrans");
            //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pair);
                //startActivity(intent, options.toBundle());
                //finish();
            //} else {
                startActivity(intent);
                finish();
            //}
        }, 4000);
    }
}