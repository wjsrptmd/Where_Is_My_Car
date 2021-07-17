package com.hintoki.where_is_my_car;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstPage extends Fragment  {
    private View view;

    public static FirstPage newInstance() {
        FirstPage fragment = new FirstPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FirstPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_page, container, false);

        // Input Data
        ParkingInfo parkingInfo = ParkingInfoManager.GetLatestParkingInfo(getContext());
        if (parkingInfo != null) {
            TextView car_name = view.findViewById(R.id.text_car_name);
            car_name.setText(parkingInfo.car_name);

            TextView location = view.findViewById(R.id.text_location);
            location.setText(parkingInfo.location);

            TextView date_time = (TextView) view.findViewById(R.id.text_date_time);
            date_time.setText(parkingInfo.date_time);
        }

        TextView tv_loc = view.findViewById(R.id.text_location);
        String location = tv_loc.getText().toString();

        // Init Map
        FragmentManager fm = getChildFragmentManager();
        Map map = (Map)fm.findFragmentById(R.id.first_map);
        map.SetLocParam(location);

//        IntentManager.IntentMapApp(getContext(), location.getText().toString(), "kakao");
        return view;
    }
}