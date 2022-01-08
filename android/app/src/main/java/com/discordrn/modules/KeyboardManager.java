package com.discordrn.modules;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Objects;

public class KeyboardManager extends ReactContextBaseJavaModule {
    public KeyboardManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "KeyboardManager";
    }

    @ReactMethod
    public void dismissGlobalKeyboard() {
        Activity activity = Objects.requireNonNull(getCurrentActivity());

        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }

        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}