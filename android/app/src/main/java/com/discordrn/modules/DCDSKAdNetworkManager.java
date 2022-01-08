package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class DCDSKAdNetworkManager extends ReactContextBaseJavaModule {
    public DCDSKAdNetworkManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDSKAdNetworkManager";
    }

    @ReactMethod
    public void updateConversionValue(int value) {
    }
}