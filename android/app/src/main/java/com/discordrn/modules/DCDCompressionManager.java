package com.discordrn.modules;

import android.util.Log;

import androidx.annotation.NonNull;

import com.discordrn.zlib.ZLibWebSocketListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.websocket.WebSocketModule;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.ws.RealWebSocket;

@ReactModule(name = "DCDCompressionManager")
public class DCDCompressionManager extends ReactContextBaseJavaModule {
    private static final String TAG = "CompressionManager";

    private final Map<Integer, String> identities = new ConcurrentHashMap<>();

    public DCDCompressionManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "DCDCompressionManager";
    }

    @SuppressWarnings("unchecked")
    public static RealWebSocket getWebSocket(ReactApplicationContext context, int socketId) {
        WebSocketModule webSocketModule = context.getNativeModule(WebSocketModule.class);

        try {
            Field field = WebSocketModule.class.getDeclaredField("mWebSocketConnections");
            field.setAccessible(true);
            Map<Integer, WebSocket> connections = (Map<Integer, WebSocket>) Objects.requireNonNull(field.get(webSocketModule));

            return (RealWebSocket) connections.get(socketId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private RealWebSocket getWebSocket(int socketId) {
        return getWebSocket(getReactApplicationContext(), socketId);
    }

    private WebSocketListener getListener(RealWebSocket webSocket) {
        try {
            Field field = RealWebSocket.class.getDeclaredField("listener");
            field.setAccessible(true);

            return (WebSocketListener) Objects.requireNonNull(field.get(webSocket));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setListener(RealWebSocket webSocket, WebSocketListener listener) {
        try {
            Field field = RealWebSocket.class.getDeclaredField("listener");
            field.setAccessible(true);
            field.set(webSocket, listener);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setIdentify(int socketId, String json) {
        identities.put(socketId, json);
    }

    @ReactMethod
    public void enableZlibStreamSupport(int socketId) {
        Log.i(TAG, "enableZlibStreamSupport: " + socketId);

        RealWebSocket webSocket = getWebSocket(socketId);
        if (webSocket == null) {
            Log.w(TAG, "NULL " + socketId);
            return;
        }

        WebSocketListener listener = getListener(webSocket);

        if (listener instanceof ZLibWebSocketListener) {
            return;
        }

        setListener(webSocket, new ZLibWebSocketListener(listener));

        if (identities.containsKey(socketId)) {
            webSocket.send(identities.get(socketId));
            identities.remove(socketId);
        }
    }

    @ReactMethod
    public void disableZlibStreamSupport(int socketId) {
        Log.i(TAG, "disableZlibStreamSupport: " + socketId);

        identities.remove(socketId);

        RealWebSocket webSocket = getWebSocket(socketId);
        if (webSocket == null) {
            Log.w(TAG, "NULL " + socketId);
            return;
        }

        WebSocketListener listener = getListener(webSocket);

        if (listener instanceof ZLibWebSocketListener) {
            setListener(webSocket, ((ZLibWebSocketListener) listener).getListener());
        }
    }
}
