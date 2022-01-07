package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.*;

import java.util.HashMap;
import java.util.Map;

public class KeyCommandsModule extends ReactContextBaseJavaModule {
    public KeyCommandsModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "KeyCommandsView";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        final WritableMap modifiers = Arguments.createMap();
        constants.put("keyModifierCommand", modifiers);
        return constants;
    }
}
