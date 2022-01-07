package com.discordrn;

import android.content.Intent;
import android.content.res.Configuration;

import com.facebook.react.ReactActivity;

public class MainActivity extends ReactActivity {
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Intent intent = new Intent("onConfigurationChanged");
        intent.putExtra("newConfig", newConfig);
        this.sendBroadcast(intent);
    }

    @Override
    protected String getMainComponentName() {
        return "Discord";
    }
}
