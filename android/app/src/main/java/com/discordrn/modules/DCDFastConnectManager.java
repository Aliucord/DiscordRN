package com.discordrn.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.util.HashMap;
import java.util.Map;

@ReactModule(name = "DCDFastConnectManager")
public class DCDFastConnectManager extends ReactContextBaseJavaModule {
    private static final String TAG = "FastConnectManager";
    private final SharedPreferences prefs;

    private final Map<Integer, String> identities = new HashMap<>();

    public DCDFastConnectManager(ReactApplicationContext context) {
        super(context);
        prefs = context.getSharedPreferences("MMKVManager", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDFastConnectManager";
    }

    @ReactMethod
    public void setClientState(String state) {
        Log.i(TAG, "setClientState: " + state);
        prefs.edit().putString("clientState", state).apply();
    }

    @ReactMethod
    public void prepareIdentify(String json, int socketId) {
        Log.i(TAG, "prepareIdentify: " + json + ", " + socketId);
        identities.put(socketId, json);
    }

    public Map<Integer, String> getIdentities() {
        return identities;
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("token", prefs.getString("token", null));
        constants.put("clientState", prefs.getString("clientState", null));
        return constants;
    }
}
