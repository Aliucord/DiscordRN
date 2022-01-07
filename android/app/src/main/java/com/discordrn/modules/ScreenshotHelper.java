package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ScreenshotHelper extends ReactContextBaseJavaModule {
    public ScreenshotHelper(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "ScreenshotHelper";
    }

    @ReactMethod
    public void appStateChanged(Boolean isActive) {
    }
}