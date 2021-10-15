package com.sagarkhurana.quizforfun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sagarkhurana.quizforfun.R;

import pl.droidsonroids.gif.GifImageView;

public class RulesViewPagerAdapter extends PagerAdapter {

    Context ctx;

    public RulesViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_rules,container,false);

        GifImageView logo = view.findViewById(R.id.dancer);

        TextView title = view.findViewById(R.id.textView31);

        switch (position)
        {
            case 0:
                logo.setImageResource(R.drawable.question1);
                title.setText(R.string.rules1_description);
                break;
            case 1:
                logo.setImageResource(R.drawable.correct);
                title.setText(R.string.rules2_description);
                break;
            case 2:
                logo.setImageResource(R.drawable.incorrect);
                title.setText(R.string.rules3_description);
                break;
            case 3:
                logo.setImageResource(R.drawable.noted);
                title.setText(R.string.rules4_description);
                break;
        }
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
