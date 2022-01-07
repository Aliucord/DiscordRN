package com.discordrn.modules;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimersModule extends ReactContextBaseJavaModule {
    private static final String TAG = "TimersModule";

    private Handler handler;
    private Timer timer;

    private final Map<Integer, Object> timeouts = new HashMap<>();
    private final Map<Integer, TimerTask> intervals = new HashMap<>();

    public TimersModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "TimersModule";
    }

    private void sendEvent(String eventName, WritableMap params) {
        getReactApplicationContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @Override
    public void initialize() {
        super.initialize();

        handler = new Handler();
        timer = new Timer();
    }

    @ReactMethod
    public void setTimeout(int id, int delay) {
        Log.i(TAG, "setTimeout: " + delay);

        timeouts.put(id, new Object());
        handler.postDelayed(() -> {
            if (!timeouts.containsKey(id))
                return;

            WritableMap data = Arguments.createMap();
            data.putInt("id", id);
            sendEvent("timer", data);
        }, delay);
    }

    @ReactMethod
    public void clearTimeout(int id) {
        Log.i(TAG, "clearTimeout: " + id);

        Object token = timeouts.remove(id);
        if (token != null) {
            handler.removeCallbacksAndMessages(token);
        }
    }

    @ReactMethod
    public void setInterval(int id, int period) {
        Log.i(TAG, "setInterval: " + period);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (!intervals.containsKey(id)) {
                    return;
                }

                WritableMap data = Arguments.createMap();
                data.putInt("id", id);
                sendEvent("interval", data);
            }
        };
        intervals.put(id, timerTask);
        timer.scheduleAtFixedRate(timerTask, 0, period);
    }

    @ReactMethod
    public void clearInterval(int id) {
        Log.i(TAG, "clearInterval: " + id);

        TimerTask task = intervals.remove(id);
        if (task != null) {
            task.cancel();
        }
    }
}
