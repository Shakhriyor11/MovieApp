package com.my_portfolio.movieapp;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.my_portfolio.movieapp.adapter.ViewPagerAdapter;

public class IntroActivity extends FragmentActivity {

    private ViewPager2 pager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        pager = findViewById(R.id.vpFragmentPlace);
        adapter = new ViewPagerAdapter(this);
        pager.setAdapter(adapter);
    }
}