package com.hintoki.where_is_my_car;

import android.util.Log;

public class ParkingInfo {
    public String date_time = "";
    public String car_name = "";
    public String location = "";

    ParkingInfo(String date_time, String car_name, String location) {
        this.date_time = date_time;
        this.car_name = car_name;
        this.location =location;
    }

    ParkingInfo(String[] info) {
        if(info.length == 3) {
            this.date_time = info[0];
            this.car_name = info[1];
            this.location =info[2];
        } else {
            Log.e("ParkingInfo", "incorrect arguments count");
        }
    }
}
