package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

import java.util.HashMap;
import java.util.Map;

public class DCDFastConnectManager extends ReactContextBaseJavaModule {
    public DCDFastConnectManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDFastConnectManager";
    }

    @ReactMethod
    public void setClientState(String state) {
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("token", null);
        constants.put("clientState", "{}");
        return constants;
    }
}
