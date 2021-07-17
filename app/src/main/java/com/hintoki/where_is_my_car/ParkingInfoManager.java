package com.hintoki.where_is_my_car;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ParkingInfoManager {
    private static String history_file = "history";

    private static File OpenFile(Context ctx, String file_name) throws IOException {
        File file = new File(ctx.getFilesDir(), file_name);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public static ParkingInfo GetLatestParkingInfo(Context ctx) {
        ArrayList<ParkingInfo> parkingInfos = GetHistory(ctx);
        if(parkingInfos.size() != 0) {
            return parkingInfos.get(0);
        }
        return null;
    }

    public static ArrayList<ParkingInfo> GetHistory(Context ctx) {
        ArrayList<ParkingInfo> list = new ArrayList<>();

        try {
            File file = OpenFile(ctx, history_file);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while ((line = br.readLine()) != null) {
                String[] info = line.split("/");
                list.add(new ParkingInfo(info));
            }

            Collections.reverse(list);
            br.close();
        } catch (IOException e) {
            Log.e("FileUtil", "GetHistory(Context ctx) " + e.toString());
        }

        return list;
    }

    public static boolean CreateDummyData(Context ctx) {
        boolean ret = false;
        try {
            File file = OpenFile(ctx, history_file);
            FileWriter fw = new FileWriter(file, true);

            String date_time = "2021-07-04 10:11:22";
            String car_name = "Genesis";
            int i = 0;
            fw.write(date_time +"/" + car_name + "_" + Integer.toString(i++) + "/" + "서울특별시 송파구 잠실동 올림픽로 240" + "\n");
            fw.write(date_time +"/" + car_name + "_" + Integer.toString(i++) + "/" + "수원시 매산동" + "\n");
            fw.write(date_time +"/" + car_name + "_" + Integer.toString(i++) + "/" + "경기도 용인시 처인구 포곡읍 에버랜드로 199 KR" + "\n");
            fw.write(date_time +"/" + car_name + "_" + Integer.toString(i++) + "/" + "서울특별시 송파구 가락동 송이로15길 13" + "\n");
            fw.write(date_time +"/" + car_name + "_" + Integer.toString(i++) + "/" + "경기도 안양시 만안구 박달동 박달로418번길 70" + "\n");

            fw.close();
            ret = true;

        } catch (IOException e) {
            Log.e("FileUtil", "CreateDummyData(Context ctx) " + e.toString());
        }

        return ret;
    }

    public static boolean ClearHistory(Context ctx) {
        boolean ret = false;

        try {
            File file = OpenFile(ctx, history_file);
            FileWriter fw = new FileWriter(file, false);
            fw.write("");
            fw.close();
            ret = true;

        } catch (IOException e) {
            Log.e("FileUtil", "ClearHistory(Context ctx) " + e.toString());
        }

        return ret;

    }
}
