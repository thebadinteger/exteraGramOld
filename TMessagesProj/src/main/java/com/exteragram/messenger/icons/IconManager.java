package com.exteragram.messenger.icons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.collection.LruCache;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.p002activity.result.PickVisualMediaRequestKt;
import androidx.p002activity.result.contract.ActivityResultContracts$PickVisualMedia;
import com.caverock.androidsvg.SVG;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.IconPackType;
import com.exteragram.messenger.icons.ui.components.InstallIconPackBottomSheet;
import com.exteragram.messenger.icons.ui.components.ReplaceIconBottomSheet;
import com.exteragram.messenger.icons.ui.picker.IconPickerController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.internal.url._UrlKt;
import org.simplifiles.SimpliFiles;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.LaunchActivity;

@Metadata(d1 = {"\u0000®\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0002©\u0001\bÆ\u0002\u0018\u00002\u00020\u0001:\bÂ\u0001Ã\u0001Ä\u0001Å\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\tJ/\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J?\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001d\u0010\u001e\u001a\u00020\u00192\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001cH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0012H\u0002¢\u0006\u0004\b \u0010\u0003J\u000f\u0010!\u001a\u00020\u0012H\u0002¢\u0006\u0004\b!\u0010\u0003J%\u0010$\u001a\u00020#2\u0006\u0010\u0015\u001a\u00020\u00072\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\u001cH\u0002¢\u0006\u0004\b$\u0010%J!\u0010(\u001a\u0004\u0018\u00010'2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010&\u001a\u00020\fH\u0002¢\u0006\u0004\b(\u0010)J\u0017\u0010*\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0007H\u0002¢\u0006\u0004\b*\u0010+J)\u0010,\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b,\u0010-JM\u00103\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\f\u00100\u001a\b\u0018\u00010.R\u00020/2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\f2\b\b\u0002\u00102\u001a\u00020\u0019H\u0002¢\u0006\u0004\b3\u00104J1\u00108\u001a\u0004\u0018\u00010\u00172\u0006\u00105\u001a\u00020\f2\u0006\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¢\u0006\u0004\b8\u00109J\u000f\u0010;\u001a\u00020:H\u0007¢\u0006\u0004\b;\u0010<J\u000f\u0010>\u001a\u00020=H\u0007¢\u0006\u0004\b>\u0010?J\u0017\u0010B\u001a\u00020\f2\u0006\u0010A\u001a\u00020@H\u0002¢\u0006\u0004\bB\u0010CJ\u001f\u0010F\u001a\u00020\u00122\u0006\u0010E\u001a\u00020D2\u0006\u0010A\u001a\u00020@H\u0002¢\u0006\u0004\bF\u0010GJ\u0015\u0010I\u001a\u00020\u00192\u0006\u0010H\u001a\u00020\f¢\u0006\u0004\bI\u0010JJ\r\u0010K\u001a\u00020\u0012¢\u0006\u0004\bK\u0010\u0003J-\u0010M\u001a\u0004\u0018\u00010L2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\f\u00100\u001a\b\u0018\u00010.R\u00020/¢\u0006\u0004\bM\u0010NJ\u0017\u0010O\u001a\u0004\u0018\u00010L2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\bO\u0010PJ\u001f\u0010Q\u001a\u0004\u0018\u00010L2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\bQ\u0010RJ\u001f\u0010S\u001a\u0004\u0018\u00010L2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\bS\u0010RJ+\u0010V\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010U\u001a\b\u0012\u0004\u0012\u00020L0T¢\u0006\u0004\bV\u0010WJ#\u0010X\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010U\u001a\b\u0012\u0004\u0012\u00020L0T¢\u0006\u0004\bX\u0010YJ+\u0010[\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010Z\u001a\u00020\u00042\f\u0010U\u001a\b\u0012\u0004\u0012\u00020L0T¢\u0006\u0004\b[\u0010\\J5\u0010^\u001a\u0004\u0018\u00010\u00172\u0006\u00105\u001a\u00020\f2\u0006\u0010]\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\f\u00100\u001a\b\u0018\u00010.R\u00020/¢\u0006\u0004\b^\u0010_J/\u0010b\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010`\u001a\u00020'2\b\u0010a\u001a\u0004\u0018\u00010\f¢\u0006\u0004\bb\u0010cJ\u001d\u0010d\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\bd\u0010\u0014J\u0015\u0010e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\be\u0010fJ\u0017\u0010h\u001a\u00020\u00122\b\b\u0002\u0010g\u001a\u00020\u0019¢\u0006\u0004\bh\u0010iJ\r\u0010j\u001a\u00020\u0012¢\u0006\u0004\bj\u0010\u0003J\u0017\u0010k\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\f¢\u0006\u0004\bk\u0010lJ\u0017\u0010m\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\f¢\u0006\u0004\bm\u0010nJ\u0017\u0010o\u001a\u0004\u0018\u00010'2\u0006\u0010\u0011\u001a\u00020\f¢\u0006\u0004\bo\u0010pJ\u0015\u0010r\u001a\u00020\u00192\u0006\u0010q\u001a\u00020\n¢\u0006\u0004\br\u0010sJ\u0015\u0010t\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\f¢\u0006\u0004\bt\u0010lJ\u001f\u0010w\u001a\u00020\u00192\u0006\u00105\u001a\u00020\f2\b\u0010v\u001a\u0004\u0018\u00010u¢\u0006\u0004\bw\u0010xJ\u001d\u0010y\u001a\u00020\u00122\u0006\u0010E\u001a\u00020D2\u0006\u00105\u001a\u00020\f¢\u0006\u0004\by\u0010zJ(\u0010\u007f\u001a\u00020\u00192\u0006\u0010{\u001a\u00020\u00042\u0006\u0010|\u001a\u00020\u00042\b\u0010~\u001a\u0004\u0018\u00010}¢\u0006\u0005\b\u007f\u0010\u0080\u0001J;\u0010\u0086\u0001\u001a\u00020\u00122\b\u0010\u0082\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0083\u0001\u001a\u00020\u00192\u0016\u0010U\u001a\u0012\u0012\u0007\u0012\u0005\u0018\u00010\u0085\u0001\u0012\u0004\u0012\u00020\u00120\u0084\u0001¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J0\u0010\u008a\u0001\u001a\u00020\u00122\b\u0010\u0089\u0001\u001a\u00030\u0088\u00012\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010q\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001J\u001a\u0010\u008e\u0001\u001a\u00020\u00192\b\u0010\u008d\u0001\u001a\u00030\u008c\u0001¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0018\u0010\u0091\u0001\u001a\u00030\u0090\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0091\u0001\u0010\u0092\u0001R\u0018\u0010\u0094\u0001\u001a\u00030\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0018\u0010\u0096\u0001\u001a\u00030\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0095\u0001R\u0018\u0010\u0097\u0001\u001a\u00030\u0093\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0095\u0001R\u001e\u0010\u0099\u0001\u001a\t\u0012\u0004\u0012\u00020\f0\u0098\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0099\u0001\u0010\u009a\u0001R)\u0010\u009c\u0001\u001a\u000f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00040\u009b\u00018\u0006¢\u0006\u0010\n\u0006\b\u009c\u0001\u0010\u009d\u0001\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R\u001c\u0010¡\u0001\u001a\u0005\u0018\u00010 \u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¡\u0001\u0010¢\u0001R\u0017\u0010£\u0001\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\b\n\u0006\b£\u0001\u0010¤\u0001R\u0017\u0010¥\u0001\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¥\u0001\u0010¤\u0001R\u0018\u0010§\u0001\u001a\u00030¦\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b§\u0001\u0010¨\u0001R\u0018\u0010ª\u0001\u001a\u00030©\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bª\u0001\u0010«\u0001R\u001e\u0010\u00ad\u0001\u001a\t\u0012\u0004\u0012\u00020\n0¬\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u00ad\u0001\u0010®\u0001R$\u0010¯\u0001\u001a\u000f\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n0\u009b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¯\u0001\u0010\u009d\u0001R\u001c\u0010±\u0001\u001a\u0005\u0018\u00010°\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b±\u0001\u0010²\u0001R\u001c\u0010´\u0001\u001a\u0005\u0018\u00010³\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b´\u0001\u0010µ\u0001R\u001c\u0010¶\u0001\u001a\u0005\u0018\u00010³\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¶\u0001\u0010µ\u0001R\u0018\u0010¸\u0001\u001a\u00030·\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¸\u0001\u0010¹\u0001R\u0019\u0010º\u0001\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bº\u0001\u0010»\u0001R\u0019\u0010¼\u0001\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¼\u0001\u0010»\u0001R\u0019\u0010½\u0001\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b½\u0001\u0010¾\u0001R.\u0010À\u0001\u001a\u0019\u0012\u0014\u0012\u0012\u0012\u0007\u0012\u0005\u0018\u00010\u0085\u0001\u0012\u0004\u0012\u00020\u00120\u0084\u00010¿\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÀ\u0001\u0010Á\u0001¨\u0006Æ\u0001"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "resId", "density", _UrlKt.FRAGMENT_ENCODE_SET, "resolvedCacheKey", "(II)J", "Lcom/exteragram/messenger/icons/IconPack;", "pack", _UrlKt.FRAGMENT_ENCODE_SET, "fileName", "Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;", "sourceCacheKey", "(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;II)Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;", "packId", _UrlKt.FRAGMENT_ENCODE_SET, "invalidateIconCaches", "(Ljava/lang/String;I)V", "generation", "resourceName", "Landroid/graphics/Bitmap;", "bitmap", _UrlKt.FRAGMENT_ENCODE_SET, "publishBitmap", "(JLjava/lang/String;Lcom/exteragram/messenger/icons/IconPack;IILandroid/graphics/Bitmap;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "packs", "syncInstalledCustomPacks", "(Ljava/util/List;)Z", "rebuildOwnerMap", "rebuildBasePreinstalledMap", "newActivePacks", "Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;", "updateActivePacks", "(JLjava/util/List;)Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;", "iconFileName", "Ljava/io/File;", "resolvePackIconFile", "(Lcom/exteragram/messenger/icons/IconPack;Ljava/lang/String;)Ljava/io/File;", "launchPrewarm", "(J)V", "cachedPackIconBitmap", "(Lcom/exteragram/messenger/icons/IconPack;II)Landroid/graphics/Bitmap;", "Landroid/content/res/Resources$Theme;", "Landroid/content/res/Resources;", "theme", "knownResourceName", "cacheResult", "getPackIconBitmap", "(Lcom/exteragram/messenger/icons/IconPack;IILandroid/content/res/Resources$Theme;Ljava/lang/String;Z)Landroid/graphics/Bitmap;", "path", "targetW", "targetH", "decodeIconFile", "(Ljava/lang/String;III)Landroid/graphics/Bitmap;", "Landroidx/core/graphics/drawable/IconCompat;", "getNotificationIcon", "()Landroidx/core/graphics/drawable/IconCompat;", "Landroid/graphics/drawable/Icon;", "getNotificationSystemIcon", "()Landroid/graphics/drawable/Icon;", "Lcom/exteragram/messenger/icons/IconPackStorageError;", "error", "iconPackErrorText", "(Lcom/exteragram/messenger/icons/IconPackStorageError;)Ljava/lang/String;", "Lorg/telegram/ui/ActionBar/BaseFragment;", "baseFragment", "showIconPackError", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/icons/IconPackStorageError;)V", "name", "isBlacklisted", "(Ljava/lang/String;)Z", "prefetchCustomPacks", "Landroid/graphics/drawable/Drawable;", "getDrawable", "(IILandroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;", "getCachedDrawable", "(I)Landroid/graphics/drawable/Drawable;", "getPackIconDrawable", "(Lcom/exteragram/messenger/icons/IconPack;I)Landroid/graphics/drawable/Drawable;", "getCachedPackIconDrawable", "Lorg/telegram/messenger/Utilities$Callback;", Callback.METHOD_NAME, "requestPackIconDrawable", "(Lcom/exteragram/messenger/icons/IconPack;ILorg/telegram/messenger/Utilities$Callback;)V", "requestDrawable", "(ILorg/telegram/messenger/Utilities$Callback;)V", "sizePx", "requestDrawableForSize", "(IILorg/telegram/messenger/Utilities$Callback;)V", "originalResId", "createBitmapFromFile", "(Ljava/lang/String;IILandroid/content/res/Resources$Theme;)Landroid/graphics/Bitmap;", "tempFile", "originalName", "saveCustomIcon", "(Ljava/lang/String;ILjava/io/File;Ljava/lang/String;)V", "resetCustomIcon", "getIcon", "(I)I", PluginsConstants.UPDATE, "initialize", "(Z)V", "awaitInitialization", "setActiveCustomPack", "(Ljava/lang/String;)V", "findPackById", "(Ljava/lang/String;)Lcom/exteragram/messenger/icons/IconPack;", "bundlePackBlocking", "(Ljava/lang/String;)Ljava/io/File;", "iconPack", "saveIconPackMetadata", "(Lcom/exteragram/messenger/icons/IconPack;)Z", "deletePack", "Lorg/telegram/messenger/MessageObject;", "messageObject", "isIconPack", "(Ljava/lang/String;Lorg/telegram/messenger/MessageObject;)Z", "handleIconPack", "(Lorg/telegram/ui/ActionBar/BaseFragment;Ljava/lang/String;)V", "requestCode", "resultCode", "Landroid/content/Intent;", "data", "onActivityResult", "(IILandroid/content/Intent;)Z", "Landroid/app/Activity;", "activity", "selectFromFiles", "Lkotlin/Function1;", "Landroid/net/Uri;", "startIconPicker", "(Landroid/app/Activity;ZLkotlin/jvm/functions/Function1;)V", "Landroid/content/Context;", "context", "showReplaceAlert", "(Landroid/content/Context;ILcom/exteragram/messenger/icons/IconPack;)V", "Lcom/exteragram/messenger/IconPackType;", "basePackType", "isBasePackOnly", "(Lcom/exteragram/messenger/IconPackType;)Z", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/CoroutineDispatcher;", "mutationDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "decodeDispatcher", "prewarmDispatcher", _UrlKt.FRAGMENT_ENCODE_SET, "blacklistedIcons", "Ljava/util/Set;", "Ljava/util/concurrent/ConcurrentHashMap;", "systemIcons", "Ljava/util/concurrent/ConcurrentHashMap;", "getSystemIcons", "()Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/exteragram/messenger/icons/IconManager$IntStringMap;", "systemNames", "Lcom/exteragram/messenger/icons/IconManager$IntStringMap;", "maxMemory", "I", "cacheSize", "Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;", "resolvedCache", "Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;", "com/exteragram/messenger/icons/IconManager$sourceCache$1", "sourceCache", "Lcom/exteragram/messenger/icons/IconManager$sourceCache$1;", "Ljava/util/concurrent/CopyOnWriteArrayList;", "activePacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "iconOwnerMap", "Landroid/util/SparseIntArray;", "basePreinstalledMap", "Landroid/util/SparseIntArray;", "Lkotlinx/coroutines/Job;", "initializationJob", "Lkotlinx/coroutines/Job;", "prewarmJob", "Ljava/util/concurrent/CountDownLatch;", "initialPacksLoaded", "Ljava/util/concurrent/CountDownLatch;", "activePacksPublished", "Z", "initializationTimedOut", "initializationGeneration", "J", "Landroid/util/SparseArray;", "resultCallbacks", "Landroid/util/SparseArray;", "IntStringMap", "SourceCacheKey", "ResolvedIndex", "ActivePacksUpdate", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 SparseIntArray.kt\nandroidx/core/util/SparseIntArrayKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 BitmapDrawable.kt\nandroidx/core/graphics/drawable/BitmapDrawableKt\n+ 6 Bitmap.kt\nandroidx/core/graphics/BitmapKt\n+ 7 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,1083:1\n777#2:1084\n873#2,2:1085\n1915#2,2:1087\n296#2,2:1109\n296#2,2:1111\n2792#2,3:1113\n25#3:1089\n56#3:1090\n1#4:1091\n27#5:1092\n27#5:1093\n27#5:1094\n27#5:1095\n27#5:1096\n27#5:1097\n83#6,6:1098\n71#6:1104\n184#7,2:1105\n184#7,2:1107\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager\n*L\n284#1:1084\n284#1:1085,2\n285#1:1087,2\n1070#1:1109,2\n1072#1:1111,2\n1080#1:1113,3\n354#1:1089\n361#1:1090\n421#1:1092\n436#1:1093\n447#1:1094\n452#1:1095\n463#1:1096\n469#1:1097\n572#1:1098,6\n609#1:1104\n746#1:1105,2\n774#1:1107,2\n*E\n"})
public final class IconManager {
    public static final IconManager INSTANCE;
    private static final CopyOnWriteArrayList<IconPack> activePacks;
    private static boolean activePacksPublished;
    private static volatile SparseIntArray basePreinstalledMap;
    private static final Set<String> blacklistedIcons;
    private static final int cacheSize;
    private static final CoroutineDispatcher decodeDispatcher;
    private static final ConcurrentHashMap<String, IconPack> iconOwnerMap;
    private static final CountDownLatch initialPacksLoaded;
    private static volatile long initializationGeneration;
    private static Job initializationJob;
    private static volatile boolean initializationTimedOut;
    private static final int maxMemory;
    private static final CoroutineDispatcher mutationDispatcher;
    private static final CoroutineDispatcher prewarmDispatcher;
    private static Job prewarmJob;
    private static final ResolvedIndex resolvedCache;
    private static final SparseArray<Function1<Uri, Unit>> resultCallbacks;
    private static final CoroutineScope scope;
    private static final IconManager$sourceCache$1 sourceCache;
    private static final ConcurrentHashMap<String, Integer> systemIcons;
    private static volatile IntStringMap systemNames;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager$ActivePacksUpdate;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "STALE", "UNCHANGED", "CHANGED", "INITIAL", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum ActivePacksUpdate {
        STALE,
        UNCHANGED,
        CHANGED,
        INITIAL;

        private static final EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[IconPackStorageError.values().length];
            try {
                iArr[IconPackStorageError.INVALID_ARCHIVE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[IconPackStorageError.MISSING_METADATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[IconPackStorageError.METADATA_TOO_LARGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[IconPackStorageError.INVALID_METADATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[IconPackStorageError.TOO_MANY_FILES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[IconPackStorageError.ARCHIVE_TOO_LARGE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[IconPackStorageError.FILE_TOO_LARGE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[IconPackStorageError.COMPRESSION_RATIO_TOO_HIGH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[IconPackStorageError.STORAGE_ERROR.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[IconPackStorageError.UNKNOWN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final long resolvedCacheKey(int resId, int density) {
        return (((long) resId) << 32) | (((long) density) & 4294967295L);
    }

    @JvmOverloads
    public final void showReplaceAlert(Context context, int i) {
        showReplaceAlert$default(this, context, i, null, 4, null);
    }

    private IconManager() {
    }

    static {
        IconManager iconManager = new IconManager();
        INSTANCE = iconManager;
        scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default(null, 1, null)));
        mutationDispatcher = CoroutineDispatcher.limitedParallelism$default(Dispatchers.getIO(), 1, null, 2, null);
        decodeDispatcher = CoroutineDispatcher.limitedParallelism$default(Dispatchers.getIO(), 2, null, 2, null);
        prewarmDispatcher = CoroutineDispatcher.limitedParallelism$default(Dispatchers.getIO(), 1, null, 2, null);
        blacklistedIcons = SetsKt.setOf((Object[]) new String[]{"system", "smiles_popup", "camera_btn", "cancel_big", "chats_archive_box", "chats_archive_arrow", "chats_archive_muted", "chats_archive_pin", "chats_widget_preview", "circle_big", "contacts_widget_preview", "etg_splash", "ev_minus", "ev_plus", "filled_chatlink_large", "field_carret_empty", "dino_pic", "circle", "widgets_light_badgebg", "greydivider", "greydivider_bottom", "greydivider_top", "groups_limit1", "ic_ab_new", "ic_foreground", "ic_foreground_monet", "ic_foreground_solid", "ic_player", "ic_reply_icon", "icon_background_clip", "icon_background_clip_round", "icon_plane", "icplaceholder", "large_ads_info", "large_away", "large_greeting", "large_log_actions", "large_monetize", "large_quickreplies", "list_selector_ex", "livepin", "load_big", "location_empty", "login_arrow1", "login_phone1", "logo_middle", "map_pin", "map_pin2", "map_pin3", "map_pin_circle", "map_pin_cone2", "map_pin_photo", "msg_media_gallery", "music_empty", "no_passport", "no_password", "nophotos", "nophotos3", "paint_elliptical_brush", "paint_neon_brush", "paint_radial_brush", "phone_activate", "photo_placeholder_in", "photo_tooltip2", "photoview_placeholder", "screencast_big", "scrollbar_vertical_thumb", "scrollbar_vertical_thumb_inset", "newmsg_divider", "ic_launcher_dr", "smiles_info", "sms_bubble", "sms_devices", "stats_tooltip", "sticker", "story_camera", "theme_preview_image", "transparent", "venue_tooltip", "videopreview", "bluecounter", "photobadge", "photos_rounded", "calendar_date", "menu_copy", "redcircle", "tooltip_arrow", "tooltip_arrow_up", "newyear", "cards_chat", "community_cards", "ic_call_notification_answer", "ic_call_notification_decline", "shortcut_compose", "shortcut_user", "slide_dot_big", "slide_dot_small", "cocoon_logo", "cocoon_text", "telegram_logo", "telegram_logo_2", "mastercard_icon", "ton_icon", "diamond"});
        systemIcons = new ConcurrentHashMap<>();
        int iMaxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        maxMemory = iMaxMemory;
        final int iMax = Math.max(1024, iMaxMemory / 8);
        cacheSize = iMax;
        resolvedCache = new ResolvedIndex(2048);
        sourceCache = new LruCache<SourceCacheKey, Bitmap>(iMax) {
            @Override // androidx.collection.LruCache
            public int sizeOf(IconManager.SourceCacheKey key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
        activePacks = new CopyOnWriteArrayList<>();
        iconOwnerMap = new ConcurrentHashMap<>();
        initialPacksLoaded = new CountDownLatch(1);
        initialize$default(iconManager, false, 1, null);
        resultCallbacks = new SparseArray<>();
    }

    public final boolean isBlacklisted(String name) {
        return blacklistedIcons.contains(name) || StringsKt.contains$default((CharSequence) name, (CharSequence) "avd", false, 2, (Object) null) || StringsKt.endsWith$default(name, "_solar", false, 2, (Object) null) || StringsKt.endsWith$default(name, "_remix", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "$", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "animationpin", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "googlepay", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "shadow", false, 2, (Object) null) || StringsKt.startsWith$default(name, "ic_monochrome", false, 2, (Object) null) || StringsKt.startsWith$default(name, "nocover", false, 2, (Object) null) || StringsKt.startsWith$default(name, "gradient_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "stickers_back_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "media_doc_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "loading_animation", false, 2, (Object) null) || StringsKt.startsWith$default(name, "intro_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "minibubble_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "book_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "call_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "dice", false, 2, (Object) null) || StringsKt.startsWith$default(name, "msg_other_new_filled", false, 2, (Object) null) || StringsKt.startsWith$default(name, "profile_level", false, 2, (Object) null) || StringsKt.startsWith$default(name, "widget_", false, 2, (Object) null) || StringsKt.startsWith$default(name, "zoom_slide", false, 2, (Object) null) || StringsKt.startsWith$default(name, "zoom_round", false, 2, (Object) null) || StringsKt.startsWith$default(name, "popup_fixed_alert", false, 2, (Object) null) || StringsKt.startsWith$default(name, "search_dark", false, 2, (Object) null) || StringsKt.startsWith$default(name, "bar_selector", false, 2, (Object) null);
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0002J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u0003J\u0016\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\nJ\b\u0010\u0016\u001a\u00020\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager$IntStringMap;", _UrlKt.FRAGMENT_ENCODE_SET, "expected", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "keys", _UrlKt.FRAGMENT_ENCODE_SET, "values", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/String;", PluginsConstants.Settings.MASK, "shift", "size", "allocate", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "get", PluginsConstants.Settings.KEY, "put", "value", "grow", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class IntStringMap {
        private int mask;
        private int shift;
        private int size;
        private int[] keys = new int[0];
        private String[] values = new String[0];

        public IntStringMap(int i) {
            int i2 = 4;
            while (i2 < i * 2) {
                i2 <<= 1;
            }
            allocate(i2);
        }

        private final void allocate(int capacity) {
            this.keys = new int[capacity];
            this.values = new String[capacity];
            this.mask = capacity - 1;
            this.shift = 32 - Integer.numberOfTrailingZeros(capacity);
        }

        public final String get(int key) {
            if (key == 0) {
                return null;
            }
            int i = ((-1640531527) * key) >>> this.shift;
            while (true) {
                int i2 = this.keys[i];
                if (i2 == key) {
                    return this.values[i];
                }
                if (i2 == 0) {
                    return null;
                }
                i = (i + 1) & this.mask;
            }
        }

        public final void put(int key, String value) {
            if (key == 0) {
                return;
            }
            if ((this.size + 1) * 2 > this.keys.length) {
                grow();
            }
            int i = ((-1640531527) * key) >>> this.shift;
            while (true) {
                int[] iArr = this.keys;
                int i2 = iArr[i];
                if (i2 == 0) {
                    iArr[i] = key;
                    this.values[i] = value;
                    this.size++;
                    return;
                } else {
                    if (i2 == key) {
                        this.values[i] = value;
                        return;
                    }
                    i = (i + 1) & this.mask;
                }
            }
        }

        private final void grow() {
            int[] iArr = this.keys;
            String[] strArr = this.values;
            allocate(iArr.length << 1);
            this.size = 0;
            int length = iArr.length;
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                if (i2 != 0) {
                    put(i2, strArr[i]);
                }
            }
        }
    }

    public final ConcurrentHashMap<String, Integer> getSystemIcons() {
        return systemIcons;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\r\b\u0082\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u000eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001a\u0010\u000eR\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u001b\u0010\u000eR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\u001c\u001a\u0004\b\u001d\u0010\u0010R\u0017\u0010\n\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\n\u0010\u001c\u001a\u0004\b\u001e\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "generation", _UrlKt.FRAGMENT_ENCODE_SET, "packId", "location", "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "resId", "density", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "J", "getGeneration", "()J", "Ljava/lang/String;", "getPackId", "getLocation", "getFileName", "I", "getResId", "getDensity", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SourceCacheKey {
        private final int density;
        private final String fileName;
        private final long generation;
        private final String location;
        private final String packId;
        private final int resId;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SourceCacheKey)) {
                return false;
            }
            SourceCacheKey sourceCacheKey = (SourceCacheKey) other;
            return this.generation == sourceCacheKey.generation && Intrinsics.areEqual(this.packId, sourceCacheKey.packId) && Intrinsics.areEqual(this.location, sourceCacheKey.location) && Intrinsics.areEqual(this.fileName, sourceCacheKey.fileName) && this.resId == sourceCacheKey.resId && this.density == sourceCacheKey.density;
        }

        public int hashCode() {
            int iHashCode = ((Long.hashCode(this.generation) * 31) + this.packId.hashCode()) * 31;
            String str = this.location;
            return ((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.fileName.hashCode()) * 31) + Integer.hashCode(this.resId)) * 31) + Integer.hashCode(this.density);
        }

        public String toString() {
            return "SourceCacheKey(generation=" + this.generation + ", packId=" + this.packId + ", location=" + this.location + ", fileName=" + this.fileName + ", resId=" + this.resId + ", density=" + this.density + ")";
        }

        public SourceCacheKey(long j, String str, String str2, String str3, int i, int i2) {
            this.generation = j;
            this.packId = str;
            this.location = str2;
            this.fileName = str3;
            this.resId = i;
            this.density = i2;
        }

        public final String getPackId() {
            return this.packId;
        }

        public final int getResId() {
            return this.resId;
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\bJ\u0006\u0010\u000f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/exteragram/messenger/icons/IconManager$ResolvedIndex;", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "entries", "Landroid/util/LongSparseArray;", "Lcom/exteragram/messenger/icons/IconManager$SourceCacheKey;", "get", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "put", _UrlKt.FRAGMENT_ENCODE_SET, "value", "clear", "removeByResId", "resId", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$ResolvedIndex\n+ 2 LongSparseArray.kt\nandroidx/core/util/LongSparseArrayKt\n*L\n1#1,1083:1\n25#2:1084\n25#2:1085\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$ResolvedIndex\n*L\n235#1:1084\n246#1:1085\n*E\n"})
    public static final class ResolvedIndex {
        private final int capacity;
        private final LongSparseArray<SourceCacheKey> entries = new LongSparseArray<>();

        public ResolvedIndex(int i) {
            this.capacity = i;
        }

        public final synchronized SourceCacheKey get(long key) {
            return this.entries.get(key);
        }

        public final synchronized void put(long key, SourceCacheKey value) {
            try {
                if (this.entries.size() >= this.capacity) {
                    this.entries.clear();
                }
                this.entries.put(key, value);
            } catch (Throwable th) {
                throw th;
            }
        }

        public final synchronized void clear() {
            this.entries.clear();
        }

        public final synchronized void removeByResId(int resId) {
            int size = this.entries.size();
            while (true) {
                size--;
                if (-1 < size) {
                    if (((int) (this.entries.keyAt(size) >> 32)) == resId) {
                        this.entries.removeAt(size);
                    }
                }
            }
        }
    }

    private final SourceCacheKey sourceCacheKey(IconPack pack, String fileName, int resId, int density) {
        long j = initializationGeneration;
        String id = pack.getId();
        File location = pack.getLocation();
        return new SourceCacheKey(j, id, location != null ? location.getAbsolutePath() : null, fileName, resId, density);
    }

    public final void invalidateIconCaches(String packId, int resId) {
        resolvedCache.removeByResId(resId);
        Set<SourceCacheKey> setKeySet = sourceCache.snapshot().keySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            SourceCacheKey sourceCacheKey = (SourceCacheKey) obj;
            if (Intrinsics.areEqual(sourceCacheKey.getPackId(), packId) && sourceCacheKey.getResId() == resId) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            sourceCache.remove((SourceCacheKey) obj2);
        }
    }

    public final synchronized boolean publishBitmap(long generation, String resourceName, IconPack pack, int resId, int density, Bitmap bitmap) {
        if (generation == initializationGeneration && Intrinsics.areEqual(iconOwnerMap.get(resourceName), pack)) {
            String str = pack.getIcons().get(resourceName);
            if (str == null) {
                return false;
            }
            SourceCacheKey sourceCacheKey = sourceCacheKey(pack, str, resId, density);
            sourceCache.put(sourceCacheKey, bitmap);
            resolvedCache.put(resolvedCacheKey(resId, density), sourceCacheKey);
            return true;
        }
        return false;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$prefetchCustomPacks$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class C01481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public C01481(Continuation<? super C01481> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01481(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                IconManager.INSTANCE.syncInstalledCustomPacks(IconPackStorage.INSTANCE.getCustomPacks());
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public final void prefetchCustomPacks() {
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new C01481(null), 3, null);
    }

    public final synchronized boolean syncInstalledCustomPacks(List<IconPack> packs) {
        boolean z;
        try {
            Iterator<IconPack> it = packs.iterator();
            z = false;
            while (it.hasNext()) {
                String id = it.next().getId();
                if (!ExteraConfig.getIconPacksLayout().contains(id) && !ExteraConfig.getIconPacksHidden().contains(id)) {
                    ExteraConfig.getIconPacksHidden().add(id);
                    z = true;
                }
            }
            if (z) {
                ExteraConfig.saveIconPacksLayout();
            }
        } catch (Throwable th) {
            throw th;
        }
        return z;
    }

    public final void rebuildOwnerMap() {
        iconOwnerMap.clear();
        int size = activePacks.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                IconPack iconPack = activePacks.get(size);
                if (!iconPack.isBase()) {
                    Iterator<String> it = iconPack.getIcons().keySet().iterator();
                    while (it.hasNext()) {
                        iconOwnerMap.put(it.next(), iconPack);
                    }
                }
                if (i < 0) {
                    break;
                } else {
                    size = i;
                }
            }
        }
        rebuildBasePreinstalledMap();
    }

    private final void rebuildBasePreinstalledMap() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = activePacks.size();
        for (int i = 0; i < size; i++) {
            IconPack iconPack = activePacks.get(i);
            SparseIntArray preinstalledMap = iconPack.getPreinstalledMap();
            if (preinstalledMap != null && iconPack.isBase()) {
                int size2 = preinstalledMap.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    int iKeyAt = preinstalledMap.keyAt(i2);
                    if (sparseIntArray.indexOfKey(iKeyAt) < 0) {
                        sparseIntArray.put(iKeyAt, preinstalledMap.valueAt(i2));
                    }
                }
            }
        }
        if (sparseIntArray.size() == 0) {
            sparseIntArray = null;
        }
        basePreinstalledMap = sparseIntArray;
    }

    public final synchronized ActivePacksUpdate updateActivePacks(long generation, List<IconPack> newActivePacks) {
        if (generation != initializationGeneration) {
            return ActivePacksUpdate.STALE;
        }
        boolean z = activePacksPublished;
        activePacksPublished = true;
        if (z && Intrinsics.areEqual(activePacks, newActivePacks)) {
            return ActivePacksUpdate.UNCHANGED;
        }
        CopyOnWriteArrayList<IconPack> copyOnWriteArrayList = activePacks;
        copyOnWriteArrayList.clear();
        copyOnWriteArrayList.addAll(newActivePacks);
        rebuildOwnerMap();
        resolvedCache.clear();
        sourceCache.evictAll();
        return !z ? ActivePacksUpdate.INITIAL : ActivePacksUpdate.CHANGED;
    }

    public final File resolvePackIconFile(IconPack pack, String iconFileName) {
        File location = pack.getLocation();
        if (location == null) {
            location = new File(IconPackStorage.INSTANCE.getIconPacksDirectory(), pack.getId());
        }
        try {
            return SimpliFiles.directory(location).file(iconFileName).getFile();
        } catch (Exception e) {
            FileLog.e("Failed to resolve icon file for pack " + pack.getId(), e);
            return null;
        }
    }

    public final synchronized void launchPrewarm(long generation) {
        try {
            if (generation != initializationGeneration) {
                return;
            }
            Job job = prewarmJob;
            if (job != null) {
                Job.cancel$default(job, null, 1, null);
            }
            prewarmJob = BuildersKt__Builders_commonKt.launch$default(scope, prewarmDispatcher, null, new C01471(generation, null), 2, null);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$launchPrewarm$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class C01471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final long $generation;
        private Object L$0;
        int label;

        public C01471(long j, Continuation<? super C01471> continuation) {
            super(2, continuation);
            this.$generation = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01471 c01471 = new C01471(this.$generation, continuation);
            c01471.L$0 = obj;
            return c01471;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            int i = AndroidUtilities.displayMetrics.densityDpi;
            long jElapsedRealtime = SystemClock.elapsedRealtime() + 150;
            int i2 = 0;
            for (Map.Entry entry : IconManager.iconOwnerMap.entrySet()) {
                String str = (String) entry.getKey();
                IconPack iconPack = (IconPack) entry.getValue();
                if (!CoroutineScopeKt.isActive(coroutineScope) || this.$generation != IconManager.initializationGeneration) {
                    return Unit.INSTANCE;
                }
                IconManager iconManager = IconManager.INSTANCE;
                Integer num = iconManager.getSystemIcons().get(str);
                if (num != null) {
                    int iIntValue = num.intValue();
                    int i3 = i2 + 1;
                    if (i2 >= 256) {
                        return Unit.INSTANCE;
                    }
                    if (SystemClock.elapsedRealtime() >= jElapsedRealtime) {
                        return Unit.INSTANCE;
                    }
                    Bitmap packIconBitmap = iconManager.getPackIconBitmap(iconPack, iIntValue, i, null, str, false);
                    if (packIconBitmap != null) {
                        if (CoroutineScopeKt.isActive(coroutineScope) && this.$generation == IconManager.initializationGeneration) {
                            int i4 = i;
                            iconManager.publishBitmap(this.$generation, str, iconPack, iIntValue, i4, packIconBitmap);
                            i = i4;
                        } else {
                            return Unit.INSTANCE;
                        }
                    }
                    i2 = i3;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final Drawable getDrawable(int resId, int density, Resources.Theme theme) {
        String str;
        IconPack iconPack;
        long j;
        Bitmap packIconBitmap;
        ConcurrentHashMap<String, IconPack> concurrentHashMap = iconOwnerMap;
        if (concurrentHashMap.isEmpty()) {
            return null;
        }
        if (density == 0) {
            density = AndroidUtilities.displayMetrics.densityDpi;
        }
        int i = density;
        SourceCacheKey sourceCacheKey = resolvedCache.get(resolvedCacheKey(resId, i));
        Bitmap bitmap = sourceCacheKey != null ? sourceCache.get(sourceCacheKey) : null;
        if (bitmap != null) {
            return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), bitmap);
        }
        IntStringMap intStringMap = systemNames;
        if (intStringMap == null || (resourceEntryName = intStringMap.get(resId)) == null) {
            try {
                String resourceEntryName = ApplicationLoader.applicationContext.getResources().getResourceEntryName(resId);
                str = resourceEntryName;
                iconPack = concurrentHashMap.get(str);
                if (iconPack != null) {
                    j = initializationGeneration;
                    packIconBitmap = getPackIconBitmap(iconPack, resId, i, theme, str, false);
                    if (packIconBitmap != null) {
                        publishBitmap(j, str, iconPack, resId, i, packIconBitmap);
                        return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), packIconBitmap);
                    }
                }
            } catch (Exception unused) {
            }
        } else {
            str = resourceEntryName;
            iconPack = concurrentHashMap.get(str);
            if (iconPack != null) {
                j = initializationGeneration;
                packIconBitmap = getPackIconBitmap(iconPack, resId, i, theme, str, false);
                if (packIconBitmap != null) {
                    publishBitmap(j, str, iconPack, resId, i, packIconBitmap);
                    return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), packIconBitmap);
                }
            }
        }
        return null;
    }

    public final Drawable getCachedDrawable(int resId) {
        SourceCacheKey sourceCacheKey;
        Bitmap bitmap;
        if (iconOwnerMap.isEmpty() || (sourceCacheKey = resolvedCache.get(resolvedCacheKey(resId, AndroidUtilities.displayMetrics.densityDpi))) == null || (bitmap = sourceCache.get(sourceCacheKey)) == null) {
            return null;
        }
        return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), bitmap);
    }

    public final Drawable getPackIconDrawable(IconPack pack, int resId) {
        Bitmap packIconBitmap$default = getPackIconBitmap$default(this, pack, resId, AndroidUtilities.displayMetrics.densityDpi, null, null, false, 48, null);
        if (packIconBitmap$default != null) {
            return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), packIconBitmap$default);
        }
        return null;
    }

