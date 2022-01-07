package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class DCDColor extends ReactContextBaseJavaModule {
    public DCDColor(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDColor";
    }

    @ReactMethod
    public void setColors(String colors, String themeColorMap) {
    }
}
