package com.discordrn.views;


import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

import java.util.Map;

public class NativeLottieNode extends ViewGroupManager<FrameLayout> {
    @NonNull
    @Override
    public String getName() {
        return "NativeLottieNode";
    }

    @NonNull
    @Override
    public FrameLayout createViewInstance(@NonNull ThemedReactContext context) {
        return new FrameLayout(context);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "create", 0,
                "setup", 1
        );
    }
}