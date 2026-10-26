package com.exteragram.messenger.preferences.appearance;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class AppearancePreferencesActivity$md3Styles$1$1 extends FunctionReferenceImpl implements Function0<Unit> {
    public AppearancePreferencesActivity$md3Styles$1$1(Object obj) {
        super(0, obj, AppearancePreferencesActivity.class, "updateMD3Styles", "updateMD3Styles()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    public final void invoke2() {
        ((AppearancePreferencesActivity) this.receiver).updateMD3Styles();
    }
}
