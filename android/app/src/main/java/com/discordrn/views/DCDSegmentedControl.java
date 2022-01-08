package com.discordrn.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;

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

import java.lang.reflect.Field;
import java.util.Map;

import kotlin.Unit;

public class DCDSegmentedControl extends SimpleViewManager<SegmentedControlGroup> {
    private int textColor;
    private int selectedTextColor;
    private int selected;

    @NonNull
    @Override
    public String getName() {
        return "DCDSegmentedControl";
    }

    @NonNull
    @Override
    protected SegmentedControlGroup createViewInstance(@NonNull ThemedReactContext reactContext) {
        SegmentedControlGroup group = new SegmentedControlGroup(reactContext);
        group.addView(createButton(reactContext));
        group.setOnSelectedOptionChangeCallback((i) -> {
            selected = i;
            ((SegmentedControlButton) group.getChildAt(i)).setTextColor(selectedTextColor);
            for (int index = 0, count = group.getChildCount(); index < count; index++)
                if (index != i) ((SegmentedControlButton) group.getChildAt(index)).setTextColor(textColor);

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
            SegmentedControlButton btn = createButton(group.getContext());
            btn.setText(values.getString(i));
            group.addView(btn);
        }
    }

    @ReactProp(name = "selectedSegmentIndex")
    public void setSelectedSegmentIndex(SegmentedControlGroup group, int i) {
        selected = i;
        group.setSelectedIndex(i, false);
    }

    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(SegmentedControlGroup group, String color) {
        group.setBackgroundColor(Color.parseColor(color));
    }

    @ReactProp(name = "titleAttributes")
    public void setTitleAttributes(SegmentedControlGroup group, ReadableMap map) {
        textColor = map.getInt("textColor");
        for (int i = 0, j = group.getChildCount(); i < j; i++)
            ((SegmentedControlButton) group.getChildAt(i)).setTextColor(textColor);
    }

    @ReactProp(name = "selectedTitleAttributes")
    public void setSelectedTitleAttributes(SegmentedControlGroup group, ReadableMap map) {
        selectedTextColor = map.getInt("textColor");
        ((SegmentedControlButton) group.getChildAt(selected)).setTextColor(selectedTextColor);
    }

    @ReactProp(name = "customSelectedTintColor")
    public void setCustomSelectedTintColor(SegmentedControlGroup group, String color) throws Throwable {
        Field field = group.getClass().getDeclaredField("sliderPaint");
        field.setAccessible(true);
        Paint paint = (Paint) field.get(group);
        if (paint != null) {
            int c = Color.parseColor(color);
            paint.setColor(c);
        }
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of("onValueChange", MapBuilder.of("registrationName", "onValueChange"));
    }

    private SegmentedControlButton createButton(Context context) {
        SegmentedControlButton btn = new SegmentedControlButton(context);
        btn.setGravity(Gravity.CENTER);
        if (textColor != 0) btn.setTextColor(textColor);
        return btn;
    }
}
