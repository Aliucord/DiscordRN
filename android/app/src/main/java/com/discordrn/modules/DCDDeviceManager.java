package com.discordrn.modules;

import android.os.Build;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.util.HashMap;
import java.util.Map;

public class DCDDeviceManager extends ReactContextBaseJavaModule {
    public DCDDeviceManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDDeviceManager";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("name", Build.MODEL);
        constants.put("systemVersion", Build.VERSION.RELEASE);
        constants.put("device", Build.MODEL + ", " + Build.PRODUCT);
        constants.put("model", "Phone");
        return constants;
    }
}