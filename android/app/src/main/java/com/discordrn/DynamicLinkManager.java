package com.discordrn;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

public class DynamicLinkManager extends ReactContextBaseJavaModule {
    DynamicLinkManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DynamicLinkManager";
    }

    @ReactMethod
    public String getInitialURL() {
        return "https://discord.com/";
    }
}
