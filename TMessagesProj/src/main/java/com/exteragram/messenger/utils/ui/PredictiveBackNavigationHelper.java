package com.exteragram.messenger.utils.ui;

import android.view.View;
import android.view.ViewTreeObserver;
import android.window.OnBackInvokedCallback;
import com.exteragram.messenger.plugins.PluginsController;
import java.util.function.BooleanSupplier;
import org.telegram.ui.ActionBar.ActionBarLayout;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.Components.GroupCallPip;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.MainTabsActivity;
import org.telegram.ui.ViewPagerActivity;

public final class PredictiveBackNavigationHelper implements ViewTreeObserver.OnPreDrawListener, ViewTreeObserver.OnWindowFocusChangeListener {
    private static final Class<?>[] BACK_HANDLER_CLASSES = {LaunchActivity.class, ActionBarLayout.class, BaseFragment.class, ViewPagerActivity.class, MainTabsActivity.class, DialogsActivity.class};
    private final LaunchActivity activity;
    private final OnBackInvokedCallback callback;
    private final BooleanSupplier canUseSystemBack;
    private final View decorView;
    private boolean detached;
    private boolean gestureInProgress;
    private boolean registered;

    public PredictiveBackNavigationHelper(final LaunchActivity launchActivity, OnBackInvokedCallback onBackInvokedCallback, final BooleanSupplier booleanSupplier) {
        this.activity = launchActivity;
        View decorView = launchActivity.getWindow().getDecorView();
        this.decorView = decorView;
        this.callback = onBackInvokedCallback;
        this.canUseSystemBack = new BooleanSupplier() {
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return PredictiveBackNavigationHelper.m2186$r8$lambda$qmnPV7dc0Iem4Y27aZdk7hEilU(booleanSupplier, launchActivity);
            }
        };
        decorView.getViewTreeObserver().addOnPreDrawListener(this);
        decorView.getViewTreeObserver().addOnWindowFocusChangeListener(this);
        update();
    }

    public static boolean m2186$r8$lambda$qmnPV7dc0Iem4Y27aZdk7hEilU(BooleanSupplier booleanSupplier, LaunchActivity launchActivity) {
        return !booleanSupplier.getAsBoolean() && canUseSystemBack(launchActivity);
    }

    private static boolean canUseSystemBack(LaunchActivity launchActivity) {
        INavigationLayout actionBarLayout = launchActivity.getActionBarLayout();
        if ((actionBarLayout instanceof ActionBarLayout) && ((ActionBarLayout) actionBarLayout).canUseSystemBack() && !hasFragments(launchActivity.getLayersActionBarLayout()) && !hasFragments(launchActivity.getRightActionBarLayout()) && !PluginsController.getInstance().hasXposedHook("onBackPressed", BACK_HANDLER_CLASSES) && !GroupCallPip.onBackPressed(false) && launchActivity.onBackPressed(false)) {
            BaseFragment lastFragment = actionBarLayout.getLastFragment();
            if (lastFragment instanceof MainTabsActivity) {
                return ((MainTabsActivity) lastFragment).canUseSystemBack();
            }
            if ((lastFragment instanceof DialogsActivity) && ((DialogsActivity) lastFragment).canUseSystemBack()) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasFragments(INavigationLayout iNavigationLayout) {
        return (iNavigationLayout == null || iNavigationLayout.getFragmentStack().isEmpty()) ? false : true;
    }

    public static void invalidate() {
        LaunchActivity launchActivity = LaunchActivity.instance;
        if (launchActivity != null) {
            launchActivity.getWindow().getDecorView().invalidate();
        }
    }

    public void setGestureInProgress(boolean z) {
        this.gestureInProgress = z;
        if (z) {
            return;
        }
        this.decorView.invalidate();
    }

    private void update() {
        boolean asBoolean;
        boolean z;
        if (this.detached || this.gestureInProgress || this.activity.isFinishing() || this.activity.isDestroyed() || this.registered == (z = !(asBoolean = this.canUseSystemBack.getAsBoolean()))) {
            return;
        }
        LaunchActivity launchActivity = this.activity;
        if (!asBoolean) {
            launchActivity.getOnBackInvokedDispatcher().registerOnBackInvokedCallback(0, this.callback);
        } else {
            launchActivity.getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.callback);
        }
        this.registered = z;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        update();
        return true;
    }

    @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
    public void onWindowFocusChanged(boolean z) {
        if (z) {
            update();
        }
    }

    public void detach() {
        this.detached = true;
        ViewTreeObserver viewTreeObserver = this.decorView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(this);
            viewTreeObserver.removeOnWindowFocusChangeListener(this);
        }
        if (this.registered) {
            this.activity.getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(this.callback);
            this.registered = false;
        }
    }
}
