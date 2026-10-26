package com.exteragram.messenger.plugins.catalog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\u0081\u0002\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0018B-\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0019"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/PluginCategory;", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "titleRes", _UrlKt.FRAGMENT_ENCODE_SET, "iconRes", "colorKey", "<init>", "(Ljava/lang/String;ILjava/lang/String;III)V", "getId", "()Ljava/lang/String;", "getTitleRes", "()I", "getIconRes", "getColorKey", "APPEARANCE", "CHATS", "MEDIA", "PRIVACY", "TOOLS", "INTEGRATIONS", "FUN", "LIBRARIES", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum PluginCategory {
    APPEARANCE("appearance", R.string.PluginCategoryAppearance, R.drawable.filter_palette, Theme.key_avatar_nameInMessageViolet),
    CHATS("chats", R.string.PluginCategoryChats, R.drawable.filter_all, Theme.key_avatar_nameInMessageBlue),
    MEDIA("media", R.string.PluginCategoryMedia, R.drawable.filter_media, Theme.key_avatar_nameInMessageOrange),
    PRIVACY("privacy", R.string.PluginCategoryPrivacy, R.drawable.filter_lock, Theme.key_avatar_nameInMessageGreen),
    TOOLS("tools", R.string.PluginCategoryTools, R.drawable.filter_work, Theme.key_avatar_nameInMessageRed),
    INTEGRATIONS("integrations", R.string.PluginCategoryIntegrations, R.drawable.filter_bots, Theme.key_avatar_nameInMessageCyan),
    FUN("fun", R.string.PluginCategoryFun, R.drawable.filter_game, Theme.key_avatar_nameInMessagePink),
    LIBRARIES("libraries", R.string.PluginCategoryLibraries, R.drawable.filter_book, Theme.key_windowBackgroundWhiteGrayText);

    private static final int MAX_CATEGORIES = 3;
    private final int colorKey;
    private final int iconRes;
    private final String id;
    private final int titleRes;
    private static final EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<PluginCategory> getEntries() {
        return $ENTRIES;
    }

    PluginCategory(String str, int i, int i2, int i3) {
        this.id = str;
        this.titleRes = i;
        this.iconRes = i2;
        this.colorKey = i3;
    }

    public final int getColorKey() {
        return this.colorKey;
    }

    public final int getIconRes() {
        return this.iconRes;
    }

    public final String getId() {
        return this.id;
    }

    public final int getTitleRes() {
        return this.titleRes;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/PluginCategory$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MAX_CATEGORIES", _UrlKt.FRAGMENT_ENCODE_SET, "parse", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/catalog/PluginCategory;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPluginCategory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginCategory.kt\ncom/exteragram/messenger/plugins/catalog/PluginCategory$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,43:1\n1642#2,10:44\n1915#2:54\n296#2,2:55\n1916#2:58\n1652#2:59\n1#3:57\n*S KotlinDebug\n*F\n+ 1 PluginCategory.kt\ncom/exteragram/messenger/plugins/catalog/PluginCategory$Companion\n*L\n37#1:44,10\n37#1:54\n37#1:55,2\n37#1:58\n37#1:59\n37#1:57\n*E\n"})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<PluginCategory> parse(String value) {
            PluginCategory next;
            if (value == null || StringsKt.isBlank(value)) {
                return CollectionsKt.emptyList();
            }
            List<String> listSplit$default = StringsKt.split$default((CharSequence) value, new char[]{','}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList();
            for (String str : listSplit$default) {
                Iterator<PluginCategory> it = PluginCategory.getEntries().iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(next.getId(), StringsKt.trim((CharSequence) str).toString().toLowerCase(Locale.ROOT)));
                PluginCategory pluginCategory = next;
                if (pluginCategory != null) {
                    arrayList.add(pluginCategory);
                }
            }
            return CollectionsKt.take(CollectionsKt.distinct(arrayList), 3);
        }
    }
}
