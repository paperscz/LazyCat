package com.sky.lazycat.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.sky.lazycat.R;


/**
 * A helper class of chrome custom tabs.
 * If the chrome custom tabs is available, then use it to open
 * links, otherwise use system browser instead.
 */

public class CustomTabsHelper {

    public static void openUrl(Context context, String url) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, R.string.no_browser_found, Toast.LENGTH_SHORT).show();
        }
    }

}
