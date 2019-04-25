package com.example.retrofitwork.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.retrofitwork.R;

public class FragmentUtils {
    public static void showFragment(FragmentManager fm, Fragment fragment){
        fm.beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
