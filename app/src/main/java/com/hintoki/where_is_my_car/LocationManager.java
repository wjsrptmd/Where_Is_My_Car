package com.hintoki.where_is_my_car;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.naver.maps.geometry.LatLng;

import java.util.List;

public class LocationManager {
    public static LatLng GetLatLng(Context ctx, String location) {
        Geocoder geocoder = new Geocoder(ctx);
        LatLng ret = null;
        try {
            List<Address> loc_info = geocoder.getFromLocationName(location, 1);
            if(loc_info.size() == 1) {
                Address add = loc_info.get(0);
                ret = new LatLng(add.getLatitude(), add.getLongitude());
            }
        } catch (Exception e) {
            Log.e("LocationManager", "GetLatLng : " + e.toString());
        }

        return ret;
    }
}
