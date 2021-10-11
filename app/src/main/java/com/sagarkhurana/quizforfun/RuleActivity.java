package com.sagarkhurana.quizforfun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.sagarkhurana.quizforfun.adapter.RulesViewPagerAdapter;

public class RuleActivity extends AppCompatActivity {

    public static ViewPager viewPager;
    RulesViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        viewPager=findViewById(R.id.viewpager);
        adapter=new RulesViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

    }
}