package com.exteragram.messenger.plugins.catalog.db;

import androidx.collection.LongSparseArray;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.EntityUpsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 :2\u00020\u0001:\u0001:B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0014J\u001e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J\u0016\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\rH\u0016J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011H\u0016J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J(\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020!H\u0016J\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00140\u00112\u0006\u0010$\u001a\u00020!H\u0016J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010'\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010(\u001a\u00020)H\u0016J\u001e\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020)2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140\u0011H\u0016J\u0010\u0010-\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J \u0010.\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020)H\u0016J\u0018\u00101\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u00100\u001a\u00020)H\u0016J\u0010\u00102\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u00103\u001a\u00020\u000fH\u0016J\b\u00104\u001a\u00020\u000fH\u0014J \u00105\u001a\u00020\u000f2\u0006\u00106\u001a\u0002072\u000e\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r09H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDao_Impl;", "Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfCatalogEntryEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryEntity;", "__upsertAdapterOfPluginRepositoryEntity", "Landroidx/room/EntityUpsertAdapter;", "Lcom/exteragram/messenger/plugins/catalog/db/PluginRepositoryEntity;", "__upsertAdapterOfPluginMetadataEntity", "Lcom/exteragram/messenger/plugins/catalog/db/PluginMetadataEntity;", "insertEntries", _UrlKt.FRAGMENT_ENCODE_SET, "entries", _UrlKt.FRAGMENT_ENCODE_SET, "replaceEntries", "channelId", _UrlKt.FRAGMENT_ENCODE_SET, "removeRepository", "replaceFolderRepositories", "repositories", "upsertRepositories", "upsertMetadata", "metadata", "getRepositories", "getRepository", "getEntries", "Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryWithMetadata;", "getEntriesWithoutMetadata", "sdkVersion", _UrlKt.FRAGMENT_ENCODE_SET, "retryError", "getDocumentIdsWithError", "error", "getEntryVersions", "Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryVersion;", "getEntry", "messageId", _UrlKt.FRAGMENT_ENCODE_SET, "deleteRepositoriesExcept", "source", "keepIds", "deleteRepository", "updateSyncState", "syncedAt", "pluginsCount", "updatePluginsCount", "deleteEntries", "pruneMetadata", "pruneEntries", "__fetchRelationshipmetadataAscomExteragramMessengerPluginsCatalogDbPluginMetadataEntity", "_connection", "Landroidx/sqlite/SQLiteConnection;", "_map", "Landroidx/collection/LongSparseArray;", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PluginCatalogDao_Impl extends PluginCatalogDao {

    public static final Companion INSTANCE = new Companion(null);
    private final RoomDatabase __db;
    private final EntityInsertAdapter<CatalogEntryEntity> __insertAdapterOfCatalogEntryEntity = new EntityInsertAdapter<CatalogEntryEntity>() {
        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR REPLACE INTO `entries` (`channelId`,`messageId`,`date`,`documentId`,`fileName`,`size`,`caption`,`message`,`views`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        }

        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement statement, CatalogEntryEntity entity) {
            statement.bindLong(1, entity.getChannelId());
            statement.bindLong(2, entity.getMessageId());
            statement.bindLong(3, entity.getDate());
            statement.bindLong(4, entity.getDocumentId());
            statement.bindText(5, entity.getFileName());
            statement.bindLong(6, entity.getSize());
            String caption = entity.getCaption();
            if (caption == null) {
                statement.bindNull(7);
            } else {
                statement.bindText(7, caption);
            }
            statement.bindBlob(8, entity.getMessage());
            statement.bindLong(9, entity.getViews());
            statement.bindLong(10, entity.getUpdatedAt());
        }
    };
    private final EntityUpsertAdapter<PluginRepositoryEntity> __upsertAdapterOfPluginRepositoryEntity = new EntityUpsertAdapter<>(new EntityInsertAdapter<PluginRepositoryEntity>() {
        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT INTO `repositories` (`channelId`,`username`,`title`,`source`,`sortOrder`,`addedAt`,`syncedAt`,`pluginsCount`) VALUES (?,?,?,?,?,?,?,?)";
        }

        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement statement, PluginRepositoryEntity entity) {
            statement.bindLong(1, entity.getChannelId());
            String username = entity.getUsername();
            if (username == null) {
                statement.bindNull(2);
            } else {
                statement.bindText(2, username);
            }
            String title = entity.getTitle();
            if (title == null) {
                statement.bindNull(3);
            } else {
                statement.bindText(3, title);
            }
            statement.bindLong(4, entity.getSource());
            statement.bindLong(5, entity.getSortOrder());
            statement.bindLong(6, entity.getAddedAt());
            statement.bindLong(7, entity.getSyncedAt());
            statement.bindLong(8, entity.getPluginsCount());
        }
    }, new EntityDeleteOrUpdateAdapter<PluginRepositoryEntity>() {
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public String createQuery() {
            return "UPDATE `repositories` SET `channelId` = ?,`username` = ?,`title` = ?,`source` = ?,`sortOrder` = ?,`addedAt` = ?,`syncedAt` = ?,`pluginsCount` = ? WHERE `channelId` = ?";
        }

        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement statement, PluginRepositoryEntity entity) {
            statement.bindLong(1, entity.getChannelId());
            String username = entity.getUsername();
            if (username == null) {
                statement.bindNull(2);
            } else {
                statement.bindText(2, username);
            }
            String title = entity.getTitle();
            if (title == null) {
                statement.bindNull(3);
            } else {
                statement.bindText(3, title);
            }
            statement.bindLong(4, entity.getSource());
            statement.bindLong(5, entity.getSortOrder());
            statement.bindLong(6, entity.getAddedAt());
            statement.bindLong(7, entity.getSyncedAt());
            statement.bindLong(8, entity.getPluginsCount());
            statement.bindLong(9, entity.getChannelId());
        }
    });
    private final EntityUpsertAdapter<PluginMetadataEntity> __upsertAdapterOfPluginMetadataEntity = new EntityUpsertAdapter<>(new EntityInsertAdapter<PluginMetadataEntity>() {
        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT INTO `metadata` (`documentId`,`pluginId`,`name`,`author`,`description`,`version`,`icon`,`appVersion`,`sdkVersion`,`requirements`,`categories`,`error`,`parsedAt`,`parsedWithSdk`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        }

        @Override // androidx.room.EntityInsertAdapter
        public void bind(SQLiteStatement statement, PluginMetadataEntity entity) {
            statement.bindLong(1, entity.getDocumentId());
            String pluginId = entity.getPluginId();
            if (pluginId == null) {
                statement.bindNull(2);
            } else {
                statement.bindText(2, pluginId);
            }
            String name = entity.getName();
            if (name == null) {
                statement.bindNull(3);
            } else {
                statement.bindText(3, name);
            }
            String author = entity.getAuthor();
            if (author == null) {
                statement.bindNull(4);
            } else {
                statement.bindText(4, author);
            }
            String description = entity.getDescription();
            if (description == null) {
                statement.bindNull(5);
            } else {
                statement.bindText(5, description);
            }
            String version = entity.getVersion();
            if (version == null) {
                statement.bindNull(6);
            } else {
                statement.bindText(6, version);
            }
            String icon = entity.getIcon();
            if (icon == null) {
                statement.bindNull(7);
            } else {
                statement.bindText(7, icon);
            }
            String appVersion = entity.getAppVersion();
            if (appVersion == null) {
                statement.bindNull(8);
            } else {
                statement.bindText(8, appVersion);
            }
            String sdkVersion = entity.getSdkVersion();
            if (sdkVersion == null) {
                statement.bindNull(9);
            } else {
                statement.bindText(9, sdkVersion);
            }
            String requirements = entity.getRequirements();
            if (requirements == null) {
                statement.bindNull(10);
            } else {
                statement.bindText(10, requirements);
            }
            String categories = entity.getCategories();
            if (categories == null) {
                statement.bindNull(11);
            } else {
                statement.bindText(11, categories);
            }
            String error = entity.getError();
            if (error == null) {
                statement.bindNull(12);
            } else {
                statement.bindText(12, error);
            }
            statement.bindLong(13, entity.getParsedAt());
            String parsedWithSdk = entity.getParsedWithSdk();
            if (parsedWithSdk == null) {
                statement.bindNull(14);
            } else {
                statement.bindText(14, parsedWithSdk);
            }
        }
    }, new EntityDeleteOrUpdateAdapter<PluginMetadataEntity>() {
        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public String createQuery() {
            return "UPDATE `metadata` SET `documentId` = ?,`pluginId` = ?,`name` = ?,`author` = ?,`description` = ?,`version` = ?,`icon` = ?,`appVersion` = ?,`sdkVersion` = ?,`requirements` = ?,`categories` = ?,`error` = ?,`parsedAt` = ?,`parsedWithSdk` = ? WHERE `documentId` = ?";
        }

        @Override // androidx.room.EntityDeleteOrUpdateAdapter
        public void bind(SQLiteStatement statement, PluginMetadataEntity entity) {
            statement.bindLong(1, entity.getDocumentId());
            String pluginId = entity.getPluginId();
            if (pluginId == null) {
                statement.bindNull(2);
            } else {
                statement.bindText(2, pluginId);
            }
            String name = entity.getName();
            if (name == null) {
                statement.bindNull(3);
            } else {
                statement.bindText(3, name);
            }
            String author = entity.getAuthor();
            if (author == null) {
                statement.bindNull(4);
            } else {
                statement.bindText(4, author);
            }
            String description = entity.getDescription();
            if (description == null) {
                statement.bindNull(5);
            } else {
                statement.bindText(5, description);
            }
            String version = entity.getVersion();
            if (version == null) {
                statement.bindNull(6);
            } else {
                statement.bindText(6, version);
            }
            String icon = entity.getIcon();
            if (icon == null) {
                statement.bindNull(7);
            } else {
                statement.bindText(7, icon);
            }
            String appVersion = entity.getAppVersion();
            if (appVersion == null) {
                statement.bindNull(8);
            } else {
                statement.bindText(8, appVersion);
            }
            String sdkVersion = entity.getSdkVersion();
            if (sdkVersion == null) {
                statement.bindNull(9);
            } else {
                statement.bindText(9, sdkVersion);
            }
            String requirements = entity.getRequirements();
            if (requirements == null) {
                statement.bindNull(10);
            } else {
                statement.bindText(10, requirements);
            }
            String categories = entity.getCategories();
            if (categories == null) {
                statement.bindNull(11);
            } else {
                statement.bindText(11, categories);
            }
            String error = entity.getError();
            if (error == null) {
                statement.bindNull(12);
            } else {
                statement.bindText(12, error);
            }
            statement.bindLong(13, entity.getParsedAt());
            String parsedWithSdk = entity.getParsedWithSdk();
            if (parsedWithSdk == null) {
                statement.bindNull(14);
            } else {
                statement.bindText(14, parsedWithSdk);
            }
            statement.bindLong(15, entity.getDocumentId());
        }
    });

    public PluginCatalogDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void insertEntries(final List<CatalogEntryEntity> entries) {
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1908$r8$lambda$4PSpl_6sKUT920WUtmXArmfTw(this.f$0, entries, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit m1908$r8$lambda$4PSpl_6sKUT920WUtmXArmfTw(PluginCatalogDao_Impl pluginCatalogDao_Impl, List list, SQLiteConnection sQLiteConnection) throws Exception {
        pluginCatalogDao_Impl.__insertAdapterOfCatalogEntryEntity.insert(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void replaceEntries(final long channelId, final List<CatalogEntryEntity> entries) {
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$WJgZjUqQ2nWacRvoyNCHOYcyFa0(this.f$0, channelId, entries, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit $r8$lambda$WJgZjUqQ2nWacRvoyNCHOYcyFa0(PluginCatalogDao_Impl pluginCatalogDao_Impl, long j, List list, SQLiteConnection sQLiteConnection) {
        super.replaceEntries(j, list);
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void removeRepository(final long channelId) {
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$0FdhHIQLJNoYfVpVZACpTtOQDLI(this.f$0, channelId, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit $r8$lambda$0FdhHIQLJNoYfVpVZACpTtOQDLI(PluginCatalogDao_Impl pluginCatalogDao_Impl, long j, SQLiteConnection sQLiteConnection) {
        super.removeRepository(j);
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void replaceFolderRepositories(final List<PluginRepositoryEntity> repositories) {
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1912$r8$lambda$YzG6HnMd1MTEECgOQTVVKQFAO0(this.f$0, repositories, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit m1912$r8$lambda$YzG6HnMd1MTEECgOQTVVKQFAO0(PluginCatalogDao_Impl pluginCatalogDao_Impl, List list, SQLiteConnection sQLiteConnection) {
        super.replaceFolderRepositories(list);
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void upsertRepositories(final List<PluginRepositoryEntity> repositories) {
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$1FcuG8zhPLno8paTDfFzc_GTXn8(this.f$0, repositories, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit $r8$lambda$1FcuG8zhPLno8paTDfFzc_GTXn8(PluginCatalogDao_Impl pluginCatalogDao_Impl, List list, SQLiteConnection sQLiteConnection) throws Exception {
        pluginCatalogDao_Impl.__upsertAdapterOfPluginRepositoryEntity.upsert(sQLiteConnection, list);
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void upsertMetadata(final PluginMetadataEntity metadata) {
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$BEFUzdlm25kxfvEtR8Qen1taAy0(this.f$0, metadata, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit $r8$lambda$BEFUzdlm25kxfvEtR8Qen1taAy0(PluginCatalogDao_Impl pluginCatalogDao_Impl, PluginMetadataEntity pluginMetadataEntity, SQLiteConnection sQLiteConnection) throws Exception {
        pluginCatalogDao_Impl.__upsertAdapterOfPluginMetadataEntity.upsert(sQLiteConnection, pluginMetadataEntity);
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public List<PluginRepositoryEntity> getRepositories() {
        final String str = "SELECT * FROM repositories ORDER BY source, sortOrder, addedAt";
        return (List) DBUtil.performBlocking(this.__db, true, false, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1913$r8$lambda$_KgM7CuiMoSArqCbTsrB9mLEXE(str, (SQLiteConnection) obj);
            }
        });
    }

    public static List m1913$r8$lambda$_KgM7CuiMoSArqCbTsrB9mLEXE(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "channelId");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "username");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "title");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "source");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sortOrder");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "addedAt");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "syncedAt");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pluginsCount");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                int i = columnIndexOrThrow2;
                int i2 = columnIndexOrThrow3;
                arrayList.add(new PluginRepositoryEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.isNull(columnIndexOrThrow2) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.isNull(columnIndexOrThrow3) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow3), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow4), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), sQLiteStatementPrepare.getLong(columnIndexOrThrow6), sQLiteStatementPrepare.getLong(columnIndexOrThrow7), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8)));
                columnIndexOrThrow2 = i;
                columnIndexOrThrow3 = i2;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public PluginRepositoryEntity getRepository(final long channelId) {
        final String str = "SELECT * FROM repositories WHERE channelId = ?";
        return (PluginRepositoryEntity) DBUtil.performBlocking(this.__db, true, false, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1916$r8$lambda$u6rGz3d44XujXU4hW3RcrFFsB0(str, channelId, (SQLiteConnection) obj);
            }
        });
    }

    public static PluginRepositoryEntity m1916$r8$lambda$u6rGz3d44XujXU4hW3RcrFFsB0(String str, long j, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, j);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "channelId");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "username");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "title");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "source");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "sortOrder");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "addedAt");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "syncedAt");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pluginsCount");
            PluginRepositoryEntity pluginRepositoryEntity = null;
            if (sQLiteStatementPrepare.step()) {
                pluginRepositoryEntity = new PluginRepositoryEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.isNull(columnIndexOrThrow2) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.isNull(columnIndexOrThrow3) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow3), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow4), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow5), sQLiteStatementPrepare.getLong(columnIndexOrThrow6), sQLiteStatementPrepare.getLong(columnIndexOrThrow7), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow8));
            }
            return pluginRepositoryEntity;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public List<CatalogEntryWithMetadata> getEntries(final long channelId) {
        final String str = "SELECT * FROM entries WHERE channelId = ? ORDER BY date DESC, messageId DESC";
        return (List) DBUtil.performBlocking(this.__db, true, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1915$r8$lambda$piLAXyWb4sEXArICkN99CYybvE(str, channelId, this, (SQLiteConnection) obj);
            }
        });
    }

    public static List m1915$r8$lambda$piLAXyWb4sEXArICkN99CYybvE(String str, long j, PluginCatalogDao_Impl pluginCatalogDao_Impl, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, j);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "channelId");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "messageId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "date");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "documentId");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fileName");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "size");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, MediaTrack.ROLE_CAPTION);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "message");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "views");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "updatedAt");
            LongSparseArray<PluginMetadataEntity> longSparseArray = new LongSparseArray<>(0, 1, null);
            while (sQLiteStatementPrepare.step()) {
                longSparseArray.put(sQLiteStatementPrepare.getLong(columnIndexOrThrow4), null);
                columnIndexOrThrow9 = columnIndexOrThrow9;
                columnIndexOrThrow10 = columnIndexOrThrow10;
            }
            int i = columnIndexOrThrow9;
            int i2 = columnIndexOrThrow10;
            sQLiteStatementPrepare.reset();
            pluginCatalogDao_Impl.__fetchRelationshipmetadataAscomExteragramMessengerPluginsCatalogDbPluginMetadataEntity(sQLiteConnection, longSparseArray);
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow);
                int i3 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                int i4 = i;
                int i5 = columnIndexOrThrow;
                int i6 = columnIndexOrThrow2;
                int i7 = i2;
                int i8 = columnIndexOrThrow5;
                int i9 = columnIndexOrThrow6;
                arrayList.add(new CatalogEntryWithMetadata(new CatalogEntryEntity(j2, i3, (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3), sQLiteStatementPrepare.getLong(columnIndexOrThrow4), sQLiteStatementPrepare.getText(columnIndexOrThrow5), sQLiteStatementPrepare.getLong(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.getBlob(columnIndexOrThrow8), (int) sQLiteStatementPrepare.getLong(i4), (int) sQLiteStatementPrepare.getLong(i7)), longSparseArray.get(sQLiteStatementPrepare.getLong(columnIndexOrThrow4))));
                columnIndexOrThrow = i5;
                columnIndexOrThrow5 = i8;
                i2 = i7;
                i = i4;
                columnIndexOrThrow6 = i9;
                columnIndexOrThrow2 = i6;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public List<CatalogEntryEntity> getEntriesWithoutMetadata(final long channelId, final String sdkVersion, final String retryError) {
        final String str = "SELECT * FROM entries WHERE channelId = ? AND documentId NOT IN (SELECT documentId FROM metadata WHERE parsedWithSdk IS ? AND error IS NOT ?) ORDER BY date DESC, messageId DESC";
        return (List) DBUtil.performBlocking(this.__db, true, false, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1907$r8$lambda$YVFT5CompY18PSKmdkGgnFOS4Y(str, channelId, sdkVersion, retryError, (SQLiteConnection) obj);
            }
        });
    }

    public static List m1907$r8$lambda$YVFT5CompY18PSKmdkGgnFOS4Y(String str, long j, String str2, String str3, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, j);
            if (str2 == null) {
                sQLiteStatementPrepare.bindNull(2);
            } else {
                sQLiteStatementPrepare.bindText(2, str2);
            }
            sQLiteStatementPrepare.bindText(3, str3);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "channelId");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "messageId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "date");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "documentId");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fileName");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "size");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, MediaTrack.ROLE_CAPTION);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "message");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "views");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "updatedAt");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                int i = columnIndexOrThrow2;
                int i2 = columnIndexOrThrow3;
                arrayList.add(new CatalogEntryEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3), sQLiteStatementPrepare.getLong(columnIndexOrThrow4), sQLiteStatementPrepare.getText(columnIndexOrThrow5), sQLiteStatementPrepare.getLong(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.getBlob(columnIndexOrThrow8), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10)));
                columnIndexOrThrow2 = i;
                columnIndexOrThrow3 = i2;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public List<Long> getDocumentIdsWithError(final String error) {
        final String str = "SELECT documentId FROM metadata WHERE error = ?";
        return (List) DBUtil.performBlocking(this.__db, true, false, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1909$r8$lambda$IbLHwq1ujm5tQg4GUqbdyHLTHs(str, error, (SQLiteConnection) obj);
            }
        });
    }

    public static List m1909$r8$lambda$IbLHwq1ujm5tQg4GUqbdyHLTHs(String str, String str2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindText(1, str2);
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                arrayList.add(Long.valueOf(sQLiteStatementPrepare.getLong(0)));
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public List<CatalogEntryVersion> getEntryVersions(final long channelId) {
        final String str = "SELECT messageId, documentId, updatedAt FROM entries WHERE channelId = ?";
        return (List) DBUtil.performBlocking(this.__db, true, false, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$pXWn5xsFeQlEUNwSQDC_dCAYzX8(str, channelId, (SQLiteConnection) obj);
            }
        });
    }

    public static List $r8$lambda$pXWn5xsFeQlEUNwSQDC_dCAYzX8(String str, long j, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, j);
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                arrayList.add(new CatalogEntryVersion((int) sQLiteStatementPrepare.getLong(0), sQLiteStatementPrepare.getLong(1), (int) sQLiteStatementPrepare.getLong(2)));
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public CatalogEntryEntity getEntry(final long channelId, final int messageId) {
        final String str = "SELECT * FROM entries WHERE channelId = ? AND messageId = ?";
        return (CatalogEntryEntity) DBUtil.performBlocking(this.__db, true, false, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$KygE7tQYWDLsCKvGijImjZ3COqs(str, channelId, messageId, (SQLiteConnection) obj);
            }
        });
    }

    public static CatalogEntryEntity $r8$lambda$KygE7tQYWDLsCKvGijImjZ3COqs(String str, long j, int i, SQLiteConnection sQLiteConnection) {
        CatalogEntryEntity catalogEntryEntity;
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, j);
            sQLiteStatementPrepare.bindLong(2, i);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "channelId");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "messageId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "date");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "documentId");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fileName");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "size");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, MediaTrack.ROLE_CAPTION);
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "message");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "views");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "updatedAt");
            if (sQLiteStatementPrepare.step()) {
                catalogEntryEntity = new CatalogEntryEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow2), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow3), sQLiteStatementPrepare.getLong(columnIndexOrThrow4), sQLiteStatementPrepare.getText(columnIndexOrThrow5), sQLiteStatementPrepare.getLong(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.getBlob(columnIndexOrThrow8), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow9), (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow10));
            } else {
                catalogEntryEntity = null;
            }
            return catalogEntryEntity;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void deleteRepositoriesExcept(final int source, final List<Long> keepIds) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM repositories WHERE source = ? AND channelId NOT IN (");
        StringUtil.appendPlaceholders(sb, keepIds.size());
        sb.append(")");
        final String string = sb.toString();
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1910$r8$lambda$Ix8B7a2Fmw2L8TQRD8XFMqz5sc(string, source, keepIds, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit m1910$r8$lambda$Ix8B7a2Fmw2L8TQRD8XFMqz5sc(String str, int i, List list, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, i);
            Iterator it = list.iterator();
            int i2 = 2;
            while (it.hasNext()) {
                sQLiteStatementPrepare.bindLong(i2, ((Number) it.next()).longValue());
                i2++;
            }
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void deleteRepository(final long channelId) {
        final String str = "DELETE FROM repositories WHERE channelId = ?";
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$p5Y5CtrjXLIQ4IA58_q2E1s6oiA(str, channelId, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit $r8$lambda$p5Y5CtrjXLIQ4IA58_q2E1s6oiA(String str, long j, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, j);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void updateSyncState(final long channelId, final long syncedAt, final int pluginsCount) {
        final String str = "UPDATE repositories SET syncedAt = ?, pluginsCount = ? WHERE channelId = ?";
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$GvsTbP4qXPhof9zMDH54f_WTcWw(str, syncedAt, pluginsCount, channelId, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit $r8$lambda$GvsTbP4qXPhof9zMDH54f_WTcWw(String str, long j, int i, long j2, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, j);
            sQLiteStatementPrepare.bindLong(2, i);
            sQLiteStatementPrepare.bindLong(3, j2);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void updatePluginsCount(final long channelId, final int pluginsCount) {
        final String str = "UPDATE repositories SET pluginsCount = ? WHERE channelId = ?";
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1911$r8$lambda$W1Q03zN8_lctypP8Ja1v_MY1Yw(str, pluginsCount, channelId, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit m1911$r8$lambda$W1Q03zN8_lctypP8Ja1v_MY1Yw(String str, int i, long j, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, i);
            sQLiteStatementPrepare.bindLong(2, j);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void deleteEntries(final long channelId) {
        final String str = "DELETE FROM entries WHERE channelId = ?";
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$or2Mi9NQlLHMKCZXYt23Or3sL6U(str, channelId, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit $r8$lambda$or2Mi9NQlLHMKCZXYt23Or3sL6U(String str, long j, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.bindLong(1, j);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void pruneMetadata() {
        final String str = "DELETE FROM metadata WHERE documentId NOT IN (SELECT documentId FROM entries)";
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.$r8$lambda$RIy5VOipXsH19cSzhbdhikGNgAU(str, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit $r8$lambda$RIy5VOipXsH19cSzhbdhikGNgAU(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao
    public void pruneEntries() {
        final String str = "DELETE FROM entries WHERE channelId NOT IN (SELECT channelId FROM repositories)";
        DBUtil.performBlocking(this.__db, false, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogDao_Impl.m1914$r8$lambda$gHWy4ivDIftbFDgXWZ9ZyZIzGs(str, (SQLiteConnection) obj);
            }
        });
    }

    public static Unit m1914$r8$lambda$gHWy4ivDIftbFDgXWZ9ZyZIzGs(String str, SQLiteConnection sQLiteConnection) {
        SQLiteStatement sQLiteStatementPrepare = sQLiteConnection.prepare(str);
        try {
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    private final void __fetchRelationshipmetadataAscomExteragramMessengerPluginsCatalogDbPluginMetadataEntity(final SQLiteConnection _connection, LongSparseArray<PluginMetadataEntity> _map) {
        if (_map.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            RelationUtil.recursiveFetchLongSparseArray(_map, false, new Function1() {
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PluginCatalogDao_Impl.$r8$lambda$fOJtSQyumaVtMjzk0kKeW5smbvA(this.f$0, _connection, (LongSparseArray) obj);
                }
            });
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT `documentId`,`pluginId`,`name`,`author`,`description`,`version`,`icon`,`appVersion`,`sdkVersion`,`requirements`,`categories`,`error`,`parsedAt`,`parsedWithSdk` FROM `metadata` WHERE `documentId` IN (");
        StringUtil.appendPlaceholders(sb, _map.size());
        sb.append(")");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(sb.toString());
        int size = _map.size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            sQLiteStatementPrepare.bindLong(i, _map.keyAt(i2));
            i++;
        }
        try {
            int columnIndex = SQLiteStatementUtil.getColumnIndex(sQLiteStatementPrepare, "documentId");
            if (columnIndex == -1) {
                return;
            }
            while (sQLiteStatementPrepare.step()) {
                long j = sQLiteStatementPrepare.getLong(columnIndex);
                if (_map.containsKey(j)) {
                    _map.put(j, new PluginMetadataEntity(sQLiteStatementPrepare.getLong(0), sQLiteStatementPrepare.isNull(1) ? null : sQLiteStatementPrepare.getText(1), sQLiteStatementPrepare.isNull(2) ? null : sQLiteStatementPrepare.getText(2), sQLiteStatementPrepare.isNull(3) ? null : sQLiteStatementPrepare.getText(3), sQLiteStatementPrepare.isNull(4) ? null : sQLiteStatementPrepare.getText(4), sQLiteStatementPrepare.isNull(5) ? null : sQLiteStatementPrepare.getText(5), sQLiteStatementPrepare.isNull(6) ? null : sQLiteStatementPrepare.getText(6), sQLiteStatementPrepare.isNull(7) ? null : sQLiteStatementPrepare.getText(7), sQLiteStatementPrepare.isNull(8) ? null : sQLiteStatementPrepare.getText(8), sQLiteStatementPrepare.isNull(9) ? null : sQLiteStatementPrepare.getText(9), sQLiteStatementPrepare.isNull(10) ? null : sQLiteStatementPrepare.getText(10), sQLiteStatementPrepare.isNull(11) ? null : sQLiteStatementPrepare.getText(11), sQLiteStatementPrepare.getLong(12), sQLiteStatementPrepare.isNull(13) ? null : sQLiteStatementPrepare.getText(13)));
                }
            }
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    public static Unit $r8$lambda$fOJtSQyumaVtMjzk0kKeW5smbvA(PluginCatalogDao_Impl pluginCatalogDao_Impl, SQLiteConnection sQLiteConnection, LongSparseArray longSparseArray) {
        pluginCatalogDao_Impl.__fetchRelationshipmetadataAscomExteragramMessengerPluginsCatalogDbPluginMetadataEntity(sQLiteConnection, longSparseArray);
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }
}
