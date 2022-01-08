package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class DCDCrashlyticsCrashReports extends ReactContextBaseJavaModule {
    public DCDCrashlyticsCrashReports(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "CrashlyticsManager";
    }

    @ReactMethod
    public void initializeManager() {
    }

    @ReactMethod
    public void getDidCrashDuringPreviousExecution(Callback callback) {
        callback.invoke(false);
    }

    @ReactMethod
    public void setUser(ReadableMap user) {
    }
}
