package com.discordrn; // replace com.your-app-name with your app’s name
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class PushNotificationAndroid extends ReactContextBaseJavaModule {
    PushNotificationAndroid(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "PushNotificationAndroid";
    }

    @ReactMethod
    public void getInitialNotification() {}
    @ReactMethod
    public void setApplicationIconBadgeNumber(int i) {}
    @ReactMethod
    public void clearAllNotifications() {}
    @ReactMethod
    public void presentLocalNotification() {}
    @ReactMethod
    public void scheduleLocalNotification() {}
    @ReactMethod
    public void getScheduledLocalNotifications() {}
    @ReactMethod
    public void cancelLocalNotifications() {}
    @ReactMethod
    public void cancelAllLocalNotifications() {}
    @ReactMethod
    public boolean checkPermissions() { return true; }
    @ReactMethod
    public void requestPermissions(Promise p) { p.resolve(true); }
    @ReactMethod
    public void openNotificationSettings() {}
    @ReactMethod
    public void addNotificationEventListener(Callback cb) {}
    @ReactMethod
    public void addRegisterEventListener(Callback cb) {}
    @ReactMethod
    public void addListener(Callback cb) {}
    @ReactMethod
    public void addEventListener(Callback cb) {}
    @ReactMethod
    public void registerEventListener(String what) {}
    @ReactMethod
    public void appStateChanged(String what) {}


    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        return constants;
    }
}