package com.exteragram.messenger.api;

import android.content.SharedPreferences;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.api.db.DatabaseHelper;
import com.exteragram.messenger.api.dto.BoostySubscriberDTO;
import com.exteragram.messenger.api.dto.ProfileDTO;
import com.exteragram.messenger.api.network.ApiClient;
import com.exteragram.messenger.api.network.ApiService;
import com.exteragram.messenger.api.worker.SyncWorker;
import com.exteragram.messenger.plugins.PluginsConstants;
import j$.time.Duration;
import j$.time.Instant;
import j$.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.ui.Components.ForegroundDetector;
import retrofit2.Response;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0007J\u001c\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007J\"\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0086@¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u000b2\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0010H\u0002J\u0012\u0010\"\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007J\b\u0010#\u001a\u00020\u0014H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/exteragram/messenger/api/ApiController;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "started", "Ljava/util/concurrent/atomic/AtomicBoolean;", "syncMutex", "Lkotlinx/coroutines/sync/Mutex;", "SYNC_TIMESTAMP_HEADER", _UrlKt.FRAGMENT_ENCODE_SET, "PROFILES_SYNC_TIME_KEY", "BOOSTY_SYNC_TIME_KEY", "SYNC_FAILURE_TIME_KEY", "PROFILES_SYNC_INTERVAL", _UrlKt.FRAGMENT_ENCODE_SET, "BOOSTY_SYNC_INTERVAL", "FAILURE_BACKOFF", "init", _UrlKt.FRAGMENT_ENCODE_SET, "sync", "prefs", "Landroid/content/SharedPreferences;", "force", _UrlKt.FRAGMENT_ENCODE_SET, "performSync", "(Landroid/content/SharedPreferences;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncWatermark", PluginsConstants.RESPONSE, "Lretrofit2/Response;", "elapsedSince", "now", "timestamp", "resetSyncState", "scheduleWorker", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nApiController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ApiController.kt\ncom/exteragram/messenger/api/ApiController\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n+ 3 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 PeriodicWorkRequest.kt\nandroidx/work/PeriodicWorkRequestKt\n*L\n1#1,243:1\n117#2,7:244\n125#2,2:313\n41#3,12:251\n41#3,12:263\n41#3,12:289\n41#3,12:301\n41#3,12:315\n3347#4,10:275\n1586#4:285\n1661#4,3:286\n364#5:327\n*S KotlinDebug\n*F\n+ 1 ApiController.kt\ncom/exteragram/messenger/api/ApiController\n*L\n89#1:244,7\n89#1:313,2\n113#1:251,12\n143#1:263,12\n160#1:289,12\n183#1:301,12\n212#1:315,12\n151#1:275,10\n153#1:285\n153#1:286,3\n229#1:327\n*E\n"})
public final class ApiController {
    private static final String BOOSTY_SYNC_TIME_KEY = "lastBoostySyncTime";
    private static final String PROFILES_SYNC_TIME_KEY = "lastProfilesSyncTime";
    private static final String SYNC_FAILURE_TIME_KEY = "lastSyncFailureTime";
    private static final String SYNC_TIMESTAMP_HEADER = "X-Sync-Timestamp";
    public static final ApiController INSTANCE = new ApiController();
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    private static final AtomicBoolean started = new AtomicBoolean(false);
    private static final Mutex syncMutex = MutexKt.Mutex$default(false, 1, null);
    private static final long PROFILES_SYNC_INTERVAL = Duration.ofMinutes(10).toMillis();
    private static final long BOOSTY_SYNC_INTERVAL = Duration.ofMinutes(40).toMillis();
    private static final long FAILURE_BACKOFF = Duration.ofMinutes(5).toMillis();

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.ApiController", f = "ApiController.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}, l = {248, 110, 112, 137, 139, 153, 156}, m = "performSync", n = {"prefs", "$this$withLock_u24default$iv", "force", "$i$f$withLock", "prefs", "$this$withLock_u24default$iv", "success", "force", "$i$f$withLock", "$i$a$-withLock$default-ApiController$performSync$2", "now", "boostyDue", "profilesDue", "attempted", "prefs", "$this$withLock_u24default$iv", "success", "boostyResponse", "force", "$i$f$withLock", "$i$a$-withLock$default-ApiController$performSync$2", "now", "boostyDue", "profilesDue", "attempted", "prefs", "$this$withLock_u24default$iv", "success", "lastSyncTimestamp", "profilesEtag", "apiService", "force", "$i$f$withLock", "$i$a$-withLock$default-ApiController$performSync$2", "now", "boostyDue", "profilesDue", "attempted", "prefs", "$this$withLock_u24default$iv", "success", "lastSyncTimestamp", "profilesEtag", "apiService", "force", "$i$f$withLock", "$i$a$-withLock$default-ApiController$performSync$2", "now", "boostyDue", "profilesDue", "attempted", "prefs", "$this$withLock_u24default$iv", "success", "lastSyncTimestamp", "profilesEtag", "apiService", PluginsConstants.RESPONSE, "profiles", "profilesToDelete", "profilesToInsert", "force", "$i$f$withLock", "$i$a$-withLock$default-ApiController$performSync$2", "now", "boostyDue", "profilesDue", "attempted", "stored", "prefs", "$this$withLock_u24default$iv", "success", "lastSyncTimestamp", "profilesEtag", "apiService", PluginsConstants.RESPONSE, "profiles", "profilesToDelete", "profilesToInsert", "force", "$i$f$withLock", "$i$a$-withLock$default-ApiController$performSync$2", "now", "boostyDue", "profilesDue", "attempted", "stored"}, s = {"L$0", "L$1", "Z$0", "I$0", "L$0", "L$1", "L$2", "Z$0", "I$0", "I$1", "J$0", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "Z$0", "I$0", "I$1", "J$0", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "I$0", "I$1", "J$0", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "I$0", "I$1", "J$0", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "Z$0", "I$0", "I$1", "J$0", "I$2", "I$3", "I$4", "I$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "Z$0", "I$0", "I$1", "J$0", "I$2", "I$3", "I$4", "I$5"})
    public static final class C01351 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        Object result;

        public C01351(Continuation<? super C01351> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ApiController.this.performSync(null, false, this);
        }
    }

    private final long elapsedSince(long now, long timestamp) {
        return (timestamp <= 0 || timestamp > now) ? LongCompanionObject.MAX_VALUE : now - timestamp;
    }

    @JvmStatic
    @JvmOverloads
    public static final void resetSyncState() {
        resetSyncState$default(null, 1, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void sync() {
        sync$default(null, false, 3, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void sync(SharedPreferences sharedPreferences) {
        sync$default(sharedPreferences, false, 2, null);
    }

    private ApiController() {
    }

    @JvmStatic
    @JvmOverloads
    public static final void resetSyncState(SharedPreferences prefs) {
        SharedPreferences.Editor editorEdit = prefs.edit();
        editorEdit.remove("lastSyncTimestamp");
        editorEdit.remove("profilesEtag");
        editorEdit.remove(PROFILES_SYNC_TIME_KEY);
        editorEdit.remove(BOOSTY_SYNC_TIME_KEY);
        editorEdit.remove(SYNC_FAILURE_TIME_KEY);
        editorEdit.apply();
    }

    @JvmStatic
    public static final void init() {
        if (started.compareAndSet(false, true)) {
            sync$default(null, false, 3, null);
            scheduleWorker();
            ForegroundDetector.getInstance().addListener(new ForegroundDetector.Listener() {
                @Override // org.telegram.ui.Components.ForegroundDetector.Listener
                public void onBecameBackground() {
                }

                @Override // org.telegram.ui.Components.ForegroundDetector.Listener
                public void onBecameForeground() {
                    ApiController.sync$default(null, false, 3, null);
                }
            });
        }
    }

    public static void sync$default(SharedPreferences sharedPreferences, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            sharedPreferences = ExteraConfig.getPreferences();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        sync(sharedPreferences, z);
    }

    @JvmStatic
    @JvmOverloads
    public static final void sync(SharedPreferences prefs, boolean force) {
        if (ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
            if (!force) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                ApiController apiController = INSTANCE;
                if (apiController.elapsedSince(jCurrentTimeMillis, prefs.getLong(SYNC_FAILURE_TIME_KEY, 0L)) < FAILURE_BACKOFF) {
                    return;
                }
                boolean z = apiController.elapsedSince(jCurrentTimeMillis, prefs.getLong(PROFILES_SYNC_TIME_KEY, 0L)) >= PROFILES_SYNC_INTERVAL;
                boolean z2 = apiController.elapsedSince(jCurrentTimeMillis, prefs.getLong(BOOSTY_SYNC_TIME_KEY, 0L)) >= BOOSTY_SYNC_INTERVAL;
                if (!z && !z2) {
                    return;
                }
            }
            BuildersKt__Builders_commonKt.launch$default(scope, null, null, new C01361(prefs, force, null), 3, null);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.api.ApiController$sync$1", f = "ApiController.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {})
    public static final class C01361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final boolean $force;
        final SharedPreferences $prefs;
        int label;

        public C01361(SharedPreferences sharedPreferences, boolean z, Continuation<? super C01361> continuation) {
            super(2, continuation);
            this.$prefs = sharedPreferences;
            this.$force = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01361(this.$prefs, this.$force, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01361) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ApiController apiController = ApiController.INSTANCE;
                SharedPreferences sharedPreferences = this.$prefs;
                boolean z = this.$force;
                this.label = 1;
                if (apiController.performSync(sharedPreferences, z, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static Object performSync$default(ApiController apiController, SharedPreferences sharedPreferences, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            sharedPreferences = ExteraConfig.getPreferences();
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return apiController.performSync(sharedPreferences, z, continuation);
    }

    public final Object performSync(SharedPreferences sharedPreferences, boolean z, Continuation<? super Boolean> continuation) throws Throwable {
        C01351 c01351;
        Ref.BooleanRef booleanRef;
        Ref.BooleanRef booleanRef2;
        Mutex mutex;
        Mutex mutex2;
        SharedPreferences sharedPreferences2;
        SharedPreferences sharedPreferences3;
        Mutex mutex3;
        ?? r18;
        Object obj;
        ?? r8;
        Mutex mutex4;
        String str;
        String str2;
        Object obj2;
        ?? r5;
        Mutex mutex5;
        Mutex mutex6;
        Mutex mutex7;
        boolean z2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        ?? r19;
        String str3;
        ?? r14;
        ?? r12;
        boolean z3;
        int i6;
        int i7;
        int i8;
        ?? r3;
        int i9;
        ?? r1;
        ?? r4;
        String string;
        String str4;
        String string2;
        ApiService apiService;
        String str5;
        Object updates;
        String str6;
        int i10;
        String str7;
        int i11;
        Object obj3;
        int i12;
        int i13;
        String str8;
        Object allProfiles;
        Object obj4;
        ?? r110;
        String str9;
        ?? r11;
        ?? r9;
        ?? r6;
        int i14;
        ?? r111;
        ?? r15;
        ?? r13;
        ?? r10;
        Response<?> response;
        ?? r7;
        String str10;
        int i15;
        int i16;
        boolean z4;
        String str11;
        ApiService apiService2;
        List list;
        String str12;
        String str13;
        ?? r2;
        int i17;
        boolean z5;
        List<ProfileDTO> list2;
        List list3;
        Object obj5;
        int i18;
        int i19;
        ?? r16;
        Mutex mutex8;
        int i20;
        Response<?> response2;
        int i21;
        int i22;
        ?? r23;
        Mutex mutex9;
        Response<?> response3;
        Object obj6;
        int i23;
        String str14;
        ApiService apiService3;
        ?? r112;
        ?? r17;
        ?? r113;
        ?? r20;
        ?? r114;
        String str15;
        ?? r115;
        ?? r21;
        List<ProfileDTO> list4;
        ?? r24;
        ?? r116;
        ?? r117;
        Object obj7;
        Object objInsertProfiles;
        int i24;
        Response<?> response4;
        ?? r22;
        ?? r118;
        ?? r119;
        String str16;
        ?? r120;
        ?? r25;
        ?? r121;
        ?? r122;
        boolean z6;
        ?? Edit;
        ?? r26;
        ?? r123;
        String str17;
        ?? r124;
        ?? r27;
        ?? Edit2;
        String str18;
        String str19;
        ?? r125;
        String str20;
        ?? r126;
        int i25;
        int i26;
        SharedPreferences sharedPreferences4;
        boolean z7;
        ApiClient apiClient;
        long jCurrentTimeMillis;
        int i27;
        int i28;
        Ref.BooleanRef booleanRef3;
        Object boostySubscribers;
        Ref.BooleanRef booleanRef4;
        SharedPreferences sharedPreferences5;
        Mutex mutex10;
        long j;
        int i29;
        String str21;
        Response response5;
        Object objInsertBoostySubscribers;
        ?? r127;
        String str22;
        long j2;
        SharedPreferences sharedPreferences6;
        Ref.BooleanRef booleanRef5;
        int i30;
        if (continuation instanceof C01351) {
            c01351 = (C01351) continuation;
            int i31 = c01351.label;
            if ((i31 & Integer.MIN_VALUE) != 0) {
                c01351.label = i31 - Integer.MIN_VALUE;
            } else {
                c01351 = new C01351(continuation);
            }
        } else {
            c01351 = new C01351(continuation);
        }
        Object obj8 = c01351.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i32 = c01351.label;
        String str23 = "profilesEtag";
        String str24 = "lastSyncTimestamp";
        ?? r28 = SYNC_FAILURE_TIME_KEY;
        ?? r128 = 0;
        r128 = 0;
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                switch (i32) {
                                    case 0:
                                        ResultKt.throwOnFailure(obj8);
                                        Mutex mutex11 = syncMutex;
                                        sharedPreferences4 = sharedPreferences;
                                        c01351.L$0 = sharedPreferences4;
                                        c01351.L$1 = mutex11;
                                        z7 = z;
                                        c01351.Z$0 = z7;
                                        c01351.I$0 = 0;
                                        c01351.label = 1;
                                        if (mutex11.lock(null, c01351) != coroutine_suspended) {
                                            mutex6 = mutex11;
                                            i6 = 0;
                                            try {
                                                apiClient = ApiClient.INSTANCE;
                                                if (!apiClient.getRequestsEnabled$TMessagesProj()) {
                                                    jCurrentTimeMillis = System.currentTimeMillis();
                                                    if (!z7 || INSTANCE.elapsedSince(jCurrentTimeMillis, sharedPreferences4.getLong(SYNC_FAILURE_TIME_KEY, 0L)) >= FAILURE_BACKOFF) {
                                                        if (!z7 || INSTANCE.elapsedSince(jCurrentTimeMillis, sharedPreferences4.getLong(BOOSTY_SYNC_TIME_KEY, 0L)) >= BOOSTY_SYNC_INTERVAL) {
                                                            i27 = 1;
                                                        } else {
                                                            i27 = 0;
                                                        }
                                                        if (!z7 || INSTANCE.elapsedSince(jCurrentTimeMillis, sharedPreferences4.getLong(PROFILES_SYNC_TIME_KEY, 0L)) >= PROFILES_SYNC_INTERVAL) {
                                                            i28 = 1;
                                                        } else {
                                                            i28 = 0;
                                                        }
                                                        if (i27 == 0 || i28 != 0) {
                                                            booleanRef3 = new Ref.BooleanRef();
                                                            booleanRef3.element = true;
                                                            if (i27 != 0) {
                                                                try {
                                                                    ApiService apiService4 = apiClient.getApiService();
                                                                    c01351.L$0 = sharedPreferences4;
                                                                    c01351.L$1 = mutex6;
                                                                    c01351.L$2 = booleanRef3;
                                                                    c01351.Z$0 = z7;
                                                                    c01351.I$0 = i6;
                                                                    c01351.I$1 = 0;
                                                                    c01351.J$0 = jCurrentTimeMillis;
                                                                    c01351.I$2 = i27;
                                                                    c01351.I$3 = i28;
                                                                    c01351.I$4 = 1;
                                                                    c01351.label = 2;
                                                                    boostySubscribers = apiService4.getBoostySubscribers(c01351);
                                                                    if (boostySubscribers != coroutine_suspended) {
                                                                        int i33 = i6;
                                                                        i3 = i27;
                                                                        booleanRef4 = booleanRef3;
                                                                        i = i33;
                                                                        z2 = z7;
                                                                        sharedPreferences5 = sharedPreferences4;
                                                                        i4 = i28;
                                                                        mutex10 = mutex6;
                                                                        j = jCurrentTimeMillis;
                                                                        i29 = 1;
                                                                        i2 = 0;
                                                                        str21 = str23;
                                                                        try {
                                                                            try {
                                                                                response5 = (Response) boostySubscribers;
                                                                                if (!response5.isSuccessful() && response5.body() != null) {
                                                                                    DatabaseHelper databaseHelper = DatabaseHelper.INSTANCE;
                                                                                    Object objBody = response5.body();
                                                                                    str2 = PROFILES_SYNC_TIME_KEY;
                                                                                    try {
                                                                                        List<BoostySubscriberDTO> list5 = (List) objBody;
                                                                                        c01351.L$0 = sharedPreferences5;
                                                                                        c01351.L$1 = mutex10;
                                                                                        c01351.L$2 = booleanRef4;
                                                                                        mutex4 = mutex10;
                                                                                        c01351.L$3 = SpillingKt.nullOutSpilledVariable(response5);
                                                                                        c01351.Z$0 = z2;
                                                                                        c01351.I$0 = i;
                                                                                        c01351.I$1 = i2;
                                                                                        c01351.J$0 = j;
                                                                                        c01351.I$2 = i3;
                                                                                        c01351.I$3 = i4;
                                                                                        c01351.I$4 = i29;
                                                                                        c01351.label = 3;
                                                                                        objInsertBoostySubscribers = databaseHelper.insertBoostySubscribers(list5, c01351);
                                                                                        i30 = i29;
                                                                                        booleanRef5 = booleanRef4;
                                                                                        sharedPreferences6 = sharedPreferences5;
                                                                                        j2 = j;
                                                                                        str22 = str21;
                                                                                        r127 = r28;
                                                                                        if (objInsertBoostySubscribers != coroutine_suspended) {
                                                                                            if (((Boolean) objInsertBoostySubscribers).booleanValue()) {
                                                                                                SharedPreferences.Editor editorEdit = sharedPreferences6.edit();
                                                                                                editorEdit.putLong(BOOSTY_SYNC_TIME_KEY, j2);
                                                                                                editorEdit.apply();
                                                                                                i14 = i30;
                                                                                                r6 = booleanRef5;
                                                                                                r9 = sharedPreferences6;
                                                                                                r11 = j2;
                                                                                                str9 = str22;
                                                                                                r110 = r127;
                                                                                            } else {
                                                                                                booleanRef5.element = false;
                                                                                                i14 = i30;
                                                                                                r6 = booleanRef5;
                                                                                                r9 = sharedPreferences6;
                                                                                                r11 = j2;
                                                                                                str9 = str22;
                                                                                                r110 = r127;
                                                                                            }
                                                                                            i9 = i4;
                                                                                            i8 = i3;
                                                                                            i6 = i;
                                                                                            r14 = r6;
                                                                                            i7 = i2;
                                                                                            r12 = r11;
                                                                                            z3 = z2;
                                                                                            mutex2 = mutex4;
                                                                                            r3 = i14;
                                                                                            r28 = r9;
                                                                                            str3 = str9;
                                                                                            r19 = r110;
                                                                                            if (i9 != 0) {
                                                                                                r12 = jCurrentTimeMillis;
                                                                                                r14 = booleanRef3;
                                                                                                str3 = str23;
                                                                                                string = r28.getString(str24, null);
                                                                                                str4 = str3;
                                                                                                string2 = r28.getString(str4, null);
                                                                                                apiService = ApiClient.INSTANCE.getApiService();
                                                                                                if (string == null) {
                                                                                                    c01351.L$0 = r28;
                                                                                                    c01351.L$1 = mutex2;
                                                                                                    c01351.L$2 = r14;
                                                                                                    str8 = str4;
                                                                                                    c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                                                                    c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                                                                    c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                                                                    c01351.Z$0 = z3;
                                                                                                    c01351.I$0 = i6;
                                                                                                    c01351.I$1 = i7;
                                                                                                    c01351.J$0 = r12;
                                                                                                    c01351.I$2 = i8;
                                                                                                    c01351.I$3 = i9;
                                                                                                    c01351.I$4 = 1;
                                                                                                    c01351.label = 4;
                                                                                                    allProfiles = apiService.getAllProfiles(string2, c01351);
                                                                                                    if (allProfiles != coroutine_suspended) {
                                                                                                        str6 = string2;
                                                                                                        i10 = i8;
                                                                                                        str7 = string;
                                                                                                        i11 = i9;
                                                                                                        obj4 = allProfiles;
                                                                                                        i12 = i7;
                                                                                                        i13 = 1;
                                                                                                        r10 = r28;
                                                                                                        r13 = r12;
                                                                                                        r15 = r14;
                                                                                                        r111 = r19;
                                                                                                        response = (Response) obj4;
                                                                                                        r28 = r10;
                                                                                                        r12 = r13;
                                                                                                        r14 = r15;
                                                                                                        str10 = str8;
                                                                                                        r19 = r111;
                                                                                                        int i34 = i13;
                                                                                                        int i35 = i11;
                                                                                                        r7 = i34;
                                                                                                        boolean z8 = z3;
                                                                                                        i15 = i12;
                                                                                                        i16 = i6;
                                                                                                        z4 = z8;
                                                                                                        str11 = str7;
                                                                                                        apiService2 = apiService;
                                                                                                        if (response.code() == 304) {
                                                                                                            ?? Edit3 = r28.edit();
                                                                                                            Edit3.putString(str24, INSTANCE.syncWatermark(response));
                                                                                                            Edit3.putLong(str2, r12);
                                                                                                            Edit3.apply();
                                                                                                        } else {
                                                                                                            String str25 = str24;
                                                                                                            String str26 = str2;
                                                                                                            if (response.isSuccessful()) {
                                                                                                            }
                                                                                                            r14.element = false;
                                                                                                        }
                                                                                                        r3 = r7 == true ? 1 : 0;
                                                                                                        r12 = jCurrentTimeMillis;
                                                                                                        r14 = booleanRef3;
                                                                                                        str3 = str23;
                                                                                                        mutex6 = mutex2;
                                                                                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                                            if (r3 != 0) {
                                                                                                                Edit = r28.edit();
                                                                                                                if (r14.element) {
                                                                                                                    Edit.remove(r19);
                                                                                                                } else {
                                                                                                                    Edit.putLong(r19, r12);
                                                                                                                }
                                                                                                                Edit.apply();
                                                                                                            }
                                                                                                            z6 = r14.element;
                                                                                                        }
                                                                                                    }
                                                                                                } else {
                                                                                                    str5 = str4;
                                                                                                    c01351.L$0 = r28;
                                                                                                    c01351.L$1 = mutex2;
                                                                                                    c01351.L$2 = r14;
                                                                                                    c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                                                                    c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                                                                    c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                                                                    c01351.Z$0 = z3;
                                                                                                    c01351.I$0 = i6;
                                                                                                    c01351.I$1 = i7;
                                                                                                    c01351.J$0 = r12;
                                                                                                    c01351.I$2 = i8;
                                                                                                    c01351.I$3 = i9;
                                                                                                    c01351.I$4 = 1;
                                                                                                    c01351.label = 5;
                                                                                                    updates = apiService.getUpdates(string, string2, c01351);
                                                                                                    if (updates != coroutine_suspended) {
                                                                                                        str6 = string2;
                                                                                                        i10 = i8;
                                                                                                        str7 = string;
                                                                                                        i11 = i9;
                                                                                                        obj3 = updates;
                                                                                                        i12 = i7;
                                                                                                        i13 = 1;
                                                                                                        r20 = r28;
                                                                                                        r113 = r12;
                                                                                                        r17 = r14;
                                                                                                        r112 = r19;
                                                                                                        response = (Response) obj3;
                                                                                                        r28 = r20;
                                                                                                        r12 = r113;
                                                                                                        r14 = r17;
                                                                                                        str10 = str5;
                                                                                                        r19 = r112;
                                                                                                        int i36 = i13;
                                                                                                        int i37 = i11;
                                                                                                        r7 = i36;
                                                                                                        boolean z9 = z3;
                                                                                                        i15 = i12;
                                                                                                        i16 = i6;
                                                                                                        z4 = z9;
                                                                                                        str11 = str7;
                                                                                                        apiService2 = apiService;
                                                                                                        if (response.code() == 304) {
                                                                                                            ?? Edit4 = r28.edit();
                                                                                                            Edit4.putString(str24, INSTANCE.syncWatermark(response));
                                                                                                            Edit4.putLong(str2, r12);
                                                                                                            Edit4.apply();
                                                                                                        } else {
                                                                                                            String str27 = str24;
                                                                                                            String str28 = str2;
                                                                                                            if (response.isSuccessful()) {
                                                                                                            }
                                                                                                            r14.element = false;
                                                                                                        }
                                                                                                        r3 = r7 == true ? 1 : 0;
                                                                                                        r12 = jCurrentTimeMillis;
                                                                                                        r14 = booleanRef3;
                                                                                                        str3 = str23;
                                                                                                        mutex6 = mutex2;
                                                                                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                                            if (r3 != 0) {
                                                                                                                Edit = r28.edit();
                                                                                                                if (r14.element) {
                                                                                                                    Edit.remove(r19);
                                                                                                                } else {
                                                                                                                    Edit.putLong(r19, r12);
                                                                                                                }
                                                                                                                Edit.apply();
                                                                                                            }
                                                                                                            z6 = r14.element;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            } else {
                                                                                                r12 = jCurrentTimeMillis;
                                                                                                r14 = booleanRef3;
                                                                                                str3 = str23;
                                                                                                mutex6 = mutex2;
                                                                                                if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                                    if (r3 != 0) {
                                                                                                        Edit = r28.edit();
                                                                                                        if (r14.element) {
                                                                                                            Edit.remove(r19);
                                                                                                        } else {
                                                                                                            Edit.putLong(r19, r12);
                                                                                                        }
                                                                                                        Edit.apply();
                                                                                                    }
                                                                                                    z6 = r14.element;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    } catch (Throwable th) {
                                                                                        th = th;
                                                                                        mutex4 = mutex10;
                                                                                        i5 = i29;
                                                                                        r5 = booleanRef4;
                                                                                        r8 = sharedPreferences5;
                                                                                        r128 = j;
                                                                                        str = str21;
                                                                                        r18 = r28;
                                                                                        try {
                                                                                            FileLog.e(th);
                                                                                            r5.element = false;
                                                                                            i14 = i5;
                                                                                            r6 = r5;
                                                                                            r9 = r8;
                                                                                            r11 = r128;
                                                                                            str9 = str;
                                                                                            r110 = r18;
                                                                                        } catch (Throwable th2) {
                                                                                            th = th2;
                                                                                            mutex6 = mutex4;
                                                                                            mutex6.unlock(null);
                                                                                            throw th;
                                                                                        }
                                                                                        break;
                                                                                    }
                                                                                } else {
                                                                                    mutex4 = mutex10;
                                                                                    str2 = PROFILES_SYNC_TIME_KEY;
                                                                                    booleanRef4.element = false;
                                                                                    i14 = i29;
                                                                                    r6 = booleanRef4;
                                                                                    r9 = sharedPreferences5;
                                                                                    r11 = j;
                                                                                    str9 = str21;
                                                                                    r110 = r28;
                                                                                    i9 = i4;
                                                                                    i8 = i3;
                                                                                    i6 = i;
                                                                                    r14 = r6;
                                                                                    i7 = i2;
                                                                                    r12 = r11;
                                                                                    z3 = z2;
                                                                                    mutex2 = mutex4;
                                                                                    r3 = i14;
                                                                                    r28 = r9;
                                                                                    str3 = str9;
                                                                                    r19 = r110;
                                                                                    if (i9 != 0) {
                                                                                        r12 = jCurrentTimeMillis;
                                                                                        r14 = booleanRef3;
                                                                                        str3 = str23;
                                                                                        try {
                                                                                            string = r28.getString(str24, null);
                                                                                            str4 = str3;
                                                                                            string2 = r28.getString(str4, null);
                                                                                            apiService = ApiClient.INSTANCE.getApiService();
                                                                                            if (string == null) {
                                                                                                c01351.L$0 = r28;
                                                                                                c01351.L$1 = mutex2;
                                                                                                c01351.L$2 = r14;
                                                                                                str8 = str4;
                                                                                                c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                                                                c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                                                                c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                                                                c01351.Z$0 = z3;
                                                                                                c01351.I$0 = i6;
                                                                                                c01351.I$1 = i7;
                                                                                                c01351.J$0 = r12;
                                                                                                c01351.I$2 = i8;
                                                                                                c01351.I$3 = i9;
                                                                                                c01351.I$4 = 1;
                                                                                                c01351.label = 4;
                                                                                                allProfiles = apiService.getAllProfiles(string2, c01351);
                                                                                                if (allProfiles != coroutine_suspended) {
                                                                                                    str6 = string2;
                                                                                                    i10 = i8;
                                                                                                    str7 = string;
                                                                                                    i11 = i9;
                                                                                                    obj4 = allProfiles;
                                                                                                    i12 = i7;
                                                                                                    i13 = 1;
                                                                                                    r10 = r28;
                                                                                                    r13 = r12;
                                                                                                    r15 = r14;
                                                                                                    r111 = r19;
                                                                                                    response = (Response) obj4;
                                                                                                    r28 = r10;
                                                                                                    r12 = r13;
                                                                                                    r14 = r15;
                                                                                                    str10 = str8;
                                                                                                    r19 = r111;
                                                                                                    int i38 = i13;
                                                                                                    int i39 = i11;
                                                                                                    r7 = i38;
                                                                                                    boolean z10 = z3;
                                                                                                    i15 = i12;
                                                                                                    i16 = i6;
                                                                                                    z4 = z10;
                                                                                                    str11 = str7;
                                                                                                    try {
                                                                                                        apiService2 = apiService;
                                                                                                        if (response.code() == 304) {
                                                                                                            try {
                                                                                                                ?? Edit5 = r28.edit();
                                                                                                                Edit5.putString(str24, INSTANCE.syncWatermark(response));
                                                                                                                Edit5.putLong(str2, r12);
                                                                                                                Edit5.apply();
                                                                                                            } catch (Throwable th3) {
                                                                                                                th = th3;
                                                                                                                r1 = r28;
                                                                                                                r4 = r7;
                                                                                                                r122 = r12;
                                                                                                                r116 = r14;
                                                                                                                r121 = r19;
                                                                                                                FileLog.e(th);
                                                                                                                r116.element = false;
                                                                                                                r26 = r1;
                                                                                                                r3 = r4;
                                                                                                                r12 = r122;
                                                                                                                r14 = r116;
                                                                                                                r19 = r121;
                                                                                                                r28 = r26;
                                                                                                            }
                                                                                                        } else {
                                                                                                            String str29 = str24;
                                                                                                            String str210 = str2;
                                                                                                            if (response.isSuccessful() || response.body() == null) {
                                                                                                                r14.element = false;
                                                                                                            } else {
                                                                                                                list = (List) response.body();
                                                                                                                if (list.isEmpty()) {
                                                                                                                    str12 = str29;
                                                                                                                    str13 = str210;
                                                                                                                    r2 = r28;
                                                                                                                    i17 = 1;
                                                                                                                } else {
                                                                                                                    str13 = str210;
                                                                                                                    ArrayList arrayList = new ArrayList();
                                                                                                                    str12 = str29;
                                                                                                                    ArrayList arrayList2 = new ArrayList();
                                                                                                                    r7 = r7;
                                                                                                                    for (Object obj9 : list) {
                                                                                                                        Object obj10 = coroutine_suspended;
                                                                                                                        try {
                                                                                                                            ProfileDTO profileDTO = (ProfileDTO) obj9;
                                                                                                                            z5 = r7 == true ? 1 : 0;
                                                                                                                            try {
                                                                                                                                int i40 = i39;
                                                                                                                                if (Intrinsics.areEqual(profileDTO.getDeleted(), Boxing.boxBoolean(true))) {
                                                                                                                                    arrayList.add(obj9);
                                                                                                                                } else {
                                                                                                                                    arrayList2.add(obj9);
                                                                                                                                }
                                                                                                                                coroutine_suspended = obj10;
                                                                                                                                r7 = z5 ? 1 : 0;
                                                                                                                                i39 = i40;
                                                                                                                            } catch (Throwable th4) {
                                                                                                                                th = th4;
                                                                                                                                r1 = r28;
                                                                                                                                r4 = z5;
                                                                                                                                r122 = r12;
                                                                                                                                r116 = r14;
                                                                                                                                r121 = r19;
                                                                                                                            }
                                                                                                                        } catch (Throwable th5) {
                                                                                                                            th = th5;
                                                                                                                            boolean z11 = r7 == true ? 1 : 0;
                                                                                                                        }
                                                                                                                        break;
                                                                                                                    }
                                                                                                                    Object obj11 = coroutine_suspended;
                                                                                                                    z5 = r7 == true ? 1 : 0;
                                                                                                                    int i41 = i39;
                                                                                                                    try {
                                                                                                                        Pair pair = new Pair(arrayList, arrayList2);
                                                                                                                        List list6 = (List) pair.component1();
                                                                                                                        List<ProfileDTO> list7 = (List) pair.component2();
                                                                                                                        if (list6.isEmpty()) {
                                                                                                                            list2 = list7;
                                                                                                                            list3 = list6;
                                                                                                                            obj5 = obj11;
                                                                                                                            ?? r29 = r14;
                                                                                                                            i18 = i41;
                                                                                                                            i19 = z5 ? 1 : 0;
                                                                                                                            r16 = r29;
                                                                                                                            mutex8 = mutex2;
                                                                                                                            i20 = i10;
                                                                                                                            response2 = response;
                                                                                                                            i21 = 1;
                                                                                                                            r21 = r28;
                                                                                                                            r115 = r12;
                                                                                                                            str15 = str10;
                                                                                                                            r114 = r19;
                                                                                                                        } else {
                                                                                                                            DatabaseHelper databaseHelper2 = DatabaseHelper.INSTANCE;
                                                                                                                            List list8 = list6;
                                                                                                                            list3 = list6;
                                                                                                                            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list8, 10));
                                                                                                                            Iterator it = list8.iterator();
                                                                                                                            while (it.hasNext()) {
                                                                                                                                arrayList3.add(Boxing.boxLong(((ProfileDTO) it.next()).getId()));
                                                                                                                            }
                                                                                                                            c01351.L$0 = r28;
                                                                                                                            c01351.L$1 = mutex2;
                                                                                                                            c01351.L$2 = r14;
                                                                                                                            c01351.L$3 = SpillingKt.nullOutSpilledVariable(str11);
                                                                                                                            c01351.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                                                                                                                            c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService2);
                                                                                                                            c01351.L$6 = response;
                                                                                                                            c01351.L$7 = SpillingKt.nullOutSpilledVariable(list);
                                                                                                                            c01351.L$8 = SpillingKt.nullOutSpilledVariable(list3);
                                                                                                                            c01351.L$9 = list7;
                                                                                                                            c01351.Z$0 = z4;
                                                                                                                            c01351.I$0 = i16;
                                                                                                                            c01351.I$1 = i15;
                                                                                                                            c01351.J$0 = r12;
                                                                                                                            c01351.I$2 = i10;
                                                                                                                            c01351.I$3 = i41;
                                                                                                                            c01351.I$4 = z5 ? 1 : 0;
                                                                                                                            list2 = list7;
                                                                                                                            c01351.I$5 = 1;
                                                                                                                            c01351.label = 6;
                                                                                                                            Object objDeleteProfiles = databaseHelper2.deleteProfiles(arrayList3, c01351);
                                                                                                                            obj5 = obj11;
                                                                                                                            if (objDeleteProfiles == obj5) {
                                                                                                                                return obj5;
                                                                                                                            }
                                                                                                                            i22 = z5 ? 1 : 0;
                                                                                                                            r23 = r14;
                                                                                                                            mutex9 = mutex2;
                                                                                                                            response3 = response;
                                                                                                                            obj6 = objDeleteProfiles;
                                                                                                                            i18 = i41;
                                                                                                                            i20 = i10;
                                                                                                                            i23 = 1;
                                                                                                                            str14 = str11;
                                                                                                                            apiService3 = apiService2;
                                                                                                                            r25 = r28;
                                                                                                                            r120 = r12;
                                                                                                                            str16 = str10;
                                                                                                                            r119 = r19;
                                                                                                                            try {
                                                                                                                                if (((Boolean) obj6).booleanValue() || i23 == 0) {
                                                                                                                                    i21 = 0;
                                                                                                                                } else {
                                                                                                                                    i21 = 1;
                                                                                                                                }
                                                                                                                                str11 = str14;
                                                                                                                                apiService2 = apiService3;
                                                                                                                                response2 = response3;
                                                                                                                                i19 = i22;
                                                                                                                                r16 = r23;
                                                                                                                                mutex8 = mutex9;
                                                                                                                                r21 = r25;
                                                                                                                                r115 = r120;
                                                                                                                                str15 = str16;
                                                                                                                                r114 = r119;
                                                                                                                            } catch (CancellationException e) {
                                                                                                                                throw e;
                                                                                                                            } catch (Throwable th6) {
                                                                                                                                th = th6;
                                                                                                                                r4 = i22;
                                                                                                                                r1 = r25;
                                                                                                                                r116 = r23;
                                                                                                                                mutex2 = mutex9;
                                                                                                                                r122 = r120;
                                                                                                                                r121 = r119;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        list4 = list2;
                                                                                                                        try {
                                                                                                                            try {
                                                                                                                                if (list4.isEmpty()) {
                                                                                                                                    i17 = i21;
                                                                                                                                    response = response2;
                                                                                                                                    mutex2 = mutex8;
                                                                                                                                    r2 = r21;
                                                                                                                                    r117 = r16;
                                                                                                                                } else {
                                                                                                                                    obj7 = obj5;
                                                                                                                                    DatabaseHelper databaseHelper3 = DatabaseHelper.INSTANCE;
                                                                                                                                    c01351.L$0 = r21;
                                                                                                                                    c01351.L$1 = mutex8;
                                                                                                                                    c01351.L$2 = r16;
                                                                                                                                    r24 = r16;
                                                                                                                                    try {
                                                                                                                                        c01351.L$3 = SpillingKt.nullOutSpilledVariable(str11);
                                                                                                                                        c01351.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                                                                                                                                        c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService2);
                                                                                                                                        c01351.L$6 = response2;
                                                                                                                                        c01351.L$7 = SpillingKt.nullOutSpilledVariable(list);
                                                                                                                                        c01351.L$8 = SpillingKt.nullOutSpilledVariable(list3);
                                                                                                                                        c01351.L$9 = SpillingKt.nullOutSpilledVariable(list4);
                                                                                                                                        c01351.Z$0 = z4;
                                                                                                                                        c01351.I$0 = i16;
                                                                                                                                        c01351.I$1 = i15;
                                                                                                                                        c01351.J$0 = r115;
                                                                                                                                        c01351.I$2 = i20;
                                                                                                                                        c01351.I$3 = i18;
                                                                                                                                        c01351.I$4 = i19;
                                                                                                                                        c01351.I$5 = i21;
                                                                                                                                        c01351.label = 7;
                                                                                                                                        objInsertProfiles = databaseHelper3.insertProfiles(list4, c01351);
                                                                                                                                        if (objInsertProfiles == obj7) {
                                                                                                                                            return obj7;
                                                                                                                                        }
                                                                                                                                        i24 = i21;
                                                                                                                                        obj8 = objInsertProfiles;
                                                                                                                                        response4 = response2;
                                                                                                                                        mutex2 = mutex8;
                                                                                                                                        r22 = r21;
                                                                                                                                        r118 = r24;
                                                                                                                                        i25 = i19;
                                                                                                                                        r126 = r115;
                                                                                                                                        str20 = str15;
                                                                                                                                        r125 = r114;
                                                                                                                                        try {
                                                                                                                                            if (((Boolean) obj8).booleanValue() || i24 == 0) {
                                                                                                                                                i26 = 0;
                                                                                                                                            } else {
                                                                                                                                                i26 = 1;
                                                                                                                                            }
                                                                                                                                            i17 = i26;
                                                                                                                                            response = response4;
                                                                                                                                            r2 = r22;
                                                                                                                                            r27 = i25;
                                                                                                                                            r124 = r126;
                                                                                                                                            r117 = r118;
                                                                                                                                            str17 = str20;
                                                                                                                                            r123 = r125;
                                                                                                                                        } catch (Throwable th7) {
                                                                                                                                            th = th7;
                                                                                                                                            r1 = r22;
                                                                                                                                            r4 = i25;
                                                                                                                                            r122 = r126;
                                                                                                                                            r116 = r118;
                                                                                                                                            r121 = r125;
                                                                                                                                            FileLog.e(th);
                                                                                                                                            r116.element = false;
                                                                                                                                            r26 = r1;
                                                                                                                                            r3 = r4;
                                                                                                                                            r12 = r122;
                                                                                                                                            r14 = r116;
                                                                                                                                            r19 = r121;
                                                                                                                                        }
                                                                                                                                    } catch (Throwable th8) {
                                                                                                                                        th = th8;
                                                                                                                                        mutex2 = mutex8;
                                                                                                                                        r1 = r21;
                                                                                                                                        r116 = r24;
                                                                                                                                        r4 = i19;
                                                                                                                                        r122 = r115;
                                                                                                                                        r121 = r114;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            } catch (Throwable th9) {
                                                                                                                                th = th9;
                                                                                                                                r24 = r16;
                                                                                                                            }
                                                                                                                        } catch (CancellationException e2) {
                                                                                                                            throw e2;
                                                                                                                        }
                                                                                                                    } catch (Throwable th10) {
                                                                                                                        th = th10;
                                                                                                                        str23 = z5 ? 1 : 0;
                                                                                                                        r7 = str23;
                                                                                                                        r1 = r28;
                                                                                                                        r4 = r7;
                                                                                                                        r122 = r12;
                                                                                                                        r116 = r14;
                                                                                                                        r121 = r19;
                                                                                                                    }
                                                                                                                }
                                                                                                                if (i17 != 0) {
                                                                                                                    r27 = r7;
                                                                                                                    r124 = r12;
                                                                                                                    r117 = r14;
                                                                                                                    str17 = str10;
                                                                                                                    r123 = r19;
                                                                                                                    r27 = i19;
                                                                                                                    r124 = r115;
                                                                                                                    str17 = str15;
                                                                                                                    r123 = r114;
                                                                                                                    Edit2 = r2.edit();
                                                                                                                    Edit2.putString(str12, INSTANCE.syncWatermark(response));
                                                                                                                    str18 = response.headers().get("ETag");
                                                                                                                    str19 = str17;
                                                                                                                    if (str18 != null) {
                                                                                                                        Edit2.putString(str19, str18);
                                                                                                                    } else {
                                                                                                                        Edit2.remove(str19);
                                                                                                                    }
                                                                                                                    Edit2.putLong(str13, r124);
                                                                                                                    Edit2.apply();
                                                                                                                    r26 = r2;
                                                                                                                    r3 = r27;
                                                                                                                    r12 = r124;
                                                                                                                    r14 = r117;
                                                                                                                    r19 = r123;
                                                                                                                } else {
                                                                                                                    r27 = r7;
                                                                                                                    r124 = r12;
                                                                                                                    r117 = r14;
                                                                                                                    str17 = str10;
                                                                                                                    r123 = r19;
                                                                                                                    r27 = i19;
                                                                                                                    r124 = r115;
                                                                                                                    str17 = str15;
                                                                                                                    r123 = r114;
                                                                                                                    r117.element = false;
                                                                                                                    r26 = r2;
                                                                                                                    r3 = r27;
                                                                                                                    r12 = r124;
                                                                                                                    r14 = r117;
                                                                                                                    r19 = r123;
                                                                                                                }
                                                                                                                r28 = r26;
                                                                                                                r12 = jCurrentTimeMillis;
                                                                                                                r14 = booleanRef3;
                                                                                                                str3 = str23;
                                                                                                                mutex6 = mutex2;
                                                                                                                if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                                                    if (r3 != 0) {
                                                                                                                        Edit = r28.edit();
                                                                                                                        if (r14.element) {
                                                                                                                            Edit.remove(r19);
                                                                                                                        } else {
                                                                                                                            Edit.putLong(r19, r12);
                                                                                                                        }
                                                                                                                        Edit.apply();
                                                                                                                    }
                                                                                                                    z6 = r14.element;
                                                                                                                }
                                                                                                            }
                                                                                                            r1 = r28;
                                                                                                            r4 = r7;
                                                                                                            r122 = r12;
                                                                                                            r116 = r14;
                                                                                                            r121 = r19;
                                                                                                            FileLog.e(th);
                                                                                                            r116.element = false;
                                                                                                            r26 = r1;
                                                                                                            r3 = r4;
                                                                                                            r12 = r122;
                                                                                                            r14 = r116;
                                                                                                            r19 = r121;
                                                                                                            r28 = r26;
                                                                                                            r12 = jCurrentTimeMillis;
                                                                                                            r14 = booleanRef3;
                                                                                                            str3 = str23;
                                                                                                            mutex6 = mutex2;
                                                                                                            if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                                                if (r3 != 0) {
                                                                                                                    Edit = r28.edit();
                                                                                                                    if (r14.element) {
                                                                                                                        Edit.remove(r19);
                                                                                                                    } else {
                                                                                                                        Edit.putLong(r19, r12);
                                                                                                                    }
                                                                                                                    Edit.apply();
                                                                                                                }
                                                                                                                z6 = r14.element;
                                                                                                            }
                                                                                                        }
                                                                                                        r3 = r7 == true ? 1 : 0;
                                                                                                    } catch (Throwable th11) {
                                                                                                        th = th11;
                                                                                                        boolean z12 = r7 == true ? 1 : 0;
                                                                                                    }
                                                                                                    r12 = jCurrentTimeMillis;
                                                                                                    r14 = booleanRef3;
                                                                                                    str3 = str23;
                                                                                                    mutex6 = mutex2;
                                                                                                    if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                                        if (r3 != 0) {
                                                                                                            Edit = r28.edit();
                                                                                                            if (r14.element) {
                                                                                                                Edit.remove(r19);
                                                                                                            } else {
                                                                                                                Edit.putLong(r19, r12);
                                                                                                            }
                                                                                                            Edit.apply();
                                                                                                        }
                                                                                                        z6 = r14.element;
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                str5 = str4;
                                                                                                c01351.L$0 = r28;
                                                                                                c01351.L$1 = mutex2;
                                                                                                c01351.L$2 = r14;
                                                                                                c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                                                                c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                                                                c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                                                                c01351.Z$0 = z3;
                                                                                                c01351.I$0 = i6;
                                                                                                c01351.I$1 = i7;
                                                                                                c01351.J$0 = r12;
                                                                                                c01351.I$2 = i8;
                                                                                                c01351.I$3 = i9;
                                                                                                c01351.I$4 = 1;
                                                                                                c01351.label = 5;
                                                                                                updates = apiService.getUpdates(string, string2, c01351);
                                                                                                if (updates != coroutine_suspended) {
                                                                                                    str6 = string2;
                                                                                                    i10 = i8;
                                                                                                    str7 = string;
                                                                                                    i11 = i9;
                                                                                                    obj3 = updates;
                                                                                                    i12 = i7;
                                                                                                    i13 = 1;
                                                                                                    r20 = r28;
                                                                                                    r113 = r12;
                                                                                                    r17 = r14;
                                                                                                    r112 = r19;
                                                                                                    response = (Response) obj3;
                                                                                                    r28 = r20;
                                                                                                    r12 = r113;
                                                                                                    r14 = r17;
                                                                                                    str10 = str5;
                                                                                                    r19 = r112;
                                                                                                    int i310 = i13;
                                                                                                    int i311 = i11;
                                                                                                    r7 = i310;
                                                                                                    boolean z13 = z3;
                                                                                                    i15 = i12;
                                                                                                    i16 = i6;
                                                                                                    z4 = z13;
                                                                                                    str11 = str7;
                                                                                                    apiService2 = apiService;
                                                                                                    if (response.code() == 304) {
                                                                                                        ?? Edit6 = r28.edit();
                                                                                                        Edit6.putString(str24, INSTANCE.syncWatermark(response));
                                                                                                        Edit6.putLong(str2, r12);
                                                                                                        Edit6.apply();
                                                                                                    } else {
                                                                                                        String str211 = str24;
                                                                                                        String str212 = str2;
                                                                                                        if (response.isSuccessful()) {
                                                                                                        }
                                                                                                        r14.element = false;
                                                                                                    }
                                                                                                    r3 = r7 == true ? 1 : 0;
                                                                                                    r12 = jCurrentTimeMillis;
                                                                                                    r14 = booleanRef3;
                                                                                                    str3 = str23;
                                                                                                    mutex6 = mutex2;
                                                                                                    if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                                        if (r3 != 0) {
                                                                                                            Edit = r28.edit();
                                                                                                            if (r14.element) {
                                                                                                                Edit.remove(r19);
                                                                                                            } else {
                                                                                                                Edit.putLong(r19, r12);
                                                                                                            }
                                                                                                            Edit.apply();
                                                                                                        }
                                                                                                        z6 = r14.element;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        } catch (Throwable th12) {
                                                                                            th = th12;
                                                                                            r1 = r28;
                                                                                            r4 = 1;
                                                                                            r122 = r12;
                                                                                            r116 = r14;
                                                                                            r121 = r19;
                                                                                        }
                                                                                        break;
                                                                                    } else {
                                                                                        r12 = jCurrentTimeMillis;
                                                                                        r14 = booleanRef3;
                                                                                        str3 = str23;
                                                                                        mutex6 = mutex2;
                                                                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                            if (r3 != 0) {
                                                                                                Edit = r28.edit();
                                                                                                if (r14.element) {
                                                                                                    Edit.remove(r19);
                                                                                                } else {
                                                                                                    Edit.putLong(r19, r12);
                                                                                                }
                                                                                                Edit.apply();
                                                                                            }
                                                                                            z6 = r14.element;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            } catch (CancellationException e3) {
                                                                                e = e3;
                                                                                Mutex mutex12 = mutex10;
                                                                                mutex7 = mutex12;
                                                                                try {
                                                                                    throw e;
                                                                                } catch (Throwable th13) {
                                                                                    th = th13;
                                                                                    mutex6 = mutex7;
                                                                                    mutex6.unlock(null);
                                                                                    throw th;
                                                                                }
                                                                            }
                                                                        } catch (Throwable th14) {
                                                                            th = th14;
                                                                            mutex4 = mutex10;
                                                                            str2 = PROFILES_SYNC_TIME_KEY;
                                                                            i5 = i29;
                                                                            r5 = booleanRef4;
                                                                            r8 = sharedPreferences5;
                                                                            r128 = j;
                                                                            str = str21;
                                                                            r18 = r28;
                                                                        }
                                                                    }
                                                                } catch (CancellationException e4) {
                                                                    e = e4;
                                                                    mutex7 = mutex6;
                                                                    throw e;
                                                                } catch (Throwable th15) {
                                                                    th = th15;
                                                                    str2 = PROFILES_SYNC_TIME_KEY;
                                                                    r18 = SYNC_FAILURE_TIME_KEY;
                                                                    int i42 = i6;
                                                                    i3 = i27;
                                                                    r5 = booleanRef3;
                                                                    i = i42;
                                                                    r8 = sharedPreferences4;
                                                                    z2 = z7;
                                                                    mutex4 = mutex6;
                                                                    r128 = jCurrentTimeMillis;
                                                                    i5 = 1;
                                                                    i2 = 0;
                                                                    i4 = i28;
                                                                    str = str23;
                                                                    FileLog.e(th);
                                                                    r5.element = false;
                                                                    i14 = i5;
                                                                    r6 = r5;
                                                                    r9 = r8;
                                                                    r11 = r128;
                                                                    str9 = str;
                                                                    r110 = r18;
                                                                    i9 = i4;
                                                                    i8 = i3;
                                                                    i6 = i;
                                                                    r14 = r6;
                                                                    i7 = i2;
                                                                    r12 = r11;
                                                                    z3 = z2;
                                                                    mutex2 = mutex4;
                                                                    r3 = i14;
                                                                    r28 = r9;
                                                                    str3 = str9;
                                                                    r19 = r110;
                                                                    if (i9 != 0) {
                                                                        r12 = jCurrentTimeMillis;
                                                                        r14 = booleanRef3;
                                                                        str3 = str23;
                                                                        string = r28.getString(str24, null);
                                                                        str4 = str3;
                                                                        string2 = r28.getString(str4, null);
                                                                        apiService = ApiClient.INSTANCE.getApiService();
                                                                        if (string == null) {
                                                                            c01351.L$0 = r28;
                                                                            c01351.L$1 = mutex2;
                                                                            c01351.L$2 = r14;
                                                                            str8 = str4;
                                                                            c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                                            c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                                            c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                                            c01351.Z$0 = z3;
                                                                            c01351.I$0 = i6;
                                                                            c01351.I$1 = i7;
                                                                            c01351.J$0 = r12;
                                                                            c01351.I$2 = i8;
                                                                            c01351.I$3 = i9;
                                                                            c01351.I$4 = 1;
                                                                            c01351.label = 4;
                                                                            allProfiles = apiService.getAllProfiles(string2, c01351);
                                                                            if (allProfiles != coroutine_suspended) {
                                                                                str6 = string2;
                                                                                i10 = i8;
                                                                                str7 = string;
                                                                                i11 = i9;
                                                                                obj4 = allProfiles;
                                                                                i12 = i7;
                                                                                i13 = 1;
                                                                                r10 = r28;
                                                                                r13 = r12;
                                                                                r15 = r14;
                                                                                r111 = r19;
                                                                                response = (Response) obj4;
                                                                                r28 = r10;
                                                                                r12 = r13;
                                                                                r14 = r15;
                                                                                str10 = str8;
                                                                                r19 = r111;
                                                                                int i312 = i13;
                                                                                int i313 = i11;
                                                                                r7 = i312;
                                                                                boolean z14 = z3;
                                                                                i15 = i12;
                                                                                i16 = i6;
                                                                                z4 = z14;
                                                                                str11 = str7;
                                                                                apiService2 = apiService;
                                                                                if (response.code() == 304) {
                                                                                    ?? Edit7 = r28.edit();
                                                                                    Edit7.putString(str24, INSTANCE.syncWatermark(response));
                                                                                    Edit7.putLong(str2, r12);
                                                                                    Edit7.apply();
                                                                                } else {
                                                                                    String str213 = str24;
                                                                                    String str214 = str2;
                                                                                    if (response.isSuccessful()) {
                                                                                    }
                                                                                    r14.element = false;
                                                                                }
                                                                                r3 = r7 == true ? 1 : 0;
                                                                                r12 = jCurrentTimeMillis;
                                                                                r14 = booleanRef3;
                                                                                str3 = str23;
                                                                                mutex6 = mutex2;
                                                                                if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                    if (r3 != 0) {
                                                                                        Edit = r28.edit();
                                                                                        if (r14.element) {
                                                                                            Edit.remove(r19);
                                                                                        } else {
                                                                                            Edit.putLong(r19, r12);
                                                                                        }
                                                                                        Edit.apply();
                                                                                    }
                                                                                    z6 = r14.element;
                                                                                } else {
                                                                                    z6 = true;
                                                                                }
                                                                            }
                                                                        } else {
                                                                            str5 = str4;
                                                                            c01351.L$0 = r28;
                                                                            c01351.L$1 = mutex2;
                                                                            c01351.L$2 = r14;
                                                                            c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                                            c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                                            c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                                            c01351.Z$0 = z3;
                                                                            c01351.I$0 = i6;
                                                                            c01351.I$1 = i7;
                                                                            c01351.J$0 = r12;
                                                                            c01351.I$2 = i8;
                                                                            c01351.I$3 = i9;
                                                                            c01351.I$4 = 1;
                                                                            c01351.label = 5;
                                                                            updates = apiService.getUpdates(string, string2, c01351);
                                                                            if (updates != coroutine_suspended) {
                                                                                str6 = string2;
                                                                                i10 = i8;
                                                                                str7 = string;
                                                                                i11 = i9;
                                                                                obj3 = updates;
                                                                                i12 = i7;
                                                                                i13 = 1;
                                                                                r20 = r28;
                                                                                r113 = r12;
                                                                                r17 = r14;
                                                                                r112 = r19;
                                                                                response = (Response) obj3;
                                                                                r28 = r20;
                                                                                r12 = r113;
                                                                                r14 = r17;
                                                                                str10 = str5;
                                                                                r19 = r112;
                                                                                int i314 = i13;
                                                                                int i315 = i11;
                                                                                r7 = i314;
                                                                                boolean z15 = z3;
                                                                                i15 = i12;
                                                                                i16 = i6;
                                                                                z4 = z15;
                                                                                str11 = str7;
                                                                                apiService2 = apiService;
                                                                                if (response.code() == 304) {
                                                                                    ?? Edit8 = r28.edit();
                                                                                    Edit8.putString(str24, INSTANCE.syncWatermark(response));
                                                                                    Edit8.putLong(str2, r12);
                                                                                    Edit8.apply();
                                                                                } else {
                                                                                    String str215 = str24;
                                                                                    String str216 = str2;
                                                                                    if (response.isSuccessful()) {
                                                                                    }
                                                                                    r14.element = false;
                                                                                }
                                                                                r3 = r7 == true ? 1 : 0;
                                                                                r12 = jCurrentTimeMillis;
                                                                                r14 = booleanRef3;
                                                                                str3 = str23;
                                                                                mutex6 = mutex2;
                                                                                if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                    z6 = true;
                                                                                } else {
                                                                                    if (r3 != 0) {
                                                                                        Edit = r28.edit();
                                                                                        if (r14.element) {
                                                                                            Edit.remove(r19);
                                                                                        } else {
                                                                                            Edit.putLong(r19, r12);
                                                                                        }
                                                                                        Edit.apply();
                                                                                    }
                                                                                    z6 = r14.element;
                                                                                }
                                                                            }
                                                                        }
                                                                        return coroutine_suspended;
                                                                    }
                                                                    r12 = jCurrentTimeMillis;
                                                                    r14 = booleanRef3;
                                                                    str3 = str23;
                                                                    mutex6 = mutex2;
                                                                    if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                        z6 = true;
                                                                    } else {
                                                                        if (r3 != 0) {
                                                                            Edit = r28.edit();
                                                                            if (r14.element) {
                                                                                Edit.remove(r19);
                                                                            } else {
                                                                                Edit.putLong(r19, r12);
                                                                            }
                                                                            Edit.apply();
                                                                        }
                                                                        z6 = r14.element;
                                                                    }
                                                                    Boolean boolBoxBoolean = Boxing.boxBoolean(z6);
                                                                    mutex6.unlock(null);
                                                                    return boolBoxBoolean;
                                                                }
                                                            } else {
                                                                str2 = PROFILES_SYNC_TIME_KEY;
                                                                r19 = SYNC_FAILURE_TIME_KEY;
                                                                r28 = sharedPreferences4;
                                                                i8 = i27;
                                                                i9 = i28;
                                                                mutex2 = mutex6;
                                                                r3 = 0;
                                                                i7 = 0;
                                                                z3 = z7;
                                                                if (i9 != 0) {
                                                                    r12 = jCurrentTimeMillis;
                                                                    r14 = booleanRef3;
                                                                    str3 = str23;
                                                                    string = r28.getString(str24, null);
                                                                    str4 = str3;
                                                                    string2 = r28.getString(str4, null);
                                                                    apiService = ApiClient.INSTANCE.getApiService();
                                                                    if (string == null) {
                                                                        c01351.L$0 = r28;
                                                                        c01351.L$1 = mutex2;
                                                                        c01351.L$2 = r14;
                                                                        str8 = str4;
                                                                        c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                                        c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                                        c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                                        c01351.Z$0 = z3;
                                                                        c01351.I$0 = i6;
                                                                        c01351.I$1 = i7;
                                                                        c01351.J$0 = r12;
                                                                        c01351.I$2 = i8;
                                                                        c01351.I$3 = i9;
                                                                        c01351.I$4 = 1;
                                                                        c01351.label = 4;
                                                                        allProfiles = apiService.getAllProfiles(string2, c01351);
                                                                        if (allProfiles != coroutine_suspended) {
                                                                            str6 = string2;
                                                                            i10 = i8;
                                                                            str7 = string;
                                                                            i11 = i9;
                                                                            obj4 = allProfiles;
                                                                            i12 = i7;
                                                                            i13 = 1;
                                                                            r10 = r28;
                                                                            r13 = r12;
                                                                            r15 = r14;
                                                                            r111 = r19;
                                                                            response = (Response) obj4;
                                                                            r28 = r10;
                                                                            r12 = r13;
                                                                            r14 = r15;
                                                                            str10 = str8;
                                                                            r19 = r111;
                                                                            int i316 = i13;
                                                                            int i317 = i11;
                                                                            r7 = i316;
                                                                            boolean z16 = z3;
                                                                            i15 = i12;
                                                                            i16 = i6;
                                                                            z4 = z16;
                                                                            str11 = str7;
                                                                            apiService2 = apiService;
                                                                            if (response.code() == 304) {
                                                                                ?? Edit9 = r28.edit();
                                                                                Edit9.putString(str24, INSTANCE.syncWatermark(response));
                                                                                Edit9.putLong(str2, r12);
                                                                                Edit9.apply();
                                                                            } else {
                                                                                String str217 = str24;
                                                                                String str218 = str2;
                                                                                if (response.isSuccessful()) {
                                                                                }
                                                                                r14.element = false;
                                                                            }
                                                                            r3 = r7 == true ? 1 : 0;
                                                                            r12 = jCurrentTimeMillis;
                                                                            r14 = booleanRef3;
                                                                            str3 = str23;
                                                                            mutex6 = mutex2;
                                                                            if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                if (r3 != 0) {
                                                                                    Edit = r28.edit();
                                                                                    if (r14.element) {
                                                                                        Edit.remove(r19);
                                                                                    } else {
                                                                                        Edit.putLong(r19, r12);
                                                                                    }
                                                                                    Edit.apply();
                                                                                }
                                                                                z6 = r14.element;
                                                                            }
                                                                        }
                                                                    } else {
                                                                        str5 = str4;
                                                                        c01351.L$0 = r28;
                                                                        c01351.L$1 = mutex2;
                                                                        c01351.L$2 = r14;
                                                                        c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                                        c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                                        c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                                        c01351.Z$0 = z3;
                                                                        c01351.I$0 = i6;
                                                                        c01351.I$1 = i7;
                                                                        c01351.J$0 = r12;
                                                                        c01351.I$2 = i8;
                                                                        c01351.I$3 = i9;
                                                                        c01351.I$4 = 1;
                                                                        c01351.label = 5;
                                                                        updates = apiService.getUpdates(string, string2, c01351);
                                                                        if (updates != coroutine_suspended) {
                                                                            str6 = string2;
                                                                            i10 = i8;
                                                                            str7 = string;
                                                                            i11 = i9;
                                                                            obj3 = updates;
                                                                            i12 = i7;
                                                                            i13 = 1;
                                                                            r20 = r28;
                                                                            r113 = r12;
                                                                            r17 = r14;
                                                                            r112 = r19;
                                                                            response = (Response) obj3;
                                                                            r28 = r20;
                                                                            r12 = r113;
                                                                            r14 = r17;
                                                                            str10 = str5;
                                                                            r19 = r112;
                                                                            int i318 = i13;
                                                                            int i319 = i11;
                                                                            r7 = i318;
                                                                            boolean z17 = z3;
                                                                            i15 = i12;
                                                                            i16 = i6;
                                                                            z4 = z17;
                                                                            str11 = str7;
                                                                            apiService2 = apiService;
                                                                            if (response.code() == 304) {
                                                                                ?? Edit10 = r28.edit();
                                                                                Edit10.putString(str24, INSTANCE.syncWatermark(response));
                                                                                Edit10.putLong(str2, r12);
                                                                                Edit10.apply();
                                                                            } else {
                                                                                String str219 = str24;
                                                                                String str2110 = str2;
                                                                                if (response.isSuccessful()) {
                                                                                }
                                                                                r14.element = false;
                                                                            }
                                                                            r3 = r7 == true ? 1 : 0;
                                                                            r12 = jCurrentTimeMillis;
                                                                            r14 = booleanRef3;
                                                                            str3 = str23;
                                                                            mutex6 = mutex2;
                                                                            if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                                if (r3 != 0) {
                                                                                    Edit = r28.edit();
                                                                                    if (r14.element) {
                                                                                        Edit.remove(r19);
                                                                                    } else {
                                                                                        Edit.putLong(r19, r12);
                                                                                    }
                                                                                    Edit.apply();
                                                                                }
                                                                                z6 = r14.element;
                                                                            }
                                                                        }
                                                                    }
                                                                    break;
                                                                } else {
                                                                    r12 = jCurrentTimeMillis;
                                                                    r14 = booleanRef3;
                                                                    str3 = str23;
                                                                    mutex6 = mutex2;
                                                                    if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                        if (r3 != 0) {
                                                                            Edit = r28.edit();
                                                                            if (r14.element) {
                                                                                Edit.remove(r19);
                                                                            } else {
                                                                                Edit.putLong(r19, r12);
                                                                            }
                                                                            Edit.apply();
                                                                        }
                                                                        z6 = r14.element;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        z6 = true;
                                                    } else {
                                                        z6 = false;
                                                    }
                                                } else {
                                                    z6 = true;
                                                }
                                                Boolean boolBoxBoolean2 = Boxing.boxBoolean(z6);
                                                mutex6.unlock(null);
                                                return boolBoxBoolean2;
                                            } catch (Throwable th16) {
                                                th = th16;
                                                mutex6.unlock(null);
                                                throw th;
                                            }
                                        }
                                        return coroutine_suspended;
                                    case 1:
                                        int i43 = c01351.I$0;
                                        boolean z18 = c01351.Z$0;
                                        Mutex mutex13 = (Mutex) c01351.L$1;
                                        SharedPreferences sharedPreferences7 = (SharedPreferences) c01351.L$0;
                                        ResultKt.throwOnFailure(obj8);
                                        mutex6 = mutex13;
                                        z7 = z18;
                                        sharedPreferences4 = sharedPreferences7;
                                        i6 = i43;
                                        apiClient = ApiClient.INSTANCE;
                                        if (!apiClient.getRequestsEnabled$TMessagesProj()) {
                                            jCurrentTimeMillis = System.currentTimeMillis();
                                            if (z7) {
                                                break;
                                            }
                                            if (z7) {
                                                i27 = 1;
                                            } else {
                                                i27 = 1;
                                            }
                                            if (z7) {
                                                i28 = 1;
                                            } else {
                                                i28 = 1;
                                            }
                                            if (i27 == 0) {
                                            }
                                            booleanRef3 = new Ref.BooleanRef();
                                            booleanRef3.element = true;
                                            if (i27 != 0) {
                                                ApiService apiService5 = apiClient.getApiService();
                                                c01351.L$0 = sharedPreferences4;
                                                c01351.L$1 = mutex6;
                                                c01351.L$2 = booleanRef3;
                                                c01351.Z$0 = z7;
                                                c01351.I$0 = i6;
                                                c01351.I$1 = 0;
                                                c01351.J$0 = jCurrentTimeMillis;
                                                c01351.I$2 = i27;
                                                c01351.I$3 = i28;
                                                c01351.I$4 = 1;
                                                c01351.label = 2;
                                                boostySubscribers = apiService5.getBoostySubscribers(c01351);
                                                if (boostySubscribers != coroutine_suspended) {
                                                    int i320 = i6;
                                                    i3 = i27;
                                                    booleanRef4 = booleanRef3;
                                                    i = i320;
                                                    z2 = z7;
                                                    sharedPreferences5 = sharedPreferences4;
                                                    i4 = i28;
                                                    mutex10 = mutex6;
                                                    j = jCurrentTimeMillis;
                                                    i29 = 1;
                                                    i2 = 0;
                                                    str21 = str23;
                                                    response5 = (Response) boostySubscribers;
                                                    if (!response5.isSuccessful()) {
                                                    }
                                                    mutex4 = mutex10;
                                                    str2 = PROFILES_SYNC_TIME_KEY;
                                                    booleanRef4.element = false;
                                                    i14 = i29;
                                                    r6 = booleanRef4;
                                                    r9 = sharedPreferences5;
                                                    r11 = j;
                                                    str9 = str21;
                                                    r110 = r28;
                                                    i9 = i4;
                                                    i8 = i3;
                                                    i6 = i;
                                                    r14 = r6;
                                                    i7 = i2;
                                                    r12 = r11;
                                                    z3 = z2;
                                                    mutex2 = mutex4;
                                                    r3 = i14;
                                                    r28 = r9;
                                                    str3 = str9;
                                                    r19 = r110;
                                                    if (i9 != 0) {
                                                        r12 = jCurrentTimeMillis;
                                                        r14 = booleanRef3;
                                                        str3 = str23;
                                                        string = r28.getString(str24, null);
                                                        str4 = str3;
                                                        string2 = r28.getString(str4, null);
                                                        apiService = ApiClient.INSTANCE.getApiService();
                                                        if (string == null) {
                                                            c01351.L$0 = r28;
                                                            c01351.L$1 = mutex2;
                                                            c01351.L$2 = r14;
                                                            str8 = str4;
                                                            c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                            c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                            c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                            c01351.Z$0 = z3;
                                                            c01351.I$0 = i6;
                                                            c01351.I$1 = i7;
                                                            c01351.J$0 = r12;
                                                            c01351.I$2 = i8;
                                                            c01351.I$3 = i9;
                                                            c01351.I$4 = 1;
                                                            c01351.label = 4;
                                                            allProfiles = apiService.getAllProfiles(string2, c01351);
                                                            if (allProfiles != coroutine_suspended) {
                                                                str6 = string2;
                                                                i10 = i8;
                                                                str7 = string;
                                                                i11 = i9;
                                                                obj4 = allProfiles;
                                                                i12 = i7;
                                                                i13 = 1;
                                                                r10 = r28;
                                                                r13 = r12;
                                                                r15 = r14;
                                                                r111 = r19;
                                                                response = (Response) obj4;
                                                                r28 = r10;
                                                                r12 = r13;
                                                                r14 = r15;
                                                                str10 = str8;
                                                                r19 = r111;
                                                                int i3110 = i13;
                                                                int i3111 = i11;
                                                                r7 = i3110;
                                                                boolean z19 = z3;
                                                                i15 = i12;
                                                                i16 = i6;
                                                                z4 = z19;
                                                                str11 = str7;
                                                                apiService2 = apiService;
                                                                if (response.code() == 304) {
                                                                    ?? Edit11 = r28.edit();
                                                                    Edit11.putString(str24, INSTANCE.syncWatermark(response));
                                                                    Edit11.putLong(str2, r12);
                                                                    Edit11.apply();
                                                                } else {
                                                                    String str2111 = str24;
                                                                    String str2112 = str2;
                                                                    if (response.isSuccessful()) {
                                                                    }
                                                                    r14.element = false;
                                                                }
                                                                r3 = r7 == true ? 1 : 0;
                                                                r12 = jCurrentTimeMillis;
                                                                r14 = booleanRef3;
                                                                str3 = str23;
                                                                mutex6 = mutex2;
                                                                if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                    z6 = true;
                                                                } else {
                                                                    if (r3 != 0) {
                                                                        Edit = r28.edit();
                                                                        if (r14.element) {
                                                                            Edit.remove(r19);
                                                                        } else {
                                                                            Edit.putLong(r19, r12);
                                                                        }
                                                                        Edit.apply();
                                                                    }
                                                                    z6 = r14.element;
                                                                }
                                                            }
                                                        } else {
                                                            str5 = str4;
                                                            c01351.L$0 = r28;
                                                            c01351.L$1 = mutex2;
                                                            c01351.L$2 = r14;
                                                            c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                            c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                            c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                            c01351.Z$0 = z3;
                                                            c01351.I$0 = i6;
                                                            c01351.I$1 = i7;
                                                            c01351.J$0 = r12;
                                                            c01351.I$2 = i8;
                                                            c01351.I$3 = i9;
                                                            c01351.I$4 = 1;
                                                            c01351.label = 5;
                                                            updates = apiService.getUpdates(string, string2, c01351);
                                                            if (updates != coroutine_suspended) {
                                                                str6 = string2;
                                                                i10 = i8;
                                                                str7 = string;
                                                                i11 = i9;
                                                                obj3 = updates;
                                                                i12 = i7;
                                                                i13 = 1;
                                                                r20 = r28;
                                                                r113 = r12;
                                                                r17 = r14;
                                                                r112 = r19;
                                                                response = (Response) obj3;
                                                                r28 = r20;
                                                                r12 = r113;
                                                                r14 = r17;
                                                                str10 = str5;
                                                                r19 = r112;
                                                                int i3112 = i13;
                                                                int i3113 = i11;
                                                                r7 = i3112;
                                                                boolean z110 = z3;
                                                                i15 = i12;
                                                                i16 = i6;
                                                                z4 = z110;
                                                                str11 = str7;
                                                                apiService2 = apiService;
                                                                if (response.code() == 304) {
                                                                    ?? Edit12 = r28.edit();
                                                                    Edit12.putString(str24, INSTANCE.syncWatermark(response));
                                                                    Edit12.putLong(str2, r12);
                                                                    Edit12.apply();
                                                                } else {
                                                                    String str2113 = str24;
                                                                    String str2114 = str2;
                                                                    if (response.isSuccessful()) {
                                                                    }
                                                                    r14.element = false;
                                                                }
                                                                r3 = r7 == true ? 1 : 0;
                                                                r12 = jCurrentTimeMillis;
                                                                r14 = booleanRef3;
                                                                str3 = str23;
                                                                mutex6 = mutex2;
                                                                if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                    z6 = true;
                                                                } else {
                                                                    if (r3 != 0) {
                                                                        Edit = r28.edit();
                                                                        if (r14.element) {
                                                                            Edit.remove(r19);
                                                                        } else {
                                                                            Edit.putLong(r19, r12);
                                                                        }
                                                                        Edit.apply();
                                                                    }
                                                                    z6 = r14.element;
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    } else {
                                                        r12 = jCurrentTimeMillis;
                                                        r14 = booleanRef3;
                                                        str3 = str23;
                                                        mutex6 = mutex2;
                                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                            z6 = true;
                                                        } else {
                                                            if (r3 != 0) {
                                                                Edit = r28.edit();
                                                                if (r14.element) {
                                                                    Edit.remove(r19);
                                                                } else {
                                                                    Edit.putLong(r19, r12);
                                                                }
                                                                Edit.apply();
                                                            }
                                                            z6 = r14.element;
                                                        }
                                                    }
                                                }
                                                break;
                                            } else {
                                                str2 = PROFILES_SYNC_TIME_KEY;
                                                r19 = SYNC_FAILURE_TIME_KEY;
                                                r28 = sharedPreferences4;
                                                i8 = i27;
                                                i9 = i28;
                                                mutex2 = mutex6;
                                                r3 = 0;
                                                i7 = 0;
                                                z3 = z7;
                                                if (i9 != 0) {
                                                    r12 = jCurrentTimeMillis;
                                                    r14 = booleanRef3;
                                                    str3 = str23;
                                                    string = r28.getString(str24, null);
                                                    str4 = str3;
                                                    string2 = r28.getString(str4, null);
                                                    apiService = ApiClient.INSTANCE.getApiService();
                                                    if (string == null) {
                                                        c01351.L$0 = r28;
                                                        c01351.L$1 = mutex2;
                                                        c01351.L$2 = r14;
                                                        str8 = str4;
                                                        c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                        c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                        c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                        c01351.Z$0 = z3;
                                                        c01351.I$0 = i6;
                                                        c01351.I$1 = i7;
                                                        c01351.J$0 = r12;
                                                        c01351.I$2 = i8;
                                                        c01351.I$3 = i9;
                                                        c01351.I$4 = 1;
                                                        c01351.label = 4;
                                                        allProfiles = apiService.getAllProfiles(string2, c01351);
                                                        if (allProfiles != coroutine_suspended) {
                                                            str6 = string2;
                                                            i10 = i8;
                                                            str7 = string;
                                                            i11 = i9;
                                                            obj4 = allProfiles;
                                                            i12 = i7;
                                                            i13 = 1;
                                                            r10 = r28;
                                                            r13 = r12;
                                                            r15 = r14;
                                                            r111 = r19;
                                                            response = (Response) obj4;
                                                            r28 = r10;
                                                            r12 = r13;
                                                            r14 = r15;
                                                            str10 = str8;
                                                            r19 = r111;
                                                            int i3114 = i13;
                                                            int i3115 = i11;
                                                            r7 = i3114;
                                                            boolean z111 = z3;
                                                            i15 = i12;
                                                            i16 = i6;
                                                            z4 = z111;
                                                            str11 = str7;
                                                            apiService2 = apiService;
                                                            if (response.code() == 304) {
                                                                ?? Edit13 = r28.edit();
                                                                Edit13.putString(str24, INSTANCE.syncWatermark(response));
                                                                Edit13.putLong(str2, r12);
                                                                Edit13.apply();
                                                            } else {
                                                                String str2115 = str24;
                                                                String str2116 = str2;
                                                                if (response.isSuccessful()) {
                                                                }
                                                                r14.element = false;
                                                            }
                                                            r3 = r7 == true ? 1 : 0;
                                                            r12 = jCurrentTimeMillis;
                                                            r14 = booleanRef3;
                                                            str3 = str23;
                                                            mutex6 = mutex2;
                                                            if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                z6 = true;
                                                            } else {
                                                                if (r3 != 0) {
                                                                    Edit = r28.edit();
                                                                    if (r14.element) {
                                                                        Edit.remove(r19);
                                                                    } else {
                                                                        Edit.putLong(r19, r12);
                                                                    }
                                                                    Edit.apply();
                                                                }
                                                                z6 = r14.element;
                                                            }
                                                        }
                                                    } else {
                                                        str5 = str4;
                                                        c01351.L$0 = r28;
                                                        c01351.L$1 = mutex2;
                                                        c01351.L$2 = r14;
                                                        c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                        c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                        c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                        c01351.Z$0 = z3;
                                                        c01351.I$0 = i6;
                                                        c01351.I$1 = i7;
                                                        c01351.J$0 = r12;
                                                        c01351.I$2 = i8;
                                                        c01351.I$3 = i9;
                                                        c01351.I$4 = 1;
                                                        c01351.label = 5;
                                                        updates = apiService.getUpdates(string, string2, c01351);
                                                        if (updates != coroutine_suspended) {
                                                            str6 = string2;
                                                            i10 = i8;
                                                            str7 = string;
                                                            i11 = i9;
                                                            obj3 = updates;
                                                            i12 = i7;
                                                            i13 = 1;
                                                            r20 = r28;
                                                            r113 = r12;
                                                            r17 = r14;
                                                            r112 = r19;
                                                            response = (Response) obj3;
                                                            r28 = r20;
                                                            r12 = r113;
                                                            r14 = r17;
                                                            str10 = str5;
                                                            r19 = r112;
                                                            int i3116 = i13;
                                                            int i3117 = i11;
                                                            r7 = i3116;
                                                            boolean z112 = z3;
                                                            i15 = i12;
                                                            i16 = i6;
                                                            z4 = z112;
                                                            str11 = str7;
                                                            apiService2 = apiService;
                                                            if (response.code() == 304) {
                                                                ?? Edit14 = r28.edit();
                                                                Edit14.putString(str24, INSTANCE.syncWatermark(response));
                                                                Edit14.putLong(str2, r12);
                                                                Edit14.apply();
                                                            } else {
                                                                String str2117 = str24;
                                                                String str2118 = str2;
                                                                if (response.isSuccessful()) {
                                                                }
                                                                r14.element = false;
                                                            }
                                                            r3 = r7 == true ? 1 : 0;
                                                            r12 = jCurrentTimeMillis;
                                                            r14 = booleanRef3;
                                                            str3 = str23;
                                                            mutex6 = mutex2;
                                                            if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                                z6 = true;
                                                            } else {
                                                                if (r3 != 0) {
                                                                    Edit = r28.edit();
                                                                    if (r14.element) {
                                                                        Edit.remove(r19);
                                                                    } else {
                                                                        Edit.putLong(r19, r12);
                                                                    }
                                                                    Edit.apply();
                                                                }
                                                                z6 = r14.element;
                                                            }
                                                        }
                                                    }
                                                    break;
                                                } else {
                                                    r12 = jCurrentTimeMillis;
                                                    r14 = booleanRef3;
                                                    str3 = str23;
                                                    mutex6 = mutex2;
                                                    if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                        z6 = true;
                                                    } else {
                                                        if (r3 != 0) {
                                                            Edit = r28.edit();
                                                            if (r14.element) {
                                                                Edit.remove(r19);
                                                            } else {
                                                                Edit.putLong(r19, r12);
                                                            }
                                                            Edit.apply();
                                                        }
                                                        z6 = r14.element;
                                                    }
                                                }
                                            }
                                            return coroutine_suspended;
                                        }
                                        z6 = true;
                                        Boolean boolBoxBoolean3 = Boxing.boxBoolean(z6);
                                        mutex6.unlock(null);
                                        return boolBoxBoolean3;
                                    case 2:
                                        int i44 = c01351.I$4;
                                        i4 = c01351.I$3;
                                        i3 = c01351.I$2;
                                        long j3 = c01351.J$0;
                                        i2 = c01351.I$1;
                                        i = c01351.I$0;
                                        z2 = c01351.Z$0;
                                        Ref.BooleanRef booleanRef6 = (Ref.BooleanRef) c01351.L$2;
                                        Mutex mutex14 = (Mutex) c01351.L$1;
                                        SharedPreferences sharedPreferences8 = (SharedPreferences) c01351.L$0;
                                        ResultKt.throwOnFailure(obj8);
                                        boostySubscribers = obj8;
                                        str21 = "profilesEtag";
                                        booleanRef4 = booleanRef6;
                                        str24 = "lastSyncTimestamp";
                                        mutex10 = mutex14;
                                        sharedPreferences5 = sharedPreferences8;
                                        i29 = i44;
                                        j = j3;
                                        response5 = (Response) boostySubscribers;
                                        if (!response5.isSuccessful()) {
                                            break;
                                        }
                                        mutex4 = mutex10;
                                        str2 = PROFILES_SYNC_TIME_KEY;
                                        booleanRef4.element = false;
                                        i14 = i29;
                                        r6 = booleanRef4;
                                        r9 = sharedPreferences5;
                                        r11 = j;
                                        str9 = str21;
                                        r110 = r28;
                                        i9 = i4;
                                        i8 = i3;
                                        i6 = i;
                                        r14 = r6;
                                        i7 = i2;
                                        r12 = r11;
                                        z3 = z2;
                                        mutex2 = mutex4;
                                        r3 = i14;
                                        r28 = r9;
                                        str3 = str9;
                                        r19 = r110;
                                        if (i9 != 0) {
                                            r12 = jCurrentTimeMillis;
                                            r14 = booleanRef3;
                                            str3 = str23;
                                            string = r28.getString(str24, null);
                                            str4 = str3;
                                            string2 = r28.getString(str4, null);
                                            apiService = ApiClient.INSTANCE.getApiService();
                                            if (string == null) {
                                                str5 = str4;
                                                c01351.L$0 = r28;
                                                c01351.L$1 = mutex2;
                                                c01351.L$2 = r14;
                                                c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                c01351.Z$0 = z3;
                                                c01351.I$0 = i6;
                                                c01351.I$1 = i7;
                                                c01351.J$0 = r12;
                                                c01351.I$2 = i8;
                                                c01351.I$3 = i9;
                                                c01351.I$4 = 1;
                                                c01351.label = 5;
                                                updates = apiService.getUpdates(string, string2, c01351);
                                                if (updates != coroutine_suspended) {
                                                    str6 = string2;
                                                    i10 = i8;
                                                    str7 = string;
                                                    i11 = i9;
                                                    obj3 = updates;
                                                    i12 = i7;
                                                    i13 = 1;
                                                    r20 = r28;
                                                    r113 = r12;
                                                    r17 = r14;
                                                    r112 = r19;
                                                    response = (Response) obj3;
                                                    r28 = r20;
                                                    r12 = r113;
                                                    r14 = r17;
                                                    str10 = str5;
                                                    r19 = r112;
                                                    int i3118 = i13;
                                                    int i3119 = i11;
                                                    r7 = i3118;
                                                    boolean z113 = z3;
                                                    i15 = i12;
                                                    i16 = i6;
                                                    z4 = z113;
                                                    str11 = str7;
                                                    apiService2 = apiService;
                                                    if (response.code() == 304) {
                                                        ?? Edit15 = r28.edit();
                                                        Edit15.putString(str24, INSTANCE.syncWatermark(response));
                                                        Edit15.putLong(str2, r12);
                                                        Edit15.apply();
                                                    } else {
                                                        String str2119 = str24;
                                                        String str21110 = str2;
                                                        if (response.isSuccessful()) {
                                                        }
                                                        r14.element = false;
                                                    }
                                                    r3 = r7 == true ? 1 : 0;
                                                    r12 = jCurrentTimeMillis;
                                                    r14 = booleanRef3;
                                                    str3 = str23;
                                                    mutex6 = mutex2;
                                                    if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                        z6 = true;
                                                    } else {
                                                        if (r3 != 0) {
                                                            Edit = r28.edit();
                                                            if (r14.element) {
                                                                Edit.remove(r19);
                                                            } else {
                                                                Edit.putLong(r19, r12);
                                                            }
                                                            Edit.apply();
                                                        }
                                                        z6 = r14.element;
                                                    }
                                                }
                                                break;
                                            } else {
                                                c01351.L$0 = r28;
                                                c01351.L$1 = mutex2;
                                                c01351.L$2 = r14;
                                                str8 = str4;
                                                c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                c01351.Z$0 = z3;
                                                c01351.I$0 = i6;
                                                c01351.I$1 = i7;
                                                c01351.J$0 = r12;
                                                c01351.I$2 = i8;
                                                c01351.I$3 = i9;
                                                c01351.I$4 = 1;
                                                c01351.label = 4;
                                                allProfiles = apiService.getAllProfiles(string2, c01351);
                                                if (allProfiles != coroutine_suspended) {
                                                    str6 = string2;
                                                    i10 = i8;
                                                    str7 = string;
                                                    i11 = i9;
                                                    obj4 = allProfiles;
                                                    i12 = i7;
                                                    i13 = 1;
                                                    r10 = r28;
                                                    r13 = r12;
                                                    r15 = r14;
                                                    r111 = r19;
                                                    response = (Response) obj4;
                                                    r28 = r10;
                                                    r12 = r13;
                                                    r14 = r15;
                                                    str10 = str8;
                                                    r19 = r111;
                                                    int i31110 = i13;
                                                    int i31111 = i11;
                                                    r7 = i31110;
                                                    boolean z114 = z3;
                                                    i15 = i12;
                                                    i16 = i6;
                                                    z4 = z114;
                                                    str11 = str7;
                                                    apiService2 = apiService;
                                                    if (response.code() == 304) {
                                                        ?? Edit16 = r28.edit();
                                                        Edit16.putString(str24, INSTANCE.syncWatermark(response));
                                                        Edit16.putLong(str2, r12);
                                                        Edit16.apply();
                                                    } else {
                                                        String str21111 = str24;
                                                        String str21112 = str2;
                                                        if (response.isSuccessful()) {
                                                        }
                                                        r14.element = false;
                                                    }
                                                    r3 = r7 == true ? 1 : 0;
                                                    r12 = jCurrentTimeMillis;
                                                    r14 = booleanRef3;
                                                    str3 = str23;
                                                    mutex6 = mutex2;
                                                    if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                        z6 = true;
                                                    } else {
                                                        if (r3 != 0) {
                                                            Edit = r28.edit();
                                                            if (r14.element) {
                                                                Edit.remove(r19);
                                                            } else {
                                                                Edit.putLong(r19, r12);
                                                            }
                                                            Edit.apply();
                                                        }
                                                        z6 = r14.element;
                                                    }
                                                }
                                                break;
                                            }
                                            return coroutine_suspended;
                                        }
                                        r12 = jCurrentTimeMillis;
                                        r14 = booleanRef3;
                                        str3 = str23;
                                        mutex6 = mutex2;
                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                            z6 = true;
                                        } else {
                                            if (r3 != 0) {
                                                Edit = r28.edit();
                                                if (r14.element) {
                                                    Edit.remove(r19);
                                                } else {
                                                    Edit.putLong(r19, r12);
                                                }
                                                Edit.apply();
                                            }
                                            z6 = r14.element;
                                        }
                                        Boolean boolBoxBoolean4 = Boxing.boxBoolean(z6);
                                        mutex6.unlock(null);
                                        return boolBoxBoolean4;
                                    case 3:
                                        int i45 = c01351.I$4;
                                        i4 = c01351.I$3;
                                        i3 = c01351.I$2;
                                        long j4 = c01351.J$0;
                                        i2 = c01351.I$1;
                                        i = c01351.I$0;
                                        z2 = c01351.Z$0;
                                        Ref.BooleanRef booleanRef7 = (Ref.BooleanRef) c01351.L$2;
                                        Mutex mutex15 = (Mutex) c01351.L$1;
                                        SharedPreferences sharedPreferences9 = (SharedPreferences) c01351.L$0;
                                        ResultKt.throwOnFailure(obj8);
                                        r127 = SYNC_FAILURE_TIME_KEY;
                                        sharedPreferences6 = sharedPreferences9;
                                        mutex4 = mutex15;
                                        str2 = PROFILES_SYNC_TIME_KEY;
                                        objInsertBoostySubscribers = obj8;
                                        str22 = "profilesEtag";
                                        booleanRef5 = booleanRef7;
                                        str24 = "lastSyncTimestamp";
                                        i30 = i45;
                                        j2 = j4;
                                        if (((Boolean) objInsertBoostySubscribers).booleanValue()) {
                                            SharedPreferences.Editor editorEdit2 = sharedPreferences6.edit();
                                            editorEdit2.putLong(BOOSTY_SYNC_TIME_KEY, j2);
                                            editorEdit2.apply();
                                            i14 = i30;
                                            r6 = booleanRef5;
                                            r9 = sharedPreferences6;
                                            r11 = j2;
                                            str9 = str22;
                                            r110 = r127;
                                        } else {
                                            booleanRef5.element = false;
                                            i14 = i30;
                                            r6 = booleanRef5;
                                            r9 = sharedPreferences6;
                                            r11 = j2;
                                            str9 = str22;
                                            r110 = r127;
                                        }
                                        i9 = i4;
                                        i8 = i3;
                                        i6 = i;
                                        r14 = r6;
                                        i7 = i2;
                                        r12 = r11;
                                        z3 = z2;
                                        mutex2 = mutex4;
                                        r3 = i14;
                                        r28 = r9;
                                        str3 = str9;
                                        r19 = r110;
                                        if (i9 != 0) {
                                            r12 = jCurrentTimeMillis;
                                            r14 = booleanRef3;
                                            str3 = str23;
                                            string = r28.getString(str24, null);
                                            str4 = str3;
                                            string2 = r28.getString(str4, null);
                                            apiService = ApiClient.INSTANCE.getApiService();
                                            if (string == null) {
                                                str5 = str4;
                                                c01351.L$0 = r28;
                                                c01351.L$1 = mutex2;
                                                c01351.L$2 = r14;
                                                c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                c01351.Z$0 = z3;
                                                c01351.I$0 = i6;
                                                c01351.I$1 = i7;
                                                c01351.J$0 = r12;
                                                c01351.I$2 = i8;
                                                c01351.I$3 = i9;
                                                c01351.I$4 = 1;
                                                c01351.label = 5;
                                                updates = apiService.getUpdates(string, string2, c01351);
                                                if (updates != coroutine_suspended) {
                                                    str6 = string2;
                                                    i10 = i8;
                                                    str7 = string;
                                                    i11 = i9;
                                                    obj3 = updates;
                                                    i12 = i7;
                                                    i13 = 1;
                                                    r20 = r28;
                                                    r113 = r12;
                                                    r17 = r14;
                                                    r112 = r19;
                                                    response = (Response) obj3;
                                                    r28 = r20;
                                                    r12 = r113;
                                                    r14 = r17;
                                                    str10 = str5;
                                                    r19 = r112;
                                                    int i31112 = i13;
                                                    int i31113 = i11;
                                                    r7 = i31112;
                                                    boolean z115 = z3;
                                                    i15 = i12;
                                                    i16 = i6;
                                                    z4 = z115;
                                                    str11 = str7;
                                                    apiService2 = apiService;
                                                    if (response.code() == 304) {
                                                        ?? Edit17 = r28.edit();
                                                        Edit17.putString(str24, INSTANCE.syncWatermark(response));
                                                        Edit17.putLong(str2, r12);
                                                        Edit17.apply();
                                                    } else {
                                                        String str21113 = str24;
                                                        String str21114 = str2;
                                                        if (response.isSuccessful()) {
                                                        }
                                                        r14.element = false;
                                                    }
                                                    r3 = r7 == true ? 1 : 0;
                                                    r12 = jCurrentTimeMillis;
                                                    r14 = booleanRef3;
                                                    str3 = str23;
                                                    mutex6 = mutex2;
                                                    if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                        z6 = true;
                                                    } else {
                                                        if (r3 != 0) {
                                                            Edit = r28.edit();
                                                            if (r14.element) {
                                                                Edit.remove(r19);
                                                            } else {
                                                                Edit.putLong(r19, r12);
                                                            }
                                                            Edit.apply();
                                                        }
                                                        z6 = r14.element;
                                                    }
                                                }
                                                break;
                                            } else {
                                                c01351.L$0 = r28;
                                                c01351.L$1 = mutex2;
                                                c01351.L$2 = r14;
                                                str8 = str4;
                                                c01351.L$3 = SpillingKt.nullOutSpilledVariable(string);
                                                c01351.L$4 = SpillingKt.nullOutSpilledVariable(string2);
                                                c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService);
                                                c01351.Z$0 = z3;
                                                c01351.I$0 = i6;
                                                c01351.I$1 = i7;
                                                c01351.J$0 = r12;
                                                c01351.I$2 = i8;
                                                c01351.I$3 = i9;
                                                c01351.I$4 = 1;
                                                c01351.label = 4;
                                                allProfiles = apiService.getAllProfiles(string2, c01351);
                                                if (allProfiles != coroutine_suspended) {
                                                    str6 = string2;
                                                    i10 = i8;
                                                    str7 = string;
                                                    i11 = i9;
                                                    obj4 = allProfiles;
                                                    i12 = i7;
                                                    i13 = 1;
                                                    r10 = r28;
                                                    r13 = r12;
                                                    r15 = r14;
                                                    r111 = r19;
                                                    response = (Response) obj4;
                                                    r28 = r10;
                                                    r12 = r13;
                                                    r14 = r15;
                                                    str10 = str8;
                                                    r19 = r111;
                                                    int i31114 = i13;
                                                    int i31115 = i11;
                                                    r7 = i31114;
                                                    boolean z116 = z3;
                                                    i15 = i12;
                                                    i16 = i6;
                                                    z4 = z116;
                                                    str11 = str7;
                                                    apiService2 = apiService;
                                                    if (response.code() == 304) {
                                                        ?? Edit18 = r28.edit();
                                                        Edit18.putString(str24, INSTANCE.syncWatermark(response));
                                                        Edit18.putLong(str2, r12);
                                                        Edit18.apply();
                                                    } else {
                                                        String str21115 = str24;
                                                        String str21116 = str2;
                                                        if (response.isSuccessful()) {
                                                        }
                                                        r14.element = false;
                                                    }
                                                    r3 = r7 == true ? 1 : 0;
                                                    r12 = jCurrentTimeMillis;
                                                    r14 = booleanRef3;
                                                    str3 = str23;
                                                    mutex6 = mutex2;
                                                    if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                                        z6 = true;
                                                    } else {
                                                        if (r3 != 0) {
                                                            Edit = r28.edit();
                                                            if (r14.element) {
                                                                Edit.remove(r19);
                                                            } else {
                                                                Edit.putLong(r19, r12);
                                                            }
                                                            Edit.apply();
                                                        }
                                                        z6 = r14.element;
                                                    }
                                                }
                                                break;
                                            }
                                            return coroutine_suspended;
                                        }
                                        r12 = jCurrentTimeMillis;
                                        r14 = booleanRef3;
                                        str3 = str23;
                                        mutex6 = mutex2;
                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                            z6 = true;
                                        } else {
                                            if (r3 != 0) {
                                                Edit = r28.edit();
                                                if (r14.element) {
                                                    Edit.remove(r19);
                                                } else {
                                                    Edit.putLong(r19, r12);
                                                }
                                                Edit.apply();
                                            }
                                            z6 = r14.element;
                                        }
                                        Boolean boolBoxBoolean5 = Boxing.boxBoolean(z6);
                                        mutex6.unlock(null);
                                        return boolBoxBoolean5;
                                    case 4:
                                        int i46 = c01351.I$4;
                                        int i47 = c01351.I$3;
                                        int i48 = c01351.I$2;
                                        long j5 = c01351.J$0;
                                        i12 = c01351.I$1;
                                        i6 = c01351.I$0;
                                        z3 = c01351.Z$0;
                                        ApiService apiService6 = (ApiService) c01351.L$5;
                                        String str30 = (String) c01351.L$4;
                                        String str31 = (String) c01351.L$3;
                                        Ref.BooleanRef booleanRef8 = (Ref.BooleanRef) c01351.L$2;
                                        Mutex mutex16 = (Mutex) c01351.L$1;
                                        SharedPreferences sharedPreferences10 = (SharedPreferences) c01351.L$0;
                                        ResultKt.throwOnFailure(obj8);
                                        str2 = PROFILES_SYNC_TIME_KEY;
                                        apiService = apiService6;
                                        r15 = booleanRef8;
                                        r111 = SYNC_FAILURE_TIME_KEY;
                                        r10 = sharedPreferences10;
                                        str6 = str30;
                                        mutex2 = mutex16;
                                        obj4 = obj8;
                                        str8 = "profilesEtag";
                                        i13 = i46;
                                        i11 = i47;
                                        str24 = "lastSyncTimestamp";
                                        i10 = i48;
                                        str7 = str31;
                                        r13 = j5;
                                        response = (Response) obj4;
                                        r28 = r10;
                                        r12 = r13;
                                        r14 = r15;
                                        str10 = str8;
                                        r19 = r111;
                                        int i31116 = i13;
                                        int i31117 = i11;
                                        r7 = i31116;
                                        boolean z117 = z3;
                                        i15 = i12;
                                        i16 = i6;
                                        z4 = z117;
                                        str11 = str7;
                                        apiService2 = apiService;
                                        if (response.code() == 304) {
                                            String str21117 = str24;
                                            String str21118 = str2;
                                            if (response.isSuccessful()) {
                                            }
                                            r14.element = false;
                                            break;
                                        } else {
                                            ?? Edit19 = r28.edit();
                                            Edit19.putString(str24, INSTANCE.syncWatermark(response));
                                            Edit19.putLong(str2, r12);
                                            Edit19.apply();
                                        }
                                        r3 = r7 == true ? 1 : 0;
                                        r12 = jCurrentTimeMillis;
                                        r14 = booleanRef3;
                                        str3 = str23;
                                        mutex6 = mutex2;
                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                            z6 = true;
                                        } else {
                                            if (r3 != 0) {
                                                Edit = r28.edit();
                                                if (r14.element) {
                                                    Edit.remove(r19);
                                                } else {
                                                    Edit.putLong(r19, r12);
                                                }
                                                Edit.apply();
                                            }
                                            z6 = r14.element;
                                        }
                                        Boolean boolBoxBoolean6 = Boxing.boxBoolean(z6);
                                        mutex6.unlock(null);
                                        return boolBoxBoolean6;
                                    case 5:
                                        int i49 = c01351.I$4;
                                        int i50 = c01351.I$3;
                                        int i51 = c01351.I$2;
                                        long j6 = c01351.J$0;
                                        i12 = c01351.I$1;
                                        i6 = c01351.I$0;
                                        z3 = c01351.Z$0;
                                        ApiService apiService7 = (ApiService) c01351.L$5;
                                        String str32 = (String) c01351.L$4;
                                        String str33 = (String) c01351.L$3;
                                        Ref.BooleanRef booleanRef9 = (Ref.BooleanRef) c01351.L$2;
                                        Mutex mutex17 = (Mutex) c01351.L$1;
                                        SharedPreferences sharedPreferences11 = (SharedPreferences) c01351.L$0;
                                        ResultKt.throwOnFailure(obj8);
                                        str2 = PROFILES_SYNC_TIME_KEY;
                                        apiService = apiService7;
                                        r17 = booleanRef9;
                                        r112 = SYNC_FAILURE_TIME_KEY;
                                        r20 = sharedPreferences11;
                                        str6 = str32;
                                        mutex2 = mutex17;
                                        obj3 = obj8;
                                        str5 = "profilesEtag";
                                        i13 = i49;
                                        i11 = i50;
                                        str24 = "lastSyncTimestamp";
                                        i10 = i51;
                                        str7 = str33;
                                        r113 = j6;
                                        response = (Response) obj3;
                                        r28 = r20;
                                        r12 = r113;
                                        r14 = r17;
                                        str10 = str5;
                                        r19 = r112;
                                        int i31118 = i13;
                                        int i31119 = i11;
                                        r7 = i31118;
                                        boolean z118 = z3;
                                        i15 = i12;
                                        i16 = i6;
                                        z4 = z118;
                                        str11 = str7;
                                        apiService2 = apiService;
                                        if (response.code() == 304) {
                                            String str21119 = str24;
                                            String str211110 = str2;
                                            if (response.isSuccessful()) {
                                            }
                                            r14.element = false;
                                            break;
                                        } else {
                                            ?? Edit110 = r28.edit();
                                            Edit110.putString(str24, INSTANCE.syncWatermark(response));
                                            Edit110.putLong(str2, r12);
                                            Edit110.apply();
                                        }
                                        r3 = r7 == true ? 1 : 0;
                                        r12 = jCurrentTimeMillis;
                                        r14 = booleanRef3;
                                        str3 = str23;
                                        mutex6 = mutex2;
                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                            z6 = true;
                                        } else {
                                            if (r3 != 0) {
                                                Edit = r28.edit();
                                                if (r14.element) {
                                                    Edit.remove(r19);
                                                } else {
                                                    Edit.putLong(r19, r12);
                                                }
                                                Edit.apply();
                                            }
                                            z6 = r14.element;
                                        }
                                        Boolean boolBoxBoolean7 = Boxing.boxBoolean(z6);
                                        mutex6.unlock(null);
                                        return boolBoxBoolean7;
                                    case 6:
                                        int i52 = c01351.I$5;
                                        int i53 = c01351.I$4;
                                        int i54 = c01351.I$3;
                                        int i55 = c01351.I$2;
                                        long j7 = c01351.J$0;
                                        i15 = c01351.I$1;
                                        i16 = c01351.I$0;
                                        z4 = c01351.Z$0;
                                        List<ProfileDTO> list9 = (List) c01351.L$9;
                                        List list10 = (List) c01351.L$8;
                                        list = (List) c01351.L$7;
                                        Response<?> response6 = (Response) c01351.L$6;
                                        ApiService apiService8 = (ApiService) c01351.L$5;
                                        String str34 = (String) c01351.L$4;
                                        String str35 = (String) c01351.L$3;
                                        Ref.BooleanRef booleanRef10 = (Ref.BooleanRef) c01351.L$2;
                                        Mutex mutex18 = (Mutex) c01351.L$1;
                                        SharedPreferences sharedPreferences12 = (SharedPreferences) c01351.L$0;
                                        try {
                                            ResultKt.throwOnFailure(obj8);
                                            obj5 = coroutine_suspended;
                                            str14 = str35;
                                            str13 = PROFILES_SYNC_TIME_KEY;
                                            i22 = i53;
                                            obj6 = obj8;
                                            mutex9 = mutex18;
                                            str16 = "profilesEtag";
                                            response3 = response6;
                                            r23 = booleanRef10;
                                            str12 = "lastSyncTimestamp";
                                            r119 = SYNC_FAILURE_TIME_KEY;
                                            r25 = sharedPreferences12;
                                            list2 = list9;
                                            i23 = i52;
                                            apiService3 = apiService8;
                                            str6 = str34;
                                            list3 = list10;
                                            i18 = i54;
                                            i20 = i55;
                                            r120 = j7;
                                            if (((Boolean) obj6).booleanValue()) {
                                                i21 = 0;
                                            } else {
                                                i21 = 0;
                                            }
                                            str11 = str14;
                                            apiService2 = apiService3;
                                            response2 = response3;
                                            i19 = i22;
                                            r16 = r23;
                                            mutex8 = mutex9;
                                            r21 = r25;
                                            r115 = r120;
                                            str15 = str16;
                                            r114 = r119;
                                            list4 = list2;
                                            if (list4.isEmpty()) {
                                                obj7 = obj5;
                                                DatabaseHelper databaseHelper4 = DatabaseHelper.INSTANCE;
                                                c01351.L$0 = r21;
                                                c01351.L$1 = mutex8;
                                                c01351.L$2 = r16;
                                                r24 = r16;
                                                c01351.L$3 = SpillingKt.nullOutSpilledVariable(str11);
                                                c01351.L$4 = SpillingKt.nullOutSpilledVariable(str6);
                                                c01351.L$5 = SpillingKt.nullOutSpilledVariable(apiService2);
                                                c01351.L$6 = response2;
                                                c01351.L$7 = SpillingKt.nullOutSpilledVariable(list);
                                                c01351.L$8 = SpillingKt.nullOutSpilledVariable(list3);
                                                c01351.L$9 = SpillingKt.nullOutSpilledVariable(list4);
                                                c01351.Z$0 = z4;
                                                c01351.I$0 = i16;
                                                c01351.I$1 = i15;
                                                c01351.J$0 = r115;
                                                c01351.I$2 = i20;
                                                c01351.I$3 = i18;
                                                c01351.I$4 = i19;
                                                c01351.I$5 = i21;
                                                c01351.label = 7;
                                                objInsertProfiles = databaseHelper4.insertProfiles(list4, c01351);
                                                if (objInsertProfiles == obj7) {
                                                    return obj7;
                                                }
                                                i24 = i21;
                                                obj8 = objInsertProfiles;
                                                response4 = response2;
                                                mutex2 = mutex8;
                                                r22 = r21;
                                                r118 = r24;
                                                i25 = i19;
                                                r126 = r115;
                                                str20 = str15;
                                                r125 = r114;
                                                if (((Boolean) obj8).booleanValue()) {
                                                    i26 = 0;
                                                } else {
                                                    i26 = 0;
                                                }
                                                i17 = i26;
                                                response = response4;
                                                r2 = r22;
                                                r27 = i25;
                                                r124 = r126;
                                                r117 = r118;
                                                str17 = str20;
                                                r123 = r125;
                                            } else {
                                                i17 = i21;
                                                response = response2;
                                                mutex2 = mutex8;
                                                r2 = r21;
                                                r117 = r16;
                                            }
                                            if (i17 != 0) {
                                                r27 = r7;
                                                r124 = r12;
                                                r117 = r14;
                                                str17 = str10;
                                                r123 = r19;
                                                r27 = i19;
                                                r124 = r115;
                                                str17 = str15;
                                                r123 = r114;
                                                Edit2 = r2.edit();
                                                Edit2.putString(str12, INSTANCE.syncWatermark(response));
                                                str18 = response.headers().get("ETag");
                                                str19 = str17;
                                                if (str18 != null) {
                                                    Edit2.putString(str19, str18);
                                                } else {
                                                    Edit2.remove(str19);
                                                }
                                                Edit2.putLong(str13, r124);
                                                Edit2.apply();
                                                r26 = r2;
                                                r3 = r27;
                                                r12 = r124;
                                                r14 = r117;
                                                r19 = r123;
                                            } else {
                                                r27 = r7;
                                                r124 = r12;
                                                r117 = r14;
                                                str17 = str10;
                                                r123 = r19;
                                                r27 = i19;
                                                r124 = r115;
                                                str17 = str15;
                                                r123 = r114;
                                                r117.element = false;
                                                r26 = r2;
                                                r3 = r27;
                                                r12 = r124;
                                                r14 = r117;
                                                r19 = r123;
                                            }
                                        } catch (CancellationException e5) {
                                            throw e5;
                                        } catch (Throwable th17) {
                                            th = th17;
                                            r4 = i53;
                                            r121 = SYNC_FAILURE_TIME_KEY;
                                            r122 = j7;
                                            r116 = booleanRef10;
                                            mutex2 = mutex18;
                                            r1 = sharedPreferences12;
                                            break;
                                        }
                                        r28 = r26;
                                        r12 = jCurrentTimeMillis;
                                        r14 = booleanRef3;
                                        str3 = str23;
                                        mutex6 = mutex2;
                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                            z6 = true;
                                        } else {
                                            if (r3 != 0) {
                                                Edit = r28.edit();
                                                if (r14.element) {
                                                    Edit.remove(r19);
                                                } else {
                                                    Edit.putLong(r19, r12);
                                                }
                                                Edit.apply();
                                            }
                                            z6 = r14.element;
                                        }
                                        Boolean boolBoxBoolean8 = Boxing.boxBoolean(z6);
                                        mutex6.unlock(null);
                                        return boolBoxBoolean8;
                                    case 7:
                                        i24 = c01351.I$5;
                                        i32 = c01351.I$4;
                                        long j8 = c01351.J$0;
                                        response4 = (Response) c01351.L$6;
                                        booleanRef2 = (Ref.BooleanRef) c01351.L$2;
                                        mutex2 = (Mutex) c01351.L$1;
                                        sharedPreferences3 = (SharedPreferences) c01351.L$0;
                                        try {
                                            ResultKt.throwOnFailure(obj8);
                                            str20 = "profilesEtag";
                                            str12 = "lastSyncTimestamp";
                                            str13 = PROFILES_SYNC_TIME_KEY;
                                            r125 = SYNC_FAILURE_TIME_KEY;
                                            r22 = sharedPreferences3;
                                            i25 = i32;
                                            r126 = j8;
                                            r118 = booleanRef2;
                                            if (((Boolean) obj8).booleanValue()) {
                                                i26 = 0;
                                            } else {
                                                i26 = 0;
                                            }
                                            i17 = i26;
                                            response = response4;
                                            r2 = r22;
                                            r27 = i25;
                                            r124 = r126;
                                            r117 = r118;
                                            str17 = str20;
                                            r123 = r125;
                                            if (i17 != 0) {
                                                r27 = r7;
                                                r124 = r12;
                                                r117 = r14;
                                                str17 = str10;
                                                r123 = r19;
                                                r27 = i19;
                                                r124 = r115;
                                                str17 = str15;
                                                r123 = r114;
                                                Edit2 = r2.edit();
                                                Edit2.putString(str12, INSTANCE.syncWatermark(response));
                                                str18 = response.headers().get("ETag");
                                                str19 = str17;
                                                if (str18 != null) {
                                                    Edit2.putString(str19, str18);
                                                } else {
                                                    Edit2.remove(str19);
                                                }
                                                Edit2.putLong(str13, r124);
                                                Edit2.apply();
                                                r26 = r2;
                                                r3 = r27;
                                                r12 = r124;
                                                r14 = r117;
                                                r19 = r123;
                                            } else {
                                                r27 = r7;
                                                r124 = r12;
                                                r117 = r14;
                                                str17 = str10;
                                                r123 = r19;
                                                r27 = i19;
                                                r124 = r115;
                                                str17 = str15;
                                                r123 = r114;
                                                r117.element = false;
                                                r26 = r2;
                                                r3 = r27;
                                                r12 = r124;
                                                r14 = r117;
                                                r19 = r123;
                                            }
                                        } catch (Throwable th18) {
                                            th = th18;
                                            r121 = SYNC_FAILURE_TIME_KEY;
                                            r1 = sharedPreferences3;
                                            r4 = i32;
                                            r122 = j8;
                                            r116 = booleanRef2;
                                            break;
                                        }
                                        r28 = r26;
                                        r12 = jCurrentTimeMillis;
                                        r14 = booleanRef3;
                                        str3 = str23;
                                        mutex6 = mutex2;
                                        if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                            z6 = true;
                                        } else {
                                            if (r3 != 0) {
                                                Edit = r28.edit();
                                                if (r14.element) {
                                                    Edit.remove(r19);
                                                } else {
                                                    Edit.putLong(r19, r12);
                                                }
                                                Edit.apply();
                                            }
                                            z6 = r14.element;
                                        }
                                        Boolean boolBoxBoolean9 = Boxing.boxBoolean(z6);
                                        mutex6.unlock(null);
                                        return boolBoxBoolean9;
                                    default:
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        return null;
                                }
                            } catch (Throwable th19) {
                                th = th19;
                                r7 = str23;
                                r1 = r28;
                                r4 = r7;
                                r122 = r12;
                                r116 = r14;
                                r121 = r19;
                                FileLog.e(th);
                                r116.element = false;
                                r26 = r1;
                                r3 = r4;
                                r12 = r122;
                                r14 = r116;
                                r19 = r121;
                                r28 = r26;
                                r12 = jCurrentTimeMillis;
                                r14 = booleanRef3;
                                str3 = str23;
                                mutex6 = mutex2;
                                if (!ApiClient.INSTANCE.getRequestsEnabled$TMessagesProj()) {
                                    z6 = true;
                                } else {
                                    if (r3 != 0) {
                                        Edit = r28.edit();
                                        if (r14.element) {
                                            Edit.remove(r19);
                                        } else {
                                            Edit.putLong(r19, r12);
                                        }
                                        Edit.apply();
                                    }
                                    z6 = r14.element;
                                }
                                Boolean boolBoxBoolean10 = Boxing.boxBoolean(z6);
                                mutex6.unlock(null);
                                return boolBoxBoolean10;
                            }
                        } catch (CancellationException e6) {
                            throw e6;
                        }
                    } catch (Throwable th20) {
                        th = th20;
                        mutex6 = mutex5;
                        mutex6.unlock(null);
                        throw th;
                    }
                } catch (CancellationException e7) {
                    e = e7;
                } catch (Throwable th21) {
                    th = th21;
                    i5 = i32;
                    r5 = str23;
                    r8 = r28;
                }
            } catch (CancellationException e8) {
                e = e8;
            } catch (Throwable th22) {
                th = th22;
                r18 = SYNC_FAILURE_TIME_KEY;
                r8 = obj;
                mutex4 = mutex3;
                str = "profilesEtag";
                str2 = PROFILES_SYNC_TIME_KEY;
                r5 = obj2;
                str24 = "lastSyncTimestamp";
                i5 = i32;
            }
        } catch (CancellationException e9) {
            throw e9;
        } catch (Throwable th23) {
            th = th23;
            booleanRef2 = booleanRef;
            mutex2 = mutex;
            sharedPreferences3 = sharedPreferences2;
        }
    }

    private final String syncWatermark(Response<?> response) {
        String str = response.headers().get(SYNC_TIMESTAMP_HEADER);
        if (str != null) {
            try {
                return Instant.parse(str).toString();
            } catch (DateTimeParseException e) {
                FileLog.e(e);
            }
        }
        return Instant.now().toString();
    }

    public static void resetSyncState$default(SharedPreferences sharedPreferences, int i, Object obj) {
        if ((i & 1) != 0) {
            sharedPreferences = ExteraConfig.getPreferences();
        }
        resetSyncState(sharedPreferences);
    }

    @JvmStatic
    public static final void scheduleWorker() {
        try {
            WorkManager.INSTANCE.getInstance(ApplicationLoader.applicationContext).enqueueUniquePeriodicWork("api_sync_work", ExistingPeriodicWorkPolicy.UPDATE, new PeriodicWorkRequest.Builder(SyncWorker.class, 6L, TimeUnit.HOURS).setConstraints(new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).setRequiresBatteryNotLow(true).build()).setBackoffCriteria(BackoffPolicy.LINEAR, FAILURE_BACKOFF, TimeUnit.MILLISECONDS).build());
        } catch (Throwable th) {
            FileLog.e(th);
        }
    }
}
