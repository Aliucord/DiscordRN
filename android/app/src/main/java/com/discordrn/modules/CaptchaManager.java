package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CaptchaManager extends ReactContextBaseJavaModule {
    public CaptchaManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "CaptchaManager";
    }

    @ReactMethod
    public void showCaptcha(Promise promise) {
        promise.resolve(null);
    }

    @ReactMethod
    public void closeCaptcha(Promise promise) {
        promise.resolve(null);
    }

    @ReactMethod
    public void addListener(String eventName) {
    }

    @ReactMethod
    public void removeListeners(double count) {
    }
}