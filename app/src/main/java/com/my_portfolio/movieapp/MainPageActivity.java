package com.my_portfolio.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.StatusBarManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;
import com.my_portfolio.movieapp.fragments.main_page.FavouritesFragment;
import com.my_portfolio.movieapp.fragments.main_page.MainPageFragment;
import com.my_portfolio.movieapp.fragments.main_page.SearchFragment;

public class MainPageActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private SearchFragment searchFragment;
    private FavouritesFragment favouritesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        getSupportFragmentManager().beginTransaction().add(R.id.main_page_container,new MainPageFragment()).commit();

        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_search));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tabLayout.getSelectedTabPosition() == 0) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_page_container, new MainPageFragment()).commit();
                } else if(tabLayout.getSelectedTabPosition() == 1) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_page_container, new SearchFragment()).commit();
                } else if (tabLayout.getSelectedTabPosition() == 2) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_page_container, new FavouritesFragment()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}