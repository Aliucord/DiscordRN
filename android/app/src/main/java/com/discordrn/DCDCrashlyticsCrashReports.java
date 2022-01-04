package com.discordrn;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class DCDCrashlyticsCrashReports extends ReactContextBaseJavaModule {
    DCDCrashlyticsCrashReports(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDCrashlyticsCrashReports";
    }

    @ReactMethod
    public void initializeManager() {
    }

    @ReactMethod
    public void getDidCrashDuringPreviousExecution() {
    }
}
