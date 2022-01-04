package com.discordrn;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

public class MMKVManager extends ReactContextBaseJavaModule {
    public MMKVManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "MMKVManager";
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
