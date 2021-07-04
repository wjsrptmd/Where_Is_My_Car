package com.hintoki.where_is_my_car;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdPage.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdPage newInstance(String param1, String param2) {
        ThirdPage fragment = new ThirdPage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_third_page,container,false);

        ArrayList<ParkingInfo> list = ParkingInfoManager.GetHistory(getContext());
        if(list.size() > 0) {
            TextView empty_text = (TextView)view.findViewById(R.id.empty_text);
            empty_text.setText("");

            ListView listView = (ListView) view.findViewById(R.id.list_history);
            ParkingListViewAdapter adapter =  new ParkingListViewAdapter();
            adapter.SetList(list);
            listView.setAdapter(adapter);

        }

        return view;
    }
}