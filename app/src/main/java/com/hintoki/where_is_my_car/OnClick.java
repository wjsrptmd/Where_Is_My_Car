package com.hintoki.where_is_my_car;

import android.view.View;

public abstract class OnClick implements View.OnClickListener {
    protected String parkingInfo;
    public OnClick(String parkingInfo) {
        this.parkingInfo = parkingInfo;
    }
}

