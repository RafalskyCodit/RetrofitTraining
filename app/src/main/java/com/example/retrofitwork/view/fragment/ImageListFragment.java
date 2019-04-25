package com.example.retrofitwork.view.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitwork.R;
import com.example.retrofitwork.adapter.ImageAdapter;
import com.example.retrofitwork.utils.FragmentUtils;
import com.example.retrofitwork.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageListFragment extends Fragment {
    private MainViewModel model;
    private ImageAdapter adapter;

    private RecyclerView imageList;
    private FloatingActionButton searchFab;

    public ImageListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ImageAdapter(getActivity().getApplicationContext());
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        observe();
        setListeners();
    }

    private void setListeners() {
        searchFab.setOnClickListener(view -> {
            FragmentUtils.showFragment(getActivity().getSupportFragmentManager(), new SearchFragment());
        });
    }

    private void observe() {
        model.getHits().observe(this, hits -> adapter.updateHits(hits));
    }

    private void init(View view) {
        imageList = view.findViewById(R.id.image_list);
        imageList.setLayoutManager(new LinearLayoutManager(getContext()));
        imageList.setAdapter(adapter);

        searchFab = view.findViewById(R.id.search_fab);

    }
}
