package com.discordrn; // replace com.your-app-name with your app’s name
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class DCDFastConnectManager extends ReactContextBaseJavaModule {
   DCDFastConnectManager(ReactApplicationContext context) {
       super(context);
   }

   @Override
   public String getName() {
       return "DCDFastConnectManager";
   }

   @ReactMethod
   public void setClientState(String state) {}

   @Override
   public Map<String, Object> getConstants() {
      final Map<String, Object> constants = new HashMap<>();
      constants.put("token", "");
      constants.put("clientState", "{}");
      return constants;
   }
}