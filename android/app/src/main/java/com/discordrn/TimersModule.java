package com.discordrn; // replace com.your-app-name with your app’s name

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.Timer;
import java.util.TimerTask;

public class TimersModule extends ReactContextBaseJavaModule {
    private static final String TAG = "TimersModule";

    TimersModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "TimersModule";
    }

    @ReactMethod
    public void setTimeout(Callback callback, int delay) {
        Log.i(TAG, "setTimeout: " + delay);
        new Handler().postDelayed(callback::invoke, delay);
    }

    @ReactMethod
    public void setInterval(int delay, int period) {
        Log.i(TAG, String.format("setInterval: delay %d, period %d", delay, period));
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                WritableMap map = Arguments.createMap();
                map.putInt("id", 69); // TODO

                getReactApplicationContext()
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("interval", map); // or "timer"? idk
            }
        }, delay, period);
    }

    @ReactMethod
    public void clearTimeout(int id) {
        Log.i(TAG, "clearTimeout: " + id);
    }
}
