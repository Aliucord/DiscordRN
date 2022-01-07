package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

public class IntentsHandler extends ReactContextBaseJavaModule {
    public IntentsHandler(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "IntentsHandler";
    }

    @ReactMethod
    public void getLaunchSendMessageIntent(Promise p) {
        final WritableMap intent = Arguments.createMap();
        intent.putString("channelId", null);
        intent.putString("guildId", null);
        p.resolve(intent);
    }
}
