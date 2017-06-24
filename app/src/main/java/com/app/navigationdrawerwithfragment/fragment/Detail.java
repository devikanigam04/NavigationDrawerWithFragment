package com.app.navigationdrawerwithfragment.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.navigationdrawerwithfragment.R;
import com.app.navigationdrawerwithfragment.activity.MainActivity;

public class Detail extends Fragment {

    public Detail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).showArrow(0, 1);
        ((MainActivity) getActivity()).showBackArrow();
    }
}