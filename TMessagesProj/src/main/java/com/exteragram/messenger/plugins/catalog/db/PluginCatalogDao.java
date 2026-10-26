package com.exteragram.messenger.plugins.catalog.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H'J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH'J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H'J\u001e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0005H'J\u0010\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH'J \u0010\u0012\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000fH'J\u0018\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000fH'J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00052\u0006\u0010\b\u001a\u00020\tH'J(\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH'J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u0006\u0010\u001e\u001a\u00020\u001bH'J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00052\u0006\u0010\b\u001a\u00020\tH'J\u001a\u0010!\u001a\u0004\u0018\u00010\u00192\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\"\u001a\u00020\u000fH'J\u0016\u0010#\u001a\u00020\u000b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00190\u0005H%J\u0010\u0010%\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020(H'J\b\u0010)\u001a\u00020\u000bH'J\b\u0010*\u001a\u00020\u000bH%J\u001e\u0010+\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00190\u0005H\u0017J\u0010\u0010,\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0017J\u0016\u0010-\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0017¨\u0006."}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDao;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRepositories", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/catalog/db/PluginRepositoryEntity;", "getRepository", "channelId", _UrlKt.FRAGMENT_ENCODE_SET, "upsertRepositories", _UrlKt.FRAGMENT_ENCODE_SET, "repositories", "deleteRepositoriesExcept", "source", _UrlKt.FRAGMENT_ENCODE_SET, "keepIds", "deleteRepository", "updateSyncState", "syncedAt", "pluginsCount", "updatePluginsCount", "getEntries", "Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryWithMetadata;", "getEntriesWithoutMetadata", "Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryEntity;", "sdkVersion", _UrlKt.FRAGMENT_ENCODE_SET, "retryError", "getDocumentIdsWithError", "error", "getEntryVersions", "Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryVersion;", "getEntry", "messageId", "insertEntries", "entries", "deleteEntries", "upsertMetadata", "metadata", "Lcom/exteragram/messenger/plugins/catalog/db/PluginMetadataEntity;", "pruneMetadata", "pruneEntries", "replaceEntries", "removeRepository", "replaceFolderRepositories", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginCatalogDao.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginCatalogDao.kt\ncom/exteragram/messenger/plugins/catalog/db/PluginCatalogDao\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,97:1\n1586#2:98\n1661#2,3:99\n*S KotlinDebug\n*F\n+ 1 PluginCatalogDao.kt\ncom/exteragram/messenger/plugins/catalog/db/PluginCatalogDao\n*L\n91#1:98\n91#1:99,3\n*E\n"})
public abstract class PluginCatalogDao {
    public abstract void deleteEntries(long channelId);

    public abstract void deleteRepositoriesExcept(int source, List<Long> keepIds);

    public abstract void deleteRepository(long channelId);

    public abstract List<Long> getDocumentIdsWithError(String error);

    public abstract List<CatalogEntryWithMetadata> getEntries(long channelId);

    public abstract List<CatalogEntryEntity> getEntriesWithoutMetadata(long channelId, String sdkVersion, String retryError);

    public abstract CatalogEntryEntity getEntry(long channelId, int messageId);

    public abstract List<CatalogEntryVersion> getEntryVersions(long channelId);

    public abstract List<PluginRepositoryEntity> getRepositories();

    public abstract PluginRepositoryEntity getRepository(long channelId);

    public abstract void insertEntries(List<CatalogEntryEntity> entries);

    public abstract void pruneEntries();

    public abstract void pruneMetadata();

    public abstract void updatePluginsCount(long channelId, int pluginsCount);

    public abstract void updateSyncState(long channelId, long syncedAt, int pluginsCount);

    public abstract void upsertMetadata(PluginMetadataEntity metadata);

    public abstract void upsertRepositories(List<PluginRepositoryEntity> repositories);

    public void replaceEntries(long channelId, List<CatalogEntryEntity> entries) {
        deleteEntries(channelId);
        insertEntries(entries);
        pruneMetadata();
    }

    public void removeRepository(long channelId) {
        deleteRepository(channelId);
        deleteEntries(channelId);
        pruneMetadata();
    }

    public void replaceFolderRepositories(List<PluginRepositoryEntity> repositories) {
        List<PluginRepositoryEntity> list = repositories;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((PluginRepositoryEntity) it.next()).getChannelId()));
        }
        deleteRepositoriesExcept(0, arrayList);
        upsertRepositories(repositories);
        pruneEntries();
        pruneMetadata();
    }
}
