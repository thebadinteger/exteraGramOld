package com.exteragram.messenger.plugins.catalog.db;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0001(BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J]\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\bHÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\bHÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015¨\u0006)"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/db/PluginRepositoryEntity;", _UrlKt.FRAGMENT_ENCODE_SET, "channelId", _UrlKt.FRAGMENT_ENCODE_SET, "username", _UrlKt.FRAGMENT_ENCODE_SET, "title", "source", _UrlKt.FRAGMENT_ENCODE_SET, "sortOrder", "addedAt", "syncedAt", "pluginsCount", "<init>", "(JLjava/lang/String;Ljava/lang/String;IIJJI)V", "getChannelId", "()J", "getUsername", "()Ljava/lang/String;", "getTitle", "getSource", "()I", "getSortOrder", "getAddedAt", "getSyncedAt", "getPluginsCount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", "hashCode", "toString", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PluginRepositoryEntity {
    public static final int SOURCE_FOLDER = 0;
    public static final int SOURCE_USER = 1;
    private final long addedAt;
    private final long channelId;
    private final int pluginsCount;
    private final int sortOrder;
    private final int source;
    private final long syncedAt;
    private final String title;
    private final String username;

    public static PluginRepositoryEntity copy$default(PluginRepositoryEntity pluginRepositoryEntity, long j, String str, String str2, int i, int i2, long j2, long j3, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            j = pluginRepositoryEntity.channelId;
        }
        long j4 = j;
        if ((i4 & 2) != 0) {
            str = pluginRepositoryEntity.username;
        }
        return pluginRepositoryEntity.copy(j4, str, (i4 & 4) != 0 ? pluginRepositoryEntity.title : str2, (i4 & 8) != 0 ? pluginRepositoryEntity.source : i, (i4 & 16) != 0 ? pluginRepositoryEntity.sortOrder : i2, (i4 & 32) != 0 ? pluginRepositoryEntity.addedAt : j2, (i4 & 64) != 0 ? pluginRepositoryEntity.syncedAt : j3, (i4 & 128) != 0 ? pluginRepositoryEntity.pluginsCount : i3);
    }

    public final long getChannelId() {
        return this.channelId;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getSource() {
        return this.source;
    }

    public final int getSortOrder() {
        return this.sortOrder;
    }

    public final long getAddedAt() {
        return this.addedAt;
    }

    public final long getSyncedAt() {
        return this.syncedAt;
    }

    public final int getPluginsCount() {
        return this.pluginsCount;
    }

    public final PluginRepositoryEntity copy(long channelId, String username, String title, int source, int sortOrder, long addedAt, long syncedAt, int pluginsCount) {
        return new PluginRepositoryEntity(channelId, username, title, source, sortOrder, addedAt, syncedAt, pluginsCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginRepositoryEntity)) {
            return false;
        }
        PluginRepositoryEntity pluginRepositoryEntity = (PluginRepositoryEntity) other;
        return this.channelId == pluginRepositoryEntity.channelId && Intrinsics.areEqual(this.username, pluginRepositoryEntity.username) && Intrinsics.areEqual(this.title, pluginRepositoryEntity.title) && this.source == pluginRepositoryEntity.source && this.sortOrder == pluginRepositoryEntity.sortOrder && this.addedAt == pluginRepositoryEntity.addedAt && this.syncedAt == pluginRepositoryEntity.syncedAt && this.pluginsCount == pluginRepositoryEntity.pluginsCount;
    }

    public int hashCode() {
        int iHashCode = Long.hashCode(this.channelId) * 31;
        String str = this.username;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.title;
        return ((((((((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.source)) * 31) + Integer.hashCode(this.sortOrder)) * 31) + Long.hashCode(this.addedAt)) * 31) + Long.hashCode(this.syncedAt)) * 31) + Integer.hashCode(this.pluginsCount);
    }

    public String toString() {
        return "PluginRepositoryEntity(channelId=" + this.channelId + ", username=" + this.username + ", title=" + this.title + ", source=" + this.source + ", sortOrder=" + this.sortOrder + ", addedAt=" + this.addedAt + ", syncedAt=" + this.syncedAt + ", pluginsCount=" + this.pluginsCount + ")";
    }

    public PluginRepositoryEntity(long j, String str, String str2, int i, int i2, long j2, long j3, int i3) {
        this.channelId = j;
        this.username = str;
        this.title = str2;
        this.source = i;
        this.sortOrder = i2;
        this.addedAt = j2;
        this.syncedAt = j3;
        this.pluginsCount = i3;
    }

    public PluginRepositoryEntity(long j, String str, String str2, int i, int i2, long j2, long j3, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, i, i2, j2, (i4 & 64) != 0 ? 0L : j3, (i4 & 128) != 0 ? 0 : i3);
    }

    public final long getChannelId() {
        return this.channelId;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getTitle() {
        return this.title;
    }

    public final int getSource() {
        return this.source;
    }

    public final int getSortOrder() {
        return this.sortOrder;
    }

    public final long getAddedAt() {
        return this.addedAt;
    }

    public final long getSyncedAt() {
        return this.syncedAt;
    }

    public final int getPluginsCount() {
        return this.pluginsCount;
    }
}
