package com.discordrn; // replace com.your-app-name with your app’s name
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class VoiceEngine extends ReactContextBaseJavaModule {
    VoiceEngine(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "VoiceEngine";
    }

    @ReactMethod
    public void setDeviceChangeCallback() {}
    @ReactMethod
    public void handleDeviceChange() {}
    @ReactMethod
    public void setVolumeChangeCallback() {}
    @ReactMethod
    public void handleVolumeChange() {}
    @ReactMethod
    public void setOnVoiceCallback() {}
    @ReactMethod
    public void handleVoiceActivity() {}
    @ReactMethod
    public void setVideoInputInitializationCallback() {}
    @ReactMethod
    public void handleVideoInputInitialization() {}
    @ReactMethod
    public void setTransportOptions() {}
    @ReactMethod
    public void on() {}
    @ReactMethod
    public void removeListener() {}
    @ReactMethod
    public void handleNewListener() {}
    @ReactMethod
    public void newListener() {}
    @ReactMethod
    public String getVoiceEngine() { return "ok"; }
    @ReactMethod
    public void getUseLegacyAudioDevice() {}
    @ReactMethod
    public void AudioSubsystems() {}
    @ReactMethod
    public void pingVoiceThread() {}
    @ReactMethod
    public String getAudioSubsystem() { return "standard"; }
    @ReactMethod
    public void watchdogTick() {}
    @ReactMethod
    public void getDebugLogging() {}
    @ReactMethod
    public void installedLogHooks() {}
    @ReactMethod
    public void setActiveSinksChangeCallback() {}
    @ReactMethod
    public void handleActiveSinksChange() {}
    @ReactMethod
    public void call() {}
    @ReactMethod
    public void Video() {}
    @ReactMethod
    public void Camera() {}
    @ReactMethod
    public void init() {}
    @ReactMethod
    public void createVoiceConnection() {}
    @ReactMethod
    public void createOwnStreamConnection() {}
    @ReactMethod
    public void setNoInputCallback() {}
    @ReactMethod
    public void setBroadcastRequestCallback() {}
    @ReactMethod
    public void setBroadcastFinishedCallback() {}
    @ReactMethod
    public void setBroadcastAnnotatedCallback() {}
    @ReactMethod
    public void setBroadcastBlockedCallback() {}
    @ReactMethod
    public void setBroadcastThumbnailCallback() {}
    @ReactMethod
    public void prototype() {}

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("platform", "android");
        constants.put("videoInputDeviceId", "0");
        constants.put("DISABLED_DEVICE_ID", "0");
        constants.put("connections", "");
        constants.put("lastVoiceActivity", "");
        constants.put("audioSubsystem", "standard");
        constants.put("audioLayer", "standard");
        constants.put("loopback", "standard");
        constants.put("deviceChangeGeneration", "standard");
        constants.put("consecutiveWatchdogFailures", "standard");
        constants.put("default", "");
        return constants;
    }
}