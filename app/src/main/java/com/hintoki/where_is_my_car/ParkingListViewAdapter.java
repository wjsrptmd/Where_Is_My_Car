package com.hintoki.where_is_my_car;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ParkingListViewAdapter extends BaseAdapter {
    ArrayList<ParkingInfo> list = new ArrayList<>();

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parking_list_view_item, parent, false);
        }

        TextView col1 = (TextView) convertView.findViewById(R.id.text_column_1) ;
        TextView col2 = (TextView) convertView.findViewById(R.id.text_column_2) ;
        TextView col3 = (TextView) convertView.findViewById(R.id.text_column_3) ;

        ParkingInfo parkingInfo = list.get(position);

        col1.setText(parkingInfo.date_time);
        col2.setText(parkingInfo.car_name);
        col3.setText(parkingInfo.location);

        return convertView;
    }

    public void SetList(ArrayList<ParkingInfo> list) {
        this.list = list;
    }

    public void onClick(View v) {
        int t = 0;
    }
}
