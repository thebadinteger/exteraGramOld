package com.exteragram.messenger.plugins.analyzer;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;

@Metadata(d1 = {"\u0000¥\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b*\u00011\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004cdefB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020H0FJ\b\u0010I\u001a\u00020HH\u0002J\u0018\u0010J\u001a\u00020G2\u0006\u0010C\u001a\u00020D2\u0006\u0010K\u001a\u00020BH\u0002J\u0012\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010N\u001a\u00020=H\u0002J\u0016\u0010O\u001a\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u0005\u0018\u00010PH\u0002J\u0018\u0010Q\u001a\u00020H2\u0006\u0010R\u001a\u00020*2\u0006\u0010S\u001a\u00020\rH\u0002J\u0010\u0010T\u001a\u00020\r2\u0006\u0010U\u001a\u00020\u0005H\u0002J\u0018\u0010V\u001a\u00020H2\u0006\u0010R\u001a\u00020*2\u0006\u0010W\u001a\u00020\u001eH\u0002JL\u0010X\u001a\u0002HY\"\u0004\b\u0000\u0010Y2\u0006\u0010W\u001a\u00020\u001e2\u0006\u0010Z\u001a\u00020\u00052\u0012\u0010[\u001a\u000e\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020H0F2\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u0002HY0FH\u0082\b¢\u0006\u0002\u0010^J\n\u0010_\u001a\u0004\u0018\u00010\u001eH\u0002J\u0010\u0010`\u001a\u00020H2\u0006\u0010a\u001a\u00020#H\u0002J\b\u0010b\u001a\u00020HH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u0012\u0012\u0004\u0012\u00020#0\"j\b\u0012\u0004\u0012\u00020#`$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010+\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b,\u0010-R\u0010\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0004\n\u0002\u00102R\u0014\u00103\u001a\u0002048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010/\u001a\u0004\b9\u0010:R\u001d\u0010<\u001a\u0004\u0018\u00010=8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010/\u001a\u0004\b>\u0010?¨\u0006g"}, d2 = {"Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "STATUS_DONE", _UrlKt.FRAGMENT_ENCODE_SET, "STATUS_UNAVAILABLE", "STATUS_FAILED", "STATUS_TIMEOUT", "STATUS_CRASHED", "STATUS_TOO_LARGE", "STATUS_MEMORY", "CONNECT_TIMEOUT_MS", _UrlKt.FRAGMENT_ENCODE_SET, "HARD_TIMEOUT_MS", "PREVIOUS_TIMEOUT_MS", "WATCH_INTERVAL_MS", "MAX_FILE_BYTES", "MIB", "RECYCLE_ANON_BYTES", "MIN_ANON_LIMIT_BYTES", "MAX_ANON_LIMIT_BYTES", "ABORT_NONE", "ABORT_TIMEOUT", "ABORT_MEMORY", "ABORT_RELEASED", "queue", "Lorg/telegram/messenger/DispatchQueue;", "watchQueue", "binder", "Landroid/os/IBinder;", "connection", "Landroid/content/ServiceConnection;", "pendingConnects", "Ljava/util/ArrayList;", "Ljava/util/concurrent/CountDownLatch;", "Lkotlin/collections/ArrayList;", "liveTasks", "Ljava/util/concurrent/atomic/AtomicInteger;", "trimCallbacksRegistered", _UrlKt.FRAGMENT_ENCODE_SET, "running", "Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer$Running;", "anonLimitBytes", "getAnonLimitBytes", "()J", "anonLimitBytes$delegate", "Lkotlin/Lazy;", "trimCallbacks", "com/exteragram/messenger/plugins/analyzer/PluginAnalyzer$trimCallbacks$1", "Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer$trimCallbacks$1;", "snapshotDir", "Ljava/io/File;", "getSnapshotDir", "()Ljava/io/File;", "cache", "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerCache;", "getCache", "()Lcom/exteragram/messenger/plugins/analyzer/AnalyzerCache;", "cache$delegate", "buildId", _UrlKt.FRAGMENT_ENCODE_SET, "getBuildId", "()Ljava/lang/String;", "buildId$delegate", "analyze", "Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer$Task;", PluginsConstants.REQUEST, "Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer$Request;", Callback.METHOD_NAME, "Lkotlin/Function1;", "Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer$Result;", _UrlKt.FRAGMENT_ENCODE_SET, "onTaskReleased", "run", "task", "cachedReport", "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport;", "sha256", "connectRemote", "Lkotlin/Pair;", "watch", "current", "deadline", "anonBytes", "pid", "recycle", "remote", "call", "T", "code", "write", "Landroid/os/Parcel;", "read", "(Landroid/os/IBinder;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "obtainBinder", "connect", "latch", "unbind", "Request", "Result", "Task", "Running", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginAnalyzer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginAnalyzer.kt\ncom/exteragram/messenger/plugins/analyzer/PluginAnalyzer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,418:1\n337#1,12:420\n337#1,12:432\n1#2:419\n296#3,2:444\n1915#3,2:446\n1915#3,2:448\n*S KotlinDebug\n*F\n+ 1 PluginAnalyzer.kt\ncom/exteragram/messenger/plugins/analyzer/PluginAnalyzer\n*L\n226#1:420,12\n285#1:432,12\n313#1:444,2\n401#1:446,2\n410#1:448,2\n*E\n"})
public final class PluginAnalyzer {
    private static final int ABORT_MEMORY = 2;
    private static final int ABORT_NONE = 0;
    private static final int ABORT_RELEASED = 3;
    private static final int ABORT_TIMEOUT = 1;
    private static final long CONNECT_TIMEOUT_MS = 10000;
    private static final long HARD_TIMEOUT_MS = 45000;
    private static final long MAX_ANON_LIMIT_BYTES = 671088640;
    private static final long MAX_FILE_BYTES = 25165824;
    private static final long MIB = 1048576;
    private static final long MIN_ANON_LIMIT_BYTES = 268435456;
    private static final long PREVIOUS_TIMEOUT_MS = 35000;
    private static final long RECYCLE_ANON_BYTES = 167772160;
    public static final int STATUS_CRASHED = 4;
    public static final int STATUS_DONE = 0;
    public static final int STATUS_FAILED = 2;
    public static final int STATUS_MEMORY = 6;
    public static final int STATUS_TIMEOUT = 3;
    public static final int STATUS_TOO_LARGE = 5;
    public static final int STATUS_UNAVAILABLE = 1;
    private static final long WATCH_INTERVAL_MS = 250;
    private static volatile IBinder binder;
    private static ServiceConnection connection;
    private static volatile Running running;
    private static boolean trimCallbacksRegistered;
    public static final PluginAnalyzer INSTANCE = new PluginAnalyzer();
    private static final DispatchQueue queue = new DispatchQueue("pluginAnalyzer");
    private static final DispatchQueue watchQueue = new DispatchQueue("pluginAnalyzerWatch");
    private static final ArrayList<CountDownLatch> pendingConnects = new ArrayList<>();
    private static final AtomicInteger liveTasks = new AtomicInteger();

    private static final Lazy anonLimitBytes = LazyKt.lazy(new Function0() {
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Long.valueOf(PluginAnalyzer.$r8$lambda$4f9sf1qZGrcagmKYAefK2QEDaIo());
        }
    });
    private static final PluginAnalyzer$trimCallbacks$1 trimCallbacks = new ComponentCallbacks2() {
        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration newConfig) {
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int level) {
            PluginAnalyzer.Running running2;
            if ((level == 15 || level >= 40) && (running2 = PluginAnalyzer.running) != null) {
                running2.abort(2);
            }
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
            PluginAnalyzer.Running running2 = PluginAnalyzer.running;
            if (running2 != null) {
                running2.abort(2);
            }
        }
    };

    private static final Lazy cache = LazyKt.lazy(new Function0() {
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return PluginAnalyzer.$r8$lambda$4WI0bjV0RXOjdW18rEubqqYb2uc();
        }
    });

    private static final Lazy buildId = LazyKt.lazy(new Function0() {
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return AnalyzerHashesKt.analyzerBuildId(ApplicationLoader.applicationContext);
        }
    });

    private PluginAnalyzer() {
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer$Request;", _UrlKt.FRAGMENT_ENCODE_SET, "filePath", _UrlKt.FRAGMENT_ENCODE_SET, "name", "previousPath", "useCache", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getFilePath", "()Ljava/lang/String;", "getName", "getPreviousPath", "getUseCache", "()Z", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Request {
        private final String filePath;
        private final String name;
        private final String previousPath;
        private final boolean useCache;

        public Request(String str, String str2, String str3, boolean z) {
            this.filePath = str;
            this.name = str2;
            this.previousPath = str3;
            this.useCache = z;
        }

        public final String getFilePath() {
            return this.filePath;
        }

        public final String getName() {
            return this.name;
        }

        public final String getPreviousPath() {
            return this.previousPath;
        }

        public final boolean getUseCache() {
            return this.useCache;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer$Result;", _UrlKt.FRAGMENT_ENCODE_SET, "status", _UrlKt.FRAGMENT_ENCODE_SET, "snapshot", "Ljava/io/File;", "report", "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport;", "diff", "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerDiff;", "message", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(ILjava/io/File;Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport;Lcom/exteragram/messenger/plugins/analyzer/AnalyzerDiff;Ljava/lang/String;)V", "getStatus", "()I", "getSnapshot", "()Ljava/io/File;", "getReport", "()Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport;", "getDiff", "()Lcom/exteragram/messenger/plugins/analyzer/AnalyzerDiff;", "getMessage", "()Ljava/lang/String;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Result {
        private final AnalyzerDiff diff;
        private final String message;
        private final AnalyzerReport report;
        private final File snapshot;
        private final int status;

        public Result(int i, File file, AnalyzerReport analyzerReport, AnalyzerDiff analyzerDiff, String str) {
            this.status = i;
            this.snapshot = file;
            this.report = analyzerReport;
            this.diff = analyzerDiff;
            this.message = str;
        }

        public Result(int i, File file, AnalyzerReport analyzerReport, AnalyzerDiff analyzerDiff, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, file, (i2 & 4) != 0 ? null : analyzerReport, (i2 & 8) != 0 ? null : analyzerDiff, (i2 & 16) != 0 ? null : str);
        }

        public final int getStatus() {
            return this.status;
        }

        public final File getSnapshot() {
            return this.snapshot;
        }

        public final AnalyzerReport getReport() {
            return this.report;
        }

        public final AnalyzerDiff getDiff() {
            return this.diff;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u000b\u001a\u00020\fJ\r\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0002\b\u000eR\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer$Task;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "value", _UrlKt.FRAGMENT_ENCODE_SET, "cancelled", "getCancelled", "()Z", "released", "Ljava/util/concurrent/atomic/AtomicBoolean;", "cancel", _UrlKt.FRAGMENT_ENCODE_SET, "release", "release$TMessagesProj", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Task {
        private volatile boolean cancelled;
        private final AtomicBoolean released = new AtomicBoolean(false);

        public final boolean getCancelled() {
            return this.cancelled;
        }

        public final void cancel() {
            this.cancelled = true;
            release$TMessagesProj();
        }

        public final void release$TMessagesProj() {
            if (this.released.compareAndSet(false, true)) {
                PluginAnalyzer.INSTANCE.onTaskReleased();
            }
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/exteragram/messenger/plugins/analyzer/PluginAnalyzer$Running;", _UrlKt.FRAGMENT_ENCODE_SET, "pid", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "getPid", "()I", "abort", "Ljava/util/concurrent/atomic/AtomicInteger;", "getAbort", "()Ljava/util/concurrent/atomic/AtomicInteger;", _UrlKt.FRAGMENT_ENCODE_SET, "reason", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Running {
        private final AtomicInteger abort = new AtomicInteger(0);
        private final int pid;

        public Running(int i) {
            this.pid = i;
        }

        public final int getPid() {
            return this.pid;
        }

        public final AtomicInteger getAbort() {
            return this.abort;
        }

        public final void abort(int reason) {
            if (this.abort.compareAndSet(0, reason)) {
                Process.killProcess(this.pid);
            }
        }
    }

    public final long getAnonLimitBytes() {
        return ((Number) anonLimitBytes.getValue()).longValue();
    }

    public static long $r8$lambda$4f9sf1qZGrcagmKYAefK2QEDaIo() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        Object systemService = ApplicationLoader.applicationContext.getSystemService("activity");
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        if (activityManager != null) {
            activityManager.getMemoryInfo(memoryInfo);
        }
        return RangesKt.coerceIn(memoryInfo.totalMem / 8, MIN_ANON_LIMIT_BYTES, MAX_ANON_LIMIT_BYTES);
    }

    private final File getSnapshotDir() {
        return new File(ApplicationLoader.applicationContext.getCacheDir(), "plugin_analyzer");
    }

    public static AnalyzerCache $r8$lambda$4WI0bjV0RXOjdW18rEubqqYb2uc() {
        return new AnalyzerCache(new File(ApplicationLoader.applicationContext.getNoBackupFilesDir(), "plugin_analyzer/reports"));
    }

    private final AnalyzerCache getCache() {
        return (AnalyzerCache) cache.getValue();
    }

    private final String getBuildId() {
        return (String) buildId.getValue();
    }

    public final Task analyze(final Request request, final Function1<? super Result, Unit> callback) {
        final Task task = new Task();
        liveTasks.incrementAndGet();
        queue.postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                PluginAnalyzer.$r8$lambda$tZrl5AYmjOT7reFxmq097i_pi8k(request, task, callback);
            }
        });
        return task;
    }

    public static void $r8$lambda$tZrl5AYmjOT7reFxmq097i_pi8k(Request request, final Task task, final Function1 function1) throws Throwable {
        final Result resultRun = INSTANCE.run(request, task);
        if (task.getCancelled()) {
            File snapshot = resultRun.getSnapshot();
            if (snapshot != null) {
                snapshot.delete();
                return;
            }
            return;
        }
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginAnalyzer.m1888$r8$lambda$yBxVuzHKfu2pHrdxPw1qNbK8yc(task, resultRun, function1);
            }
        });
    }

    public static void m1888$r8$lambda$yBxVuzHKfu2pHrdxPw1qNbK8yc(Task task, Result result, Function1 function1) {
        if (task.getCancelled()) {
            File snapshot = result.getSnapshot();
            if (snapshot != null) {
                snapshot.delete();
                return;
            }
            return;
        }
        task.release$TMessagesProj();
        function1.invoke(result);
    }

    public final void onTaskReleased() {
        if (liveTasks.decrementAndGet() > 0) {
            return;
        }
        Running running2 = running;
        if (running2 != null) {
            running2.abort(3);
        }
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginAnalyzer.$r8$lambda$d6nJO7SAB2XRMOuzhmx3aXWiYKw();
            }
        });
    }

    public static void $r8$lambda$d6nJO7SAB2XRMOuzhmx3aXWiYKw() {
        if (liveTasks.get() == 0) {
            INSTANCE.unbind();
        }
    }

    private final Result run(Request request, Task task) throws Throwable {
        ?? r6;
        String strSha256Hex;
        Running running2;
        ?? r10;
        ?? r11;
        Running running3;
        ?? r19;
        ?? r7;
        AtomicInteger abort;
        Integer numValueOf;
        ?? r17;
        Parcel parcel;
        Running running4;
        int i;
        ?? r3;
        Running running5;
        Parcel parcel2;
        Result result;
        int i2;
        File file;
        AnalyzerReport analyzerReportCachedReport;
        File file2 = new File(request.getFilePath());
        if (!file2.isFile()) {
            return new Result(2, null, null, null, "file not found", 12, null);
        }
        if (file2.length() > MAX_FILE_BYTES) {
            return new Result(5, null, null, null, null, 28, null);
        }
        try {
            byte[] bytes = FilesKt.readBytes(file2);
            ?? Sha256Hex = AnalyzerHashesKt.sha256Hex(bytes);
            getSnapshotDir().mkdirs();
            File snapshotDir = getSnapshotDir();
            String extension = FilesKt.getExtension(file2);
            if (extension.length() == 0) {
                extension = "plugin";
            }
            File file3 = new File(snapshotDir, Sha256Hex + "." + extension);
            if (!file3.isFile() || file3.length() != bytes.length) {
                FilesKt.writeBytes(file3, bytes);
            }
            if (task.getCancelled()) {
                return new Result(2, file3, null, null, null, 28, null);
            }
            if (request.getUseCache() && request.getPreviousPath() == null && (analyzerReportCachedReport = cachedReport(Sha256Hex)) != null) {
                return new Result(0, file3, analyzerReportCachedReport, null, null, 24, null);
            }
            String previousPath = request.getPreviousPath();
            int i3 = 0;
            if (previousPath != null) {
                file = new File(previousPath);
                if (!file.isFile() || file.length() > MAX_FILE_BYTES) {
                    r6 = file;
                    r6 = 0;
                }
            } else {
                r6 = file;
                r6 = 0;
            }
            if (r6 != 0) {
                try {
                    byte[] bytes2 = FilesKt.readBytes(r6);
                    if (bytes2 != null) {
                        strSha256Hex = AnalyzerHashesKt.sha256Hex(bytes2);
                    } else {
                        strSha256Hex = null;
                    }
                } catch (Exception unused) {
                }
            } else {
                strSha256Hex = null;
            }
            int i4 = 3;
            try {
                try {
                    Pair<IBinder, Integer> pairConnectRemote = connectRemote();
                    if (pairConnectRemote == null) {
                        try {
                            i2 = 1;
                            try {
                                Result result2 = new Result(4, file3, null, null, "service did not start", 12, null);
                                running = null;
                                return result2;
                            } catch (RemoteException e) {
                                e = e;
                                r6 = 0;
                                r19 = 0;
                                i3 = i2;
                                Sha256Hex = 0;
                                binder = r19;
                                if (r6 != 0) {
                                    r7 = r19;
                                } else {
                                    r7 = r19;
                                }
                                if (r7 != 0) {
                                }
                                if (r7 != 0) {
                                    if (r7.intValue() == 2) {
                                        r7 = numValueOf;
                                        i4 = 6;
                                    }
                                    r7 = numValueOf;
                                    Result result3 = new Result(i4, file3, null, null, e.toString(), 12, null);
                                    running = r19;
                                    if (r6 != 0) {
                                        recycle(r6, Sha256Hex);
                                    }
                                    return result3;
                                }
                                r7 = numValueOf;
                                r7 = numValueOf;
                                i4 = 4;
                                r7 = numValueOf;
                                Result result4 = new Result(i4, file3, null, null, e.toString(), 12, null);
                                running = r19;
                                if (r6 != 0) {
                                    recycle(r6, Sha256Hex);
                                }
                                return result4;
                            } catch (Exception e2) {
                                e = e2;
                                r6 = 0;
                                running3 = null;
                                i3 = i2;
                                Sha256Hex = 0;
                                FileLog.e(e);
                                Result result5 = new Result(2, file3, null, null, e.toString(), 12, null);
                                running = running3;
                                if (r6 != 0) {
                                    recycle(r6, Sha256Hex);
                                }
                                return result5;
                            } catch (Throwable th) {
                                th = th;
                                r10 = 0;
                                r11 = 0;
                                running2 = null;
                                i3 = i2;
                                running = running2;
                                if (r11 != 0) {
                                    recycle(r11, r10);
                                }
                                throw th;
                            }
                        } catch (RemoteException e3) {
                            e = e3;
                            i2 = 1;
                        } catch (Exception e4) {
                            e = e4;
                            i2 = 1;
                        } catch (Throwable th2) {
                            th = th2;
                            i2 = 1;
                        }
                    } else {
                        IBinder iBinderComponent1 = pairConnectRemote.component1();
                        try {
                            Running running6 = new Running(pairConnectRemote.component2().intValue());
                            try {
                                try {
                                    running = running6;
                                    if (task.getCancelled()) {
                                        try {
                                            running6.abort(3);
                                        } catch (Throwable th3) {
                                            th = th3;
                                            running2 = null;
                                            i3 = 1;
                                            r10 = iBinderComponent1;
                                            r11 = running6;
                                            running = running2;
                                            if (r11 != 0 && r10 != 0 && r11.getAbort().get() == 0 && liveTasks.get() > i3 && anonBytes(r11.getPid()) > RECYCLE_ANON_BYTES) {
                                                recycle(r11, r10);
                                            }
                                            throw th;
                                        }
                                    }
                                    long jCurrentTimeMillis = System.currentTimeMillis() + HARD_TIMEOUT_MS;
                                    long j = strSha256Hex != null ? PREVIOUS_TIMEOUT_MS : 0L;
                                    try {
                                        watch(running6, jCurrentTimeMillis + j);
                                        ?? Obtain = Parcel.obtain();
                                        Parcel parcelObtain = Parcel.obtain();
                                        try {
                                            Obtain.writeInterfaceToken(PluginAnalyzerService.DESCRIPTOR);
                                            Obtain.writeString(file3.getAbsolutePath());
                                            Obtain.writeString(request.getName());
                                            Obtain.writeString(Sha256Hex);
                                            Obtain.writeInt(request.getUseCache() ? 1 : 0);
                                            Obtain.writeString((strSha256Hex == null || r6 == 0) ? null : r6.getAbsolutePath());
                                            Obtain.writeString(r6 != 0 ? r6.getName() : null);
                                            Obtain.writeString(strSha256Hex);
                                            try {
                                                if (!iBinderComponent1.transact(2, Obtain, parcelObtain, 0)) {
                                                    throw new RemoteException("transaction rejected");
                                                }
                                                int i5 = parcelObtain.readInt();
                                                if (i5 == 0) {
                                                    running4 = null;
                                                    i = 1;
                                                    r3 = iBinderComponent1;
                                                    running5 = running6;
                                                    parcel2 = parcelObtain;
                                                    parcel2.readString();
                                                    String string = parcel2.readString();
                                                    String string2 = parcel2.readString();
                                                    result = new Result(0, file3, string != null ? AnalyzerReport.INSTANCE.parse(string) : null, string2 != null ? AnalyzerDiff.INSTANCE.parse(string2) : null, null, 16, null);
                                                } else if (i5 != 1) {
                                                    try {
                                                        try {
                                                            running4 = null;
                                                            i = 1;
                                                            r3 = iBinderComponent1;
                                                            parcel2 = parcelObtain;
                                                            running5 = running6;
                                                            result = new Result(2, file3, null, null, parcelObtain.readString(), 12, null);
                                                        } catch (Throwable th4) {
                                                            th = th4;
                                                            parcel = parcelObtain;
                                                            r17 = Obtain;
                                                        }
                                                    } catch (Throwable th5) {
                                                        th = th5;
                                                        r17 = Obtain;
                                                        parcel = parcelObtain;
                                                    }
                                                } else {
                                                    running4 = null;
                                                    i = 1;
                                                    r3 = iBinderComponent1;
                                                    running5 = running6;
                                                    parcel2 = parcelObtain;
                                                    result = new Result(1, file3, null, null, parcel2.readString(), 12, null);
                                                }
                                                Obtain.recycle();
                                                parcel2.recycle();
                                                running = running4;
                                                if (running5.getAbort().get() == 0 && liveTasks.get() > i && anonBytes(running5.getPid()) > RECYCLE_ANON_BYTES) {
                                                    recycle(running5, r3);
                                                }
                                                return result;
                                            } catch (Throwable th6) {
                                                th = th6;
                                                r17 = j;
                                            }
                                        } catch (Throwable th7) {
                                            th = th7;
                                        }
                                        r17 = Obtain;
                                        parcel = parcelObtain;
                                        r17.recycle();
                                        parcel.recycle();
                                        throw th;
                                    } catch (RemoteException e5) {
                                        e = e5;
                                    } catch (Exception e6) {
                                        e = e6;
                                        FileLog.e(e);
                                        Result result6 = new Result(2, file3, null, null, e.toString(), 12, null);
                                        running = running3;
                                        if (r6 != 0 && Sha256Hex != 0 && r6.getAbort().get() == 0 && liveTasks.get() > i3 && anonBytes(r6.getPid()) > RECYCLE_ANON_BYTES) {
                                            recycle(r6, Sha256Hex);
                                        }
                                        return result6;
                                    }
                                } catch (Throwable th8) {
                                    th = th8;
                                    running2 = null;
                                    i3 = 1;
                                    r10 = iBinderComponent1;
                                    r11 = running6;
                                }
                            } catch (RemoteException e7) {
                                e = e7;
                                r19 = 0;
                                i3 = 1;
                                Sha256Hex = iBinderComponent1;
                                r6 = running6;
                            } catch (Exception e8) {
                                e = e8;
                                running3 = null;
                                i3 = 1;
                                Sha256Hex = iBinderComponent1;
                                r6 = running6;
                            }
                        } catch (RemoteException e9) {
                            e = e9;
                            r19 = 0;
                            i3 = 1;
                            Sha256Hex = iBinderComponent1;
                            r6 = 0;
                        } catch (Exception e10) {
                            e = e10;
                            running3 = null;
                            i3 = 1;
                            Sha256Hex = iBinderComponent1;
                            r6 = 0;
                        } catch (Throwable th9) {
                            th = th9;
                            running2 = null;
                            i3 = 1;
                            r11 = 0;
                            r10 = iBinderComponent1;
                        }
                    }
                } catch (Throwable th10) {
                    th = th10;
                    r10 = Sha256Hex;
                    r11 = r6;
                }
            } catch (RemoteException e11) {
                e = e11;
                r19 = 0;
                i3 = 1;
                Sha256Hex = 0;
                r6 = 0;
            } catch (Exception e12) {
                e = e12;
                running3 = null;
                i3 = 1;
                Sha256Hex = 0;
                r6 = 0;
            } catch (Throwable th11) {
                th = th11;
                running2 = null;
                i3 = 1;
                r10 = 0;
                r11 = 0;
            }
            binder = r19;
            if (r6 != 0 || (abort = r6.getAbort()) == null) {
                r7 = r19;
            } else {
                numValueOf = Integer.valueOf(abort.get());
            }
            if (r7 != 0 || r7.intValue() != i3) {
                if (r7 != 0) {
                    r7 = numValueOf;
                } else if (r7.intValue() == 2) {
                    r7 = numValueOf;
                    i4 = 6;
                }
                r7 = numValueOf;
                i4 = 4;
            }
            r7 = numValueOf;
            Result result7 = new Result(i4, file3, null, null, e.toString(), 12, null);
            running = r19;
            if (r6 != 0 && Sha256Hex != 0 && r6.getAbort().get() == 0 && liveTasks.get() > i3 && anonBytes(r6.getPid()) > RECYCLE_ANON_BYTES) {
                recycle(r6, Sha256Hex);
            }
            return result7;
        } catch (Exception e13) {
            FileLog.e(e13);
            return new Result(2, null, null, null, e13.toString(), 12, null);
        }
    }

    private final AnalyzerReport cachedReport(String sha256) {
        String str;
        String buildId2 = getBuildId();
        if (buildId2 != null && (str = INSTANCE.getCache().get(buildId2, sha256)) != null) {
            try {
                return AnalyzerReport.INSTANCE.parse(str);
            } catch (Exception e) {
                FileLog.e(e);
            }
        }
        return null;
    }

    private final Pair<IBinder, Integer> connectRemote() {
        IBinder iBinderObtainBinder;
        for (int i = 0; i < 2 && (iBinderObtainBinder = INSTANCE.obtainBinder()) != null; i++) {
            try {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    continue;
                    parcelObtain.writeInterfaceToken(PluginAnalyzerService.DESCRIPTOR);
                    if (iBinderObtainBinder.transact(1, parcelObtain, parcelObtain2, 0)) {
                        Integer numValueOf = Integer.valueOf(parcelObtain2.readInt());
                        parcelObtain.recycle();
                        parcelObtain2.recycle();
                        return TuplesKt.to(iBinderObtainBinder, numValueOf);
                    }
                    throw new RemoteException("transaction rejected");
                } catch (Throwable th) {
                    parcelObtain.recycle();
                    parcelObtain2.recycle();
                    throw th;
                }
            } catch (RemoteException unused) {
                binder = null;
            }
        }
        return null;
    }

    private final void watch(final Running current, final long deadline) {
        watchQueue.postRunnable(new Runnable() {
            @Override // java.lang.Runnable
            public void run() {
                Running running2 = PluginAnalyzer.running;
                Running running3 = current;
                if (running2 == running3 && running3.getAbort().get() == 0) {
                    if (System.currentTimeMillis() >= deadline) {
                        current.abort(1);
                        return;
                    }
                    PluginAnalyzer pluginAnalyzer = PluginAnalyzer.INSTANCE;
                    if (pluginAnalyzer.anonBytes(current.getPid()) <= pluginAnalyzer.getAnonLimitBytes()) {
                        PluginAnalyzer.watchQueue.postRunnable(this, PluginAnalyzer.WATCH_INTERVAL_MS);
                    } else {
                        current.abort(2);
                    }
                }
            }
        }, WATCH_INTERVAL_MS);
    }

    public final long anonBytes(int pid) {
        Object next;
        String strSubstringAfter$default;
        String string;
        String strSubstringBefore$default;
        Long longOrNull;
        try {
            Iterator it = FilesKt.readLines$default(new File("/proc/" + pid + "/status"), null, 1, null).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!StringsKt.startsWith$default((String) next, "RssAnon:", false, 2, (Object) null));
            String str = (String) next;
            if (str != null && (strSubstringAfter$default = StringsKt.substringAfter$default(str, ':', (String) null, 2, (Object) null)) != null && (string = StringsKt.trim((CharSequence) strSubstringAfter$default).toString()) != null && (strSubstringBefore$default = StringsKt.substringBefore$default(string, ' ', (String) null, 2, (Object) null)) != null && (longOrNull = StringsKt.toLongOrNull(strSubstringBefore$default)) != null) {
                return longOrNull.longValue() * 1024;
            }
        } catch (Exception unused) {
        }
        return 0L;
    }

    private final void recycle(Running current, IBinder remote) throws InterruptedException {
        binder = null;
        Process.killProcess(current.getPid());
        long jCurrentTimeMillis = System.currentTimeMillis() + 10000;
        while (remote.isBinderAlive() && System.currentTimeMillis() < jCurrentTimeMillis) {
            Thread.sleep(20L);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginAnalyzer.m1886$r8$lambda$5ABcGGZozFcJOmAvVTXqumZxvY(countDownLatch);
            }
        });
        countDownLatch.await(10000L, TimeUnit.MILLISECONDS);
    }

    public static void m1886$r8$lambda$5ABcGGZozFcJOmAvVTXqumZxvY(CountDownLatch countDownLatch) {
        INSTANCE.unbind();
        countDownLatch.countDown();
    }

    private final <T> T call(IBinder remote, int code, Function1<? super Parcel, Unit> write, Function1<? super Parcel, ? extends T> read) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken(PluginAnalyzerService.DESCRIPTOR);
            write.invoke(parcelObtain);
            if (!remote.transact(code, parcelObtain, parcelObtain2, 0)) {
                throw new RemoteException("transaction rejected");
            }
            T tInvoke = read.invoke(parcelObtain2);
            InlineMarker.finallyStart(1);
            parcelObtain.recycle();
            parcelObtain2.recycle();
            InlineMarker.finallyEnd(1);
            return tInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            parcelObtain.recycle();
            parcelObtain2.recycle();
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    private final IBinder obtainBinder() throws InterruptedException {
        IBinder iBinder = binder;
        if (iBinder != null) {
            if (!iBinder.isBinderAlive()) {
                iBinder = null;
            }
            if (iBinder != null) {
                return iBinder;
            }
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginAnalyzer.INSTANCE.connect(countDownLatch);
            }
        });
        countDownLatch.await(10000L, TimeUnit.MILLISECONDS);
        IBinder iBinder2 = binder;
        if (iBinder2 == null || !iBinder2.isBinderAlive()) {
            return null;
        }
        return iBinder2;
    }

    public final void connect(CountDownLatch latch) {
        boolean zBindService;
        if (!trimCallbacksRegistered) {
            trimCallbacksRegistered = true;
            ApplicationLoader.applicationContext.registerComponentCallbacks(trimCallbacks);
        }
        IBinder iBinder = binder;
        if (iBinder != null && iBinder.isBinderAlive()) {
            latch.countDown();
            return;
        }
        pendingConnects.add(latch);
        if (connection != null) {
            return;
        }
        Context context = ApplicationLoader.applicationContext;
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                PluginAnalyzer.binder = service;
                ArrayList arrayList = PluginAnalyzer.pendingConnects;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    ((CountDownLatch) obj).countDown();
                }
                PluginAnalyzer.pendingConnects.clear();
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                PluginAnalyzer.binder = null;
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                PluginAnalyzer.binder = null;
                if (PluginAnalyzer.connection == this) {
                    PluginAnalyzer.INSTANCE.unbind();
                }
            }
        };
        int i = 0;
        try {
            zBindService = context.bindService(new Intent(context, (Class<?>) PluginAnalyzerService.class), serviceConnection, 1);
        } catch (Exception e) {
            FileLog.e(e);
            zBindService = false;
        }
        if (zBindService) {
            connection = serviceConnection;
            return;
        }
        ArrayList<CountDownLatch> arrayList = pendingConnects;
        int size = arrayList.size();
        while (i < size) {
            CountDownLatch countDownLatch = arrayList.get(i);
            i++;
            countDownLatch.countDown();
        }
        pendingConnects.clear();
    }

    public final void unbind() {
        ServiceConnection serviceConnection = connection;
        if (serviceConnection == null) {
            return;
        }
        connection = null;
        binder = null;
        ArrayList<CountDownLatch> arrayList = pendingConnects;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            CountDownLatch countDownLatch = arrayList.get(i);
            i++;
            countDownLatch.countDown();
        }
        pendingConnects.clear();
        try {
            ApplicationLoader.applicationContext.unbindService(serviceConnection);
        } catch (Exception unused) {
        }
    }
}
