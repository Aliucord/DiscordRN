// manually converted to java from https://github.com/expo/expo/blob/master/packages/expo-random/android/src/main/java/expo/modules/random/RandomModule.kt
package com.discordrn.modules;

import android.util.Base64;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.security.SecureRandom;

public class ExpoRandom extends ReactContextBaseJavaModule {
    private final SecureRandom secureRandom = new SecureRandom();

    public ExpoRandom(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "ExpoRandom";
    }

    @ReactMethod
    public void getRandomBase64StringAsync(int randomByteCount, Promise promise) {
        promise.resolve(getRandomBase64String(randomByteCount));
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getRandomBase64String(int randomByteCount) {
        byte[] output = new byte[randomByteCount];
        secureRandom.nextBytes(output);
        return Base64.encodeToString(output, Base64.NO_WRAP);
    }
}