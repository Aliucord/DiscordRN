package com.discordrn;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null); // https://github.com/software-mansion/react-native-screens#android
    }
}
