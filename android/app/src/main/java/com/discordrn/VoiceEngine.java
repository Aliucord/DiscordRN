package com.discordrn; // replace com.your-app-name with your app’s name

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

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
    public void setDeviceChangeCallback(Callback cb) {
    }

    @ReactMethod
    public void handleDeviceChange() {
    }

    @ReactMethod
    public void setVolumeChangeCallback(Callback cb) {
    }

    @ReactMethod
    public void handleVolumeChange() {
    }

    @ReactMethod
    public void setOnVoiceCallback(Callback cb) {
    }

    @ReactMethod
    public void handleVoiceActivity() {
    }

    @ReactMethod
    public void setVideoInputInitializationCallback(Callback cb) {
    }

    @ReactMethod
    public void handleVideoInputInitialization() {
    }

    @ReactMethod
    public void setTransportOptions(ReadableMap obj) {
    }

    @ReactMethod
    public String getVoiceEngine() {
        return "ok";
    }

    @ReactMethod
    public void getUseLegacyAudioDevice() {
    }

    @ReactMethod
    public void pingVoiceThread() {
    }

    @ReactMethod
    public String getAudioSubsystem(double bruh) {
        return "standard";
    }

    @ReactMethod
    public void watchdogTick() {
    }

    @ReactMethod
    public void getDebugLogging() {
    }

    @ReactMethod
    public void installedLogHooks() {
    }

    @ReactMethod
    public void setActiveSinksChangeCallback(Callback cb) {
    }

    @ReactMethod
    public void handleActiveSinksChange() {
    }

    @ReactMethod
    public void init() {
    }

    @ReactMethod
    public void createVoiceConnection() {
    }

    @ReactMethod
    public void createOwnStreamConnection() {
    }

    @ReactMethod
    public void setNoInputCallback(Callback cb) {
    }

    @ReactMethod
    public void setBroadcastRequestCallback(Callback cb) {
    }

    @ReactMethod
    public void setBroadcastFinishedCallback(Callback cb) {
    }

    @ReactMethod
    public void setBroadcastAnnotatedCallback(Callback cb) {
    }

    @ReactMethod
    public void setBroadcastBlockedCallback(Callback cb) {
    }

    @ReactMethod
    public void setBroadcastThumbnailCallback(Callback cb) {
    }

    @ReactMethod
    public void getInputDevices(Callback cb) {
    }

    @ReactMethod
    public void getOutputDevices(Callback cb) {
    }

    @ReactMethod
    public void getVideoInputDevices(Callback cb) {
    }

    @ReactMethod
    public void setInputDevice(int index) {
    }

    @ReactMethod
    public void setOutputDevice(int index) {
    }

    @ReactMethod
    public void setInputVolume(int volume) {
    }

    @ReactMethod
    public void setOutputVolume(int volume) {
    }

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