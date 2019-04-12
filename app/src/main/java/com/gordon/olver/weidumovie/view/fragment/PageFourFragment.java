package com.gordon.olver.weidumovie.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gordon.olver.weidumovie.R;
import com.gordon.olver.weidumovie.view.activity.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageFourFragment extends Fragment {


    public PageFourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_page_four, container, false);

        return  inflate;
    }

}