    private final Bitmap cachedPackIconBitmap(IconPack pack, int resId, int density) {
        String str;
        String str2;
        IntStringMap intStringMap = systemNames;
        if (intStringMap == null || (str = intStringMap.get(resId)) == null || (str2 = pack.getIcons().get(str)) == null) {
            return null;
        }
        return sourceCache.get(sourceCacheKey(pack, str2, resId, density));
    }

    public final Drawable getCachedPackIconDrawable(IconPack pack, int resId) {
        Bitmap bitmapCachedPackIconBitmap = cachedPackIconBitmap(pack, resId, AndroidUtilities.displayMetrics.densityDpi);
        if (bitmapCachedPackIconBitmap != null) {
            return new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), bitmapCachedPackIconBitmap);
        }
        return null;
    }

    public final void requestPackIconDrawable(IconPack pack, int resId, Utilities.Callback<Drawable> callback) {
        int i = AndroidUtilities.displayMetrics.densityDpi;
        Bitmap bitmapCachedPackIconBitmap = cachedPackIconBitmap(pack, resId, i);
        if (bitmapCachedPackIconBitmap == null) {
            BuildersKt__Builders_commonKt.launch$default(scope, decodeDispatcher, null, new C01502(pack, resId, i, callback, null), 2, null);
        } else {
            callback.run(new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), bitmapCachedPackIconBitmap));
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestPackIconDrawable$2", f = "IconManager.kt", i = {0}, l = {474}, m = "invokeSuspend", n = {"bitmap"}, s = {"L$0"})
    public static final class C01502 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final Utilities.Callback<Drawable> $callback;
        final int $density;
        final IconPack $pack;
        final int $resId;
        Object L$0;
        int label;

        public C01502(IconPack iconPack, int i, int i2, Utilities.Callback<Drawable> callback, Continuation<? super C01502> continuation) {
            super(2, continuation);
            this.$pack = iconPack;
            this.$resId = i;
            this.$density = i2;
            this.$callback = callback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01502(this.$pack, this.$resId, this.$density, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01502) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Bitmap packIconBitmap$default = IconManager.getPackIconBitmap$default(IconManager.INSTANCE, this.$pack, this.$resId, this.$density, null, null, false, 48, null);
                if (packIconBitmap$default == null) {
                    return Unit.INSTANCE;
                }
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$callback, packIconBitmap$default, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(packIconBitmap$default);
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
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

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestPackIconDrawable$2$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$requestPackIconDrawable$2$1\n+ 2 BitmapDrawable.kt\nandroidx/core/graphics/drawable/BitmapDrawableKt\n*L\n1#1,1083:1\n27#2:1084\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$requestPackIconDrawable$2$1\n*L\n475#1:1084\n*E\n"})
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final Bitmap $bitmap;
            final Utilities.Callback<Drawable> $callback;
            int label;

            public AnonymousClass1(Utilities.Callback<Drawable> callback, Bitmap bitmap, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$callback = callback;
                this.$bitmap = bitmap;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$callback, this.$bitmap, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                this.$callback.run(new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), this.$bitmap));
                return Unit.INSTANCE;
            }
        }
    }

    public final void requestDrawable(int resId, Utilities.Callback<Drawable> callback) {
        if (iconOwnerMap.isEmpty()) {
            return;
        }
        Drawable cachedDrawable = getCachedDrawable(resId);
        if (cachedDrawable != null) {
            callback.run(cachedDrawable);
        } else {
            BuildersKt__Builders_commonKt.launch$default(scope, decodeDispatcher, null, new AnonymousClass2(resId, AndroidUtilities.displayMetrics.densityDpi, callback, null), 2, null);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestDrawable$2", f = "IconManager.kt", i = {0}, l = {489}, m = "invokeSuspend", n = {"drawable"}, s = {"L$0"})
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final Utilities.Callback<Drawable> $callback;
        final int $density;
        final int $resId;
        Object L$0;
        int label;

        public AnonymousClass2(int i, int i2, Utilities.Callback<Drawable> callback, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$resId = i;
            this.$density = i2;
            this.$callback = callback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$resId, this.$density, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Drawable drawable = IconManager.INSTANCE.getDrawable(this.$resId, this.$density, null);
                if (drawable == null) {
                    return Unit.INSTANCE;
                }
                MainCoroutineDispatcher main = Dispatchers.getMain();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$callback, drawable, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(drawable);
                this.label = 1;
                if (BuildersKt.withContext(main, anonymousClass1, this) == coroutine_suspended) {
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

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestDrawable$2$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final Utilities.Callback<Drawable> $callback;
            final Drawable $drawable;
            int label;

            public AnonymousClass1(Utilities.Callback<Drawable> callback, Drawable drawable, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$callback = callback;
                this.$drawable = drawable;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$callback, this.$drawable, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                this.$callback.run(this.$drawable);
                return Unit.INSTANCE;
            }
        }
    }

    public final void requestDrawableForSize(int resId, int sizePx, Utilities.Callback<Drawable> callback) {
        IntStringMap intStringMap;
        String str;
        IconPack iconPack;
        String str2;
        ConcurrentHashMap<String, IconPack> concurrentHashMap = iconOwnerMap;
        if (concurrentHashMap.isEmpty() || sizePx <= 0 || (intStringMap = systemNames) == null || (str = intStringMap.get(resId)) == null || (iconPack = concurrentHashMap.get(str)) == null || (str2 = iconPack.getIcons().get(str)) == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(scope, decodeDispatcher, null, new C01491(iconPack, str2, sizePx, callback, null), 2, null);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestDrawableForSize$1", f = "IconManager.kt", i = {0, 0, 0}, l = {502}, m = "invokeSuspend", n = {"file", "bitmap", "density"}, s = {"L$0", "L$1", "I$0"})
    public static final class C01491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final Utilities.Callback<Drawable> $callback;
        final String $iconFileName;
        final IconPack $pack;
        final int $sizePx;
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        public C01491(IconPack iconPack, String str, int i, Utilities.Callback<Drawable> callback, Continuation<? super C01491> continuation) {
            super(2, continuation);
            this.$pack = iconPack;
            this.$iconFileName = str;
            this.$sizePx = i;
            this.$callback = callback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01491(this.$pack, this.$iconFileName, this.$sizePx, this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                IconManager iconManager = IconManager.INSTANCE;
                File fileResolvePackIconFile = iconManager.resolvePackIconFile(this.$pack, this.$iconFileName);
                if (fileResolvePackIconFile == null) {
                    return Unit.INSTANCE;
                }
                int i2 = AndroidUtilities.displayMetrics.densityDpi;
                String absolutePath = fileResolvePackIconFile.getAbsolutePath();
                int i3 = this.$sizePx;
                Bitmap bitmapDecodeIconFile = iconManager.decodeIconFile(absolutePath, i3, i3, i2);
                if (bitmapDecodeIconFile == null) {
                    return Unit.INSTANCE;
                }
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C00211 c00211 = new C00211(this.$callback, bitmapDecodeIconFile, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(fileResolvePackIconFile);
                this.L$1 = SpillingKt.nullOutSpilledVariable(bitmapDecodeIconFile);
                this.I$0 = i2;
                this.label = 1;
                if (BuildersKt.withContext(main, c00211, this) == coroutine_suspended) {
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

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$requestDrawableForSize$1$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$requestDrawableForSize$1$1\n+ 2 BitmapDrawable.kt\nandroidx/core/graphics/drawable/BitmapDrawableKt\n*L\n1#1,1083:1\n27#2:1084\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$requestDrawableForSize$1$1\n*L\n503#1:1084\n*E\n"})
        public static final class C00211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final Bitmap $bitmap;
            final Utilities.Callback<Drawable> $callback;
            int label;

            public C00211(Utilities.Callback<Drawable> callback, Bitmap bitmap, Continuation<? super C00211> continuation) {
                super(2, continuation);
                this.$callback = callback;
                this.$bitmap = bitmap;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00211(this.$callback, this.$bitmap, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                this.$callback.run(new BitmapDrawable(ApplicationLoader.applicationContext.getResources(), this.$bitmap));
                return Unit.INSTANCE;
            }
        }
    }

    public static Bitmap getPackIconBitmap$default(IconManager iconManager, IconPack iconPack, int i, int i2, Resources.Theme theme, String str, boolean z, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            str = null;
        }
        String str2 = str;
        if ((i3 & 32) != 0) {
            z = true;
        }
        return iconManager.getPackIconBitmap(iconPack, i, i2, theme, str2, z);
    }

    public final Bitmap getPackIconBitmap(IconPack pack, int resId, int density, Resources.Theme theme, String knownResourceName, boolean cacheResult) {
        if (knownResourceName == null) {
            IntStringMap intStringMap = systemNames;
            knownResourceName = intStringMap != null ? intStringMap.get(resId) : null;
            if (knownResourceName == null) {
                try {
                    knownResourceName = ApplicationLoader.applicationContext.getResources().getResourceEntryName(resId);
                } catch (Exception unused) {
                    return null;
                }
            }
        }
        String str = pack.getIcons().get(knownResourceName);
        if (str == null) {
            return null;
        }
        SourceCacheKey sourceCacheKey = sourceCacheKey(pack, str, resId, density);
        IconManager$sourceCache$1 iconManager$sourceCache$1 = sourceCache;
        Bitmap bitmap = iconManager$sourceCache$1.get(sourceCacheKey);
        if (bitmap != null) {
            return bitmap;
        }
        File fileResolvePackIconFile = resolvePackIconFile(pack, str);
        if (fileResolvePackIconFile == null) {
            return null;
        }
        Bitmap bitmapCreateBitmapFromFile = createBitmapFromFile(fileResolvePackIconFile.getAbsolutePath(), resId, density, theme);
        if (bitmapCreateBitmapFromFile != null && cacheResult) {
            iconManager$sourceCache$1.put(sourceCacheKey, bitmapCreateBitmapFromFile);
        }
        return bitmapCreateBitmapFromFile;
    }

    public final Bitmap createBitmapFromFile(String path, int originalResId, int density, Resources.Theme theme) {
        Drawable drawableForDensity;
        try {
            Resources resources = ApplicationLoader.applicationContext.getResources();
            ExteraResources exteraResources = resources instanceof ExteraResources ? (ExteraResources) resources : null;
            if (exteraResources == null || (drawableForDensity = exteraResources.getOriginalDrawable(originalResId)) == null) {
                drawableForDensity = ResourcesCompat.getDrawableForDensity(ApplicationLoader.applicationContext.getResources(), originalResId, density, theme);
            }
            return decodeIconFile(path, Math.max(1, drawableForDensity != null ? drawableForDensity.getIntrinsicWidth() : AndroidUtilities.dp(24.0f)), Math.max(1, drawableForDensity != null ? drawableForDensity.getIntrinsicHeight() : AndroidUtilities.dp(24.0f)), density);
        } catch (Exception e) {
            FileLog.e("Error loading icon bitmap: " + path, e);
            return null;
        } catch (OutOfMemoryError e2) {
            FileLog.e("Out of memory loading icon bitmap: " + path, e2);
            return null;
        }
    }

    public final Bitmap decodeIconFile(String path, int targetW, int targetH, int density) {
        Bitmap bitmap;
        int i;
        Bitmap bitmap2;
        Bitmap bitmap3 = null;
        try {
            File file = new File(path);
            DecodedIconStore decodedIconStore = DecodedIconStore.INSTANCE;
            Bitmap bitmap4 = decodedIconStore.get(file, targetW, targetH, density);
            if (bitmap4 != null) {
                return bitmap4;
            }
            if (StringsKt.endsWith(path, ".svg", true)) {
                FileInputStream fileInputStream = new FileInputStream(path);
                try {
                    SVG fromInputStream = SVG.getFromInputStream(fileInputStream);
                    CloseableKt.closeFinally(fileInputStream, null);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(targetW, targetH, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmapCreateBitmap);
                    fromInputStream.setDocumentWidth(targetW);
                    fromInputStream.setDocumentHeight(targetH);
                    fromInputStream.renderToCanvas(canvas);
                    bitmapCreateBitmap.setDensity(density);
                    decodedIconStore.put(file, targetW, targetH, density, bitmapCreateBitmap);
                    return bitmapCreateBitmap;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileInputStream, th);
                        throw th2;
                    }
                }
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);
            int i2 = options.outWidth;
            if (i2 <= 0 || (i = options.outHeight) <= 0 || ((long) i2) * ((long) i) > 100000000) {
                return null;
            }
            options.inSampleSize = 1;
            if (i > targetH || i2 > targetW) {
                int i3 = i / 2;
                int i4 = i2 / 2;
                while (true) {
                    int i5 = options.inSampleSize;
                    if (i3 / i5 < targetH || i4 / i5 < targetW) {
                        break;
                    }
                    options.inSampleSize = i5 * 2;
                }
            }
            long jMax = Math.max(((long) targetW) * ((long) targetH), 1048576L);
            while (true) {
                long j = options.outWidth;
                int i6 = options.inSampleSize;
                bitmap = bitmap3;
                long j2 = jMax;
                try {
                    if ((((j + ((long) i6)) - 1) / ((long) i6)) * (((((long) options.outHeight) + ((long) i6)) - 1) / ((long) i6)) <= j2) {
                        break;
                    }
                    options.inSampleSize = i6 * 2;
                    bitmap3 = bitmap;
                    jMax = j2;
                } catch (Exception e) {
                    e = e;
                    FileLog.e("Error loading icon bitmap: " + path, e);
                    return bitmap;
                } catch (OutOfMemoryError e2) {
                    e = e2;
                    FileLog.e("Out of memory loading icon bitmap: " + path, e);
                    return bitmap;
                }
            }
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(path, options);
            if (bitmapDecodeFile == null) {
                return bitmap;
            }
            if (bitmapDecodeFile.getWidth() != targetW || bitmapDecodeFile.getHeight() != targetH) {
                Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeFile, targetW, targetH, true);
                if (!Intrinsics.areEqual(bitmapCreateScaledBitmap, bitmapDecodeFile)) {
                    bitmapDecodeFile.recycle();
                }
                bitmapCreateScaledBitmap.setDensity(density);
                bitmap2 = bitmapCreateScaledBitmap;
            } else {
                bitmapDecodeFile.setDensity(density);
                bitmap2 = bitmapDecodeFile;
            }
            DecodedIconStore.INSTANCE.put(file, targetW, targetH, density, bitmap2);
            return bitmap2;
        } catch (Exception e3) {
            e = e3;
            bitmap = null;
            FileLog.e("Error loading icon bitmap: " + path, e);
            return bitmap;
        } catch (OutOfMemoryError e4) {
            e = e4;
            bitmap = null;
            FileLog.e("Out of memory loading icon bitmap: " + path, e);
            return bitmap;
        }
    }

    public final void saveCustomIcon(String packId, int resId, File tempFile, String originalName) {
        String resourceEntryName;
        IntStringMap intStringMap = systemNames;
        if (intStringMap == null || (resourceEntryName = intStringMap.get(resId)) == null) {
            try {
                resourceEntryName = ApplicationLoader.applicationContext.getResources().getResourceEntryName(resId);
            } catch (Exception unused) {
                return;
            }
        }
        BuildersKt__Builders_commonKt.launch$default(scope, mutationDispatcher, null, new C01521(packId, tempFile, originalName, resourceEntryName, resId, null), 2, null);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$saveCustomIcon$1", f = "IconManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {671}, m = "invokeSuspend", n = {"$this$launch", "packToEdit", "ext", "sanitized", "baseName", "stem", "packDirectory", "destination", "updatedMap", "previousFileName", "updatedPack", "preDecoded", "density"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0"})
    @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveCustomIcon$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,1083:1\n1#2:1084\n392#3,4:1085\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveCustomIcon$1\n*L\n645#1:1085,4\n*E\n"})
    public static final class C01521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final String $originalName;
        final String $packId;
        final int $resId;
        final String $resourceName;
        final File $tempFile;
        int I$0;
        private Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        public C01521(String str, File file, String str2, String str3, int i, Continuation<? super C01521> continuation) {
            super(2, continuation);
            this.$packId = str;
            this.$tempFile = file;
            this.$originalName = str2;
            this.$resourceName = str3;
            this.$resId = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01521 c01521 = new C01521(this.$packId, this.$tempFile, this.$originalName, this.$resourceName, this.$resId, continuation);
            c01521.L$0 = obj;
            return c01521;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            File destination = null;
            try {
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                } else {
                    ResultKt.throwOnFailure(obj);
                    IconPack packToEdit = IconPackStorage.INSTANCE.findPackById(this.$packId);
                    if (packToEdit == null) {
                        return Unit.INSTANCE;
                    }
                    String ext = FilesKt.getExtension(this.$tempFile);
                    String str = this.$originalName;
                    String fileNameFromUserString = str != null ? FileManager.fileNameFromUserString(str) : null;
                    String sanitized = fileNameFromUserString == null ? "" : fileNameFromUserString;
                    String baseName = sanitized.length() == 0 ? this.$resourceName : sanitized;
                    String substringBeforeLast = StringsKt.substringBeforeLast(baseName, '.', baseName);
                    String str2 = substringBeforeLast.length() == 0 ? this.$resourceName : substringBeforeLast;
                    String stem = StringsKt.take(str2, 64);
                    int lastIndex = StringsKt.getLastIndex(stem);
                    String validPrefix = "";
                    while (lastIndex >= 0) {
                        char charAt = stem.charAt(lastIndex);
                        if (!Character.isHighSurrogate(charAt)) {
                            validPrefix = stem.substring(0, lastIndex + 1);
                            break;
                        }
                        lastIndex--;
                    }
                    File packDirFile = new File(IconPackStorage.INSTANCE.getIconPacksDirectory(), packToEdit.getId());
                    SimpliDirectory packDirectory = SimpliFiles.directory(packDirFile).create();
                    do {
                        destination = packDirectory.file(validPrefix + '_' + UUID.randomUUID() + '.' + ext).getFile();
                    } while (destination.exists());

                    SimpliFiles.file(this.$tempFile).copyTo(destination, OverwritePolicy.ERROR);
                    Map<String, String> updatedMap = MapsKt.toMutableMap(packToEdit.getIcons());
                    String previousFileName = updatedMap.put(this.$resourceName, destination.getName());
                    IconPack updatedPack = IconPack.copy$default(packToEdit, null, null, null, null, updatedMap, null, null, 111, null);
                    if (!IconPackStorage.INSTANCE.saveIconPackMetadata(updatedPack)) {
                        try {
                            if (destination.exists()) {
                                SimpliFiles.file(destination).delete();
                            }
                        } catch (Throwable unused) {
                        }
                        return Unit.INSTANCE;
                    }
                    if (previousFileName != null && !updatedMap.values().contains(previousFileName)) {
                        try {
                            File oldFile = IconManager.INSTANCE.resolvePackIconFile(packToEdit, previousFileName);
                            if (oldFile != null && oldFile.exists()) {
                                SimpliFiles.file(oldFile).delete();
                            }
                        } catch (Throwable th) {
                            FileLog.e("Failed to delete old icon", th);
                        }
                    }
                    int densityDpi = AndroidUtilities.displayMetrics.densityDpi;
                    Bitmap preDecoded = IconManager.INSTANCE.createBitmapFromFile(destination.getAbsolutePath(), this.$resId, densityDpi, null);
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(updatedPack, this.$resId, preDecoded, this.$resourceName, densityDpi, null);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(packToEdit);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(ext);
                    this.L$3 = SpillingKt.nullOutSpilledVariable(sanitized);
                    this.L$4 = SpillingKt.nullOutSpilledVariable(baseName);
                    this.L$5 = SpillingKt.nullOutSpilledVariable(validPrefix);
                    this.L$6 = SpillingKt.nullOutSpilledVariable(packDirectory);
                    this.L$7 = SpillingKt.nullOutSpilledVariable(destination);
                    this.L$8 = SpillingKt.nullOutSpilledVariable(updatedMap);
                    this.L$9 = SpillingKt.nullOutSpilledVariable(previousFileName);
                    this.L$10 = SpillingKt.nullOutSpilledVariable(updatedPack);
                    this.L$11 = SpillingKt.nullOutSpilledVariable(preDecoded);
                    this.I$0 = densityDpi;
                    this.label = 1;
                    if (BuildersKt.withContext(main, anonymousClass2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                destination = null;
            } catch (Exception e) {
                FileLog.e("Failed to save custom icon", e);
            } finally {
                try {
                    if (this.$tempFile.exists()) {
                        SimpliFiles.file(this.$tempFile).delete();
                    }
                } catch (Throwable unused2) {
                }
                if (destination != null) {
                    try {
                        if (destination.exists()) {
                            SimpliFiles.file(destination).delete();
                        }
                    } catch (Throwable unused3) {
                    }
                }
            }
            return Unit.INSTANCE;
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$saveCustomIcon$1$2", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveCustomIcon$1$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1083:1\n363#2,7:1084\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveCustomIcon$1$2\n*L\n672#1:1084,7\n*E\n"})
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final int $density;
            final Bitmap $preDecoded;
            final int $resId;
            final String $resourceName;
            final IconPack $updatedPack;
            int label;

            public AnonymousClass2(IconPack iconPack, int i, Bitmap bitmap, String str, int i2, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$updatedPack = iconPack;
                this.$resId = i;
                this.$preDecoded = bitmap;
                this.$resourceName = str;
                this.$density = i2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$updatedPack, this.$resId, this.$preDecoded, this.$resourceName, this.$density, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    CopyOnWriteArrayList copyOnWriteArrayList = IconManager.activePacks;
                    IconPack iconPack = this.$updatedPack;
                    Iterator it = copyOnWriteArrayList.iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i = -1;
                            break;
                        }
                        if (Intrinsics.areEqual(((IconPack) it.next()).getId(), iconPack.getId())) {
                            break;
                        }
                        i++;
                    }
                    if (i != -1) {
                        IconManager.activePacks.set(i, this.$updatedPack);
                        IconManager iconManager = IconManager.INSTANCE;
                        iconManager.rebuildOwnerMap();
                        iconManager.invalidateIconCaches(this.$updatedPack.getId(), this.$resId);
                        Bitmap bitmap = this.$preDecoded;
                        if (bitmap != null) {
                            Boxing.boxBoolean(iconManager.publishBitmap(IconManager.initializationGeneration, this.$resourceName, this.$updatedPack, this.$resId, this.$density, bitmap));
                        }
                    }
                    NotificationCenter.getGlobalInstance().lambda$postNotificationNameOnUIThread$1(NotificationCenter.iconPackUpdated, new Object[0]);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }
    }

    public final void resetCustomIcon(String packId, int resId) {
        String resourceEntryName;
        IntStringMap intStringMap = systemNames;
        if (intStringMap == null || (resourceEntryName = intStringMap.get(resId)) == null) {
            try {
                resourceEntryName = ApplicationLoader.applicationContext.getResources().getResourceEntryName(resId);
            } catch (Exception unused) {
                return;
            }
        }
        BuildersKt__Builders_commonKt.launch$default(scope, mutationDispatcher, null, new C01511(packId, resourceEntryName, resId, null), 2, null);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$resetCustomIcon$1", f = "IconManager.kt", i = {0, 0, 0, 0, 0}, l = {717}, m = "invokeSuspend", n = {"$this$launch", "packToEdit", "iconFileName", "updatedMap", "updatedPack"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4"})
    @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$resetCustomIcon$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1083:1\n1#2:1084\n*E\n"})
    public static final class C01511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final String $packId;
        final int $resId;
        final String $resourceName;
        private Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        public C01511(String str, String str2, int i, Continuation<? super C01511> continuation) {
            super(2, continuation);
            this.$packId = str;
            this.$resourceName = str2;
            this.$resId = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01511 c01511 = new C01511(this.$packId, this.$resourceName, this.$resId, continuation);
            c01511.L$0 = obj;
            return c01511;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            Object objM2406constructorimpl;
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                IconPackStorage iconPackStorage = IconPackStorage.INSTANCE;
                IconPack iconPackFindPackById = iconPackStorage.findPackById(this.$packId);
                if (iconPackFindPackById != null && (str = iconPackFindPackById.getIcons().get(this.$resourceName)) != null) {
                    Map mutableMap = MapsKt.toMutableMap(iconPackFindPackById.getIcons());
                    mutableMap.remove(this.$resourceName);
                    IconPack iconPackCopy$default = IconPack.copy$default(iconPackFindPackById, null, null, null, null, mutableMap, null, null, 111, null);
                    if (!iconPackStorage.saveIconPackMetadata(iconPackCopy$default)) {
                        return Unit.INSTANCE;
                    }
                    if (!mutableMap.values().contains(str)) {
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            File fileResolvePackIconFile = IconManager.INSTANCE.resolvePackIconFile(iconPackFindPackById, str);
                            if (fileResolvePackIconFile != null && fileResolvePackIconFile.exists()) {
                                SimpliFiles.file(fileResolvePackIconFile).delete();
                            }
                            objM2406constructorimpl = Result.m2406constructorimpl(Unit.INSTANCE);
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM2406constructorimpl = Result.m2406constructorimpl(ResultKt.createFailure(th));
                        }
                        Throwable thM2409exceptionOrNullimpl = Result.m2409exceptionOrNullimpl(objM2406constructorimpl);
                        if (thM2409exceptionOrNullimpl != null) {
                            FileLog.e("Failed to delete old icon", thM2409exceptionOrNullimpl);
                        }
                    }
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    AnonymousClass3 anonymousClass3 = new AnonymousClass3(iconPackCopy$default, this.$resId, null);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(iconPackFindPackById);
                    this.L$2 = SpillingKt.nullOutSpilledVariable(str);
                    this.L$3 = SpillingKt.nullOutSpilledVariable(mutableMap);
                    this.L$4 = SpillingKt.nullOutSpilledVariable(iconPackCopy$default);
                    this.label = 1;
                    if (BuildersKt.withContext(main, anonymousClass3, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$resetCustomIcon$1$3", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$resetCustomIcon$1$3\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1083:1\n363#2,7:1084\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$resetCustomIcon$1$3\n*L\n718#1:1084,7\n*E\n"})
        public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final int $resId;
            final IconPack $updatedPack;
            int label;

            public AnonymousClass3(IconPack iconPack, int i, Continuation<? super AnonymousClass3> continuation) {
                super(2, continuation);
                this.$updatedPack = iconPack;
                this.$resId = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass3(this.$updatedPack, this.$resId, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    CopyOnWriteArrayList copyOnWriteArrayList = IconManager.activePacks;
                    IconPack iconPack = this.$updatedPack;
                    Iterator it = copyOnWriteArrayList.iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i = -1;
                            break;
                        }
                        if (Intrinsics.areEqual(((IconPack) it.next()).getId(), iconPack.getId())) {
                            break;
                        }
                        i++;
                    }
                    if (i != -1) {
                        IconManager.activePacks.set(i, this.$updatedPack);
                        IconManager iconManager = IconManager.INSTANCE;
                        iconManager.rebuildOwnerMap();
                        iconManager.invalidateIconCaches(this.$updatedPack.getId(), this.$resId);
                    }
                    NotificationCenter.getGlobalInstance().lambda$postNotificationNameOnUIThread$1(NotificationCenter.iconPackUpdated, new Object[0]);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }
    }

    public final int getIcon(int resId) {
        int i;
        SparseIntArray sparseIntArray = basePreinstalledMap;
        return (sparseIntArray == null || (i = sparseIntArray.get(resId, -1)) == -1) ? resId : i;
    }

    @JvmStatic
    public static final IconCompat getNotificationIcon() {
        Object obj;
        Object next;
        int iIntValue;
        IconCompat iconCompatCreateWithBitmap;
        try {
            ExteraConfig.loadConfig();
            Iterator it = SequencesKt.mapNotNull(SequencesKt.filterNot(CollectionsKt.asSequence(ExteraConfig.getIconPacksLayout()), new Function1() {
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(StringsKt.startsWith$default((String) obj2, "base.", false, 2, (Object) null));
                }
            }), new Function1() {
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return IconPackStorage.INSTANCE.findPackById((String) obj2);
                }
            }).iterator();
            do {
                obj = null;
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!((IconPack) next).getIcons().containsKey("notification"));
            IconPack iconPack = (IconPack) next;
            if (iconPack != null) {
                if (Build.VERSION.SDK_INT >= 31) {
                    Uri iconUri = IconPackProvider.INSTANCE.getIconUri(iconPack.getId(), "notification");
                    if (iconUri != null) {
                        try {
                            ApplicationLoader.applicationContext.grantUriPermission("com.android.systemui", iconUri, 1);
                        } catch (Exception e) {
                            FileLog.e(e);
                        }
                        iconCompatCreateWithBitmap = IconCompat.createWithContentUri(iconUri);
                    } else {
                        iconCompatCreateWithBitmap = null;
                    }
                    if (iconCompatCreateWithBitmap != null) {
                        return iconCompatCreateWithBitmap;
                    }
                } else {
                    Bitmap packIconBitmap$default = getPackIconBitmap$default(INSTANCE, iconPack, R.drawable.notification, AndroidUtilities.displayMetrics.densityDpi, null, "notification", false, 32, null);
                    if (packIconBitmap$default != null) {
                        iconCompatCreateWithBitmap = IconCompat.createWithBitmap(packIconBitmap$default);
                    } else {
                        iconCompatCreateWithBitmap = null;
                    }
                    if (iconCompatCreateWithBitmap != null) {
                        return iconCompatCreateWithBitmap;
                    }
                }
            }
            for (Object obj2 : SequencesKt.map(SequencesKt.mapNotNull(SequencesKt.filter(CollectionsKt.asSequence(ExteraConfig.getIconPacksLayout()), new Function1() {
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return Boolean.valueOf(StringsKt.startsWith$default((String) obj3, "base.", false, 2, (Object) null));
                }
            }), new Function1() {
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return IconManager.$r8$lambda$Vs1GV53ha9TZG53mOuPSdSA1wwU((String) obj3);
                }
            }), new Function1() {
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return Integer.valueOf(((SparseIntArray) obj3).get(R.drawable.notification, -1));
                }
            })) {
                if (((Number) obj2).intValue() != -1) {
                    obj = obj2;
                    break;
                }
            }
            Integer num = (Integer) obj;
            if (num != null) {
                iIntValue = num.intValue();
            } else {
                iIntValue = R.drawable.notification;
            }
            return IconCompat.createWithResource(ApplicationLoader.applicationContext, iIntValue);
        } catch (Exception e2) {
            FileLog.e("Failed to resolve notification icon", e2);
            return IconCompat.createWithResource(ApplicationLoader.applicationContext, R.drawable.notification);
        }
    }

    public static SparseIntArray $r8$lambda$Vs1GV53ha9TZG53mOuPSdSA1wwU(String str) {
        IconPack basePack = BaseIconPacks.INSTANCE.getBasePack(str);
        if (basePack != null) {
            return basePack.getPreinstalledMap();
        }
        return null;
    }

    @JvmStatic
    public static final Icon getNotificationSystemIcon() {
        return getNotificationIcon().toIcon(ApplicationLoader.applicationContext);
    }

    public static void initialize$default(IconManager iconManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        iconManager.initialize(z);
    }

    public final synchronized void initialize(boolean update) {
        Job job;
        Job job2 = initializationJob;
        if (job2 == null || !job2.isActive() || update) {
            if (update && (job = initializationJob) != null) {
                Job.cancel$default(job, null, 1, null);
            }
            Job job3 = prewarmJob;
            if (job3 != null) {
                Job.cancel$default(job3, null, 1, null);
            }
            initializationGeneration++;
            initializationJob = BuildersKt__Builders_commonKt.launch$default(scope, null, null, new C01461(initializationGeneration, update, null), 3, null);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$initialize$1", f = "IconManager.kt", i = {0, 1}, l = {845, 859}, m = "invokeSuspend", n = {"result", "result"}, s = {"L$0", "L$0"})
    public static final class C01461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final long $generation;
        final boolean $update;
        Object L$0;
        int label;

        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ActivePacksUpdate.values().length];
                try {
                    iArr[ActivePacksUpdate.STALE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ActivePacksUpdate.UNCHANGED.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[ActivePacksUpdate.CHANGED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[ActivePacksUpdate.INITIAL.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public C01461(long j, boolean z, Continuation<? super C01461> continuation) {
            super(2, continuation);
            this.$generation = j;
            this.$update = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01461(this.$generation, this.$update, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i != 1 && i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            } else {
                ResultKt.throwOnFailure(obj);
                IconManager iconManager = IconManager.INSTANCE;
                if (iconManager.getSystemIcons().isEmpty()) {
                    for (java.lang.reflect.Field field : R.drawable.class.getFields()) {
                        String name = field.getName();
                        if (!iconManager.isBlacklisted(name)) {
                            try {
                                int resId = field.getInt(null);
                                iconManager.getSystemIcons().put(name, Boxing.boxInt(resId));
                                iconManager.getSystemNames().put(Boxing.boxInt(resId), name);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
                if (this.$generation != IconManager.initializationGeneration) {
                    return Unit.INSTANCE;
                }
                ArrayList<IconPack> newActivePacks = new ArrayList<>();
                Iterator<String> it = ExteraConfig.getIconPacksLayout().iterator();
                while (it.hasNext()) {
                    String packId = it.next();
                    IconPack pack = (packId != null && packId.startsWith("base."))
                            ? BaseIconPacks.INSTANCE.getBasePack(packId)
                            : IconPackStorage.INSTANCE.findPackById(packId);
                    if (pack != null) {
                        newActivePacks.add(pack);
                    }
                }
                if (this.$generation != IconManager.initializationGeneration) {
                    return Unit.INSTANCE;
                }
                ActivePacksUpdate updateResult = iconManager.updateActivePacks(this.$generation, newActivePacks);
                switch (updateResult) {
                    case STALE:
                        return Unit.INSTANCE;
                    case UNCHANGED:
                        if (this.$update) {
                            IconManager.resolvedCache.evictAll();
                            IconManager.sourceCache.evictAll();
                            this.label = 1;
                            if (BuildersKt.withContext(Dispatchers.getMain(), new C00201(this.$generation, null), this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                        break;
                    case CHANGED:
                        this.label = 2;
                        if (BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(this.$generation, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                }
            }
            IconManager.INSTANCE.launchPrewarm(this.$generation);
            return Unit.INSTANCE;
        }


        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$initialize$1$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C00201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final long $generation;
            int label;

            public C00201(long j, Continuation<? super C00201> continuation) {
                super(2, continuation);
                this.$generation = j;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00201(this.$generation, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                if (this.$generation != IconManager.initializationGeneration) {
                    return Unit.INSTANCE;
                }
                NotificationCenter.getGlobalInstance().lambda$postNotificationNameOnUIThread$1(NotificationCenter.iconPackUpdated, new Object[0]);
                return Unit.INSTANCE;
            }
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$initialize$1$2", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$initialize$1$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1083:1\n296#2,2:1084\n296#2,2:1086\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$initialize$1$2\n*L\n864#1:1084,2\n865#1:1086,2\n*E\n"})
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final long $generation;
            final ActivePacksUpdate $result;
            int label;

            public AnonymousClass2(long j, ActivePacksUpdate activePacksUpdate, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$generation = j;
                this.$result = activePacksUpdate;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$generation, this.$result, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object next;
                Object next2;
                BaseFragment safeLastFragment;
                Activity parentActivity;
                Activity parentActivity2;
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                if (this.$generation != IconManager.initializationGeneration) {
                    return Unit.INSTANCE;
                }
                if (ExteraConfig.getEditingIconPackId() != null) {
                    Iterator it = IconManager.activePacks.iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!Intrinsics.areEqual(((IconPack) next).getId(), ExteraConfig.getEditingIconPackId()));
                    IconPack iconPack = (IconPack) next;
                    Iterator it2 = IconManager.activePacks.iterator();
                    do {
                        if (!it2.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it2.next();
                    } while (((IconPack) next2).isBase());
                    IconPack iconPack2 = (IconPack) next2;
                    if (iconPack != null) {
                        if (!Intrinsics.areEqual(iconPack.getId(), iconPack2 != null ? iconPack2.getId() : null)) {
                            ExteraConfig.setEditingIconPackId(null);
                            safeLastFragment = LaunchActivity.getSafeLastFragment();
                            if (safeLastFragment != null && (parentActivity = safeLastFragment.getParentActivity()) != null && (parentActivity instanceof LaunchActivity)) {
                                IconPickerController.setActive((LaunchActivity) parentActivity, false);
                            }
                        } else {
                            BaseFragment safeLastFragment2 = LaunchActivity.getSafeLastFragment();
                            if (safeLastFragment2 != null && (parentActivity2 = safeLastFragment2.getParentActivity()) != null && (parentActivity2 instanceof LaunchActivity)) {
                                IconPickerController.setActive((LaunchActivity) parentActivity2, true);
                            }
                        }
                    } else {
                        ExteraConfig.setEditingIconPackId(null);
                        safeLastFragment = LaunchActivity.getSafeLastFragment();
                        if (safeLastFragment != null) {
                            IconPickerController.setActive((LaunchActivity) parentActivity, false);
                        }
                    }
                }
                ActivePacksUpdate activePacksUpdate = this.$result;
                ActivePacksUpdate activePacksUpdate2 = ActivePacksUpdate.INITIAL;
                if (activePacksUpdate == activePacksUpdate2 && !IconManager.initializationTimedOut) {
                    return Unit.INSTANCE;
                }
                NotificationCenter.getGlobalInstance().lambda$postNotificationNameOnUIThread$1(NotificationCenter.iconPackUpdated, new Object[0]);
                BaseFragment safeLastFragment3 = LaunchActivity.getSafeLastFragment();
                if (safeLastFragment3 != null) {
                    ActivePacksUpdate activePacksUpdate3 = this.$result;
                    Theme.reloadAllResources(safeLastFragment3.getParentActivity());
                    if (activePacksUpdate3 == activePacksUpdate2) {
                        Activity parentActivity3 = safeLastFragment3.getParentActivity();
                        LaunchActivity launchActivity = parentActivity3 instanceof LaunchActivity ? (LaunchActivity) parentActivity3 : null;
                        if (launchActivity != null) {
                            launchActivity.rebuildAllFragments(true);
                        }
                    } else {
                        INavigationLayout parentLayout = safeLastFragment3.getParentLayout();
                        if (parentLayout != null) {
                            parentLayout.rebuildFragments(0);
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void awaitInitialization() {
        try {
            if (initialPacksLoaded.await(1000L, TimeUnit.MILLISECONDS)) {
                return;
            }
            initializationTimedOut = true;
            FileLog.e("Icon packs were not loaded in 1000ms");
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    public final void setActiveCustomPack(String packId) {
        if (packId == null || ExteraConfig.getIconPacksLayout().contains(packId)) {
            return;
        }
        ExteraConfig.getIconPacksLayout().add(packId);
        ExteraConfig.getIconPacksHidden().remove(packId);
        ExteraConfig.saveIconPacksLayout();
        initialize(true);
    }

    public final IconPack findPackById(String packId) {
        return IconPackStorage.INSTANCE.findPackById(packId);
    }

    public final File bundlePackBlocking(String packId) {
        return IconPackStorage.INSTANCE.bundlePackBlocking(packId);
    }

    public final boolean saveIconPackMetadata(IconPack iconPack) {
        IconPack iconPack2 = (IconPack) BuildersKt.runBlockingK(mutationDispatcher, new IconManager$saveIconPackMetadata$savedPack$1(iconPack, null));
        if (iconPack2 == null) {
            return false;
        }
        BuildersKt__Builders_commonKt.launch$default(scope, Dispatchers.getMain(), null, new C01531(iconPack2, null), 2, null);
        return true;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$saveIconPackMetadata$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nIconManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveIconPackMetadata$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1083:1\n363#2,7:1084\n*S KotlinDebug\n*F\n+ 1 IconManager.kt\ncom/exteragram/messenger/icons/IconManager$saveIconPackMetadata$1\n*L\n937#1:1084,7\n*E\n"})
    public static final class C01531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final IconPack $savedPack;
        int label;

        public C01531(IconPack iconPack, Continuation<? super C01531> continuation) {
            super(2, continuation);
            this.$savedPack = iconPack;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01531(this.$savedPack, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CopyOnWriteArrayList copyOnWriteArrayList = IconManager.activePacks;
                IconPack iconPack = this.$savedPack;
                Iterator it = copyOnWriteArrayList.iterator();
                int i = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i = -1;
                        break;
                    }
                    if (Intrinsics.areEqual(((IconPack) it.next()).getId(), iconPack.getId())) {
                        break;
                    }
                    i++;
                }
                if (i != -1) {
                    IconManager.activePacks.set(i, this.$savedPack);
                    IconManager.INSTANCE.rebuildOwnerMap();
                    IconManager.resolvedCache.clear();
                    IconManager.sourceCache.evictAll();
                }
                NotificationCenter.getGlobalInstance().lambda$postNotificationNameOnUIThread$1(NotificationCenter.iconPackUpdated, new Object[0]);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$deletePack$1", f = "IconManager.kt", i = {}, l = {953}, m = "invokeSuspend", n = {}, s = {})
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final String $packId;
        int label;

        public AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$packId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$packId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                IconPackStorage.INSTANCE.deletePack(this.$packId);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C00181 c00181 = new C00181(this.$packId, null);
                this.label = 1;
                if (BuildersKt.withContext(main, c00181, this) == coroutine_suspended) {
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

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$deletePack$1$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C00181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final String $packId;
            int label;

            public C00181(String str, Continuation<? super C00181> continuation) {
                super(2, continuation);
                this.$packId = str;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00181(this.$packId, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                if (ExteraConfig.getIconPacksLayout().contains(this.$packId) || ExteraConfig.getIconPacksHidden().contains(this.$packId)) {
                    ExteraConfig.getIconPacksLayout().remove(this.$packId);
                    ExteraConfig.getIconPacksHidden().remove(this.$packId);
                    ExteraConfig.saveIconPacksLayout();
                }
                IconManager.INSTANCE.initialize(true);
                return Unit.INSTANCE;
            }
        }
    }

    public final void deletePack(String packId) {
        BuildersKt__Builders_commonKt.launch$default(scope, mutationDispatcher, null, new AnonymousClass1(packId, null), 2, null);
    }

    public final boolean isIconPack(String path, MessageObject messageObject) {
        return (messageObject != null ? messageObject.getDocumentName() : null) != null && path.length() > 0 && StringsKt.endsWith$default(path, ".icons", false, 2, (Object) null);
    }

    private final String iconPackErrorText(IconPackStorageError error) {
        int i;
        switch (WhenMappings.$EnumSwitchMapping$0[error.ordinal()]) {
            case 1:
                i = R.string.IconPackErrorInvalidArchive;
                break;
            case 2:
                i = R.string.IconPackErrorMissingMetadata;
                break;
            case 3:
                i = R.string.IconPackErrorMetadataTooLarge;
                break;
            case 4:
                i = R.string.IconPackErrorInvalidMetadata;
                break;
            case 5:
                i = R.string.IconPackErrorTooManyFiles;
                break;
            case 6:
                i = R.string.IconPackErrorArchiveTooLarge;
                break;
            case 7:
                i = R.string.IconPackErrorFileTooLarge;
                break;
            case 8:
                i = R.string.IconPackErrorCompressionRatioTooHigh;
                break;
            case 9:
                i = R.string.IconPackErrorStorage;
                break;
            case 10:
                i = R.string.UnknownError;
                break;
            default:
                throw new kotlin.NoWhenBranchMatchedException();
                return null;
        }
        return LocaleController.getString(i);
    }

    public final void showIconPackError(BaseFragment baseFragment, IconPackStorageError error) {
        BulletinFactory.of(baseFragment).createSimpleBulletin(R.raw.error, iconPackErrorText(error)).show();
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$handleIconPack$1", f = "IconManager.kt", i = {0, 1, 1}, l = {998, 999}, m = "invokeSuspend", n = {"file", "file", "packResult"}, s = {"L$0", "L$0", "L$1"})
    public static final class C01451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final BaseFragment $baseFragment;
        final String $path;
        Object L$0;
        Object L$1;
        int label;

        public C01451(String str, BaseFragment baseFragment, Continuation<? super C01451> continuation) {
            super(2, continuation);
            this.$path = str;
            this.$baseFragment = baseFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C01451(this.$path, this.$baseFragment, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            File file;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                file = new File(this.$path);
                this.L$0 = file;
                this.label = 1;
                obj = IconPackStorage.INSTANCE.parsePackFromZip(file, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                file = (File) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else if (i == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            IconPackStorageResult<IconPack> packResult = (IconPackStorageResult<IconPack>) obj;
            this.L$0 = file;
            this.L$1 = packResult;
            this.label = 2;
            if (BuildersKt.withContext(Dispatchers.getMain(), new C00191(packResult, this.$baseFragment, file, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
        @DebugMetadata(c = "com.exteragram.messenger.icons.IconManager$handleIconPack$1$1", f = "IconManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        public static final class C00191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final BaseFragment $baseFragment;
            final File $file;
            final IconPackStorageResult<IconPack> $packResult;
            int label;

            public C00191(IconPackStorageResult<IconPack> iconPackStorageResult, BaseFragment baseFragment, File file, Continuation<? super C00191> continuation) {
                super(2, continuation);
                this.$packResult = iconPackStorageResult;
                this.$baseFragment = baseFragment;
                this.$file = file;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00191(this.$packResult, this.$baseFragment, this.$file, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                IconPackStorageResult<IconPack> iconPackStorageResult = this.$packResult;
                if (iconPackStorageResult instanceof IconPackStorageResult.Success) {
                    final IconPack iconPack = (IconPack) ((IconPackStorageResult.Success) iconPackStorageResult).getValue();
                    Activity parentActivity = this.$baseFragment.getParentActivity();
                    final File file = this.$file;
                    final BaseFragment baseFragment = this.$baseFragment;
                    this.$baseFragment.showDialog(new InstallIconPackBottomSheet(parentActivity, iconPack, new InstallIconPackBottomSheet.InstallDelegate() {
                        @Override // com.exteragram.messenger.icons.ui.components.InstallIconPackBottomSheet.InstallDelegate
                        public final void onInstall(boolean z, boolean z2) {
                            BuildersKt__Builders_commonKt.launch$default(IconManager.scope, null, null, new IconManager$handleIconPack$1$1$bottomSheet$1$1(file, baseFragment, z2, iconPack, z, null), 3, null);
                        }
                    }));
                } else if (iconPackStorageResult instanceof IconPackStorageResult.Failure) {
                    IconManager.INSTANCE.showIconPackError(this.$baseFragment, ((IconPackStorageResult.Failure) iconPackStorageResult).getError());
                } else {
                    throw new kotlin.NoWhenBranchMatchedException();
                    return null;
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final void handleIconPack(BaseFragment baseFragment, String path) {
        BuildersKt__Builders_commonKt.launch$default(scope, null, null, new C01451(path, baseFragment, null), 3, null);
    }

    public final boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        SparseArray<Function1<Uri, Unit>> sparseArray = resultCallbacks;
        Function1<Uri, Unit> function1 = sparseArray.get(requestCode);
        if (function1 == null) {
            return false;
        }
        sparseArray.remove(requestCode);
        function1.invoke(data != null ? data.getData() : null);
        return true;
    }

    public final void startIconPicker(Activity activity, boolean selectFromFiles, Function1<? super Uri, Unit> callback) {
        Intent intentCreateIntent;
        resultCallbacks.put(43, callback);
        if (ActivityResultContracts$PickVisualMedia.INSTANCE.isPhotoPickerAvailable(activity) && !selectFromFiles) {
            intentCreateIntent = new ActivityResultContracts$PickVisualMedia().createIntent((Context) activity, PickVisualMediaRequestKt.PickVisualMediaRequest(ActivityResultContracts$PickVisualMedia.ImageOnly.INSTANCE));
        } else {
            Intent intent = new Intent(selectFromFiles ? "android.intent.action.OPEN_DOCUMENT" : "android.intent.action.GET_CONTENT");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "image/svg+xml"});
            if (selectFromFiles) {
                intent.addCategory("android.intent.category.OPENABLE");
            }
            intentCreateIntent = intent;
        }
        activity.startActivityForResult(intentCreateIntent, 43);
    }

    public static void showReplaceAlert$default(IconManager iconManager, Context context, int i, IconPack iconPack, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            iconPack = null;
        }
        iconManager.showReplaceAlert(context, i, iconPack);
    }

    @JvmOverloads
    public final void showReplaceAlert(Context context, int resId, IconPack iconPack) {
        if (iconPack == null) {
            Object obj = null;
            if (ExteraConfig.getEditingIconPackId() != null) {
                for (Object obj2 : activePacks) {
                    if (Intrinsics.areEqual(((IconPack) obj2).getId(), ExteraConfig.getEditingIconPackId())) {
                        obj = obj2;
                        break;
                    }
                }
                iconPack = (IconPack) obj;
            } else {
                for (Object obj3 : activePacks) {
                    if (!((IconPack) obj3).isBase()) {
                        obj = obj3;
                        break;
                    }
                }
                iconPack = (IconPack) obj;
            }
            if (iconPack == null) {
                return;
            }
        }
        BaseFragment safeLastFragment = LaunchActivity.getSafeLastFragment();
        if (safeLastFragment == null) {
            return;
        }
        safeLastFragment.showDialog(new ReplaceIconBottomSheet(context, resId, iconPack));
    }

    public final boolean isBasePackOnly(IconPackType basePackType) {
        if (ExteraConfig.getIconPack() != basePackType) {
            return false;
        }
        CopyOnWriteArrayList<IconPack> copyOnWriteArrayList = activePacks;
        if (copyOnWriteArrayList != null && copyOnWriteArrayList.isEmpty()) {
            return true;
        }
        Iterator<T> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            if (!((IconPack) it.next()).isBase()) {
                return false;
            }
        }
        return true;
    }
}
