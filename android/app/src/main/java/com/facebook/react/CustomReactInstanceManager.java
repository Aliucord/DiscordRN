package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.ErrorCustomizer;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.uimanager.UIImplementationProvider;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class CustomReactInstanceManager extends ReactInstanceManager {
    public CustomReactInstanceManager(Context applicationContext, @Nullable Activity currentActivity, @Nullable DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler, JavaScriptExecutorFactory javaScriptExecutorFactory, @Nullable JSBundleLoader bundleLoader, @Nullable String jsMainModulePath, List<ReactPackage> packages, boolean useDeveloperSupport, @Nullable NotThreadSafeBridgeIdleDebugListener bridgeIdleDebugListener, LifecycleState initialLifecycleState, @Nullable UIImplementationProvider mUIImplementationProvider, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler, @Nullable RedBoxHandler redBoxHandler, boolean lazyViewManagersEnabled, @Nullable DevBundleDownloadListener devBundleDownloadListener, int minNumShakes, int minTimeLeftInFrameForNonBatchedOperationMs, @Nullable JSIModulePackage jsiModulePackage, @Nullable Map<String, RequestHandler> customPackagerCommandHandlers) {
        super(applicationContext, currentActivity, defaultHardwareBackBtnHandler, javaScriptExecutorFactory, bundleLoader, jsMainModulePath, packages, useDeveloperSupport, bridgeIdleDebugListener, initialLifecycleState, mUIImplementationProvider, nativeModuleCallExceptionHandler, redBoxHandler, lazyViewManagersEnabled, devBundleDownloadListener, minNumShakes, minTimeLeftInFrameForNonBatchedOperationMs, jsiModulePackage, customPackagerCommandHandlers);

        try {
            Field field = ReactInstanceManager.class.getDeclaredField("mDevSupportManager");
            field.setAccessible(true);
            field.set(this, new DevSupportManagerWrapper(getDevSupportManager()));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createReactContextInBackground() {
        if (!hasStartedCreatingInitialContext()) {
            try {
                Field field = ReactInstanceManager.class.getDeclaredField("mHasStartedCreatingInitialContext");
                field.setAccessible(true);
                field.set(this, true);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            this.recreateReactContextInBackground();
        }
    }

    @Override
    public void recreateReactContextInBackground() {
        try {
            Method method = ReactInstanceManager.class.getDeclaredMethod("recreateReactContextInBackgroundFromBundleLoader");
            method.setAccessible(true);
            method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static class DevSupportManagerWrapper implements DevSupportManager {
        private static final String TAG = "DevSupportManagerWrapper";

        private final DevSupportManager wrapped;

        public DevSupportManagerWrapper(DevSupportManager wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public void showNewJavaError(String message, Throwable e) {
            wrapped.showNewJavaError(message, e);
        }

        @Override
        public void addCustomDevOption(String optionName, DevOptionHandler optionHandler) {
            wrapped.addCustomDevOption(optionName, optionHandler);
        }

        @Nullable
        @Override
        public View createRootView(String appKey) {
            return wrapped.createRootView(appKey);
        }

        @Override
        public void destroyRootView(View rootView) {
            wrapped.destroyRootView(rootView);
        }

        @Override
        public void showNewJSError(String message, ReadableArray details, int errorCookie) {
            wrapped.showNewJSError(message, details, errorCookie);
        }

        @Override
        public void updateJSError(String message, ReadableArray details, int errorCookie) {
            wrapped.updateJSError(message, details, errorCookie);
        }

        @Override
        public void hideRedboxDialog() {
            wrapped.hideRedboxDialog();
        }

        @Override
        public void showDevOptionsDialog() {
            wrapped.showDevOptionsDialog();
        }

        @Override
        public void setDevSupportEnabled(boolean isDevSupportEnabled) {
            wrapped.setDevSupportEnabled(isDevSupportEnabled);
        }

        @Override
        public void startInspector() {
            wrapped.startInspector();
        }

        @Override
        public void stopInspector() {
            wrapped.stopInspector();
        }

        @Override
        public boolean getDevSupportEnabled() {
            return wrapped.getDevSupportEnabled();
        }

        @Override
        public DeveloperSettings getDevSettings() {
            return wrapped.getDevSettings();
        }

        @Override
        public void onNewReactContextCreated(ReactContext reactContext) {
            wrapped.onNewReactContextCreated(reactContext);
        }

        @Override
        public void onReactInstanceDestroyed(ReactContext reactContext) {
            wrapped.onReactInstanceDestroyed(reactContext);
        }

        @Override
        public String getSourceMapUrl() {
            return wrapped.getSourceMapUrl();
        }

        @Override
        public String getSourceUrl() {
            return wrapped.getSourceUrl();
        }

        @Override
        public String getJSBundleURLForRemoteDebugging() {
            return wrapped.getJSBundleURLForRemoteDebugging();
        }

        @Override
        public String getDownloadedJSBundleFile() {
            return wrapped.getDownloadedJSBundleFile();
        }

        @Override
        public boolean hasUpToDateJSBundleInCache() {
            return wrapped.hasUpToDateJSBundleInCache();
        }

        @Override
        public void reloadSettings() {
            wrapped.reloadSettings();
        }

        @Override
        public void handleReloadJS() {
            Log.i(TAG, "ignored handleReloadJS");
//            wrapped.handleReloadJS();
        }

        @Override
        public void reloadJSFromServer(String bundleURL) {
            Log.i(TAG, "ignored reloadJSFromServer");
//            wrapped.reloadJSFromServer(bundleURL);
        }

        @Override
        public void loadSplitBundleFromServer(String bundlePath, DevSplitBundleCallback callback) {
            wrapped.loadSplitBundleFromServer(bundlePath, callback);
        }

        @Override
        public void isPackagerRunning(PackagerStatusCallback callback) {
            wrapped.isPackagerRunning(callback);
        }

        @Override
        public void setHotModuleReplacementEnabled(boolean isHotModuleReplacementEnabled) {
            wrapped.setHotModuleReplacementEnabled(isHotModuleReplacementEnabled);
        }

        @Override
        public void setRemoteJSDebugEnabled(boolean isRemoteJSDebugEnabled) {
            wrapped.setRemoteJSDebugEnabled(isRemoteJSDebugEnabled);
        }

        @Override
        public void setFpsDebugEnabled(boolean isFpsDebugEnabled) {
            wrapped.setRemoteJSDebugEnabled(isFpsDebugEnabled);
        }

        @Override
        public void toggleElementInspector() {
            wrapped.toggleElementInspector();
        }

        @Nullable
        @Override
        public File downloadBundleResourceFromUrlSync(String resourceURL, File outputFile) {
            return wrapped.downloadBundleResourceFromUrlSync(resourceURL, outputFile);
        }

        @Nullable
        @Override
        public String getLastErrorTitle() {
            return wrapped.getLastErrorTitle();
        }

        @Nullable
        @Override
        public StackFrame[] getLastErrorStack() {
            return wrapped.getLastErrorStack();
        }

        @Override
        public void registerErrorCustomizer(ErrorCustomizer errorCustomizer) {
            wrapped.registerErrorCustomizer(errorCustomizer);
        }

        @Override
        public void setPackagerLocationCustomizer(PackagerLocationCustomizer packagerLocationCustomizer) {
            wrapped.setPackagerLocationCustomizer(packagerLocationCustomizer);
        }

        @Override
        public void handleException(Exception e) {
            wrapped.handleException(e);
        }
    }
}
