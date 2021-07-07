package com.hintoki.where_is_my_car;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class IntentManager {
    public static boolean DoIntent(Context ctx, Uri uri) {
        boolean ret = false;
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);

        try {
            ctx.startActivity(mapIntent);
            ret = true;
        } catch (ActivityNotFoundException e) {
            Log.e("IntentManager", "Intent " + e.toString());
        }
        return ret;
    }

    public static boolean IntentMapApp(Context ctx, String location, String type) {
        boolean ret = DoIntent(ctx, SchemGenerator.GetMapScheme(location, type));
        if(!ret) {
            // 해당 앱이 없음.
            ret = DoIntent(ctx, SchemGenerator.GetMarketScheme(type));
        }

        return ret;
    }
}
