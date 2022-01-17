package com.discordrn.views;

import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class DCDChatInput extends ViewGroupManager<LinearLayout> {
    @NonNull
    @Override
    public String getName() {
        return "DCDChatInput";
    }

    @NonNull
    @Override
    protected LinearLayout createViewInstance(@NonNull ThemedReactContext context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        EditText editor = new EditText(context);
        editor.setInputType(InputType.TYPE_CLASS_TEXT);
        layout.addView(editor, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return layout;
    }
}