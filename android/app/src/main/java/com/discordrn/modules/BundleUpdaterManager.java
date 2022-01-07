package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BundleUpdaterManager extends ReactContextBaseJavaModule {
    public BundleUpdaterManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "BundleUpdaterManager";
    }

    @ReactMethod
    public void getInitialBundleDownloaded(Promise promise) {
        promise.resolve(null);
    }
}