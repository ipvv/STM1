package com.vsahin.moneycim.ChartList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vsahin.moneycim.R;

import butterknife.ButterKnife;


public class tabchart3  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.tabchart3,container,false );
        ButterKnife.bind(this, rootview);

        return rootview;
    }
}