package com.sagarkhurana.quizforfun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.sagarkhurana.quizforfun.adapter.RulesViewPagerAdapter;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

public class RuleActivity extends AppCompatActivity {

    public static ViewPager viewPager;
    RulesViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        SpringDotsIndicator springDotsIndicator = findViewById(R.id.spring_dots_indicator);
        ImageView back= findViewById(R.id.imageRule);

        //Event onClick for back button
        back.setOnClickListener(v -> finish());

        //Init dotIndicator and PagerAdapter
        viewPager=findViewById(R.id.viewpager);
        adapter=new RulesViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        springDotsIndicator.setViewPager(viewPager);
    }
}