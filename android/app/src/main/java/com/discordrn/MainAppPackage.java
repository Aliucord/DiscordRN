package com.discordrn;

import androidx.annotation.NonNull;

import com.discordrn.modules.*;
import com.discordrn.views.*;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.List;

public class MainAppPackage implements ReactPackage {
    @SuppressWarnings("rawtypes")
    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactContext) {
        List<ViewManager> views = new ArrayList<>();

        views.add(new DCDSegmentedControl());
        views.add(new ViewStub("DCDSafeArea"));
        // views.add(new ViewStub("DCDChatList"));
        views.add(new DCDChatList());
        views.add(new DCDChat());
        views.add(new DCDChatInput());
        views.add(new ViewStub("ChannelSpine"));
        views.add(new ViewStub("KeyCommandsView")); // https://github.com/envoy/react-native-key-commands, ios only
        views.add(new NativeLottieNode());

        return views;
    }

    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();

        modules.add(new ActionSheetAndroid(reactContext));
        modules.add(new BrowserManager(reactContext));
        modules.add(new BundleUpdaterManager(reactContext));
        modules.add(new CaptchaManager(reactContext));
        modules.add(new DCDChatInputManager(reactContext));
        modules.add(new DCDChatManager(reactContext));
        modules.add(new DCDColor(reactContext));
        modules.add(new DCDCompressionManager(reactContext));
        modules.add(new DCDCrashlyticsCrashReports(reactContext));
        modules.add(new DCDDeviceManager(reactContext));
        modules.add(new DCDFastConnectManager(reactContext));
        modules.add(new DCDIconManager(reactContext));
        modules.add(new DCDNotificationCategoryUtils(reactContext));
        modules.add(new DCDSafeAreaManager(reactContext));
        modules.add(new DCDSKAdNetworkManager(reactContext));
        modules.add(new DCDTheme(reactContext));
        modules.add(new DynamicLinkManager(reactContext));
        modules.add(new ExpoRandom(reactContext));
        modules.add(new ImageManager(reactContext));
        modules.add(new InfoDictionaryManager(reactContext));
        modules.add(new IntentsHandler(reactContext));
        modules.add(new KeyboardManager(reactContext));
        modules.add(new KeyCommandsModule(reactContext));
        modules.add(new LocalizationManager(reactContext));
        modules.add(new MediaManager(reactContext));
        modules.add(new MMKVManager(reactContext, "DCDStrongboxManager"));
        modules.add(new MMKVManager(reactContext, "MMKVManager"));
        modules.add(new NativePermissionManager(reactContext));
        modules.add(new OnePasswordManager(reactContext));
        modules.add(new ProximitySensorManager(reactContext));
        modules.add(new PushNotificationAndroid(reactContext));
        modules.add(new RNCPushNotificationIOS(reactContext));
        modules.add(new RNSentryModule(reactContext));
        modules.add(new ScreenshotHelper(reactContext));
        modules.add(new TimersModule(reactContext));
        modules.add(new TTIManager(reactContext));
        modules.add(new UserSearchWorkerManager(reactContext));
        modules.add(new VoiceEngine(reactContext));

        return modules;
    }

}
