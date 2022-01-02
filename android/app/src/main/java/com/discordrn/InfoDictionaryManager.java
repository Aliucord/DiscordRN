package com.discordrn; // replace com.your-app-name with your app’s name
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class InfoDictionaryManager extends ReactContextBaseJavaModule {
    InfoDictionaryManager(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "InfoDictionaryManager";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("SentryDsn", "");
        constants.put("UserSettings", "{\"systemTheme\": \"DARK\", \"useSystemTheme\": true}");
        return constants;
    }
}