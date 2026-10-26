package com.exteragram.messenger.plugins.ui.components;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class InstallPluginBottomSheet$analysisSection$1 extends FunctionReferenceImpl implements Function0<Unit> {
    public InstallPluginBottomSheet$analysisSection$1(Object obj) {
        super(0, obj, InstallPluginBottomSheet.class, "onAnalysisChanged", "onAnalysisChanged()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    public final void invoke2() {
        ((InstallPluginBottomSheet) this.receiver).onAnalysisChanged();
    }
}
