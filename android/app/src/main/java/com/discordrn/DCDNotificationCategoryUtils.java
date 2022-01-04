package com.discordrn;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class DCDNotificationCategoryUtils extends ReactContextBaseJavaModule {
    DCDNotificationCategoryUtils(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDNotificationCategoryUtils";
    }

    @ReactMethod
    public void registerNotificationCategories() {
    }
}
