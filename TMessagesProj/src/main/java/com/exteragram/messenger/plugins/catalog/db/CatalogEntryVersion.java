package com.exteragram.messenger.plugins.catalog.db;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryVersion;", _UrlKt.FRAGMENT_ENCODE_SET, "messageId", _UrlKt.FRAGMENT_ENCODE_SET, "documentId", _UrlKt.FRAGMENT_ENCODE_SET, "updatedAt", "<init>", "(IJI)V", "getMessageId", "()I", "getDocumentId", "()J", "getUpdatedAt", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CatalogEntryVersion {
    private final long documentId;
    private final int messageId;
    private final int updatedAt;

    public CatalogEntryVersion(int i, long j, int i2) {
        this.messageId = i;
        this.documentId = j;
        this.updatedAt = i2;
    }

    public final int getMessageId() {
        return this.messageId;
    }

    public final long getDocumentId() {
        return this.documentId;
    }

    public final int getUpdatedAt() {
        return this.updatedAt;
    }
}
