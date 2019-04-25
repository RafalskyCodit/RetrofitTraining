package com.example.retrofitwork.view.fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.retrofitwork.R;
import com.example.retrofitwork.utils.FragmentUtils;
import com.example.retrofitwork.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private MainViewModel model;

    private EditText requestEdit;
    private Spinner imageTypeSpinner;
    private Spinner imageOrderSpinner;
    private Button searchButton;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setListeners();
    }

    private void setListeners() {
        searchButton.setOnClickListener(view -> {
            String request = requestEdit.getText().toString();
            String imageType = imageTypeSpinner.getSelectedItem().toString();
            String imageOrder = imageOrderSpinner.getSelectedItem().toString();

            if (!TextUtils.isEmpty(request)){
                model.getPictures(request, imageType, imageOrder);
                FragmentUtils.showFragment(getActivity().getSupportFragmentManager(), new ImageListFragment());
            }else{
                Toast.makeText(getContext(), R.string.fill_request, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init(View view) {
        requestEdit = view.findViewById(R.id.request_edit);

        imageTypeSpinner = view.findViewById(R.id.type_chooser);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.image_types));
        imageTypeSpinner.setAdapter(typeAdapter);

        imageOrderSpinner = view.findViewById(R.id.order_chooser);
        ArrayAdapter<String> orderAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.image_order));
        imageOrderSpinner.setAdapter(orderAdapter);

        searchButton = view.findViewById(R.id.search_button);
    }
}
