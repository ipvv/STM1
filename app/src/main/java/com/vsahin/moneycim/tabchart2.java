package com.vsahin.moneycim;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by saedc on 24/01/18.
 */

public class tabchart2  extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.tabchart2,container,false );
        ButterKnife.bind(this, rootview);

        return rootview;
    }
}
