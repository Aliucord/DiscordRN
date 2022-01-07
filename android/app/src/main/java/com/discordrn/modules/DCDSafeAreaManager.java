package com.discordrn.modules;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.util.HashMap;
import java.util.Map;

public class DCDSafeAreaManager extends ReactContextBaseJavaModule {
    public DCDSafeAreaManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDSafeAreaManager";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("top", 0);
        constants.put("bottom", 0);
        constants.put("left", 0);
        constants.put("right", 0);
        return constants;
    }
}
