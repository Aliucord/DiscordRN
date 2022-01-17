package com.discordrn.modules;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.discordrn.models.MessageContent;
import com.discordrn.models.Row;
import com.discordrn.views.DCDChatList;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.UIManagerModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class DCDChatManager extends ReactContextBaseJavaModule {
    private static final Gson gson;
    private static final Type rowsType = TypeToken.getParameterized(List.class, Row.class).getType();

    static {
        RuntimeTypeAdapterFactory<MessageContent> contentFactory = RuntimeTypeAdapterFactory.of(MessageContent.class, "type");
        contentFactory.registerSubtype(MessageContent.Text.class, "text");
        contentFactory.registerSubtype(MessageContent.Link.class, "link");
        contentFactory.registerSubtype(MessageContent.CodeBlock.class, "codeBlock");
        contentFactory.registerSubtype(MessageContent.Mention.class, "mention");
        contentFactory.registerSubtype(MessageContent.CustomEmoji.class, "customEmoji");
        contentFactory.registerSubtype(MessageContent.Strong.class, "strong");
        contentFactory.registerSubtype(MessageContent.Em.class, "em");
        contentFactory.registerSubtype(MessageContent.InlineCode.class, "inlineCode");

        gson = new GsonBuilder()
                .registerTypeAdapterFactory(contentFactory)
                .create();
    }

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
            for (Row row : rows) {
                if (row.message == null || row.message.content == null)
                    continue;

                for (MessageContent content : row.message.content) {
                    if (content instanceof MessageContent.Text) {
                        adapter.data.add(((MessageContent.Text) content).content);
                    }
                }
            }
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
