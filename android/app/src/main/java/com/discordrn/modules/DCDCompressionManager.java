package com.discordrn.modules;

import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.websocket.WebSocketModule;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.zip.Inflater;

import okio.Buffer;
import okio.ByteString;
import okio.InflaterSource;

public class DCDCompressionManager extends ReactContextBaseJavaModule {
    private static final String TAG = "CompressionManager";

    public DCDCompressionManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDCompressionManager";
    }

    private WebSocketModule getSocketModule() {
        return getReactApplicationContext().getNativeModule(WebSocketModule.class);
    }

    private static final ByteString FLUSH = ByteString.of((byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0xFF);

    private Set<Integer> enabled = new HashSet<>();

    @ReactMethod
    public void enableZlibStreamSupport(int socketId) {
        Log.i(TAG, "enableZlibStreamSupport: " + socketId);

        if (enabled.contains(socketId))
            return;

        enabled.add(socketId);

        Buffer buffer = new Buffer();
        Buffer result = new Buffer();
        Inflater inflater = new Inflater();

        WebSocketModule webSocketModule = getSocketModule();
        webSocketModule.setContentHandler(socketId, new WebSocketModule.ContentHandler() {
            @Override
            public void onMessage(String text, WritableMap params) {
                params.putString("data", text);
            }

            @Override
            public void onMessage(ByteString byteString, WritableMap params) {
                buffer.write(byteString);

                long size = buffer.size();
                if (size < 4 || !buffer.rangeEquals(size - 4, FLUSH))
                    return;

                InflaterSource source = new InflaterSource(buffer, inflater);
                result.clear();

                try {
                    try {
                        while (source.read(result, Long.MAX_VALUE) != -1) {
                        }
                    } catch (EOFException ignored) {
                    }
                } catch (IOException e) {
                    Log.e(TAG, "inflating failed: " + e);
                }

                buffer.clear();

                String json = result.snapshot().utf8();

                Log.d(TAG, "received: " + json);
                params.putString("type", "text");
                params.putString("data", json);

                try {
                    int op = new JSONObject(json).getInt("op");
                    if (op == 10) {
                        Map<Integer, String> identities = Objects.requireNonNull(getReactApplicationContext().getNativeModule(DCDFastConnectManager.class)).getIdentities();

                        if (identities.containsKey(socketId)) {
                            String identity = identities.get(socketId);
                            Log.d(TAG, "sending: " + identity);
                            webSocketModule.send(identity, socketId);
                        }
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "failed to parse json: " + e);
                }
            }
        });
    }

    @ReactMethod
    public void disableZlibStreamSupport(int socketId) {
        Log.i(TAG, "disableZlibStreamSupport: " + socketId);

        if (!enabled.contains(socketId))
            return;

        enabled.remove(socketId);
        WebSocketModule webSocketModule = getSocketModule();
        webSocketModule.setContentHandler(socketId, null);
    }
}
