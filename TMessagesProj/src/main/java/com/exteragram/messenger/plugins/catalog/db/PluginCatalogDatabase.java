package com.exteragram.messenger.plugins.catalog.db;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "catalogDao", "Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDao;", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class PluginCatalogDatabase extends RoomDatabase {
    private static volatile PluginCatalogDatabase INSTANCE;

    public static final Companion INSTANCE = new Companion(null);
    private static final PluginCatalogDatabase$Companion$MIGRATION_1_2$1 MIGRATION_1_2 = new Migration() {
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            db.execSQL("ALTER TABLE metadata ADD COLUMN categories TEXT");
            db.execSQL("ALTER TABLE metadata ADD COLUMN parsedWithSdk TEXT");
        }
    };
    private static final PluginCatalogDatabase$Companion$MIGRATION_2_3$1 MIGRATION_2_3 = new Migration() {
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase db) {
            db.execSQL("ALTER TABLE entries ADD COLUMN views INTEGER NOT NULL DEFAULT 0");
            db.execSQL("ALTER TABLE entries ADD COLUMN updatedAt INTEGER NOT NULL DEFAULT 0");
            db.execSQL("UPDATE entries SET updatedAt = date");
        }
    };

    public abstract PluginCatalogDao catalogDao();

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0004*\u0002\u0007\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDatabase$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "INSTANCE", "Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDatabase;", "MIGRATION_1_2", "com/exteragram/messenger/plugins/catalog/db/PluginCatalogDatabase$Companion$MIGRATION_1_2$1", "Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDatabase$Companion$MIGRATION_1_2$1;", "MIGRATION_2_3", "com/exteragram/messenger/plugins/catalog/db/PluginCatalogDatabase$Companion$MIGRATION_2_3$1", "Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDatabase$Companion$MIGRATION_2_3$1;", "getInstance", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPluginCatalogDatabase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginCatalogDatabase.kt\ncom/exteragram/messenger/plugins/catalog/db/PluginCatalogDatabase$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PluginCatalogDatabase getInstance() {
            PluginCatalogDatabase pluginCatalogDatabase;
            PluginCatalogDatabase pluginCatalogDatabase2 = PluginCatalogDatabase.INSTANCE;
            if (pluginCatalogDatabase2 != null) {
                return pluginCatalogDatabase2;
            }
            synchronized (this) {
                pluginCatalogDatabase = PluginCatalogDatabase.INSTANCE;
                if (pluginCatalogDatabase == null) {
                    RoomDatabase roomDatabaseBuild = Room.databaseBuilder(ApplicationLoader.applicationContext, PluginCatalogDatabase.class, "plugin-catalog").fallbackToDestructiveMigrationOnDowngrade(true).addMigrations(PluginCatalogDatabase.MIGRATION_1_2, PluginCatalogDatabase.MIGRATION_2_3).build();
                    PluginCatalogDatabase.INSTANCE = (PluginCatalogDatabase) roomDatabaseBuild;
                    pluginCatalogDatabase = (PluginCatalogDatabase) roomDatabaseBuild;
                }
            }
            return pluginCatalogDatabase;
        }
    }
}
