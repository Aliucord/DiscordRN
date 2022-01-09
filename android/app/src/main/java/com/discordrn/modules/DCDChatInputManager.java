package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class DCDChatInputManager extends ReactContextBaseJavaModule {
    public DCDChatInputManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDChatInputManager";
    }

    @ReactMethod
    public void setText(int id, String text) {
    }

    @ReactMethod
    public void refreshFirstResponder(int id) {
    }

    @ReactMethod
    public void backspace(int id) {
    }

    @ReactMethod
    public void blur(int id) {
    }

    @ReactMethod
    public void focus(int id) {
    }

    @ReactMethod
    public void closeCustomKeyboard(int id) {
    }

    @ReactMethod
    public void openSystemKeyboard(int id) {
    }

    @ReactMethod
    public void openCustomKeyboard(int id) {
    }

    @ReactMethod
    public void setSelectedRange(int id, int start, int end) {
    }

    @ReactMethod
    public void replaceRange(int id, String text, int start, int end) {
    }

    @ReactMethod
    public void updateTextBlocks(int id, int t, int n) {
    }
}
