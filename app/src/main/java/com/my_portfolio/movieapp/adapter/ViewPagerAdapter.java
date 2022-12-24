package com.my_portfolio.movieapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.my_portfolio.movieapp.fragments.intro.IntroFragmentThree;
import com.my_portfolio.movieapp.fragments.intro.IntroFragmentTwo;
import com.my_portfolio.movieapp.fragments.intro.SplashFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private int NUM_OF_PAGE = 3;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SplashFragment();
            case 1:
                return new IntroFragmentTwo();
            case 2:
                return new IntroFragmentThree();
            default:
                return new SplashFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_OF_PAGE;
    }
}
