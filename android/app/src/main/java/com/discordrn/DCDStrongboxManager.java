package com.discordrn;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

public class DCDStrongboxManager extends ReactContextBaseJavaModule {
    public DCDStrongboxManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDStrongboxManager";
    }

    @ReactMethod
    public void setItem(String key, String value, Promise promise) {
        promise.resolve(true);
    }

    @ReactMethod
    public void getItem(String key, Promise promise) {
        promise.resolve(null);
    }

    @ReactMethod
    public void removeItem(String key, Promise promise) {
        promise.resolve(null);
    }

    @ReactMethod
    public void clear(Object fn, Promise promise) {
        promise.resolve(null);
    }

    public void refresh(Object idk, Promise promise) {
        promise.resolve(null);
    }
}
