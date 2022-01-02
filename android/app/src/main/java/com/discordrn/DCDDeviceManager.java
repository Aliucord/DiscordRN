package com.discordrn; // replace com.your-app-name with your app’s name
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class DCDDeviceManager extends ReactContextBaseJavaModule {
    DCDDeviceManager(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "DCDDeviceManager";
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("SMALL_SCREEN_WIDTH", "100");
        constants.put("systemVersion", "{\"os_version\": \"12\"}");
        constants.put("device", "");
        return constants;
    }
}