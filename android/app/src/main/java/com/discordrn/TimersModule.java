package com.discordrn; // replace com.your-app-name with your app’s name
import android.os.Handler;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.TimingModule;

import java.util.*;

public class TimersModule extends ReactContextBaseJavaModule {
    TimersModule(ReactApplicationContext context) {
       super(context);
   }

   @Override
   public String getName() {
       return "TimersModule";
   }

   @ReactMethod
   public void setTimeout(Callback cb, int t) {
        new Handler().postDelayed(cb::invoke, t);
   }
   @ReactMethod
   public void setInterval(Callback cb, int t) {
       new Timer().scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
             cb.invoke();
          }
       }, 0, t);
   }
   @ReactMethod
   public void clearTimeout(int t) {}

   @Override
   public Map<String, Object> getConstants() {
      final Map<String, Object> constants = new HashMap<>();
    //   constants.put("", "");
      return constants;
   }
}
