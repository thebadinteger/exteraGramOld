package com.exteragram.messenger.plugins.ui.components;

import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\u0018\u00002\u00020\u0001:\u0001\u001dBY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/AnalysisSummary;", _UrlKt.FRAGMENT_ENCODE_SET, "iconRes", _UrlKt.FRAGMENT_ENCODE_SET, "color", "title", _UrlKt.FRAGMENT_ENCODE_SET, MediaTrack.ROLE_SUBTITLE, "chips", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/ui/components/AnalysisSummary$Chip;", "loading", _UrlKt.FRAGMENT_ENCODE_SET, "badge", "badgeColor", "<init>", "(IILjava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/util/List;ZLjava/lang/CharSequence;I)V", "getIconRes", "()I", "getColor", "getTitle", "()Ljava/lang/CharSequence;", "getSubtitle", "getChips", "()Ljava/util/List;", "getLoading", "()Z", "getBadge", "getBadgeColor", "Chip", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AnalysisSummary {
    private final CharSequence badge;
    private final int badgeColor;
    private final List<Chip> chips;
    private final int color;
    private final int iconRes;
    private final boolean loading;
    private final CharSequence subtitle;
    private final CharSequence title;

    public AnalysisSummary(int i, int i2, CharSequence charSequence, CharSequence charSequence2, List<Chip> list, boolean z, CharSequence charSequence3, int i3) {
        this.iconRes = i;
        this.color = i2;
        this.title = charSequence;
        this.subtitle = charSequence2;
        this.chips = list;
        this.loading = z;
        this.badge = charSequence3;
        this.badgeColor = i3;
    }

    public final int getIconRes() {
        return this.iconRes;
    }

    public final int getColor() {
        return this.color;
    }

    public final CharSequence getTitle() {
        return this.title;
    }

    public final CharSequence getSubtitle() {
        return this.subtitle;
    }

    public AnalysisSummary(int i, int i2, CharSequence charSequence, CharSequence charSequence2, List list, boolean z, CharSequence charSequence3, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, charSequence, charSequence2, (i4 & 16) != 0 ? CollectionsKt.emptyList() : list, (i4 & 32) != 0 ? false : z, (i4 & 64) != 0 ? null : charSequence3, (i4 & 128) != 0 ? 0 : i3);
    }

    public final List<Chip> getChips() {
        return this.chips;
    }

    public final boolean getLoading() {
        return this.loading;
    }

    public final CharSequence getBadge() {
        return this.badge;
    }

    public final int getBadgeColor() {
        return this.badgeColor;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/AnalysisSummary$Chip;", _UrlKt.FRAGMENT_ENCODE_SET, "iconRes", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "color", "<init>", "(ILjava/lang/CharSequence;I)V", "getIconRes", "()I", "getText", "()Ljava/lang/CharSequence;", "getColor", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Chip {
        private final int color;
        private final int iconRes;
        private final CharSequence text;

        public Chip(int i, CharSequence charSequence, int i2) {
            this.iconRes = i;
            this.text = charSequence;
            this.color = i2;
        }

        public final int getColor() {
            return this.color;
        }

        public final int getIconRes() {
            return this.iconRes;
        }

        public final CharSequence getText() {
            return this.text;
        }
    }
}
