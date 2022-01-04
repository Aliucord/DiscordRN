package com.discordrn;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

import java.util.Map;

public class DCDColor extends ReactContextBaseJavaModule {
    DCDColor(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDColor";
    }

    @ReactMethod
    public void setColors(String s, String colors) {}
}
