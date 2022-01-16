package com.discordrn.views;

import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class DCDChat extends ViewGroupManager<LinearLayout> {
    @NonNull
    @Override
    public String getName() {
        return "DCDChat";
    }

    @NonNull
    @Override
    protected LinearLayout createViewInstance(@NonNull ThemedReactContext context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        return layout;
    }
}