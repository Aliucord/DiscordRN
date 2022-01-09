package com.discordrn.modules;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

import java.util.HashMap;
import java.util.Map;

public class DCDFastConnectManager extends ReactContextBaseJavaModule {
    private static final String TAG = "FastConnectManager";

    public DCDFastConnectManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDFastConnectManager";
    }

    @ReactMethod
    public void setClientState(String state) {
        Log.i(TAG, "setClientState: " + state);
    }

    @ReactMethod
    public void prepareIdentify(String json, int socketId) {
        Log.i(TAG, "prepareIdentify: " + json + ", " + socketId);
        getReactApplicationContext().getNativeModule(DCDCompressionManager.class).setIdentify(socketId, json);
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("token", your token);
        constants.put("clientState", "{}");
        return constants;
    }
}
