package com.hintoki.where_is_my_car;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.navigation.fragment.NavHostFragment;

import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMapSdk;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondPage#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class SecondPage extends Fragment  {
    public static SecondPage newInstance(String param1, String param2) {
        SecondPage fragment = new SecondPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SecondPage() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second_page,container,false);
        MapView mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        return view;
    }
}