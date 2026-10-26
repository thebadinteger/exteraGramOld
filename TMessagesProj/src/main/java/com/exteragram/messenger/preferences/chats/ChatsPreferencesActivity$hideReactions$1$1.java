package com.exteragram.messenger.preferences.chats;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final class ChatsPreferencesActivity$hideReactions$1$1 extends FunctionReferenceImpl implements Function0<Unit> {
    public ChatsPreferencesActivity$hideReactions$1$1(Object obj) {
        super(0, obj, ChatsPreferencesActivity.class, "updateReplySettings", "updateReplySettings()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    public final void invoke2() {
        ((ChatsPreferencesActivity) this.receiver).updateReplySettings();
    }
}
