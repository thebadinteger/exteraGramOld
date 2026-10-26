package com.exteragram.messenger.plugins.catalog;

import android.app.Activity;
import com.exteragram.messenger.plugins.catalog.db.PluginRepositoryEntity;
import com.exteragram.messenger.plugins.catalog.ui.PluginCatalogActivity;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.BulletinFactory;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0005J \u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/PluginRepositoryLinks;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "build", _UrlKt.FRAGMENT_ENCODE_SET, "username", "open", _UrlKt.FRAGMENT_ENCODE_SET, "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "showAddAlert", "controller", "Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController;", "chat", "Lorg/telegram/tgnet/TLRPC$Chat;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginRepositoryLinks.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginRepositoryLinks.kt\ncom/exteragram/messenger/plugins/catalog/PluginRepositoryLinks\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,72:1\n1807#2,3:73\n*S KotlinDebug\n*F\n+ 1 PluginRepositoryLinks.kt\ncom/exteragram/messenger/plugins/catalog/PluginRepositoryLinks\n*L\n45#1:73,3\n*E\n"})
public final class PluginRepositoryLinks {
    public static final PluginRepositoryLinks INSTANCE = new PluginRepositoryLinks();

    private PluginRepositoryLinks() {
    }

    public final String build(String username) {
        return "https://t.me/exteraPluginRepo?c=" + username;
    }

    public final void open(final BaseFragment fragment, String username) {
        Activity parentActivity = fragment.getParentActivity();
        if (parentActivity == null) {
            return;
        }
        final AlertDialog alertDialog = new AlertDialog(parentActivity, 3);
        alertDialog.showDelayed(300L);
        final MessagesController messagesController = fragment.getMessagesController();
        messagesController.getUserNameResolver().resolve(username, new Utilities.Callback() {
            @Override // org.telegram.messenger.Utilities.Callback
            public final void run(Object obj) {
                PluginRepositoryLinks.$r8$lambda$yTOiJHudMSxsnf5qSgG9YPX3PBI(alertDialog, messagesController, fragment, (Long) obj);
            }
        });
    }

    public static void $r8$lambda$yTOiJHudMSxsnf5qSgG9YPX3PBI(AlertDialog alertDialog, MessagesController messagesController, final BaseFragment baseFragment, Long l) {
        alertDialog.dismiss();
        final TLRPC.Chat chat = (l == null || l.longValue() >= 0) ? null : messagesController.getChat(Long.valueOf(-l.longValue()));
        if (chat == null || !ChatObject.isChannel(chat)) {
            BulletinFactory.of(baseFragment).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.PluginCatalogChannelNotFound)).show();
        } else {
            final PluginCatalogController companion = PluginCatalogController.INSTANCE.getInstance(baseFragment.getCurrentAccount());
            companion.loadRepositories(new Function1() {
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PluginRepositoryLinks.$r8$lambda$PQ6f5Fb9nDxrVdoVUOgmIEKsdN4(baseFragment, chat, companion, (List) obj);
                }
            });
        }
    }

    public static Unit $r8$lambda$PQ6f5Fb9nDxrVdoVUOgmIEKsdN4(BaseFragment baseFragment, TLRPC.Chat chat, PluginCatalogController pluginCatalogController, List list) {
        List list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            INSTANCE.showAddAlert(baseFragment, pluginCatalogController, chat);
        } else {
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                if (((PluginRepositoryEntity) it.next()).getChannelId() == chat.id) {
                    baseFragment.presentFragment(new PluginCatalogActivity(chat.id));
                }
            }
            INSTANCE.showAddAlert(baseFragment, pluginCatalogController, chat);
        }
        return Unit.INSTANCE;
    }

    private final void showAddAlert(final BaseFragment fragment, final PluginCatalogController controller, final TLRPC.Chat chat) {
        Activity parentActivity = fragment.getParentActivity();
        if (parentActivity == null) {
            return;
        }
        fragment.showDialog(new AlertDialog.Builder(parentActivity, fragment.getResourceProvider()).setTitle(LocaleController.getString(R.string.PluginCatalogAddRepository)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.PluginCatalogAddRepositoryConfirm, chat.title))).setPositiveButton(LocaleController.getString(R.string.Add), new AlertDialog.OnButtonClickListener() {
            @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
            public final void onClick(AlertDialog alertDialog, int i) {
                PluginCatalogController pluginCatalogController = controller;
                TLRPC.Chat chat2 = chat;
                pluginCatalogController.addRepository(chat2, new Function1() {
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PluginRepositoryLinks.m1906$r8$lambda$FtDSmbU1Ole3bLvtyZQ6zjYPTc(pluginCatalogController, chat2, baseFragment, ((Boolean) obj).booleanValue());
                    }
                });
            }
        }).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create());
    }

    public static Unit m1906$r8$lambda$FtDSmbU1Ole3bLvtyZQ6zjYPTc(PluginCatalogController pluginCatalogController, TLRPC.Chat chat, BaseFragment baseFragment, boolean z) {
        if (z) {
            PluginCatalogController.syncRepository$default(pluginCatalogController, chat.id, true, false, null, 8, null);
        }
        baseFragment.presentFragment(new PluginCatalogActivity(chat.id));
        return Unit.INSTANCE;
    }
}
