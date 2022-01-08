package com.discordrn.views;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class ViewStub extends ViewGroupManager<FrameLayout> {
    private final String name;

    public ViewStub(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String getName() {
        return name;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public FrameLayout createViewInstance(@NonNull ThemedReactContext context) {
        return new FrameLayout(context);
    }
}