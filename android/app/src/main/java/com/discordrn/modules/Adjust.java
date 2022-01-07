// https://github.com/adjust/react_native_sdk/blob/master/android/src/main/java/com/adjust/sdk/Adjust.java

package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class Adjust extends ReactContextBaseJavaModule {
    public Adjust(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "Adjust";
    }

    @ReactMethod
    public void setAttributionCallbackListener() {
    }
}
