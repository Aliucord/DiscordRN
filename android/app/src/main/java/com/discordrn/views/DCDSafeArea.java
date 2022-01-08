package com.discordrn.views;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class DCDSafeArea extends ViewGroupManager<LinearLayout> {
    private LinearLayout layout;

    @NonNull
    @Override
    public String getName() {
        return "DCDSafeArea";
    }

    @NonNull
    @Override
    protected LinearLayout createViewInstance(@NonNull ThemedReactContext context) {
        return new LinearLayout(context);
    }
}
