package com.example.android.electracksih;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

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
    Switch onOff;
    graph_frame gframe;

    Firebase mRef;
    public static ArrayList<SensorData> sensorDataList=new ArrayList<>();
int a;
    public Device_Fragment() {
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_device__fragment, container, false);

//        gframe= new graph_frame();
//       // gframe.setArguments(bundle);
//        FragmentManager fm= getActivity().getSupportFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.graph_container, gframe);
//        ft.commit();



        onOff = (Switch) view.findViewById(R.id.onOff);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.device_recycler_view_frag);
        // in content do not change the layout size of the RecyclerView
        //  mRecyclerView.setHasFixedSize(true);
        Bundle bundle = getArguments();
        a = bundle.getInt("id", 0);

        Firebase.setAndroidContext(getContext());

        loadDataFromFirebase();
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this.getActivity());
        mRecyclerView.setAdapter(mAdapter);

        onOff.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String mid;
                int stat;
                boolean switch_checked = onOff.isChecked();
                if (switch_checked) {
                    mid = "lightturnedon";
                    stat = 1;
                } else {
                    mid = "lightturnedoff";
                    stat = 0;
                }


//                String urlString="http://192.168.43.189:5000/lightturnedon?arg1=&arg2=&ar3=";
                String urlString = "http://172.28.25.147:5000";
                switch (a) {
                    case 1: {
                        urlString = urlString + "/" + mid + "?arg1=1&arg2=114&arg3=" + stat;
                    }
                    case 2: {
                        urlString = urlString + "/" + mid + "?arg1=1&arg2=113&arg3=" + stat;

                    }
                    case 3: {
                        urlString = urlString + "/" + mid + "?arg1=1&arg2=112&arg3=" + stat;

                    }
                    case 4: {
                        urlString = urlString + "/" + mid + "?arg1=1&arg2=115&arg3=" + stat;

                    }

                }
                myTask myTask = new myTask(getContext(), urlString, new myTask.onSpecificStateChangeListener() {
                    @Override
                    public void onStateChanged(String string) {
                        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
                    }
                });
                myTask.execute(urlString);
            }
        });

        gframe = new graph_frame();
        // gframe.setArguments(bundle);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.graph_container, gframe);
        ft.commit();
        return view;
    }

    private void loadDataFromFirebase() {
        mRef = new Firebase("https://not-so-awesome-project-45a2e.firebaseio.com/sensors/");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("changed",dataSnapshot.toString());
                sensorDataList.clear();
                for(DataSnapshot oneDataSnapshot:dataSnapshot.getChildren()){
                    DataSnapshot oneDataSnapshot_data=oneDataSnapshot.child("data");
                    String value=oneDataSnapshot_data.getValue(String.class);
                    String values1[]=value.split(",");
                    int sizeOfValues=values1.length;
                    if(sizeOfValues<6){
                        continue;
                    }
                    String value2=values1[sizeOfValues-1];
                    String values2[]=value2.split("\"");

                    DataSnapshot oneDataSnapshot_time=oneDataSnapshot.child("time");
                    Long timeOfReading=oneDataSnapshot_time.getValue(Long.class);
                    Log.d("haha",sizeOfValues+"");
                    SensorData sensorData=new SensorData(values1[1],values1[2],values1[3],values1[4],values1[5],values2[0],"1",timeOfReading);
                    Log.d("data",oneDataSnapshot.toString());
                    Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();
                    sensorDataList.add(sensorData);
                }
                int size=sensorDataList.size();
                gframe.updateValueOfY(Double.parseDouble(sensorDataList.get(size-2).Curr1));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
