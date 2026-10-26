package com.exteragram.messenger.icons;

import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$handleIconPack$1$1$bottomSheet$1$1", f = "IconManager.kt", i = {1}, l = {WebSocketProtocol.CLOSE_NO_STATUS_CODE, 1006}, m = "invokeSuspend", n = {"installResult"}, s = {"L$0"})
public final class IconManager$handleIconPack$1$1$bottomSheet$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final BaseFragment $baseFragment;
    final boolean $enable;
    final File $file;
    final IconPack $pack;
    final boolean $update;
    Object L$0;
    int label;

    public IconManager$handleIconPack$1$1$bottomSheet$1$1(File file, BaseFragment baseFragment, boolean z, IconPack iconPack, boolean z2, Continuation<? super IconManager$handleIconPack$1$1$bottomSheet$1$1> continuation) {
        super(2, continuation);
        this.$file = file;
        this.$baseFragment = baseFragment;
        this.$update = z;
        this.$pack = iconPack;
        this.$enable = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new IconManager$handleIconPack$1$1$bottomSheet$1$1(this.$file, this.$baseFragment, this.$update, this.$pack, this.$enable, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((IconManager$handleIconPack$1$1$bottomSheet$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = IconPackStorage.INSTANCE.installPack(this.$file, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        IconPackStorageResult<Unit> installResult = (IconPackStorageResult<Unit>) obj;
        this.L$0 = installResult;
        this.label = 2;
        if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass1(installResult, this.$baseFragment, this.$update, this.$pack, this.$enable, null), this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$handleIconPack$1$1$bottomSheet$1$1$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final BaseFragment $baseFragment;
        final boolean $enable;
        final IconPackStorageResult<Unit> $installResult;
        final IconPack $pack;
        final boolean $update;
        int label;

        public AnonymousClass1(IconPackStorageResult<Unit> iconPackStorageResult, BaseFragment baseFragment, boolean z, IconPack iconPack, boolean z2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$installResult = iconPackStorageResult;
            this.$baseFragment = baseFragment;
            this.$update = z;
            this.$pack = iconPack;
            this.$enable = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$installResult, this.$baseFragment, this.$update, this.$pack, this.$enable, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            IconPackStorageResult<Unit> iconPackStorageResult = this.$installResult;
            if (iconPackStorageResult instanceof IconPackStorageResult.Success) {
                BulletinFactory.of(this.$baseFragment).createSimpleBulletin(R.raw.contact_check, LocaleController.formatString(this.$update ? R.string.PluginUpdated : R.string.PluginInstalled, this.$pack.getName())).show();
                if (this.$enable) {
                    IconManager.INSTANCE.setActiveCustomPack(this.$pack.getId());
                } else {
                    IconManager iconManager = IconManager.INSTANCE;
                    iconManager.syncInstalledCustomPacks(CollectionsKt.listOf(this.$pack));
                    iconManager.initialize(true);
                }
            } else if (iconPackStorageResult instanceof IconPackStorageResult.Failure) {
                IconManager.INSTANCE.showIconPackError(this.$baseFragment, ((IconPackStorageResult.Failure) iconPackStorageResult).getError());
            } else {
                throw new kotlin.NoWhenBranchMatchedException();
                return null;
            }
            return Unit.INSTANCE;
        }
    }
}
