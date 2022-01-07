package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

public class DynamicLinkManager extends ReactContextBaseJavaModule {
    public DynamicLinkManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DynamicLinkManager";
    }

    @ReactMethod
    public void getInitialURL(Promise promise) {
        promise.resolve(null);
    }
}
