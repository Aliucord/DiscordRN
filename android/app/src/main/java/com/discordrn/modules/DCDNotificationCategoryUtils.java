package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class DCDNotificationCategoryUtils extends ReactContextBaseJavaModule {
    public DCDNotificationCategoryUtils(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDNotificationCategoryUtils";
    }

    @ReactMethod
    public void registerNotificationCategories(ReadableMap idk) {
    }
}
