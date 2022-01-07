package com.discordrn.views;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class ViewStub extends ViewGroupManager<LinearLayout> {
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
    public LinearLayout createViewInstance(@NonNull ThemedReactContext context) {
        LinearLayout layout = new LinearLayout(context);

        TextView text = new TextView(context);
        text.setText(name + " stub");

        layout.addView(text);

        return layout;
    }
}