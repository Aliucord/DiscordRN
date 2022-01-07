package com.discordrn;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.facebook.common.logging.FLog;
import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.debug.holder.Printer;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.hermes.reactexecutor.HermesExecutorFactory;
import com.facebook.react.CustomReactInstanceManager;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSBundleLoaderDelegate;
import com.facebook.react.common.LifecycleState;
import com.facebook.soloader.SoLoader;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {
    private final ReactNativeHost mReactNativeHost =
            new ReactNativeHost(this) {
                @Override
                public boolean getUseDeveloperSupport() {
                    return BuildConfig.DEBUG;
                }

                @Override
                protected List<ReactPackage> getPackages() {
                    List<ReactPackage> packages = new PackageList(this).getPackages();
                    packages.add(new MainAppPackage());
                    return packages;
                }

                @Override
                protected String getJSMainModuleName() {
                    return "index";
                }

                @Override
                protected String getBundleAssetName() {
                    return "discord.android.bundle";
                }

                @Override
                protected ReactInstanceManager createReactInstanceManager() {
                    return new CustomReactInstanceManager(
                            getApplication(),
                            null,
                            null,
                            new HermesExecutorFactory(),
                            new JSBundleLoader() {
                                @Override
                                public String loadScript(JSBundleLoaderDelegate delegate) {
                                    AssetManager assetManager = getApplicationContext().getAssets();

                                    delegate.loadScriptFromAssets(assetManager, "assets://preload.js", true);

                                    String assetUrl = "assets://" + getBundleAssetName();
                                    delegate.loadScriptFromAssets(assetManager, assetUrl, false);
                                    return assetUrl;
                                }
                            },
                            getJSMainModuleName(),
                            getPackages(),
                            getUseDeveloperSupport(),
                            null,
                            LifecycleState.BEFORE_CREATE,
                            getUIImplementationProvider(),
                            null,
                            null,
                            false,
                            null,
                            -1,
                            -1,
                            getJSIModulePackage(),
                            null
                    );
                }
            };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FLog.setMinimumLoggingLevel(Log.VERBOSE);
        SoLoader.init(this, false);
        initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
        PrinterHolder.setPrinter(new Printer() {
            @Override
            public void logMessage(DebugOverlayTag tag, String message, Object... args) {
                Log.v("Printer", String.format("[%s] %s", tag.name, String.format(message, args)));
            }

            @Override
            public void logMessage(DebugOverlayTag tag, String message) {
                Log.v("Printer", String.format("[%s] %s", tag.name, message));
            }

            @Override
            public boolean shouldDisplayLogMessage(DebugOverlayTag tag) {
                return true;
            }
        });
    }

    private static void initializeFlipper(Context context, ReactInstanceManager reactInstanceManager) {
        if (BuildConfig.DEBUG) {
            try {
                Class.forName("com.discordrn.ReactNativeFlipper")
                        .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
                        .invoke(null, context, reactInstanceManager);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}
