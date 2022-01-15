package com.discordrn.modules;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.khoiron.actionsheets.ActionSheet;

import java.util.ArrayList;

public class ActionSheetAndroid extends ReactContextBaseJavaModule {
    public ActionSheetAndroid(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "ActionSheetAndroid";
    }

    @ReactMethod
    public void options(String title, String message, String cancel, String primaryBold, ReadableArray options, String primary, String backgroundPrimary, int f, String textDanger, String textNormal, String backgroundSecondary, Promise promise) {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < options.size(); i++) {
            data.add(options.getString(i));
        }

        ActionSheet actionSheet = new ActionSheet(getCurrentActivity(), data)
                .setCancelTitle(cancel);

        if (title == null) {
            actionSheet.hideTitle();
        } else {
            actionSheet.setTitle(title);
        }

        actionSheet.create((option, position) -> promise.resolve(position));
    }
}