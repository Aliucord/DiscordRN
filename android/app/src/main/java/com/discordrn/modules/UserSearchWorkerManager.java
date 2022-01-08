package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class UserSearchWorkerManager extends ReactContextBaseJavaModule {
    public UserSearchWorkerManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "UserSearchWorkerManager";
    }

    @ReactMethod
    public void onmessage(String data) {
    }
}