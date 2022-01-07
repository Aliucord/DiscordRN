package com.discordrn.modules;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class DCDTheme extends ReactContextBaseJavaModule {
    public DCDTheme(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDTheme";
    }

    @ReactMethod
    public void updateTheme(String theme) {
        Log.i("DCDTheme", "updateTheme: " + theme);
    }
}
