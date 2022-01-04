package com.discordrn; // replace your-app-name with your app’s name

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainAppPackage implements ReactPackage {

   @NonNull
   @Override
   public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
       return Collections.emptyList();
   }

   @NonNull
   @Override
   public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
       List<NativeModule> modules = new ArrayList<>();

       modules.add(new DCDFastConnectManager(reactContext));
       modules.add(new InfoDictionaryManager(reactContext));
       modules.add(new RNSentryModule(reactContext));
       modules.add(new NativePermissionManager(reactContext));
       modules.add(new ExpoRandom(reactContext));
       modules.add(new VoiceEngine(reactContext));
       modules.add(new DCDDeviceManager(reactContext));
       modules.add(new TimersModule(reactContext));
       modules.add(new TTIManager(reactContext));
       modules.add(new BundleUpdaterManager(reactContext));
       modules.add(new PushNotificationAndroid(reactContext));
       modules.add(new ScreenshotHelper(reactContext));
       modules.add(new DCDStrongboxManager(reactContext));
       modules.add(new MMKVManager(reactContext));

       return modules;
   }

}
