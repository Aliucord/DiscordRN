package com.discordrn.zlib;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public final class ZLibWebSocketListener extends WebSocketListener {
    private final Inflater inflater = new Inflater();
    private final WebSocketListener listener;
    private final Inflater loggingInflater = new Inflater();

    public static final class ZLibByteStream extends ByteArrayOutputStream {
        private static final int INFLATE_BUFFER_SIZE = 4096;
        private static final int INITIAL_BUFFER_SIZE_MULTIPLIER = 2;
        private static final CharsetDecoder UTF8_DECODER = StandardCharsets.UTF_8.newDecoder();

        public ZLibByteStream(ByteString byteString, Inflater inflater) throws IOException {
            super(byteString.size() * INITIAL_BUFFER_SIZE_MULTIPLIER);
            InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(this, inflater, INFLATE_BUFFER_SIZE);
            byteString.write(inflaterOutputStream);
            inflaterOutputStream.flush();
        }

        public final InputStreamReader toReader() {
            return new InputStreamReader(new ByteArrayInputStream(this.buf, 0, this.count), UTF8_DECODER);
        }
    }

    public ZLibWebSocketListener(WebSocketListener listener) {
        this.listener = listener;
    }

    private void resetInflaters() {
        this.inflater.reset();
        this.loggingInflater.reset();
    }

    public final WebSocketListener getListener() {
        return this.listener;
    }

    @Override
    public void onClosed(@NonNull WebSocket webSocket, int i, @NonNull String str) {
        resetInflaters();
        this.listener.onClosed(webSocket, i, str);
    }

    @Override
    public void onClosing(@NonNull WebSocket webSocket, int i, @NonNull String str) {
        resetInflaters();
        this.listener.onClosing(webSocket, i, str);
    }

    @Override
    public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable th, Response response) {
        resetInflaters();
        this.listener.onFailure(webSocket, th, response);
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull String str) {
        Log.w("ZLibWebSocketListener", "onMessage: " + str);
        this.listener.onMessage(webSocket, str);
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString byteString) {
        try {
            Log.w("ZLibWebSocketListener", "onMessage: " + byteString.hex());
            try (InputStreamReader reader = new ZLibByteStream(byteString, this.loggingInflater).toReader()) {
                String readText = ReadWrite.readText(reader);
                Log.w("ZLibWebSocketListener", "onMessage: " + readText);
                listener.onMessage(webSocket, readText);
            }
        } catch (IOException e) {
            Log.w("ZLibWebSocketListener", "onMessage: " + e);
        }
    }

    @Override
    public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
        resetInflaters();
        this.listener.onOpen(webSocket, response);
    }
}
