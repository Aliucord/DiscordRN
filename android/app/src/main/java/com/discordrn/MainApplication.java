package com.discordrn;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.debug.holder.Printer;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSBundleLoaderDelegate;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
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
                    @SuppressWarnings("UnnecessaryLocalVariable")
                    List<ReactPackage> packages = new PackageList(this).getPackages();
                    // Packages that cannot be autolinked yet can be added manually here, for example:
                    // packages.add(new MyReactNativePackage());
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
                    ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_START);
                    ReactInstanceManagerBuilder builder =
                            ReactInstanceManager.builder()
                                    .setApplication(getApplication())
                                    .setJSMainModulePath(getJSMainModuleName())
                                    .setUseDeveloperSupport(getUseDeveloperSupport())
                                    .setRedBoxHandler(getRedBoxHandler())
                                    .setJavaScriptExecutorFactory(getJavaScriptExecutorFactory())
                                    .setUIImplementationProvider(getUIImplementationProvider())
                                    .setJSIModulesPackage(getJSIModulePackage())
                                    .setInitialLifecycleState(LifecycleState.BEFORE_CREATE);

                    for (ReactPackage reactPackage : getPackages()) {
                        builder.addPackage(reactPackage);
                    }

                    builder.setJSBundleLoader(new JSBundleLoader() {
                        @Override
                        public String loadScript(JSBundleLoaderDelegate delegate) {
                            AssetManager assetManager = getApplicationContext().getAssets();

                            delegate.loadScriptFromAssets(assetManager, "assets://preload.js", true);

                            String assetUrl = "assets://" + getBundleAssetName();
                            delegate.loadScriptFromAssets(assetManager, assetUrl, false);
                            return assetUrl;
                        }
                    });

                    ReactInstanceManager reactInstanceManager = builder.build();
                    ReactMarker.logMarker(ReactMarkerConstants.BUILD_REACT_INSTANCE_MANAGER_END);
                    return reactInstanceManager;
                }
            };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
        PrinterHolder.setPrinter(new Printer() {
            @Override
            public void logMessage(DebugOverlayTag tag, String message, Object... args) {
                Log.d("Printer", String.format("[%s] %s", tag.name, String.format(message, args)));
            }

            @Override
            public void logMessage(DebugOverlayTag tag, String message) {
                Log.d("Printer", String.format("[%s] %s", tag.name, message));
            }

            @Override
            public boolean shouldDisplayLogMessage(DebugOverlayTag tag) {
                return true;
            }
        });
    }

    /**
     * Loads Flipper in React Native templates. Call this in the onCreate method with something like
     * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
     *
     * @param context
     * @param reactInstanceManager
     */
    private static void initializeFlipper(
            Context context, ReactInstanceManager reactInstanceManager) {
        if (BuildConfig.DEBUG) {
            try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
                Class<?> aClass = Class.forName("com.discordrn.ReactNativeFlipper");
                aClass
                        .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
                        .invoke(null, context, reactInstanceManager);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
