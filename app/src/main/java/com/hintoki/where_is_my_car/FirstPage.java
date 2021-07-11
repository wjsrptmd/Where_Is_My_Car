package com.hintoki.where_is_my_car;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;


public class FirstPage extends Fragment implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private View view;
    private Marker marker;

    public static FirstPage newInstance(String param1, String param2) {
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

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        locationSource =
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        marker = new Marker();
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

//        IntentManager.IntentMapApp(getContext(), location.getText().toString(), "kakao");
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);

        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setCompassEnabled(true);
        uiSettings.setScaleBarEnabled(true);
        uiSettings.setZoomControlEnabled(true);
        uiSettings.setLocationButtonEnabled(true);

        TextView tv_loc = view.findViewById(R.id.text_location);
        String location = tv_loc.getText().toString();
        LatLng latLng = LocationManager.GetLatLng(getContext(), location);

        marker.setMap(null);
        marker.setPosition(latLng);
        marker.setMap(naverMap);

        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(latLng);
        naverMap.moveCamera(cameraUpdate);
    }
}