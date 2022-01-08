package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.util.HashMap;
import java.util.Map;

public class BrowserManager extends ReactContextBaseJavaModule {
    public BrowserManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "BrowserManager";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("selectedBrowser", "SAFARI");
        constants.put("isChromeInstalled", false);
        return constants;
    }
}