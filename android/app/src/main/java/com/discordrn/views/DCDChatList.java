package com.discordrn.views;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.discordrn.models.Message;
import com.discordrn.models.MessageContent;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.ArrayList;
import java.util.List;

public class DCDChatList extends SimpleViewManager<RecyclerView> {
    public static final class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        public static final class ViewHolder extends RecyclerView.ViewHolder {
            public final TextView textView;

            public ViewHolder(@NonNull TextView itemView) {
                super(itemView);
                textView = itemView;
            }
        }

        public final List<Message> data = new ArrayList<>();

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(new TextView(parent.getContext()));
        }

        @SuppressWarnings("StringConcatenationInLoop")
        private String parseMessageContentToString(List<MessageContent> contentList) {
            String msgString = "";
            for (MessageContent c : contentList) {
                if (c instanceof MessageContent.Text)
                    msgString += ((MessageContent.Text) c).content;
                if (c instanceof MessageContent.Link)
                    msgString += parseMessageContentToString(((MessageContent.Link) c).content);
                if (c instanceof MessageContent.CodeBlock)
                    msgString += "\n```" + ((MessageContent.CodeBlock) c).content + "\n```\n";
                if (c instanceof MessageContent.Mention)
                    msgString += parseMessageContentToString(((MessageContent.Mention) c).content);
                if (c instanceof MessageContent.Emoji)
                    msgString += ((MessageContent.Emoji) c).content;
                if (c instanceof MessageContent.CustomEmoji)
                    msgString += ":" + ((MessageContent.CustomEmoji) c).alt + ":";
                if (c instanceof MessageContent.Channel)
                    msgString += "#" + parseMessageContentToString(((MessageContent.Channel) c).content);
                if (c instanceof MessageContent.Strong)
                    msgString += "**" + parseMessageContentToString(((MessageContent.Strong) c).content) + "**";
                if (c instanceof MessageContent.Emphasis)
                    msgString += "*" + parseMessageContentToString(((MessageContent.Emphasis) c).content) + "*";
                if (c instanceof MessageContent.Stroke)
                    msgString += "*" + parseMessageContentToString(((MessageContent.Stroke) c).content) + "*";
                if (c instanceof MessageContent.Underlined)
                    msgString += "*" + parseMessageContentToString(((MessageContent.Underlined) c).content) + "*";
                if (c instanceof MessageContent.InlineCode)
                    msgString += "``" + ((MessageContent.InlineCode) c).content + "``";
            }
            return msgString;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Message msg = data.get(position);
            if (msg != null) {
                String msgString = "[" + msg.username + "] " + parseMessageContentToString(msg.content);

                holder.textView.setText(msgString);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDChatList";
    }

    @NonNull
    @Override
    protected RecyclerView createViewInstance(@NonNull ThemedReactContext context) {
        RecyclerView recyclerView = new RecyclerView(context);
        // note: most of theese arent doing anything, still need to figure out why it wont grow to fill the space
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new Adapter());
        recyclerView.setHasFixedSize(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        return recyclerView;
    }
}
