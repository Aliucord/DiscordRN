package com.discordrn.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

public class MMKVManager extends ReactContextBaseJavaModule {
    private final String TAG = "MMKVManager";
    private final SharedPreferences prefs;
    private final String name;

    public MMKVManager(ReactApplicationContext reactContext, String name) {
        super(reactContext);
        prefs = reactContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        this.name = name;
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    @ReactMethod
    public void setItem(String key, String value, Promise promise) {
        Log.i(TAG, String.format("setItem: %s = %s", key, value));
        prefs.edit().putString(key, value).apply();
        promise.resolve(true);
    }

    @ReactMethod
    public void getItem(String key, Promise promise) {
        Log.i(TAG, String.format("getItem: %s", key));
        promise.resolve(prefs.getString(key, null));
    }

    @ReactMethod
    public void removeItem(String key, Promise promise) {
        Log.i(TAG, String.format("removeItem: %s", key));
        prefs.edit().remove(key).apply();
        promise.resolve(true);
    }

    @ReactMethod
    public void clear(ReadableArray keys, Promise promise) {
        Log.i(TAG, "clear");
        SharedPreferences.Editor editor = prefs.edit();
        for (int i = 0, j = keys.size(); i < j; i++) {
            editor.remove(keys.getString(i));
        }
        editor.apply();
        promise.resolve(null);
    }

    @ReactMethod
    public void refresh(ReadableArray keys, Promise promise) {
        promise.resolve(null);
    }
}
