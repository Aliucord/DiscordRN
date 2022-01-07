package com.discordrn.views;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.views.text.ReactTextView;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class KeyCommandsView extends ViewGroupManager<LinearLayout> {
    ReactApplicationContext mCallerContext;
  
    public KeyCommandsView(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }
  
    @Override
    public String getName() {
        return "KeyCommandsView";
    }

    @Override
    public LinearLayout createViewInstance(ThemedReactContext context) {
        LinearLayout layout = new LinearLayout(context);

        TextView text = new TextView(context);
        text.setText("uwu");

        layout.addView(text);

        return layout;
    }
}