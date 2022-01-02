package com.discordrn; // replace com.your-app-name with your app’s name

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class NativePermissionManager extends ReactContextBaseJavaModule {
    NativePermissionManager(ReactApplicationContext context) {
        super(context);
    }

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

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        // constants.put("", "");
        return constants;
    }
}