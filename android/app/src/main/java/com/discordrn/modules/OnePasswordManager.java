package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class OnePasswordManager extends ReactContextBaseJavaModule {
    public OnePasswordManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "OnePasswordManager";
    }

    @ReactMethod
    public void isSupported(Promise promise) {
        promise.resolve(false);
    }

    @ReactMethod
    public void findLogin(String a, String b, Promise promise) {
        promise.resolve(null);
    }
}