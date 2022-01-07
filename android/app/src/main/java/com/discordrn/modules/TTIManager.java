package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import java.util.HashMap;
import java.util.Map;

public class TTIManager extends ReactContextBaseJavaModule {
    private final long timestamp = System.currentTimeMillis();

    public TTIManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "TTIManager";
    }

    @ReactMethod
    public void getJSBundleTimestamps(Promise promise) {
        WritableMap data = Arguments.createMap();
        data.putInt("JSBundleLoadedTimestamp", 0);
        data.putInt("JSBundleParsedTimestamp", 0);
        promise.resolve(data);
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("AppOpenedTimestamp", timestamp);
        return constants;
    }
}