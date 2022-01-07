package com.discordrn.modules;

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
        constants.put("name", "Totally not an emulator");
        constants.put("systemVersion", "12");
        // TODO figure out what these would be for android
        constants.put("device", "iPad8,6");
        constants.put("model", "iPad");
        return constants;
    }
}