package com.discordrn.modules;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.discordrn.models.Row;
import com.discordrn.views.DCDChatList;
import com.facebook.react.bridge.*;
import com.facebook.react.uimanager.UIManagerModule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class DCDChatManager extends ReactContextBaseJavaModule {
    private static final Gson gson = new Gson();
    private static final Type rowsType = TypeToken.getParameterized(List.class, Row.class).getType();

    public DCDChatManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDChatManager";
    }

    @SuppressLint("NotifyDataSetChanged")
    @ReactMethod
    public void updateRows(int id, String json, boolean b) {
        Objects.requireNonNull(getReactApplicationContext().getCurrentActivity()).runOnUiThread(() -> {
            UIManagerModule managerModule = getReactApplicationContext().getNativeModule(UIManagerModule.class);
            assert managerModule != null;
            LinearLayout layout = (LinearLayout) managerModule.resolveView(id);
            RecyclerView chatList = (RecyclerView) layout.getChildAt(0);
            DCDChatList.Adapter adapter = (DCDChatList.Adapter) chatList.getAdapter();

            List<Row> rows = gson.fromJson(json, rowsType);
            assert adapter != null;
            for (Row row : rows) adapter.data.add(row.message.content.toString());
            adapter.notifyDataSetChanged();
        });

    }

    @ReactMethod
    public void clearRows(int id) {
    }

    @ReactMethod
    public void scrollTo(int id, int message, boolean instant) {
    }

    @ReactMethod
    public void fadeIn(int id) {}

    @ReactMethod
    public void scrollToBottom(int id, boolean b) {}

    @ReactMethod
    public void scrollToTop(int id, boolean b) {}
}
