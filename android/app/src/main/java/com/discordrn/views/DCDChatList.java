package com.discordrn.views;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        public final List<String> data = new ArrayList<>();

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(new TextView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(data.get(position));
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
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new Adapter());
        return recyclerView;
    }
}
