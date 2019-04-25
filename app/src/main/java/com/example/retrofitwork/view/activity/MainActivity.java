package com.example.retrofitwork.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.retrofitwork.R;
import com.example.retrofitwork.utils.FragmentUtils;
import com.example.retrofitwork.view.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentUtils.showFragment(getSupportFragmentManager(), new SearchFragment());
    }
}
