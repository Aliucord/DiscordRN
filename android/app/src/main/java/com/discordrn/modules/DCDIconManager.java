package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

public class DCDIconManager extends ReactContextBaseJavaModule {
    public DCDIconManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDIconManager";
    }

    @ReactMethod
    public void setHosts(String cdn, String api) {
    }
}
