package com.my_portfolio.movieapp.fragments.intro;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.my_portfolio.movieapp.LoginActivity;
import com.my_portfolio.movieapp.R;


public class IntroFragmentTwo extends Fragment {

    private ImageView skipBtn;
    private AppCompatButton btnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro_two, container, false);

        skipBtn = view.findViewById(R.id.skipBtn);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnNext = view.findViewById(R.id.fragment_two_nextBtn);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntroFragmentThree fragmentThree = new IntroFragmentThree();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.vpFragmentPlace,fragmentThree);
                ft.commit();
            }
        });

        return view;
    }
}