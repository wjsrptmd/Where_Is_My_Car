package com.hintoki.where_is_my_car;

import android.net.Uri;

public class SchemGenerator {
    public static Uri GetMapScheme(String location, String type) {
        String str = "";
        if(type == "naver") {
            str = String.format("nmap://search?query=%s&appname=com.hintoki.where_is_my_car", location);
        } else if (type == "kakao") {
            // TODO : kakao
        } else {
            // TODO : google
        }
        return Uri.parse(str);
    }

    public static Uri GetMarketScheme(String type) {
        String str = "";
        if(type == "naver") {
            str = "market://details?id=com.nhn.android.nmap";
        } else if (type == "kakao") {
            // TODO : kakao
        } else {
            // TODO : google
        }
        return Uri.parse(str);
    }
}
