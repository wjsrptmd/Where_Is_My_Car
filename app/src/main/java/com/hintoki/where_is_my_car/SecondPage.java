package com.hintoki.where_is_my_car;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondPage extends Fragment {
    private AdapterView.OnItemClickListener listner;

    public static SecondPage newInstance(String param1, String param2) {
        SecondPage fragment = new SecondPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SecondPage() {
        // Required empty public constructor
    }

    private Map GetMap() {
        FragmentManager fm = getChildFragmentManager();
        Map map = (Map)fm.findFragmentById(R.id.second_map);
        return map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listner = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ParkingInfo parkingInfo = (ParkingInfo)parent.getItemAtPosition(position);
                Map map = GetMap();
                map.SetLocationMap(parkingInfo.location);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second_page,container,false);

        ArrayList<ParkingInfo> list = ParkingInfoManager.GetHistory(getContext());
        if(list.size() > 0) {
            TextView empty_text = (TextView)view.findViewById(R.id.empty_text);
            empty_text.setText("");

            ListView listView = (ListView) view.findViewById(R.id.list_history);
            ParkingListViewAdapter adapter =  new ParkingListViewAdapter();
            adapter.SetList(list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(listner);

            String loc = list.get(0).location;
            // Init Map
            Map map = GetMap();
            map.SetLocParam(loc);
        }

        return view;
    }
}