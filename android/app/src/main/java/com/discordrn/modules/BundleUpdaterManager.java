package com.discordrn.modules;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class BundleUpdaterManager extends ReactContextBaseJavaModule {
    public BundleUpdaterManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "BundleUpdaterManager";
    }

    @ReactMethod
    public void getInitialBundleDownloaded(Promise promise) {
        promise.resolve(null);
    }

    @ReactMethod
    public void reload() {
        ReactApplicationContext context = getReactApplicationContext();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }
}