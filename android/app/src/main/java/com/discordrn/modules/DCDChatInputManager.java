package com.discordrn.modules;

import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.UIManagerModule;

import java.util.Objects;

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
        Objects.requireNonNull(getReactApplicationContext().getCurrentActivity()).runOnUiThread(() -> {
            UIManagerModule managerModule = getReactApplicationContext().getNativeModule(UIManagerModule.class);
            assert managerModule != null;
            LinearLayout layout = (LinearLayout) managerModule.resolveView(id);
            EditText editor = (EditText) layout.getChildAt(0);
            editor.setText(text);
        });
    }

    @ReactMethod
    public void refreshFirstResponder(int id) {
    }

    @ReactMethod
    public void backspace(int id) {
        Objects.requireNonNull(getReactApplicationContext().getCurrentActivity()).runOnUiThread(() -> {
            UIManagerModule managerModule = getReactApplicationContext().getNativeModule(UIManagerModule.class);
            assert managerModule != null;
            LinearLayout layout = (LinearLayout) managerModule.resolveView(id);
            EditText editor = (EditText) layout.getChildAt(0);
            editor.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            editor.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
        });
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
