package com.discordrn.modules;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.palette.graphics.Palette;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeArray;

import java.io.IOException;
import java.net.URL;

public class ImageManager extends ReactContextBaseJavaModule {
    public ImageManager(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "ImageManager";
    }

    @ReactMethod
    public void getDominantColor(ReadableMap image, Promise promise) {
        try {
            URL url = new URL(image.getString("uri"));
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            getDominantColor(bitmap, promise);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ReactMethod
    public void getDominantColorLocalAsset(ReadableMap image, Promise promise) {
        ReactApplicationContext context = getReactApplicationContext();
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier(image.getString("uri"), "drawable", context.getPackageName());
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId);

        getDominantColor(bitmap, promise);
    }

    private void getDominantColor(Bitmap bitmap, Promise promise) {
        new Palette.Builder(bitmap).generate(palette -> {
            int color = palette == null ? Color.BLACK : palette.getDominantColor(Color.BLACK);
            int r = (color >> 16) & 0xFF;
            int g = (color >> 8) & 0xFF;
            int b = (color) & 0xFF;

            WritableNativeArray colorArray = new WritableNativeArray();
            colorArray.pushInt(r);
            colorArray.pushInt(g);
            colorArray.pushInt(b);
            promise.resolve(colorArray);
        });
    }
}
