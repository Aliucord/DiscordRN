package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ProximitySensorManager extends ReactContextBaseJavaModule {
    public ProximitySensorManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "ProximitySensorManager";
    }

    @ReactMethod
    public void setProximityMonitoringEnabled(boolean enabled) {
    }
}