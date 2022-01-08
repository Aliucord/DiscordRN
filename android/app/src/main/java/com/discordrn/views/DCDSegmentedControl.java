package com.discordrn.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alanvan.segmented_control.SegmentedControlButton;
import com.alanvan.segmented_control.SegmentedControlGroup;
import com.facebook.react.bridge.*;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Map;

import kotlin.Unit;

public class DCDSegmentedControl extends SimpleViewManager<SegmentedControlGroup> {
    @NonNull
    @Override
    public String getName() {
        return "DCDSegmentedControl";
    }

    @NonNull
    @Override
    protected SegmentedControlGroup createViewInstance(@NonNull ThemedReactContext reactContext) {
        SegmentedControlGroup group = new SegmentedControlGroup(reactContext);
        group.addView(new SegmentedControlButton(reactContext));
        group.setOnSelectedOptionChangeCallback((i) -> {
            WritableMap data = Arguments.createMap();
            data.putInt("selectedSegmentIndex", i);
            ((ReactContext) group.getContext()).getJSModule(RCTEventEmitter.class).receiveEvent(group.getId(), "onValueChange", data);
            return Unit.INSTANCE;
        });
        return group;
    }

    @ReactProp(name = "values")
    public void setValues(SegmentedControlGroup group, ReadableArray values) {
        int size = values.size();
        if (size == 0) return;
        else ((SegmentedControlButton) group.getChildAt(0)).setText(values.getString(0));
        for (int i = 1; i < size; i++) {
            SegmentedControlButton btn = new SegmentedControlButton(group.getContext());
            btn.setText(values.getString(i));
            group.addView(btn);
        }
    }

    @ReactProp(name = "selectedSegmentIndex")
    public void setSelectedSegmentIndex(SegmentedControlGroup group, int i) {
        group.setSelectedIndex(i, false);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("onValueChange", MapBuilder.of("registrationName", "onValueChange"));
    }
}
