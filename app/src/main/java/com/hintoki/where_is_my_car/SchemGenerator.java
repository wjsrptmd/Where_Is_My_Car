package com.hintoki.where_is_my_car;

import android.net.Uri;

public class SchemGenerator {
    public static Uri GetMapScheme(String location, String type) {
        String str = "";
        if(type == "naver") {
            str = String.format("nmap://search?query=%s&appname=com.hintoki.where_is_my_car", location);
        } else if (type == "kakao") {
            str = String.format("kakaomap://search?q=%s&appname=com.hintoki.where_is_my_car", location);
        } else {
            str = String.format("geo:0,0?q=%s&appname=com.hintoki.where_is_my_car", location);
        }
        return Uri.parse(str);
    }

    public static Uri GetMarketScheme(String type) {
        String pakage_name = "";
        if(type == "naver") {
            pakage_name = "com.nhn.android.nmap";
        } else if (type == "kakao") {
            pakage_name = "net.daum.android.map";
        } else {
            pakage_name = "com.google.android.apps.maps";
        }
        return Uri.parse(String.format("market://details?id=%s", pakage_name));
    }
}
