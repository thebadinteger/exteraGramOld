package com.exteragram.messenger.plugins.catalog;

import android.content.SharedPreferences;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.PythonPluginsEngine;
import com.exteragram.messenger.plugins.catalog.db.CatalogEntryEntity;
import com.exteragram.messenger.plugins.catalog.db.CatalogEntryVersion;
import com.exteragram.messenger.plugins.catalog.db.CatalogEntryWithMetadata;
import com.exteragram.messenger.plugins.catalog.db.PluginCatalogDao;
import com.exteragram.messenger.plugins.catalog.db.PluginCatalogDatabase;
import com.exteragram.messenger.plugins.catalog.db.PluginMetadataEntity;
import com.exteragram.messenger.plugins.catalog.db.PluginRepositoryEntity;
import com.google.android.gms.cast.MediaTrack;
import com.sun.jna.Callback;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_chatlists;

@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 q2\u00020\u0001:\u0003opqB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010*\u001a\u00020\u00162\u0018\u0010+\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0,\u0012\u0004\u0012\u00020\u00160\u0015J(\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u00102\u0018\u0010+\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002000,\u0012\u0004\u0012\u00020\u00160\u0015J\u0016\u00101\u001a\b\u0012\u0004\u0012\u0002000,2\u0006\u0010/\u001a\u00020\u0010H\u0002J\u000e\u00102\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\u0010J\u0006\u00103\u001a\u00020\u001cJ\u0006\u00104\u001a\u00020\u001cJ\u000e\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u001cJ&\u00107\u001a\u00020\u00162\u0006\u00108\u001a\u0002092\u0016\b\u0002\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015J\u000e\u0010:\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u0010J8\u0010;\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u001c2\u0006\u0010<\u001a\u00020\u001c2\u0018\b\u0002\u0010+\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015J0\u0010=\u001a\u00020\u00162\u0006\u0010>\u001a\u00020-2\b\u0010?\u001a\u0004\u0018\u0001092\u0014\u0010+\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010@\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J@\u0010A\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u00102\u0006\u0010B\u001a\u00020@2\u0006\u0010C\u001a\u00020\u00032\u0006\u0010D\u001a\u00020\u00032\u0016\u0010E\u001a\u0012\u0012\u0004\u0012\u00020F0\u0014j\b\u0012\u0004\u0012\u00020F`\u0017H\u0002J \u0010G\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u00102\u000e\u0010H\u001a\n\u0012\u0004\u0012\u00020F\u0018\u00010,H\u0002J\u001f\u0010I\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u00102\b\u0010J\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0002\u0010KJ\u0010\u0010L\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u0010H\u0002J\u000e\u0010M\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u0010J\b\u0010N\u001a\u00020\u0016H\u0002J\u0018\u0010O\u001a\u00020\u00162\u0006\u0010P\u001a\u00020#2\u0006\u0010Q\u001a\u00020RH\u0002J\u0018\u0010S\u001a\u00020\u00162\u0006\u0010P\u001a\u00020#2\u0006\u0010T\u001a\u00020UH\u0002J,\u0010V\u001a\u00020\u00162\u0006\u0010W\u001a\u0002002\u001c\u0010+\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010R\u0012\u0006\u0012\u0004\u0018\u00010Y\u0012\u0004\u0012\u00020\u00160XJ.\u0010Z\u001a\u00020\u00162\u0006\u0010[\u001a\u00020Y2\u0006\u0010\\\u001a\u00020\u00032\u0014\u0010+\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010R\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\u0012\u0010]\u001a\u0004\u0018\u00010R2\u0006\u0010^\u001a\u00020_H\u0002J5\u0010`\u001a\u00020\u00162\u0006\u0010a\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0016\u0010b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010d0c\"\u0004\u0018\u00010dH\u0016¢\u0006\u0002\u0010eJ\u001a\u0010f\u001a\u0004\u0018\u00010Y2\u0006\u0010/\u001a\u00020\u00102\u0006\u0010g\u001a\u00020FH\u0002J\u001a\u0010h\u001a\u0004\u0018\u00010F2\u0006\u0010/\u001a\u00020\u00102\u0006\u0010i\u001a\u00020jH\u0002J\u0018\u0010k\u001a\u00020U2\u0006\u0010l\u001a\u00020\u00102\u0006\u0010m\u001a\u00020\u001eH\u0002J\u0010\u0010n\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0082\u0001\u0010\u0012\u001av\u0012\u0004\u0012\u00020\u0010\u00120\u0012.\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00160\u00150\u0014j\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00160\u0015`\u00170\u0013j:\u0012\u0004\u0012\u00020\u0010\u00120\u0012.\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00160\u00150\u0014j\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00160\u0015`\u0017`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u001d\u001a\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f0\u0013j\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001f`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006r"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "account", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "dao", "Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDao;", "getDao", "()Lcom/exteragram/messenger/plugins/catalog/db/PluginCatalogDao;", "messagesController", "Lorg/telegram/messenger/MessagesController;", "getMessagesController", "()Lorg/telegram/messenger/MessagesController;", "syncingChannels", "Ljava/util/HashSet;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/HashSet;", "syncCallbacks", "Ljava/util/HashMap;", "Ljava/util/ArrayList;", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/ArrayList;", "Lkotlin/collections/HashMap;", "pendingForcedSyncs", "metadataRequests", "resolvingFolder", _UrlKt.FRAGMENT_ENCODE_SET, "downloads", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController$PendingDownload;", "observingDownloads", "metadataQueue", "Lkotlin/collections/ArrayDeque;", "Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController$MetadataJob;", "queuedDocuments", "activeMetadataJobs", "metadataUnavailable", "pendingUpdates", "postUpdatesRunnable", "Ljava/lang/Runnable;", "loadRepositories", Callback.METHOD_NAME, _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/catalog/db/PluginRepositoryEntity;", "loadCatalog", "channelId", "Lcom/exteragram/messenger/plugins/catalog/CatalogPlugin;", "getVisiblePlugins", "isSyncing", "isResolvingFolder", "isMetadataAvailable", "refreshFolderRepositories", "force", "addRepository", "chat", "Lorg/telegram/tgnet/TLRPC$Chat;", "removeRepository", "syncRepository", "loadMetadata", "resolveInputPeer", "repository", "cachedChat", "Lorg/telegram/tgnet/TLRPC$InputPeer;", "loadHistoryPage", "peer", "offsetId", "page", "collected", "Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryEntity;", "finishSync", "entries", "completeSync", "changed", "(JLjava/lang/Integer;)V", "getTopMessage", "loadMissingMetadata", "pumpMetadataQueue", "parseMetadata", "job", "file", "Ljava/io/File;", "storeMetadata", "metadata", "Lcom/exteragram/messenger/plugins/catalog/db/PluginMetadataEntity;", "downloadPlugin", "plugin", "Lkotlin/Function2;", "Lorg/telegram/messenger/MessageObject;", "downloadDocument", "messageObject", PluginsConstants.MenuItemProperties.PRIORITY, "findDownloadedFile", "document", "Lorg/telegram/tgnet/TLRPC$Document;", "didReceivedNotification", "id", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "createMessageObject", "entry", "toCatalogEntry", "message", "Lorg/telegram/tgnet/TLRPC$Message;", "failedMetadata", "documentId", "error", "postUpdate", "PendingDownload", "MetadataJob", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginCatalogController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginCatalogController.kt\ncom/exteragram/messenger/plugins/catalog/PluginCatalogController\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,726:1\n777#2:727\n873#2,2:728\n1586#2:730\n1661#2,3:731\n777#2:734\n873#2,2:735\n1915#2,2:744\n1586#2:747\n1661#2,3:748\n777#2:751\n873#2,2:752\n1220#2,2:754\n1249#2,4:756\n1606#2:760\n1617#2:761\n1924#2,2:762\n1926#2:765\n1618#2:766\n1220#2,2:779\n1249#2,4:781\n1642#2,10:785\n1915#2:795\n1916#2:797\n1652#2:798\n1834#2,4:799\n1220#2,2:803\n1249#2,4:805\n1642#2,10:821\n1915#2:831\n1916#2:833\n1652#2:834\n410#3,7:737\n1#4:746\n1#4:764\n1#4:796\n1#4:832\n41#5,12:767\n41#5,12:809\n*S KotlinDebug\n*F\n+ 1 PluginCatalogController.kt\ncom/exteragram/messenger/plugins/catalog/PluginCatalogController\n*L\n117#1:727\n117#1:728,2\n118#1:730\n118#1:731,3\n119#1:734\n119#1:735,2\n409#1:744,2\n82#1:747\n82#1:748,3\n82#1:751\n82#1:752,2\n175#1:754,2\n175#1:756,4\n177#1:760\n177#1:761\n177#1:762,2\n177#1:765\n177#1:766\n170#1:779,2\n170#1:781,4\n171#1:785,10\n171#1:795\n171#1:797\n171#1:798\n217#1:799,4\n370#1:803,2\n370#1:805,4\n421#1:821,10\n421#1:831\n421#1:833\n421#1:834\n255#1:737,7\n177#1:764\n171#1:796\n421#1:832\n194#1:767,12\n390#1:809,12\n*E\n"})
public final class PluginCatalogController implements NotificationCenter.NotificationCenterDelegate {
    public static final long ALL_REPOSITORIES = 0;
    public static final String ERROR_INVALID_MESSAGE = "INVALID_MESSAGE";
    public static final String ERROR_INVALID_METADATA = "INVALID_METADATA";
    public static final String ERROR_TOO_LARGE = "FILE_TOO_LARGE";
    private static final long FOLDER_REFRESH_INTERVAL = 300000;
    private static final String KEY_FOLDER_RESOLVED_AT = "folderResolvedAt";
    private static final String KEY_SORT_ORDER = "sortOrder";
    private static final long MAX_ARCHIVE_METADATA_FILE_SIZE = 10485760;
    private static final long MAX_METADATA_FILE_SIZE = 2097152;
    private static final int MAX_METADATA_JOBS = 3;
    private static final int MAX_PAGES = 20;
    private static final int PAGE_SIZE = 100;
    private static final long SYNC_INTERVAL = 600000;
    public static final String TRUSTED_FOLDER_SLUG = "pPhOtEq00KhjYTc6";
    private static final long UPDATE_DEBOUNCE_MS = 100;
    private static int extensionEnginesKey;
    private final int account;
    private int activeMetadataJobs;
    private final HashMap<String, PendingDownload> downloads;
    private final ArrayDeque<MetadataJob> metadataQueue;
    private final HashSet<Long> metadataRequests;
    private boolean metadataUnavailable;
    private boolean observingDownloads;
    private final HashSet<Long> pendingForcedSyncs;
    private final HashSet<Long> pendingUpdates;
    private final Runnable postUpdatesRunnable;
    private final HashSet<Long> queuedDocuments;
    private boolean resolvingFolder;
    private final HashMap<Long, ArrayList<Function1<Integer, Unit>>> syncCallbacks;
    private final HashSet<Long> syncingChannels;

    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DispatchQueue> catalogQueue$delegate = LazyKt.lazy(new Function0() {
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PluginCatalogController.$r8$lambda$EnICIMTJwwfChhA4Pcyv2fYYsqI();
        }
    });
    private static final HashMap<String, PluginsController.PluginsEngine> extensionEngines = new HashMap<>();
    private static final Lazy<SharedPreferences> preferences$delegate = LazyKt.lazy(new Function0() {
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ApplicationLoader.applicationContext.getSharedPreferences("plugin_catalog", 0);
        }
    });
    private static final PluginCatalogController[] instances = new PluginCatalogController[16];

    public PluginCatalogController(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    @JvmStatic
    public static final PluginCatalogController getInstance(int i) {
        return INSTANCE.getInstance(i);
    }

    private PluginCatalogController(int i) {
        this.account = i;
        this.syncingChannels = new HashSet<>();
        this.syncCallbacks = new HashMap<>();
        this.pendingForcedSyncs = new HashSet<>();
        this.metadataRequests = new HashSet<>();
        this.downloads = new HashMap<>();
        this.metadataQueue = new ArrayDeque<>();
        this.queuedDocuments = new HashSet<>();
        this.pendingUpdates = new HashSet<>();
        this.postUpdatesRunnable = new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$qvoWDPwNoq3rxMSQnTTg9UWx1pA(this.f$0);
            }
        };
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00122\u0010\u0004\u001a.\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005j\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006`\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR=\u0010\u0004\u001a.\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0005j\u0016\u0012\u0012\u0012\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006`\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController$PendingDownload;", _UrlKt.FRAGMENT_ENCODE_SET, "document", "Lorg/telegram/tgnet/TLRPC$Document;", "callbacks", "Ljava/util/ArrayList;", "Lkotlin/Function1;", "Ljava/io/File;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/ArrayList;", "<init>", "(Lorg/telegram/tgnet/TLRPC$Document;Ljava/util/ArrayList;)V", "getDocument", "()Lorg/telegram/tgnet/TLRPC$Document;", "getCallbacks", "()Ljava/util/ArrayList;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PendingDownload {
        private final ArrayList<Function1<File, Unit>> callbacks;
        private final TLRPC.Document document;

        public PendingDownload(TLRPC.Document document, ArrayList<Function1<File, Unit>> arrayList) {
            this.document = document;
            this.callbacks = arrayList;
        }

        public final ArrayList<Function1<File, Unit>> getCallbacks() {
            return this.callbacks;
        }

        public final TLRPC.Document getDocument() {
            return this.document;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController$MetadataJob;", _UrlKt.FRAGMENT_ENCODE_SET, "channelId", _UrlKt.FRAGMENT_ENCODE_SET, "entry", "Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryEntity;", "maxSize", "<init>", "(JLcom/exteragram/messenger/plugins/catalog/db/CatalogEntryEntity;J)V", "getChannelId", "()J", "getEntry", "()Lcom/exteragram/messenger/plugins/catalog/db/CatalogEntryEntity;", "getMaxSize", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MetadataJob {
        private final long channelId;
        private final CatalogEntryEntity entry;
        private final long maxSize;

        public MetadataJob(long j, CatalogEntryEntity catalogEntryEntity, long j2) {
            this.channelId = j;
            this.entry = catalogEntryEntity;
            this.maxSize = j2;
        }

        public final long getChannelId() {
            return this.channelId;
        }

        public final CatalogEntryEntity getEntry() {
            return this.entry;
        }

        public final long getMaxSize() {
            return this.maxSize;
        }
    }

    private final PluginCatalogDao getDao() {
        return PluginCatalogDatabase.INSTANCE.getInstance().catalogDao();
    }

    private final MessagesController getMessagesController() {
        return MessagesController.getInstance(this.account);
    }

    public static void $r8$lambda$qvoWDPwNoq3rxMSQnTTg9UWx1pA(PluginCatalogController pluginCatalogController) {
        ArrayList arrayList = new ArrayList(pluginCatalogController.pendingUpdates);
        pluginCatalogController.pendingUpdates.clear();
        NotificationCenter notificationCenter = NotificationCenter.getInstance(pluginCatalogController.account);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            notificationCenter.lambda$postNotificationNameOnUIThread$1(NotificationCenter.pluginCatalogUpdated, (Long) obj);
        }
    }

    public final void loadRepositories(final Function1<? super List<PluginRepositoryEntity>, Unit> callback) {
        INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.m1899$r8$lambda$WBnLXwNM3xY9UsDHTQW7xfD_lU(this.f$0, callback);
            }
        });
    }

    public static void m1899$r8$lambda$WBnLXwNM3xY9UsDHTQW7xfD_lU(final PluginCatalogController pluginCatalogController, final Function1 function1) {
        final List<PluginRepositoryEntity> repositories = pluginCatalogController.getDao().getRepositories();
        List<PluginRepositoryEntity> list = repositories;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((PluginRepositoryEntity) it.next()).getChannelId()));
        }
        final ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            if (pluginCatalogController.getMessagesController().getChat(Long.valueOf(((Number) obj).longValue())) == null) {
                arrayList2.add(obj);
            }
        }
        if (arrayList2.isEmpty()) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    function1.invoke(repositories);
                }
            });
        } else {
            final MessagesStorage messagesStorage = MessagesStorage.getInstance(pluginCatalogController.account);
            messagesStorage.getStorageQueue().postRunnable(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogController.$r8$lambda$A1ZU2JcyiBV3TtzM0R5ZTN2ygEg(messagesStorage, arrayList2, pluginCatalogController, function1, repositories);
                }
            });
        }
    }

    public static void $r8$lambda$A1ZU2JcyiBV3TtzM0R5ZTN2ygEg(MessagesStorage messagesStorage, List list, final PluginCatalogController pluginCatalogController, final Function1 function1, final List list2) {
        final ArrayList<TLRPC.Chat> chats = messagesStorage.getChats(new ArrayList<>(list));
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$jah5PaSoOaClCe5iiq60vN_eutg(this.f$0, chats, function1, list2);
            }
        });
    }

    public static void $r8$lambda$jah5PaSoOaClCe5iiq60vN_eutg(PluginCatalogController pluginCatalogController, ArrayList arrayList, Function1 function1, List list) {
        pluginCatalogController.getMessagesController().putChats(arrayList, true);
        function1.invoke(list);
    }

    public final void loadCatalog(final long channelId, final Function1<? super List<CatalogPlugin>, Unit> callback) {
        INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.m1903$r8$lambda$lx8SquYg9D1s8VxU2zN8ugS170(this.f$0, channelId, callback);
            }
        });
    }

    public static void m1903$r8$lambda$lx8SquYg9D1s8VxU2zN8ugS170(final PluginCatalogController pluginCatalogController, long j, final Function1 function1) {
        final List<CatalogPlugin> visiblePlugins = pluginCatalogController.getVisiblePlugins(j);
        PluginRepositoryEntity repository = pluginCatalogController.getDao().getRepository(j);
        final boolean z = (repository == null || repository.getSyncedAt() == 0 || repository.getPluginsCount() == visiblePlugins.size()) ? false : true;
        if (z) {
            pluginCatalogController.getDao().updatePluginsCount(j, visiblePlugins.size());
        }
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$h9bEJ7KKhP_G6euwEGV4hGNmJnE(function1, visiblePlugins, z, pluginCatalogController);
            }
        });
    }

    public static void $r8$lambda$h9bEJ7KKhP_G6euwEGV4hGNmJnE(Function1 function1, List list, boolean z, PluginCatalogController pluginCatalogController) {
        function1.invoke(list);
        if (z) {
            pluginCatalogController.postUpdate(0L);
        }
    }

    private final List<CatalogPlugin> getVisiblePlugins(long channelId) {
        List<CatalogEntryWithMetadata> entries = getDao().getEntries(channelId);
        ArrayList arrayList = new ArrayList();
        for (Object obj : entries) {
            if (INSTANCE.isSupported(((CatalogEntryWithMetadata) obj).getEntry().getFileName())) {
                arrayList.add(obj);
            }
        }
        CatalogPlugin.Companion companion = CatalogPlugin.INSTANCE;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            arrayList2.add(companion.from((CatalogEntryWithMetadata) obj2));
        }
        List<CatalogPlugin> listLatestVersions = CatalogPlugin.INSTANCE.latestVersions(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : listLatestVersions) {
            if (!((CatalogPlugin) obj3).isFailed()) {
                arrayList3.add(obj3);
            }
        }
        return arrayList3;
    }

    public final boolean isSyncing(long channelId) {
        return this.syncingChannels.contains(Long.valueOf(channelId));
    }

    public final boolean getResolvingFolder() {
        return this.resolvingFolder;
    }

    public final boolean isMetadataAvailable() {
        return ExteraConfig.getPluginsEngine() && !this.metadataUnavailable;
    }

    public final void refreshFolderRepositories(boolean force) {
        if (this.resolvingFolder) {
            return;
        }
        if (force || System.currentTimeMillis() - INSTANCE.getPreferences().getLong(KEY_FOLDER_RESOLVED_AT, 0L) >= FOLDER_REFRESH_INTERVAL) {
            this.resolvingFolder = true;
            postUpdate(0L);
            TL_chatlists.TL_chatlists_checkChatlistInvite tL_chatlists_checkChatlistInvite = new TL_chatlists.TL_chatlists_checkChatlistInvite();
            tL_chatlists_checkChatlistInvite.slug = TRUSTED_FOLDER_SLUG;
            ConnectionsManager.getInstance(this.account).sendRequest(tL_chatlists_checkChatlistInvite, new RequestDelegate() {
                @Override // org.telegram.tgnet.RequestDelegate
                public final void run(TLObject tLObject, TLRPC.TL_error tL_error) {
                    AndroidUtilities.runOnUIThread(new Runnable() {
                        @Override // java.lang.Runnable
                        public final void run() {
                            PluginCatalogController.$r8$lambda$SM75etGC1Uj54vpfhBblCVZ4nHA(tLObject, tL_error, pluginCatalogController);
                        }
                    });
                }
            });
        }
    }

    public static void $r8$lambda$SM75etGC1Uj54vpfhBblCVZ4nHA(TLObject tLObject, TLRPC.TL_error tL_error, final PluginCatalogController pluginCatalogController) {
        Iterable<TLRPC.Peer> iterablePlus;
        ArrayList<TLRPC.Chat> arrayList;
        ArrayList<TLRPC.User> arrayList2;
        int i = 0;
        if (tLObject instanceof TL_chatlists.TL_chatlists_chatlistInvite) {
            TL_chatlists.TL_chatlists_chatlistInvite tL_chatlists_chatlistInvite = (TL_chatlists.TL_chatlists_chatlistInvite) tLObject;
            iterablePlus = tL_chatlists_chatlistInvite.peers;
            arrayList = tL_chatlists_chatlistInvite.chats;
            arrayList2 = tL_chatlists_chatlistInvite.users;
        } else if (tLObject instanceof TL_chatlists.TL_chatlists_chatlistInviteAlready) {
            TL_chatlists.TL_chatlists_chatlistInviteAlready tL_chatlists_chatlistInviteAlready = (TL_chatlists.TL_chatlists_chatlistInviteAlready) tLObject;
            iterablePlus = CollectionsKt.plus((Collection) tL_chatlists_chatlistInviteAlready.already_peers, (Iterable) tL_chatlists_chatlistInviteAlready.missing_peers);
            arrayList = tL_chatlists_chatlistInviteAlready.chats;
            arrayList2 = tL_chatlists_chatlistInviteAlready.users;
        } else {
            FileLog.e("PluginCatalog: failed to resolve folder: " + (tL_error != null ? tL_error.text : null));
            pluginCatalogController.resolvingFolder = false;
            pluginCatalogController.postUpdate(0L);
            return;
        }
        pluginCatalogController.getMessagesController().putChats(arrayList, false);
        pluginCatalogController.getMessagesController().putUsers(arrayList2, false);
        MessagesStorage.getInstance(pluginCatalogController.account).putUsersAndChats(arrayList2, arrayList, true, true);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        int size = arrayList.size();
        while (i < size) {
            TLRPC.Chat chat = arrayList.get(i);
            i++;
            linkedHashMap.put(Long.valueOf(chat.id), chat);
        }
        final ArrayList arrayList3 = new ArrayList();
        for (TLRPC.Peer peer : iterablePlus) {
            TLRPC.Chat chat2 = (TLRPC.Chat) linkedHashMap.get(Long.valueOf(peer.channel_id));
            if (chat2 == null || peer.channel_id == 0 || !ChatObject.isChannel(chat2)) {
                chat2 = null;
            }
            if (chat2 != null) {
                arrayList3.add(chat2);
            }
        }
        INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$iokRRahKemksdNyQx3wLViHkj0E(this.f$0, arrayList3);
            }
        });
    }

    public static void $r8$lambda$iokRRahKemksdNyQx3wLViHkj0E(final PluginCatalogController pluginCatalogController, List list) {
        PluginRepositoryEntity pluginRepositoryEntity;
        List<PluginRepositoryEntity> repositories = pluginCatalogController.getDao().getRepositories();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(repositories, 10)), 16));
        for (Object obj : repositories) {
            linkedHashMap.put(Long.valueOf(((PluginRepositoryEntity) obj).getChannelId()), obj);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj2 : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TLRPC.Chat chat = (TLRPC.Chat) obj2;
            PluginRepositoryEntity pluginRepositoryEntity2 = (PluginRepositoryEntity) linkedHashMap.get(Long.valueOf(chat.id));
            if (pluginRepositoryEntity2 == null || pluginRepositoryEntity2.getSource() != 1) {
                pluginRepositoryEntity = new PluginRepositoryEntity(chat.id, ChatObject.getPublicUsername(chat), chat.title, 0, i, pluginRepositoryEntity2 != null ? pluginRepositoryEntity2.getAddedAt() : jCurrentTimeMillis, pluginRepositoryEntity2 != null ? pluginRepositoryEntity2.getSyncedAt() : 0L, pluginRepositoryEntity2 != null ? pluginRepositoryEntity2.getPluginsCount() : 0);
            } else {
                pluginRepositoryEntity = null;
            }
            if (pluginRepositoryEntity != null) {
                arrayList.add(pluginRepositoryEntity);
            }
            i = i2;
        }
        pluginCatalogController.getDao().replaceFolderRepositories(arrayList);
        SharedPreferences.Editor editorEdit = INSTANCE.getPreferences().edit();
        editorEdit.putLong(KEY_FOLDER_RESOLVED_AT, jCurrentTimeMillis);
        editorEdit.apply();
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$87fW8lXJgUC1DkQumA4F4C2tccc(this.f$0);
            }
        });
    }

    public static void $r8$lambda$87fW8lXJgUC1DkQumA4F4C2tccc(PluginCatalogController pluginCatalogController) {
        pluginCatalogController.resolvingFolder = false;
        pluginCatalogController.postUpdate(0L);
    }

    public static void addRepository$default(PluginCatalogController pluginCatalogController, TLRPC.Chat chat, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        pluginCatalogController.addRepository(chat, function1);
    }

    public final void addRepository(final TLRPC.Chat chat, final Function1<? super Boolean, Unit> callback) {
        if (!ChatObject.isChannel(chat)) {
            if (callback != null) {
                callback.invoke(Boolean.FALSE);
            }
        } else {
            MessagesStorage.getInstance(this.account).putUsersAndChats(null, CollectionsKt.arrayListOf(chat), true, true);
            final String publicUsername = ChatObject.getPublicUsername(chat);
            final String str = chat.title;
            INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogController.$r8$lambda$2QgDqXvNuSO8i4unJtjOcAcyNQ0(this.f$0, chat, publicUsername, str, callback);
                }
            });
        }
    }

    public static void $r8$lambda$2QgDqXvNuSO8i4unJtjOcAcyNQ0(final PluginCatalogController pluginCatalogController, TLRPC.Chat chat, String str, String str2, final Function1 function1) {
        if (pluginCatalogController.getDao().getRepository(chat.id) != null) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogController.m1890$r8$lambda$3TSb95KwioLOXpaXu2d5AmFQNU(function1);
                }
            });
            return;
        }
        List<PluginRepositoryEntity> repositories = pluginCatalogController.getDao().getRepositories();
        int i = 0;
        if (!(repositories instanceof Collection) || !repositories.isEmpty()) {
            Iterator<T> it = repositories.iterator();
            while (it.hasNext()) {
                if (((PluginRepositoryEntity) it.next()).getSource() == 1 && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        pluginCatalogController.getDao().upsertRepositories(CollectionsKt.listOf(new PluginRepositoryEntity(chat.id, str, str2, 1, i, System.currentTimeMillis(), 0L, 0, 192, null)));
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.m1897$r8$lambda$I0VTwohJjf7P4ARVvL4hJ0AqH4(function1, pluginCatalogController);
            }
        });
    }

    public static void m1890$r8$lambda$3TSb95KwioLOXpaXu2d5AmFQNU(Function1 function1) {
        if (function1 != null) {
            function1.invoke(Boolean.FALSE);
        }
    }

    public static void m1897$r8$lambda$I0VTwohJjf7P4ARVvL4hJ0AqH4(Function1 function1, PluginCatalogController pluginCatalogController) {
        if (function1 != null) {
            function1.invoke(Boolean.TRUE);
        }
        pluginCatalogController.postUpdate(0L);
    }

    public final void removeRepository(final long channelId) {
        INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.m1894$r8$lambda$ETtN1QqGqk7g1CeJfdOK9w_vs4(this.f$0, channelId);
            }
        });
    }

    public static void m1894$r8$lambda$ETtN1QqGqk7g1CeJfdOK9w_vs4(final PluginCatalogController pluginCatalogController, final long j) {
        PluginRepositoryEntity repository = pluginCatalogController.getDao().getRepository(j);
        if (repository == null || repository.getSource() != 1) {
            return;
        }
        pluginCatalogController.getDao().removeRepository(j);
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.m1895$r8$lambda$EuiS0Uy1osQg9Z8fThYALSraxo(this.f$0, j);
            }
        });
    }

    public static void m1895$r8$lambda$EuiS0Uy1osQg9Z8fThYALSraxo(PluginCatalogController pluginCatalogController, final long j) {
        CollectionsKt.removeAll((List) pluginCatalogController.metadataQueue, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(PluginCatalogController.m1900$r8$lambda$XthcvpipNIAEasoMbA1msxrNGc(j, (PluginCatalogController.MetadataJob) obj));
            }
        });
        pluginCatalogController.postUpdate(0L);
    }

    public static boolean m1900$r8$lambda$XthcvpipNIAEasoMbA1msxrNGc(long j, MetadataJob metadataJob) {
        return metadataJob.getChannelId() == j;
    }

    public static void syncRepository$default(PluginCatalogController pluginCatalogController, long j, boolean z, boolean z2, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            function1 = null;
        }
        pluginCatalogController.syncRepository(j, z, z2, function1);
    }

    public final void syncRepository(final long channelId, final boolean force, boolean loadMetadata, Function1<? super Integer, Unit> callback) {
        if (loadMetadata) {
            this.metadataRequests.add(Long.valueOf(channelId));
        }
        if (callback != null) {
            HashMap<Long, ArrayList<Function1<Integer, Unit>>> map = this.syncCallbacks;
            Long lValueOf = Long.valueOf(channelId);
            ArrayList<Function1<Integer, Unit>> arrayList = map.get(lValueOf);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                map.put(lValueOf, arrayList);
            }
            arrayList.add(callback);
        }
        if (this.syncingChannels.add(Long.valueOf(channelId))) {
            postUpdate(channelId);
            final int topMessage = getTopMessage(channelId);
            INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogController.m1889$r8$lambda$476D7k7N4NjnGunMzDErl4E_U(this.f$0, channelId, topMessage, force);
                }
            });
        } else if (force) {
            this.pendingForcedSyncs.add(Long.valueOf(channelId));
        }
    }

    public static void m1889$r8$lambda$476D7k7N4NjnGunMzDErl4E_U(final PluginCatalogController pluginCatalogController, final long j, int i, boolean z) {
        final PluginRepositoryEntity repository = pluginCatalogController.getDao().getRepository(j);
        Companion companion = INSTANCE;
        boolean z2 = i > companion.getPreferences().getInt(companion.syncedTopMessageKey(j), 0);
        if (repository == null || (!z && !z2 && System.currentTimeMillis() - repository.getSyncedAt() < SYNC_INTERVAL)) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogController.m1893$r8$lambda$Bs8xJqRNhsUUGq1LPHxE5PQLlw(this.f$0, j, repository);
                }
            });
            return;
        }
        TLRPC.Chat chat = pluginCatalogController.getMessagesController().getChat(Long.valueOf(j));
        if (chat == null) {
            chat = MessagesStorage.getInstance(pluginCatalogController.account).getChatSync(j);
        }
        final TLRPC.Chat chat2 = chat;
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController pluginCatalogController2 = this.f$0;
                pluginCatalogController2.resolveInputPeer(repository, chat2, new Function1() {
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return PluginCatalogController.$r8$lambda$U4DhdFXNBjHN8NpZ2FtVWn8bLQw(j, pluginCatalogController2, (TLRPC.InputPeer) obj);
                    }
                });
            }
        });
    }

    public static void m1893$r8$lambda$Bs8xJqRNhsUUGq1LPHxE5PQLlw(PluginCatalogController pluginCatalogController, long j, PluginRepositoryEntity pluginRepositoryEntity) {
        pluginCatalogController.syncingChannels.remove(Long.valueOf(j));
        pluginCatalogController.postUpdate(j);
        pluginCatalogController.completeSync(j, pluginRepositoryEntity != null ? 0 : null);
        if (!pluginCatalogController.metadataRequests.remove(Long.valueOf(j)) || pluginRepositoryEntity == null) {
            return;
        }
        pluginCatalogController.loadMissingMetadata(j);
    }

    public static Unit $r8$lambda$U4DhdFXNBjHN8NpZ2FtVWn8bLQw(long j, PluginCatalogController pluginCatalogController, TLRPC.InputPeer inputPeer) {
        if (inputPeer == null) {
            FileLog.e("PluginCatalog: cannot resolve repository " + j);
            pluginCatalogController.finishSync(j, null);
        } else {
            pluginCatalogController.loadHistoryPage(j, inputPeer, 0, 0, new ArrayList<>());
        }
        return Unit.INSTANCE;
    }

    public final void resolveInputPeer(final PluginRepositoryEntity repository, TLRPC.Chat cachedChat, final Function1<? super TLRPC.InputPeer, Unit> callback) {
        TLRPC.Chat chat = getMessagesController().getChat(Long.valueOf(repository.getChannelId()));
        if (chat != null) {
            cachedChat = chat;
        } else if (cachedChat != null) {
            getMessagesController().putChat(cachedChat, true);
        } else {
            cachedChat = null;
        }
        if (cachedChat != null && cachedChat.access_hash != 0) {
            callback.invoke(MessagesController.getInputPeer(cachedChat));
            return;
        }
        String username = repository.getUsername();
        if (username == null || username.length() == 0) {
            callback.invoke(null);
        } else {
            getMessagesController().getUserNameResolver().resolve(username, new Utilities.Callback() {
                @Override // org.telegram.messenger.Utilities.Callback
                public final void run(Object obj) {
                    PluginCatalogController.$r8$lambda$7yFmCObItfdgVIdGUKdFGzL6BxM(repository, this, callback, (Long) obj);
                }
            });
        }
    }

    public static void $r8$lambda$7yFmCObItfdgVIdGUKdFGzL6BxM(PluginRepositoryEntity pluginRepositoryEntity, PluginCatalogController pluginCatalogController, Function1 function1, Long l) {
        TLRPC.Chat chat = (l != null && l.longValue() == (-pluginRepositoryEntity.getChannelId())) ? pluginCatalogController.getMessagesController().getChat(Long.valueOf(pluginRepositoryEntity.getChannelId())) : null;
        function1.invoke(chat != null ? MessagesController.getInputPeer(chat) : null);
    }

    private final void loadHistoryPage(final long channelId, final TLRPC.InputPeer peer, int offsetId, final int page, final ArrayList<CatalogEntryEntity> collected) {
        TLRPC.TL_messages_search tL_messages_search = new TLRPC.TL_messages_search();
        tL_messages_search.peer = peer;
        tL_messages_search.q = _UrlKt.FRAGMENT_ENCODE_SET;
        tL_messages_search.filter = new TLRPC.TL_inputMessagesFilterDocument();
        tL_messages_search.offset_id = offsetId;
        tL_messages_search.limit = 100;
        ConnectionsManager.getInstance(this.account).sendRequest(tL_messages_search, new RequestDelegate() {
            @Override // org.telegram.tgnet.RequestDelegate
            public final void run(TLObject tLObject, TLRPC.TL_error tL_error) {
                PluginCatalogController.$r8$lambda$EjGN529KrzcLjUcjg56Qhb4Ty9k(channelId, this, collected, page, peer, tLObject, tL_error);
            }
        });
    }

    public static void $r8$lambda$EjGN529KrzcLjUcjg56Qhb4Ty9k(final long j, final PluginCatalogController pluginCatalogController, final ArrayList arrayList, final int i, final TLRPC.InputPeer inputPeer, final TLObject tLObject, TLRPC.TL_error tL_error) {
        if (!(tLObject instanceof TLRPC.messages_Messages)) {
            FileLog.e("PluginCatalog: history request failed for " + j + ": " + (tL_error != null ? tL_error.text : null));
            AndroidUtilities.runOnUIThread(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.finishSync(j, null);
                }
            });
            return;
        }
        INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                PluginCatalogController.m1896$r8$lambda$FkJUGqemmZUDqRXvryG8peq4I(tLObject, pluginCatalogController, j, arrayList, i, inputPeer);
            }
        });
    }

    public static void m1896$r8$lambda$FkJUGqemmZUDqRXvryG8peq4I(final TLObject tLObject, final PluginCatalogController pluginCatalogController, final long j, final ArrayList arrayList, final int i, final TLRPC.InputPeer inputPeer) throws Throwable {
        TLRPC.messages_Messages messages_messages = (TLRPC.messages_Messages) tLObject;
        ArrayList<TLRPC.Message> arrayList2 = messages_messages.messages;
        int size = arrayList2.size();
        boolean z = false;
        int i2 = 0;
        while (i2 < size) {
            TLRPC.Message message = arrayList2.get(i2);
            i2++;
            CatalogEntryEntity catalogEntry = pluginCatalogController.toCatalogEntry(j, message);
            if (catalogEntry != null) {
                arrayList.add(catalogEntry);
            }
        }
        TLRPC.Message message2 = (TLRPC.Message) CollectionsKt.lastOrNull((List) messages_messages.messages);
        final int i3 = message2 != null ? message2.id : 0;
        if (messages_messages.messages.size() >= 100 && i3 != 0 && i + 1 < 20) {
            z = true;
        }
        final boolean z2 = z;
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$jS2IokaFAjn4VsGYRQi38XU9tt8(this.f$0, tLObject, z2, j, inputPeer, i3, i, arrayList);
            }
        });
    }

    public static void $r8$lambda$jS2IokaFAjn4VsGYRQi38XU9tt8(PluginCatalogController pluginCatalogController, TLObject tLObject, boolean z, long j, TLRPC.InputPeer inputPeer, int i, int i2, ArrayList arrayList) {
        TLRPC.messages_Messages messages_messages = (TLRPC.messages_Messages) tLObject;
        pluginCatalogController.getMessagesController().putChats(messages_messages.chats, false);
        pluginCatalogController.getMessagesController().putUsers(messages_messages.users, false);
        if (z) {
            pluginCatalogController.loadHistoryPage(j, inputPeer, i, i2 + 1, arrayList);
        } else {
            pluginCatalogController.finishSync(j, arrayList);
        }
    }

    public final void finishSync(final long channelId, final List<CatalogEntryEntity> entries) {
        if (entries == null) {
            this.syncingChannels.remove(Long.valueOf(channelId));
            postUpdate(channelId);
            completeSync(channelId, null);
            if (this.metadataRequests.remove(Long.valueOf(channelId))) {
                loadMissingMetadata(channelId);
                return;
            }
            return;
        }
        final int topMessage = getTopMessage(channelId);
        INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$MQsXbntglIilFMy7XoWTsCniUjY(this.f$0, channelId, entries, topMessage);
            }
        });
    }

    public static void $r8$lambda$MQsXbntglIilFMy7XoWTsCniUjY(final PluginCatalogController pluginCatalogController, long j, List list, int i) {
        final long j2;
        final Ref.IntRef intRef = new Ref.IntRef();
        PluginRepositoryEntity repository = pluginCatalogController.getDao().getRepository(j);
        if (repository != null) {
            List<CatalogEntryVersion> entryVersions = pluginCatalogController.getDao().getEntryVersions(j);
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(entryVersions, 10)), 16));
            for (Object obj : entryVersions) {
                linkedHashMap.put(Integer.valueOf(((CatalogEntryVersion) obj).getMessageId()), obj);
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                CatalogEntryEntity catalogEntryEntity = (CatalogEntryEntity) it.next();
                CatalogEntryVersion catalogEntryVersion = (CatalogEntryVersion) linkedHashMap.get(Integer.valueOf(catalogEntryEntity.getMessageId()));
                if (catalogEntryVersion == null) {
                    catalogEntryEntity.setUpdatedAt(catalogEntryEntity.getDate());
                    if (((long) catalogEntryEntity.getDate()) * 1000 > repository.getSyncedAt()) {
                        if (INSTANCE.isSupported(catalogEntryEntity.getFileName())) {
                            intRef.element++;
                        }
                    }
                } else if (catalogEntryVersion.getDocumentId() == catalogEntryEntity.getDocumentId()) {
                    catalogEntryEntity.setUpdatedAt(catalogEntryVersion.getUpdatedAt());
                } else if (INSTANCE.isSupported(catalogEntryEntity.getFileName())) {
                    intRef.element++;
                }
            }
            pluginCatalogController.getDao().replaceEntries(j, list);
            j2 = j;
            pluginCatalogController.getDao().updateSyncState(j2, System.currentTimeMillis(), pluginCatalogController.getVisiblePlugins(j).size());
            Companion companion = INSTANCE;
            SharedPreferences.Editor editorEdit = companion.getPreferences().edit();
            editorEdit.putInt(companion.syncedTopMessageKey(j2), i);
            editorEdit.apply();
        } else {
            j2 = j;
        }
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$x7C6yRONI2r6_J5eQtuiWJ2H7T4(this.f$0, j2, intRef);
            }
        });
    }

    public static void $r8$lambda$x7C6yRONI2r6_J5eQtuiWJ2H7T4(PluginCatalogController pluginCatalogController, long j, Ref.IntRef intRef) {
        pluginCatalogController.syncingChannels.remove(Long.valueOf(j));
        pluginCatalogController.postUpdate(j);
        pluginCatalogController.postUpdate(0L);
        pluginCatalogController.completeSync(j, Integer.valueOf(intRef.element));
        if (pluginCatalogController.metadataRequests.remove(Long.valueOf(j))) {
            pluginCatalogController.loadMissingMetadata(j);
        }
    }

    private final void completeSync(long channelId, Integer changed) {
        if (this.pendingForcedSyncs.remove(Long.valueOf(channelId))) {
            syncRepository$default(this, channelId, true, false, null, 8, null);
            return;
        }
        ArrayList<Function1<Integer, Unit>> arrayListRemove = this.syncCallbacks.remove(Long.valueOf(channelId));
        if (arrayListRemove != null) {
            int size = arrayListRemove.size();
            int i = 0;
            while (i < size) {
                Function1<Integer, Unit> function1 = arrayListRemove.get(i);
                i++;
                function1.invoke(changed);
            }
        }
    }

    private final int getTopMessage(long channelId) {
        TLRPC.Dialog dialog = getMessagesController().dialogs_dict.get(-channelId);
        if (dialog != null) {
            return dialog.top_message;
        }
        return 0;
    }

    public final void loadMissingMetadata(final long channelId) {
        if (ExteraConfig.getPluginsEngine()) {
            this.metadataUnavailable = false;
            INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogController.$r8$lambda$wLPr_T0PPpdDkFaBmT3gxzF96yw(this.f$0, channelId);
                }
            });
        }
    }

    public static void $r8$lambda$wLPr_T0PPpdDkFaBmT3gxzF96yw(final PluginCatalogController pluginCatalogController, long j) {
        long j2;
        MetadataJob metadataJob;
        HashSet hashSet = CollectionsKt.toHashSet(pluginCatalogController.getDao().getDocumentIdsWithError(ERROR_TOO_LARGE));
        List<CatalogEntryEntity> entriesWithoutMetadata = pluginCatalogController.getDao().getEntriesWithoutMetadata(j, PythonPluginsEngine.INSTANCE.getSDK_VERSION(), ERROR_TOO_LARGE);
        final ArrayList arrayList = new ArrayList();
        for (CatalogEntryEntity catalogEntryEntity : entriesWithoutMetadata) {
            Long metadataSizeLimit = INSTANCE.getMetadataSizeLimit(catalogEntryEntity.getFileName());
            if (metadataSizeLimit == null || (catalogEntryEntity.getSize() > metadataSizeLimit.longValue() && hashSet.contains(Long.valueOf(catalogEntryEntity.getDocumentId())))) {
                j2 = j;
                metadataJob = null;
            } else {
                j2 = j;
                metadataJob = new MetadataJob(j2, catalogEntryEntity, metadataSizeLimit.longValue());
            }
            if (metadataJob != null) {
                arrayList.add(metadataJob);
            }
            j = j2;
        }
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$anxnaxcadNHK107QkG4zz03p7Gg(arrayList, pluginCatalogController);
            }
        });
    }

    public static void $r8$lambda$anxnaxcadNHK107QkG4zz03p7Gg(List list, PluginCatalogController pluginCatalogController) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MetadataJob metadataJob = (MetadataJob) it.next();
            if (pluginCatalogController.queuedDocuments.add(Long.valueOf(metadataJob.getEntry().getDocumentId()))) {
                pluginCatalogController.metadataQueue.addLast(metadataJob);
            }
        }
        pluginCatalogController.pumpMetadataQueue();
    }

    private final void pumpMetadataQueue() {
        final MetadataJob metadataJobRemoveFirstOrNull;
        while (this.activeMetadataJobs < 3 && (metadataJobRemoveFirstOrNull = this.metadataQueue.removeFirstOrNull()) != null) {
            if (metadataJobRemoveFirstOrNull.getEntry().getSize() > metadataJobRemoveFirstOrNull.getMaxSize()) {
                storeMetadata(metadataJobRemoveFirstOrNull, failedMetadata(metadataJobRemoveFirstOrNull.getEntry().getDocumentId(), ERROR_TOO_LARGE));
            } else {
                MessageObject messageObjectCreateMessageObject = createMessageObject(metadataJobRemoveFirstOrNull.getChannelId(), metadataJobRemoveFirstOrNull.getEntry());
                if ((messageObjectCreateMessageObject != null ? messageObjectCreateMessageObject.getDocument() : null) == null) {
                    storeMetadata(metadataJobRemoveFirstOrNull, failedMetadata(metadataJobRemoveFirstOrNull.getEntry().getDocumentId(), ERROR_INVALID_MESSAGE));
                } else {
                    this.activeMetadataJobs++;
                    downloadDocument(messageObjectCreateMessageObject, 0, new Function1() {
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return PluginCatalogController.m1892$r8$lambda$9TwAl1w83KLLicyIMJquf46J5Y(this.f$0, metadataJobRemoveFirstOrNull, (File) obj);
                        }
                    });
                }
            }
        }
    }

    public static Unit m1892$r8$lambda$9TwAl1w83KLLicyIMJquf46J5Y(PluginCatalogController pluginCatalogController, MetadataJob metadataJob, File file) {
        if (file == null) {
            pluginCatalogController.activeMetadataJobs--;
            pluginCatalogController.queuedDocuments.remove(Long.valueOf(metadataJob.getEntry().getDocumentId()));
            pluginCatalogController.pumpMetadataQueue();
        } else {
            pluginCatalogController.parseMetadata(metadataJob, file);
        }
        return Unit.INSTANCE;
    }

    private final void parseMetadata(final MetadataJob job, File file) {
        final String absolutePath = file.getAbsolutePath();
        PluginsController.INSTANCE.runOnPluginsQueue(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.m1891$r8$lambda$5546PNT6On0ME1on5LP6y7q5oE(job, absolutePath, this);
            }
        });
    }

    public static void m1891$r8$lambda$5546PNT6On0ME1on5LP6y7q5oE(final MetadataJob metadataJob, String str, final PluginCatalogController pluginCatalogController) {
        final PluginMetadataEntity pluginMetadataEntityFailedMetadata;
        String str2;
        String str3;
        PluginsController.PluginsEngine pluginEngine = PluginsController.INSTANCE.getPluginEngine(INSTANCE.probeFile(metadataJob.getEntry().getFileName()));
        Map<String, String> pluginMetadata = null;
        if (pluginEngine != null) {
            try {
                pluginMetadata = pluginEngine.parsePluginMetadata(str);
            } catch (Throwable th) {
                FileLog.e("PluginCatalog: failed to parse " + metadataJob.getEntry().getFileName(), th);
            }
        }
        if (pluginEngine == null) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogController.$r8$lambda$_MQxLQZnTMEsGNh8LZBr8I2YoxE(this.f$0, metadataJob);
                }
            });
            return;
        }
        if (pluginMetadata != null && pluginMetadata.isEmpty()) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogController.$r8$lambda$IbdSN5byNn1lYI7HLBjeZQPrIKc(this.f$0, metadataJob);
                }
            });
            return;
        }
        if (pluginMetadata == null || (str2 = pluginMetadata.get("id")) == null || str2.length() == 0 || (str3 = pluginMetadata.get("name")) == null || str3.length() == 0) {
            pluginMetadataEntityFailedMetadata = pluginCatalogController.failedMetadata(metadataJob.getEntry().getDocumentId(), ERROR_INVALID_METADATA);
        } else {
            pluginMetadataEntityFailedMetadata = new PluginMetadataEntity(metadataJob.getEntry().getDocumentId(), pluginMetadata.get("id"), pluginMetadata.get("name"), pluginMetadata.get("author"), pluginMetadata.get(MediaTrack.ROLE_DESCRIPTION), pluginMetadata.get("version"), pluginMetadata.get("icon"), pluginMetadata.get("app_version"), pluginMetadata.get("sdk_version"), pluginMetadata.get(Common.ASSET_REQUIREMENTS), pluginMetadata.get("category"), null, System.currentTimeMillis(), PythonPluginsEngine.INSTANCE.getSDK_VERSION());
        }
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.m1901$r8$lambda$Zs9TQP7RXM9yTHUztvGGxtB6M(this.f$0, metadataJob, pluginMetadataEntityFailedMetadata);
            }
        });
    }

    public static void $r8$lambda$_MQxLQZnTMEsGNh8LZBr8I2YoxE(PluginCatalogController pluginCatalogController, MetadataJob metadataJob) {
        pluginCatalogController.activeMetadataJobs--;
        pluginCatalogController.queuedDocuments.remove(Long.valueOf(metadataJob.getEntry().getDocumentId()));
        pluginCatalogController.pumpMetadataQueue();
    }

    public static void $r8$lambda$IbdSN5byNn1lYI7HLBjeZQPrIKc(PluginCatalogController pluginCatalogController, MetadataJob metadataJob) {
        pluginCatalogController.activeMetadataJobs--;
        pluginCatalogController.metadataUnavailable = true;
        pluginCatalogController.metadataQueue.clear();
        pluginCatalogController.queuedDocuments.clear();
        pluginCatalogController.postUpdate(metadataJob.getChannelId());
    }

    public static void m1901$r8$lambda$Zs9TQP7RXM9yTHUztvGGxtB6M(PluginCatalogController pluginCatalogController, MetadataJob metadataJob, PluginMetadataEntity pluginMetadataEntity) {
        pluginCatalogController.activeMetadataJobs--;
        pluginCatalogController.storeMetadata(metadataJob, pluginMetadataEntity);
    }

    private final void storeMetadata(final MetadataJob job, final PluginMetadataEntity metadata) {
        INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.m1898$r8$lambda$IUmcabuxTM7s30tKkTLaaXhx4o(this.f$0, metadata, job);
            }
        });
    }

    public static void m1898$r8$lambda$IUmcabuxTM7s30tKkTLaaXhx4o(final PluginCatalogController pluginCatalogController, PluginMetadataEntity pluginMetadataEntity, final MetadataJob metadataJob) {
        pluginCatalogController.getDao().upsertMetadata(pluginMetadataEntity);
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.m1902$r8$lambda$hA0L0DgBP8JU64cSqL8oJWqtqs(this.f$0, metadataJob);
            }
        });
    }

    public static void m1902$r8$lambda$hA0L0DgBP8JU64cSqL8oJWqtqs(PluginCatalogController pluginCatalogController, MetadataJob metadataJob) {
        pluginCatalogController.queuedDocuments.remove(Long.valueOf(metadataJob.getEntry().getDocumentId()));
        pluginCatalogController.postUpdate(metadataJob.getChannelId());
        pluginCatalogController.pumpMetadataQueue();
    }

    public final void downloadPlugin(final CatalogPlugin plugin, final Function2<? super File, ? super MessageObject, Unit> callback) {
        INSTANCE.getCatalogQueue().postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$c8tXUybU_C7JUcmp3VQ05oEomhs(this.f$0, plugin, callback);
            }
        });
    }

    public static void $r8$lambda$c8tXUybU_C7JUcmp3VQ05oEomhs(final PluginCatalogController pluginCatalogController, final CatalogPlugin catalogPlugin, final Function2 function2) {
        final CatalogEntryEntity entry = pluginCatalogController.getDao().getEntry(catalogPlugin.getChannelId(), catalogPlugin.getMessageId());
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogController.$r8$lambda$Tbgol6kQywgVVcQwTP4gTfWEc4c(entry, function2, pluginCatalogController, catalogPlugin);
            }
        });
    }

    public static void $r8$lambda$Tbgol6kQywgVVcQwTP4gTfWEc4c(CatalogEntryEntity catalogEntryEntity, final Function2 function2, PluginCatalogController pluginCatalogController, CatalogPlugin catalogPlugin) {
        final MessageObject messageObjectCreateMessageObject = catalogEntryEntity != null ? pluginCatalogController.createMessageObject(catalogPlugin.getChannelId(), catalogEntryEntity) : null;
        if ((messageObjectCreateMessageObject != null ? messageObjectCreateMessageObject.getDocument() : null) == null) {
            function2.invoke(null, null);
        } else {
            pluginCatalogController.downloadDocument(messageObjectCreateMessageObject, 3, new Function1() {
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PluginCatalogController.m1905$r8$lambda$qwlFT7hx31zKdEcy3EG5LKPyTI(function2, messageObjectCreateMessageObject, (File) obj);
                }
            });
        }
    }

    public static Unit m1905$r8$lambda$qwlFT7hx31zKdEcy3EG5LKPyTI(Function2 function2, MessageObject messageObject, File file) {
        function2.invoke(file, messageObject);
        return Unit.INSTANCE;
    }

    private final void downloadDocument(MessageObject messageObject, int priority, Function1<? super File, Unit> callback) {
        TLRPC.Document document = messageObject.getDocument();
        File fileFindDownloadedFile = findDownloadedFile(document);
        if (fileFindDownloadedFile != null) {
            callback.invoke(fileFindDownloadedFile);
            return;
        }
        String attachFileName = FileLoader.getAttachFileName(document);
        PendingDownload pendingDownload = this.downloads.get(attachFileName);
        if (pendingDownload != null) {
            pendingDownload.getCallbacks().add(callback);
            if (priority != 0) {
                FileLoader.getInstance(this.account).loadFile(document, messageObject, priority, 1);
                return;
            }
            return;
        }
        this.downloads.put(attachFileName, new PendingDownload(document, CollectionsKt.arrayListOf(callback)));
        if (!this.observingDownloads) {
            this.observingDownloads = true;
            NotificationCenter.getInstance(this.account).addObserver(this, NotificationCenter.fileLoaded);
            NotificationCenter.getInstance(this.account).addObserver(this, NotificationCenter.fileLoadFailed);
        }
        FileLoader.getInstance(this.account).loadFile(document, messageObject, priority, 1);
    }

    private final File findDownloadedFile(TLRPC.Document document) {
        FileLoader fileLoader = FileLoader.getInstance(this.account);
        File pathToAttach = fileLoader.getPathToAttach(document, true);
        if (!pathToAttach.exists()) {
            pathToAttach = null;
        }
        if (pathToAttach != null) {
            return pathToAttach;
        }
        File pathToAttach2 = fileLoader.getPathToAttach(document, false);
        if (pathToAttach2.exists()) {
            return pathToAttach2;
        }
        return null;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int id, int account, Object... args) {
        PendingDownload pendingDownloadRemove;
        int i = NotificationCenter.fileLoaded;
        if (id == i || id == NotificationCenter.fileLoadFailed) {
            HashMap<String, PendingDownload> map = this.downloads;
            Object obj = args[0];
            File fileFindDownloadedFile = null;
            String str = obj instanceof String ? (String) obj : null;
            if (str == null || (pendingDownloadRemove = map.remove(str)) == null) {
                return;
            }
            if (id == i) {
                Object orNull = ArraysKt.getOrNull(args, 1);
                File file = orNull instanceof File ? (File) orNull : null;
                if (file == null) {
                    fileFindDownloadedFile = findDownloadedFile(pendingDownloadRemove.getDocument());
                } else {
                    fileFindDownloadedFile = file.exists() ? file : null;
                    if (fileFindDownloadedFile == null) {
                        fileFindDownloadedFile = findDownloadedFile(pendingDownloadRemove.getDocument());
                    }
                }
            }
            if (this.downloads.isEmpty() && this.observingDownloads) {
                this.observingDownloads = false;
                NotificationCenter.getInstance(this.account).removeObserver(this, i);
                NotificationCenter.getInstance(this.account).removeObserver(this, NotificationCenter.fileLoadFailed);
            }
            Iterator<Function1<File, Unit>> it = pendingDownloadRemove.getCallbacks().iterator();
            while (it.hasNext()) {
                it.next().invoke(fileFindDownloadedFile);
            }
        }
    }

    private final MessageObject createMessageObject(long channelId, CatalogEntryEntity entry) {
        SerializedData serializedData = new SerializedData(entry.getMessage());
        try {
            TLRPC.Message messageTLdeserialize = TLRPC.Message.TLdeserialize(serializedData, serializedData.readInt32(false), false);
            if (messageTLdeserialize == null) {
                return null;
            }
            messageTLdeserialize.dialog_id = -channelId;
            return new MessageObject(this.account, messageTLdeserialize, false, false);
        } catch (Exception e) {
            FileLog.e(e);
            return null;
        } finally {
            serializedData.cleanup();
        }
        serializedData.cleanup();
    }

    private final CatalogEntryEntity toCatalogEntry(long channelId, TLRPC.Message message) throws Throwable {
        SerializedData serializedData;
        String documentFileName;
        SerializedData serializedData2;
        TLRPC.MessageMedia messageMedia = message.media;
        if (messageMedia == null || (serializedData = messageMedia.document) == 0 || (documentFileName = FileLoader.getDocumentFileName(serializedData)) == null) {
            return null;
        }
        SerializedData serializedData3 = new SerializedData(message.getObjectSize());
        try {
            try {
                message.serializeToStream(serializedData3);
                int i = message.id;
                int i2 = message.date;
                long j = serializedData.id;
                long j2 = serializedData.size;
                String str = message.message;
                String str2 = (str == null || str.length() <= 0) ? null : str;
                serializedData2 = serializedData3;
                try {
                    CatalogEntryEntity catalogEntryEntity = new CatalogEntryEntity(channelId, i, i2, j, documentFileName, j2, str2, serializedData3.toByteArray(), message.views, Math.max(message.date, message.edit_date));
                    serializedData2.cleanup();
                    return catalogEntryEntity;
                } catch (Exception e) {
                    e = e;
                    FileLog.e(e);
                    serializedData2.cleanup();
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                serializedData.cleanup();
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            serializedData2 = serializedData3;
        } catch (Throwable th2) {
            th = th2;
            serializedData = serializedData3;
            serializedData.cleanup();
            throw th;
        }
    }

    private final PluginMetadataEntity failedMetadata(long documentId, String error) {
        return new PluginMetadataEntity(documentId, null, null, null, null, null, null, null, null, null, null, error, System.currentTimeMillis(), PythonPluginsEngine.INSTANCE.getSDK_VERSION());
    }

    private final void postUpdate(long channelId) {
        this.pendingUpdates.add(Long.valueOf(channelId));
        AndroidUtilities.cancelRunOnUIThread(this.postUpdatesRunnable);
        AndroidUtilities.runOnUIThread(this.postUpdatesRunnable, UPDATE_DEBOUNCE_MS);
    }

    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J\u0012\u0010)\u001a\u0004\u0018\u00010&2\u0006\u0010*\u001a\u00020\u0005H\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010*\u001a\u00020\u0005H\u0002J\u0017\u0010-\u001a\u0004\u0018\u00010\u00072\u0006\u0010*\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u0002002\u0006\u0010*\u001a\u00020\u0005H\u0002J\u0010\u0010:\u001a\u0002082\u0006\u0010;\u001a\u00020\fH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!R.\u0010$\u001a\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010&0%j\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010&`'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00101\u001a\u0002028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b5\u0010#\u001a\u0004\b3\u00104R\u0018\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010807X\u0082\u0004¢\u0006\u0004\n\u0002\u00109¨\u0006<"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TRUSTED_FOLDER_SLUG", _UrlKt.FRAGMENT_ENCODE_SET, "ALL_REPOSITORIES", _UrlKt.FRAGMENT_ENCODE_SET, "ERROR_TOO_LARGE", "ERROR_INVALID_MESSAGE", "ERROR_INVALID_METADATA", "PAGE_SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "MAX_PAGES", "MAX_METADATA_JOBS", "MAX_METADATA_FILE_SIZE", "MAX_ARCHIVE_METADATA_FILE_SIZE", "SYNC_INTERVAL", "FOLDER_REFRESH_INTERVAL", "UPDATE_DEBOUNCE_MS", "KEY_FOLDER_RESOLVED_AT", "KEY_SORT_ORDER", "value", PluginCatalogController.KEY_SORT_ORDER, "getSortOrder", "()I", "setSortOrder", "(I)V", "syncedTopMessageKey", "channelId", "catalogQueue", "Lorg/telegram/messenger/DispatchQueue;", "getCatalogQueue", "()Lorg/telegram/messenger/DispatchQueue;", "catalogQueue$delegate", "Lkotlin/Lazy;", "extensionEngines", "Ljava/util/HashMap;", "Lcom/exteragram/messenger/plugins/PluginsController$PluginsEngine;", "Lkotlin/collections/HashMap;", "extensionEnginesKey", "getEngine", "fileName", "isSupported", _UrlKt.FRAGMENT_ENCODE_SET, "getMetadataSizeLimit", "(Ljava/lang/String;)Ljava/lang/Long;", "probeFile", "Ljava/io/File;", "preferences", "Landroid/content/SharedPreferences;", "getPreferences", "()Landroid/content/SharedPreferences;", "preferences$delegate", "instances", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController;", "[Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController;", "getInstance", "account", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPluginCatalogController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginCatalogController.kt\ncom/exteragram/messenger/plugins/catalog/PluginCatalogController$Companion\n+ 2 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,726:1\n41#2,12:727\n1#3:739\n*S KotlinDebug\n*F\n+ 1 PluginCatalogController.kt\ncom/exteragram/messenger/plugins/catalog/PluginCatalogController$Companion\n*L\n677#1:727,12\n*E\n"})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getSortOrder() {
            return getPreferences().getInt(PluginCatalogController.KEY_SORT_ORDER, 0);
        }

        public final void setSortOrder(int i) {
            SharedPreferences.Editor editorEdit = getPreferences().edit();
            editorEdit.putInt(PluginCatalogController.KEY_SORT_ORDER, i);
            editorEdit.apply();
        }

        public final String syncedTopMessageKey(long channelId) {
            return "syncedTopMessage_" + channelId;
        }

        public final DispatchQueue getCatalogQueue() {
            return (DispatchQueue) PluginCatalogController.catalogQueue$delegate.getValue();
        }

        private final PluginsController.PluginsEngine getEngine(String fileName) {
            String lowerCase = StringsKt.substringAfterLast(fileName, '.', _UrlKt.FRAGMENT_ENCODE_SET).toLowerCase(Locale.ROOT);
            if (lowerCase.length() == 0) {
                return null;
            }
            PluginsController.Companion companion = PluginsController.INSTANCE;
            int iHashCode = companion.getEngines().keySet().hashCode();
            if (iHashCode != PluginCatalogController.extensionEnginesKey) {
                PluginCatalogController.extensionEngines.clear();
                PluginCatalogController.extensionEnginesKey = iHashCode;
            }
            if (!PluginCatalogController.extensionEngines.containsKey(lowerCase)) {
                PluginCatalogController.extensionEngines.put(lowerCase, companion.getPluginEngine(probeFile(fileName)));
            }
            return (PluginsController.PluginsEngine) PluginCatalogController.extensionEngines.get(lowerCase);
        }

        public final boolean isSupported(String fileName) {
            return getEngine(fileName) != null;
        }

        public final Long getMetadataSizeLimit(String fileName) {
            PluginsController.PluginsEngine engine = getEngine(fileName);
            if (engine == null) {
                return null;
            }
            return engine instanceof PythonPluginsEngine ? Long.valueOf(PluginCatalogController.MAX_METADATA_FILE_SIZE) : Long.valueOf(PluginCatalogController.MAX_ARCHIVE_METADATA_FILE_SIZE);
        }

        public final File probeFile(String fileName) {
            return new File("plugin." + StringsKt.substringAfterLast(fileName, '.', _UrlKt.FRAGMENT_ENCODE_SET).toLowerCase(Locale.ROOT));
        }

        public final SharedPreferences getPreferences() {
            return (SharedPreferences) PluginCatalogController.preferences$delegate.getValue();
        }

        @JvmStatic
        public final PluginCatalogController getInstance(int account) {
            PluginCatalogController pluginCatalogController;
            PluginCatalogController pluginCatalogController2 = PluginCatalogController.instances[account];
            if (pluginCatalogController2 != null) {
                return pluginCatalogController2;
            }
            synchronized (PluginCatalogController.instances) {
                pluginCatalogController = PluginCatalogController.instances[account];
                if (pluginCatalogController == null) {
                    pluginCatalogController = new PluginCatalogController(account, null);
                    PluginCatalogController.instances[account] = pluginCatalogController;
                }
            }
            return pluginCatalogController;
        }
    }

    public static DispatchQueue $r8$lambda$EnICIMTJwwfChhA4Pcyv2fYYsqI() {
        return new DispatchQueue("pluginCatalogQueue");
    }
}
