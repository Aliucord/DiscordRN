package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class NativePermissionManager extends ReactContextBaseJavaModule {
    public NativePermissionManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "NativePermissionManager";
    }

    @ReactMethod
    public boolean requestCameraAuthorization() {
        return true;
    }

    @ReactMethod
    public boolean requestMicrophoneAuthorization() {
        return true;
    }

    @ReactMethod
    public boolean requestPhotoAuthorization() {
        return true;
    }

    @ReactMethod
    public boolean requestContactsAuthorization() {
        return false;
    }

    @ReactMethod
    public boolean requestPermissionLookup() {
        return true;
    }

    @ReactMethod
    public boolean hasCameraAuthorization() {
        return true;
    }

    @ReactMethod
    public boolean hasMicrophoneAuthorization() {
        return true;
    }

    @ReactMethod
    public boolean hasPermissionLookup() {
        return true;
    }
}