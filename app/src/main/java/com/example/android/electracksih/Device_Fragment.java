package com.example.android.electracksih;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shruti on 27-03-2018.
 */

public class Device_Fragment extends android.support.v4.app.Fragment {
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView2;
    RecyclerView.Adapter mAdapter;
    RecyclerView.Adapter mAdapter2;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.LayoutManager mLayoutManager2;


    public  Device_Fragment(){};
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_device__fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.device_recycler_view_frag);
        // in content do not change the layout size of the RecyclerView
      //  mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this.getActivity());
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }
}
