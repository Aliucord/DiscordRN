package com.discordrn;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class PushNotificationAndroid extends ReactContextBaseJavaModule {
    PushNotificationAndroid(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "PushNotificationAndroid";
    }

    @ReactMethod
    public void getInitialNotification(Promise promise) {
        promise.resolve(null);
    }

    @ReactMethod
    public void setApplicationIconBadgeNumber(int i) {
    }

    @ReactMethod
    public void clearAllNotifications() {
    }

    @ReactMethod
    public void presentLocalNotification() {
    }

    @ReactMethod
    public void scheduleLocalNotification() {
    }

    @ReactMethod
    public void getScheduledLocalNotifications() {
    }

    @ReactMethod
    public void cancelLocalNotifications() {
    }

    @ReactMethod
    public void cancelAllLocalNotifications() {
    }

    @ReactMethod
    public boolean checkPermissions() {
        return true;
    }

    @ReactMethod
    public void requestPermissions(Promise p) {
        p.resolve(true);
    }

    @ReactMethod
    public void openNotificationSettings() {
    }

    @ReactMethod
    public void addNotificationEventListener(Callback cb) {
    }

    @ReactMethod
    public void addRegisterEventListener(Callback cb) {
    }

    @ReactMethod
    public void addListener(Callback cb) {
    }

    @ReactMethod
    public void addEventListener(Callback cb) {
    }

    @ReactMethod
    public void registerEventListener(String what) {
    }

    @ReactMethod
    public void appStateChanged(String what) {
    }

}