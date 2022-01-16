package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class DCDChatManager extends ReactContextBaseJavaModule {
    public DCDChatManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDChatManager";
    }

    @ReactMethod
    public void updateRows(int id, String json, boolean b) {
    }

    @ReactMethod
    public void clearRows(int id) {
    }

    @ReactMethod
    public void scrollTo(int id, int message, boolean instant) {
    }

    @ReactMethod
    public void fadeIn(int id) {}

    @ReactMethod
    public void scrollToBottom(int id, boolean b) {}

    @ReactMethod
    public void scrollToTop(int id, boolean b) {}
}
