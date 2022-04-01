package com.example.itescam.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.itescam.R;
import com.example.itescam.view.fragment.FridayFragment;
import com.example.itescam.view.fragment.MondayFragment;
import com.example.itescam.view.fragment.ThuersdayFragment;
import com.example.itescam.view.fragment.TuesdayFragment;
import com.example.itescam.view.fragment.VPAdapter;
import com.example.itescam.view.fragment.WednesdayFragment;
import com.google.android.material.tabs.TabLayout;

public class HorarioActivity extends AppCompatActivity {

    //TextView topicM, topicTu, topicW, topicTh, topicF;
    //LinearLayout layoutM, layoutTu, layoutW, layoutTh, layoutF;

    TabLayout tab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewP);

        tab.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new MondayFragment(), "");
        vpAdapter.addFragment(new TuesdayFragment(), "");
        vpAdapter.addFragment(new WednesdayFragment(), "");
        vpAdapter.addFragment(new ThuersdayFragment(), "");
        vpAdapter.addFragment(new FridayFragment(), "");

        viewPager.setAdapter(vpAdapter);

        /*topicM = findViewById(R.id.topicM);
        topicTu = findViewById(R.id.topicTu);
        topicW = findViewById(R.id.topicW);
        topicTh = findViewById(R.id.topicTh);
        topicF = findViewById(R.id.topicF);

        layoutM = findViewById(R.id.layoutMonday);
        layoutTu = findViewById(R.id.layoutTuesday);
        layoutW = findViewById(R.id.layoutWednesday);
        layoutTh = findViewById(R.id.layoutThuersday);
        layoutF = findViewById(R.id.layoutFriday);

        layoutM.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        layoutTu.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        layoutW.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        layoutTh.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        layoutF.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);*/
    }

    /*public  void expandM(View view){
        int v = (topicM.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layoutM, new AutoTransition());
        topicM.setVisibility(v);
    }

    public  void expandTu(View view){
        int v = (topicTu.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layoutTu, new AutoTransition());
        topicTu.setVisibility(v);
    }

    public  void expandW(View view){
        int v = (topicW.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layoutW, new AutoTransition());
        topicW.setVisibility(v);
    }

    public  void expandTh(View view){
        int v = (topicTh.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layoutTh, new AutoTransition());
        topicTh.setVisibility(v);
    }

    public  void expandF(View view){
        int v = (topicF.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layoutF, new AutoTransition());
        topicF.setVisibility(v);
    }*/
}