package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import java.util.HashMap;
import java.util.Map;

public class VoiceEngine extends ReactContextBaseJavaModule {
    public VoiceEngine(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "VoiceEngine";
    }

    @ReactMethod
    public void setTransportOptions(ReadableMap obj) {
    }

    @ReactMethod
    public boolean getUseLegacyAudioDevice() {
        return false;
    }

    @ReactMethod
    public void pingVoiceThread(Callback callback) {
        callback.invoke();
    }

    @ReactMethod
    public void getAudioSubsystem(Callback callback) {
        callback.invoke("standard", "");
    }

    @ReactMethod
    public boolean getDebugLogging() {
        return true;
    }

    @ReactMethod
    public void init() {
    }

//    @ReactMethod
//    public void createVoiceConnection() {
//    }

//    @ReactMethod
//    public void createOwnStreamConnection() {
//    }

    @ReactMethod
    public void getInputDevices(Callback cb) {
        cb.invoke(Arguments.createArray());
    }

    @ReactMethod
    public void getOutputDevices(Callback cb) {
        cb.invoke(Arguments.createArray());
    }

    @ReactMethod
    public void getVideoInputDevices(Callback cb) {
        cb.invoke(Arguments.createArray());
    }

    @ReactMethod
    public void setInputDevice(String index) {
    }

    @ReactMethod
    public void setOutputDevice(String index) {
    }

    @ReactMethod
    public void setInputVolume(int volume) {
    }

    @ReactMethod
    public void setOutputVolume(int volume) {
    }

    @ReactMethod
    public void setEmitVADLevel(boolean a, boolean b, ReadableMap c) {
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        final WritableMap modes = Arguments.createMap();
        modes.putInt("VOICE", 0);
        modes.putInt("LISTEN", 1);
        modes.putInt("VIDEO", 2);
        constants.put("AVAudioSessionMode", modes);
        return constants;
    }
}