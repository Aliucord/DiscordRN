package com.discordrn; // replace com.your-app-name with your app’s name
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import java.util.Map;
import java.util.HashMap;

public class BundleUpdaterManager extends ReactContextBaseJavaModule {
    BundleUpdaterManager(ReactApplicationContext context) {
       super(context);
    }

    @Override
    public String getName() {
        return "BundleUpdaterManager";
    }

    @ReactMethod
    public void reload() {}
    @ReactMethod
    public void getInitialBundleDownloaded(Promise promise) { promise.resolve(Arguments.createMap()); } // guessing this is bool
    @ReactMethod
    public void checkForUpdateAndReload() {}
    @ReactMethod
    public void addListener() {}
    @ReactMethod
    public void removeListeners() {}

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        // constants.put("", "");
        return constants;
    }
}