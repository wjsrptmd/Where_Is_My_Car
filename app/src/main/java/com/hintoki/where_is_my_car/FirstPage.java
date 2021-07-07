package com.hintoki.where_is_my_car;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstPage#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FirstPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstPage.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstPage newInstance(String param1, String param2) {
        FirstPage fragment = new FirstPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FirstPage() {
        // Required empty public constructor
    }

    private void SetParkingInfo(View view, ParkingInfo parkingInfo) {
        if (parkingInfo != null) {
            TextView car_name = view.findViewById(R.id.text_car_name);
            car_name.setText(parkingInfo.car_name);

            TextView location = view.findViewById(R.id.text_location);
            location.setText(parkingInfo.location);

            TextView date_time = (TextView) view.findViewById(R.id.text_date_time);
            date_time.setText(parkingInfo.date_time);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first_page,container,false);

        // Input Data
        ParkingInfo parkingInfo = ParkingInfoManager.GetLatestParkingInfo(getContext());
        SetParkingInfo(view, parkingInfo);

        Button find_btn = (Button)view.findViewById(R.id.find_button);
        TextView location = (TextView)view.findViewById(R.id.text_location);
        String test = location.getText().toString();
        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString(ARG_PARAM1, location.getText().toString());
//                NavHostFragment.findNavController(FirstPage.this)
//                        .navigate(R.id.move_to_SecondPage, args);

                IntentManager.IntentMapApp(getContext(), location.getText().toString(), "naver");
            }
        });
        Button history_btn = (Button)view.findViewById(R.id.history_button);
        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FirstPage.this)
                        .navigate(R.id.move_to_ThirdPage);
            }
        });

        // Dummy Data
        Button dummy_btn = (Button)view.findViewById(R.id.dummy_data_button);
        dummy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingInfoManager.CreateDummyData(getContext());
            }
        });

        Button clear_btn = (Button)view.findViewById(R.id.clear_data_button);
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkingInfoManager.ClearHistory(getContext());
            }
        });

        return view;
    }
}