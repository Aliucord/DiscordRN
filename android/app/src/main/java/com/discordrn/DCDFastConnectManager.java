package com.discordrn; // replace com.your-app-name with your app’s name

import com.facebook.react.bridge.*;

import java.util.HashMap;
import java.util.Map;

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
      constants.put("token", null);
      constants.put("clientState", "{}");
      return constants;
   }
}
