package com.discordrn.modules;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

// TODO setup https://docs.swmansion.com/react-native-reanimated/docs/1.x.x/ properly
public class ReanimatedModule extends ReactContextBaseJavaModule {
    public ReanimatedModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "ReanimatedModule";
    }

    @ReactMethod
    public void createNode(double nodeId, ReadableMap nodeConfig) {
        Log.i("ReanimatedModule", String.format("createNode: %f - %s", nodeId, nodeConfig.toString()));
    }
}
