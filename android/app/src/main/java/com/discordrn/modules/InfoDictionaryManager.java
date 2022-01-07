package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.util.HashMap;
import java.util.Map;

public class InfoDictionaryManager extends ReactContextBaseJavaModule {
    public InfoDictionaryManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "InfoDictionaryManager";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("SentryDsn", "");
        constants.put("UserSettings", "{\"systemTheme\": \"DARK\", \"useSystemTheme\": true}");
        constants.put("Manifest", "[]");
        return constants;
    }
}