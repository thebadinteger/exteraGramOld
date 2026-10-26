package com.exteragram.messenger.plugins.ui.components;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.analyzer.AnalysisCatalog;
import com.exteragram.messenger.plugins.analyzer.AnalyzerDiff;
import com.exteragram.messenger.plugins.analyzer.AnalyzerReport;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 ?2\u00020\u0001:\u0002>?B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\tH\u0002J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u0012H\u0016J\b\u0010#\u001a\u00020\tH\u0014J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0014J(\u0010(\u001a\u00020\f2\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020+0*j\b\u0012\u0004\u0012\u00020+`,2\u0006\u0010-\u001a\u00020\u0010H\u0002J>\u0010.\u001a\u00020\f2\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020+0*j\b\u0012\u0004\u0012\u00020+`,2\u0006\u0010/\u001a\u00020\u00172\f\u00100\u001a\b\u0012\u0004\u0012\u000202012\u0006\u00103\u001a\u00020\u0017H\u0002J(\u00104\u001a\u00020\f2\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020+0*j\b\u0012\u0004\u0012\u00020+`,2\u0006\u0010/\u001a\u00020\u0017H\u0002J\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u000202H\u0002J\u0010\u00108\u001a\u00020\t2\u0006\u00109\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u00020\u00172\u0006\u0010<\u001a\u00020=H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0019\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001b0\u001aj\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001b`\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginAnalysisReportSheet;", "Lorg/telegram/ui/Components/BottomSheetWithRecyclerListView;", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "report", "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport;", "diff", "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerDiff;", "confirmTitle", _UrlKt.FRAGMENT_ENCODE_SET, "onConfirm", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport;Lcom/exteragram/messenger/plugins/analyzer/AnalyzerDiff;Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)V", "listAdapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "sheetContainer", "Landroid/widget/FrameLayout;", "contentReady", _UrlKt.FRAGMENT_ENCODE_SET, "expanded", "Ljava/util/HashSet;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/HashSet;", "rows", "Ljava/util/HashMap;", "Lcom/exteragram/messenger/plugins/ui/components/AnalysisStatementRow;", "Lkotlin/collections/HashMap;", "toggle", "Landroid/view/View$OnClickListener;", "copy", "text", "onViewCreated", "containerView", "getTitle", "createAdapter", "Lorg/telegram/ui/Components/RecyclerListView$SelectionAdapter;", "listView", "Lorg/telegram/ui/Components/RecyclerListView;", "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "adapter", "addGroup", "title", "statements", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport$Statement;", "prefix", "addHeader", "evidenceOf", "Lcom/exteragram/messenger/plugins/ui/components/AnalysisEvidence;", "statement", "describePayload", "payload", "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport$Payload;", "groupTitle", "severity", _UrlKt.FRAGMENT_ENCODE_SET, "Candidate", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginAnalysisReportSheet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginAnalysisReportSheet.kt\ncom/exteragram/messenger/plugins/ui/components/PluginAnalysisReportSheet\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,350:1\n1#2:351\n1586#3:352\n1661#3,3:353\n777#3:356\n873#3,2:357\n777#3:359\n873#3,2:360\n777#3:362\n873#3,2:363\n1807#3,3:379\n777#3:382\n873#3,2:383\n1807#3,3:385\n2792#3,3:388\n1068#3:391\n296#3,2:392\n1586#3:394\n1661#3,3:395\n1807#3,3:398\n410#4,7:365\n410#4,7:372\n*S KotlinDebug\n*F\n+ 1 PluginAnalysisReportSheet.kt\ncom/exteragram/messenger/plugins/ui/components/PluginAnalysisReportSheet\n*L\n166#1:352\n166#1:353,3\n167#1:356\n167#1:357,2\n171#1:359\n171#1:360,2\n173#1:362\n173#1:363,2\n232#1:379,3\n233#1:382\n233#1:383,2\n240#1:385,3\n244#1:388,3\n287#1:391\n288#1:392,2\n297#1:394\n297#1:395,3\n239#1:398,3\n182#1:365,7\n203#1:372,7\n*E\n"})
public final class PluginAnalysisReportSheet extends BottomSheetWithRecyclerListView {
    private static final float BOTTOM_GAP = 12.0f;
    private static final int MAX_EXCERPT = 200;
    private static final int MAX_LINES = 4;
    private static final int MAX_VALUES = 12;
    private static final float SECTION_GAP = 12.0f;
    private static final float SHEET_TOP_INSET = 16.0f;
    private boolean contentReady;
    private final AnalyzerDiff diff;
    private final HashSet<String> expanded;
    private UniversalAdapter listAdapter;
    private final AnalyzerReport report;
    private final HashMap<String, AnalysisStatementRow> rows;
    private FrameLayout sheetContainer;
    private final View.OnClickListener toggle;
    private static final Set<String> COMBINED_STATEMENTS = SetsKt.setOf((Object[]) new String[]{"remote_code", "remote_control", "exfiltrate_messages", "forward_messages", "hidden_account_actions", "hides_behavior", "account_takeover", "exfiltrate_files", "telegram_leak", "session_via_telegram"});
    private static final Map<String, Set<String>> FLOW_STATEMENTS = MapsKt.mapOf(TuplesKt.to("forward_messages", SetsKt.setOf("flow:forward")), TuplesKt.to("exfiltrate_messages", SetsKt.setOf((Object[]) new String[]{"flow:message", "flow:contacts"})), TuplesKt.to("device_id_upload", SetsKt.setOf("flow:device-id")), TuplesKt.to("exfiltrate_files", SetsKt.setOf("flow:shared-file")), TuplesKt.to("telegram_leak", SetsKt.setOf((Object[]) new String[]{"flow:device-id", "flow:shared-file", "flow:contacts"})), TuplesKt.to("session_via_telegram", SetsKt.setOf("flow:credentials")));

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void setLastVisible(boolean z) {
        super.setLastVisible(z);
    }

    public PluginAnalysisReportSheet(BaseFragment baseFragment, AnalyzerReport analyzerReport, AnalyzerDiff analyzerDiff, CharSequence charSequence, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(baseFragment, analyzerReport, analyzerDiff, (i & 8) != 0 ? null : charSequence, (i & 16) != 0 ? null : function0);
    }

    public PluginAnalysisReportSheet(BaseFragment baseFragment, AnalyzerReport analyzerReport, AnalyzerDiff analyzerDiff, CharSequence charSequence, final Function0<Unit> function0) {
        super(baseFragment.getParentActivity(), baseFragment, false, false, false, true, BottomSheetWithRecyclerListView.ActionBarType.FADING, baseFragment.getResourceProvider());
        this.report = analyzerReport;
        this.diff = analyzerDiff;
        this.expanded = new HashSet<>();
        this.rows = new HashMap<>();
        this.toggle = new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PluginAnalysisReportSheet.$r8$lambda$Eoh0vVfWDRk105FZkOAn_cmU7Fc(this.f$0, view);
            }
        };
        this.topPadding = 0.2f;
        if (!ExteraConfig.getSectionsSeparatedHeaders()) {
            this.headerPaddingBottom = Math.max(0, AndroidUtilities.dp(12.0f) - (AndroidUtilities.dp(SHEET_TOP_INSET) - this.backgroundPaddingTop));
        }
        int i = Theme.key_windowBackgroundGray;
        int themedColor = getThemedColor(i);
        setBackgroundColor(themedColor);
        fixNavigationBar(themedColor);
        ActionBar actionBar = this.actionBar;
        actionBar.setBackgroundColor(getThemedColor(i));
        actionBar.setCastShadows(false);
        this.showShadow = false;
        this.takeTranslationIntoAccount = true;
        RecyclerListView recyclerListView = this.recyclerListView;
        recyclerListView.setClipToPadding(false);
        recyclerListView.setOverScrollMode(2);
        int i2 = this.backgroundPaddingLeft;
        recyclerListView.setPadding(i2, 0, i2, 0);
        recyclerListView.setSections();
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator() {
            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onMoveAnimationUpdate(RecyclerView.ViewHolder holder) {
                super.onMoveAnimationUpdate(holder);
                ((BottomSheet) this.this$0).containerView.invalidate();
            }
        };
        defaultItemAnimator.setSupportsChangeAnimations(false);
        defaultItemAnimator.setDelayAnimations(false);
        defaultItemAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        defaultItemAnimator.setDurations(350L);
        recyclerListView.setItemAnimator(defaultItemAnimator);
        if (function0 != null) {
            ButtonWithCounterView buttonWithCounterView = new ButtonWithCounterView(getContext(), true, this.resourcesProvider);
            buttonWithCounterView.setRound();
            buttonWithCounterView.setColor(PluginAnalysisCellsKt.analysisSeverityColor(3, this.resourcesProvider));
            buttonWithCounterView.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
            buttonWithCounterView.setText(charSequence, false);
            buttonWithCounterView.setOnClickListener(new View.OnClickListener() {
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PluginAnalysisReportSheet.m1961$r8$lambda$cn6HU2XKExakaqgYxPOweZdErQ(this.f$0, function0, view);
                }
            });
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(1);
            linearLayout.setBackground(PluginAnalysisCellsKt.sheetBottomBackdrop(getThemedColor(i)));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, AndroidUtilities.dp(48.0f));
            int iDp = this.backgroundPaddingLeft + AndroidUtilities.dp(12.0f);
            layoutParams.setMargins(iDp, AndroidUtilities.dp(12.0f), iDp, AndroidUtilities.dp(12.0f));
            Unit unit = Unit.INSTANCE;
            linearLayout.addView(buttonWithCounterView, layoutParams);
            linearLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                    PluginAnalysisReportSheet.$r8$lambda$Xe2PRM0wHd2LrwUxnn7uwRblXPI(this.f$0, view, i3, i4, i5, i6, i7, i8, i9, i10);
                }
            });
            FrameLayout frameLayout = this.sheetContainer;
            if (frameLayout != null) {
                frameLayout.addView(linearLayout, LayoutHelper.createFrame(-1, -2, 80));
            }
        }
        this.contentReady = true;
        UniversalAdapter universalAdapter = this.listAdapter;
        if (universalAdapter != null) {
            universalAdapter.update(false);
        }
        this.layoutManager.scrollToPositionWithOffset(0, 0);
    }

    public static void $r8$lambda$Eoh0vVfWDRk105FZkOAn_cmU7Fc(PluginAnalysisReportSheet pluginAnalysisReportSheet, View view) {
        Object tag = view.getTag();
        String str = tag instanceof String ? (String) tag : null;
        if (str == null) {
            return;
        }
        if (!pluginAnalysisReportSheet.expanded.remove(str)) {
            pluginAnalysisReportSheet.expanded.add(str);
        }
        UniversalAdapter universalAdapter = pluginAnalysisReportSheet.listAdapter;
        if (universalAdapter != null) {
            universalAdapter.update(true);
        }
    }

    public static void m1961$r8$lambda$cn6HU2XKExakaqgYxPOweZdErQ(PluginAnalysisReportSheet pluginAnalysisReportSheet, Function0 function0, View view) {
        pluginAnalysisReportSheet.dismiss();
        function0.invoke();
    }

    public static void $r8$lambda$Xe2PRM0wHd2LrwUxnn7uwRblXPI(PluginAnalysisReportSheet pluginAnalysisReportSheet, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i4 - i2;
        if (pluginAnalysisReportSheet.recyclerListView.getPaddingBottom() != i9) {
            RecyclerListView recyclerListView = pluginAnalysisReportSheet.recyclerListView;
            int i10 = pluginAnalysisReportSheet.backgroundPaddingLeft;
            recyclerListView.setPadding(i10, 0, i10, i9);
        }
    }

    public final void copy(CharSequence text) {
        FrameLayout frameLayout = this.sheetContainer;
        if (frameLayout == null || !AndroidUtilities.addToClipboard(text)) {
            return;
        }
        BulletinFactory.of(frameLayout, this.resourcesProvider).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void onViewCreated(FrameLayout containerView) {
        super.onViewCreated(containerView);
        this.sheetContainer = containerView;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.PluginAnalysisReport);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView listView) {
        Context context = getContext();
        int i = this.currentAccount;
        BaseFragment baseFragment = getBaseFragment();
        UniversalAdapter universalAdapter = new UniversalAdapter(listView, context, i, baseFragment != null ? baseFragment.getClassGuid() : 0, true, new Utilities.Callback2() {
            @Override // org.telegram.messenger.Utilities.Callback2
            public final void run(Object obj, Object obj2) {
                this.f$0.fillItems((ArrayList) obj, (UniversalAdapter) obj2);
            }
        }, this.resourcesProvider);
        this.listAdapter = universalAdapter;
        return universalAdapter;
    }

    public final void fillItems(ArrayList<UItem> items, UniversalAdapter adapter) {
        Set setEmptySet;
        List<AnalyzerReport.Statement> newStatements;
        if (this.contentReady) {
            AnalyzerDiff analyzerDiff = this.diff;
            if (analyzerDiff == null || (newStatements = analyzerDiff.getNewStatements()) == null) {
                setEmptySet = null;
            } else {
                List<AnalyzerReport.Statement> list = newStatements;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((AnalyzerReport.Statement) it.next()).getId());
                }
                setEmptySet = CollectionsKt.toSet(arrayList);
            }
            if (setEmptySet == null) {
                setEmptySet = SetsKt.emptySet();
            }
            List<AnalyzerReport.Statement> statements = this.report.getStatements();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : statements) {
                if (setEmptySet.contains(((AnalyzerReport.Statement) obj).getId())) {
                    arrayList2.add(obj);
                }
            }
            if (!arrayList2.isEmpty()) {
                addGroup(items, LocaleController.getString(R.string.PluginAnalysisNewInVersion), arrayList2, "new");
            }
            List<AnalyzerReport.Statement> statements2 = this.report.getStatements();
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : statements2) {
                if (!setEmptySet.contains(((AnalyzerReport.Statement) obj2).getId())) {
                    arrayList3.add(obj2);
                }
            }
            for (int i = 3; -1 < i; i--) {
                ArrayList arrayList4 = new ArrayList();
                int size = arrayList3.size();
                int i2 = 0;
                while (i2 < size) {
                    Object obj3 = arrayList3.get(i2);
                    i2++;
                    if (((AnalyzerReport.Statement) obj3).getSeverity() == i) {
                        arrayList4.add(obj3);
                    }
                }
                if (!arrayList4.isEmpty()) {
                    addGroup(items, groupTitle(i), arrayList4, "all");
                }
            }
            List<AnalysisCatalog.Limit> listLimits = AnalysisCatalog.INSTANCE.limits(this.report);
            if (!listLimits.isEmpty()) {
                addHeader(items, LocaleController.getString(R.string.PluginAnalysisGroupLimits));
                for (AnalysisCatalog.Limit limit : listLimits) {
                    HashMap<String, AnalysisStatementRow> map = this.rows;
                    String str = "limit:" + limit.getTitle();
                    AnalysisStatementRow analysisStatementRow = map.get(str);
                    if (analysisStatementRow == null) {
                        AnalysisStatementRow analysisStatementRow2 = new AnalysisStatementRow("limit:" + limit.getTitle(), limit.getIconRes(), PluginAnalysisCellsKt.analysisSeverityColor(1, this.resourcesProvider), limit.getTitle(), limit.getSubtitle(), CollectionsKt.emptyList(), null, null, 128, null);
                        map.put(str, analysisStatementRow2);
                        analysisStatementRow = analysisStatementRow2;
                    }
                    items.add(AnalysisStatementCell.Factory.Companion.of$default(AnalysisStatementCell.Factory.INSTANCE, analysisStatementRow, false, null, null, 8, null));
                }
            }
            items.add(UItem.asShadow(LocaleController.getString(R.string.PluginAnalysisLimitations)));
        }
    }

    private final void addGroup(ArrayList<UItem> items, String title, List<AnalyzerReport.Statement> statements, String prefix) {
        addHeader(items, title);
        for (AnalyzerReport.Statement statement : statements) {
            String str = prefix + ":" + statement.getId();
            HashMap<String, AnalysisStatementRow> map = this.rows;
            AnalysisStatementRow analysisStatementRow = map.get(str);
            if (analysisStatementRow == null) {
                AnalysisCatalog analysisCatalog = AnalysisCatalog.INSTANCE;
                List<String> listValues = analysisCatalog.values(this.report, statement);
                int size = listValues.size() - 12;
                int iconRes = analysisCatalog.category(statement).getIconRes();
                int iAnalysisSeverityColor = PluginAnalysisCellsKt.analysisSeverityColor(statement.getSeverity(), this.resourcesProvider);
                String strName = analysisCatalog.name(statement);
                String strDescription = analysisCatalog.description(statement);
                List listTake = CollectionsKt.take(listValues, 12);
                AnalysisEvidence analysisEvidenceEvidenceOf = evidenceOf(statement);
                AnalysisStatementRow analysisStatementRow2 = new AnalysisStatementRow(str, iconRes, iAnalysisSeverityColor, strName, strDescription, listTake, !analysisEvidenceEvidenceOf.getLines().isEmpty() ? analysisEvidenceEvidenceOf : null, CollectionsKt.plus((Collection) CollectionsKt.listOfNotNull(size > 0 ? LocaleController.formatString(R.string.PluginAnalysisMoreValues, Integer.valueOf(size)) : null), (Iterable) analysisCatalog.notes(statement)));
                map.put(str, analysisStatementRow2);
                analysisStatementRow = analysisStatementRow2;
            }
            items.add(AnalysisStatementCell.Factory.INSTANCE.of(analysisStatementRow, this.expanded.contains(str), this.toggle, new AnonymousClass1(this)));
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class AnonymousClass1 extends FunctionReferenceImpl implements Function1<CharSequence, Unit> {
        public AnonymousClass1(Object obj) {
            super(1, obj, PluginAnalysisReportSheet.class, "copy", "copy(Ljava/lang/CharSequence;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public Unit invoke(CharSequence charSequence) {
            invoke2(charSequence);
            return Unit.INSTANCE;
        }

        public final void invoke2(CharSequence charSequence) {
            ((PluginAnalysisReportSheet) this.receiver).copy(charSequence);
        }
    }

    private final void addHeader(ArrayList<UItem> items, String title) {
        if (!items.isEmpty()) {
            UItem uItemAsSpace = UItem.asSpace(AndroidUtilities.dp(12.0f));
            uItemAsSpace.transparent = true;
            items.add(uItemAsSpace);
        }
        items.add(InstallPluginBottomSheetKt.pluginSheetHeader(title));
    }

    private final AnalysisEvidence evidenceOf(AnalyzerReport.Statement statement) {
        boolean z;
        Object next;
        String string;
        CharSequence charSequenceTake;
        List listFindingsOf = this.report.findingsOf(statement);
        if (COMBINED_STATEMENTS.contains(statement.getId())) {
            List list = listFindingsOf;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((AnalyzerReport.Finding) it.next()).getSeverity() > 0) {
                        listFindingsOf = new ArrayList();
                        for (Object obj : list) {
                            if (((AnalyzerReport.Finding) obj).getSeverity() > 0) {
                                listFindingsOf.add(obj);
                            }
                        }
                        break;
                    }
                }
            }
        }
        final Set<String> set = FLOW_STATEMENTS.get(statement.getId());
        Function1 function1 = new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(PluginAnalysisReportSheet.m1959$r8$lambda$ML82R4Upi415aBPwcn5THVb4yw(set, (AnalyzerReport.Evidence) obj2));
            }
        };
        if (set == null) {
            z = false;
            break;
        }
        List list2 = listFindingsOf;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            z = false;
            break;
        }
        Iterator it2 = list2.iterator();
        loop2: while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            List<AnalyzerReport.Evidence> evidence = ((AnalyzerReport.Finding) it2.next()).getEvidence();
            if (!(evidence instanceof Collection) || !evidence.isEmpty()) {
                Iterator<T> it3 = evidence.iterator();
                while (it3.hasNext()) {
                    if (((Boolean) function1.invoke(it3.next())).booleanValue()) {
                        z = true;
                        break loop2;
                    }
                }
            }
        }
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        Iterator it4 = listFindingsOf.iterator();
        while (true) {
            int i = 2;
            String str = null;
            if (!it4.hasNext()) {
                break;
            }
            AnalyzerReport.Finding finding = (AnalyzerReport.Finding) it4.next();
            if (z) {
                List<AnalyzerReport.Evidence> evidence2 = finding.getEvidence();
                if (!(evidence2 instanceof Collection) || !evidence2.isEmpty()) {
                    Iterator<T> it5 = evidence2.iterator();
                    while (true) {
                        if (it5.hasNext()) {
                            if (((Boolean) function1.invoke(it5.next())).booleanValue()) {
                            }
                        }
                    }
                }
            }
            for (AnalyzerReport.Evidence evidence3 : finding.getEvidence()) {
                if (!z || ((Boolean) function1.invoke(evidence3)).booleanValue()) {
                    String excerpt = evidence3.getExcerpt();
                    if (excerpt == null) {
                        excerpt = evidence3.getInstruction();
                        if (excerpt != null) {
                            String scope = evidence3.getScope();
                            if (scope != null) {
                                excerpt = scope + ": " + excerpt;
                            }
                        } else {
                            excerpt = str;
                        }
                        if (excerpt == null) {
                            String symbol = evidence3.getSymbol();
                            if (!StringsKt.startsWith$default(symbol, "native:", false, i, (Object) str)) {
                                symbol = str;
                            }
                            excerpt = symbol != null ? StringsKt.removePrefix(symbol, (CharSequence) "native:") : str;
                            if (excerpt == null) {
                            }
                        }
                    }
                    List list3 = listFindingsOf;
                    boolean zStartsWith$default = StringsKt.startsWith$default(evidence3.getSymbol(), "net:", false, i, (Object) str);
                    String str2 = evidence3.getArtifact() + ":" + evidence3.getLine() + ":" + (evidence3.getExcerpt() == null ? excerpt : _UrlKt.FRAGMENT_ENCODE_SET);
                    Candidate candidate = (Candidate) map.get(str2);
                    if (candidate == null) {
                        AnalyzerReport.Payload payload = evidence3.getPayload();
                        if (payload != null && payload.getInComment() && evidence3.getEndLine() > evidence3.getLine()) {
                            string = LocaleController.formatString(R.string.PluginAnalysisHiddenInComment, Integer.valueOf(evidence3.getLine()), Integer.valueOf(evidence3.getEndLine()));
                        } else if (this.report.isRoot(evidence3.getArtifact())) {
                            string = null;
                        } else if (Intrinsics.areEqual(this.report.kindOf(evidence3.getArtifact()), "dex")) {
                            string = LocaleController.getString(R.string.PluginAnalysisCompiledCode);
                        } else {
                            string = Intrinsics.areEqual(this.report.kindOf(evidence3.getArtifact()), "elf") ? LocaleController.getString(R.string.PluginAnalysisNativeLibrary) : LocaleController.getString(R.string.PluginAnalysisDecodedCode);
                        }
                        if (payload == null) {
                            charSequenceTake = StringsKt.take(StringsKt.trim((CharSequence) excerpt).toString(), 200);
                        } else {
                            if (!payload.getDataLine()) {
                                payload = null;
                            }
                            if (payload == null || (charSequenceTake = describePayload(payload)) == null) {
                                charSequenceTake = StringsKt.take(StringsKt.trim((CharSequence) excerpt).toString(), 200);
                            }
                        }
                        Candidate candidate2 = new Candidate(new AnalysisEvidence.Line(string, evidence3.getLine(), charSequenceTake, StringsKt.trim((CharSequence) excerpt).toString()), finding.getSeverity(), finding.getCapabilities(), zStartsWith$default);
                        map.put(str2, candidate2);
                        arrayList.add(candidate2);
                    } else if (!zStartsWith$default) {
                        candidate.setAddress(false);
                    }
                    listFindingsOf = list3;
                    i = 2;
                    str = null;
                }
            }
        }
        List list4 = listFindingsOf;
        final Comparator comparatorCompareBy = ComparisonsKt.compareBy(new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Boolean.valueOf(((AnalysisEvidence.Line) obj2).getLabel() != null);
            }
        }, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return Integer.valueOf(((AnalysisEvidence.Line) obj2).getLine());
            }
        });
        final Comparator comparator = new Comparator() {
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((PluginAnalysisReportSheet.Candidate) t2).getSeverity()), Integer.valueOf(((PluginAnalysisReportSheet.Candidate) t).getSeverity()));
            }
        };
        final Comparator comparatorThen = new Comparator() {
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                return iCompare != 0 ? iCompare : ComparisonsKt.compareValues(Boolean.valueOf(((PluginAnalysisReportSheet.Candidate) t).getAddress()), Boolean.valueOf(((PluginAnalysisReportSheet.Candidate) t2).getAddress()));
            }
        };
        if (Intrinsics.areEqual(statement.getId(), "network")) {
            comparatorThen = ComparisonsKt.then(new Comparator() {
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Boolean.valueOf(((PluginAnalysisReportSheet.Candidate) t).getAddress()), Boolean.valueOf(((PluginAnalysisReportSheet.Candidate) t2).getAddress()));
                }
            }, comparatorThen);
        }
        List<Candidate> listSortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() {
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparatorThen.compare(t, t2);
                return iCompare != 0 ? iCompare : comparatorCompareBy.compare(((PluginAnalysisReportSheet.Candidate) t).getLine(), ((PluginAnalysisReportSheet.Candidate) t2).getLine());
            }
        });
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        if (COMBINED_STATEMENTS.contains(statement.getId()) && !z) {
            for (String str3 : statement.getCapabilities()) {
                Iterator it6 = (Intrinsics.areEqual(str3, "network") ? CollectionsKt.sortedWith(listSortedWith, new Comparator() {
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Boolean.valueOf(((PluginAnalysisReportSheet.Candidate) t).getAddress()), Boolean.valueOf(((PluginAnalysisReportSheet.Candidate) t2).getAddress()));
                    }
                }) : listSortedWith).iterator();
                while (true) {
                    if (!it6.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it6.next();
                    Candidate candidate3 = (Candidate) next;
                    if (candidate3.getCapabilities().contains(str3) && !linkedHashSet.contains(candidate3)) {
                        break;
                    }
                }
                Candidate candidate4 = (Candidate) next;
                if (candidate4 != null) {
                    linkedHashSet.add(candidate4);
                }
            }
        }
        for (Candidate candidate5 : listSortedWith) {
            if (linkedHashSet.size() >= 4) {
                break;
            }
            linkedHashSet.add(candidate5);
        }
        List listTake = CollectionsKt.take(linkedHashSet, 4);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listTake, 10));
        Iterator it7 = listTake.iterator();
        while (it7.hasNext()) {
            arrayList2.add(((Candidate) it7.next()).getLine());
        }
        List listSortedWith2 = CollectionsKt.sortedWith(arrayList2, comparatorCompareBy);
        int size = arrayList.size();
        if (!z) {
            Iterator it8 = list4.iterator();
            int evidenceTotal = 0;
            while (it8.hasNext()) {
                evidenceTotal += ((AnalyzerReport.Finding) it8.next()).getEvidenceTotal();
            }
            size = Math.max(size, evidenceTotal);
        }
        return new AnalysisEvidence(listSortedWith2, size - listSortedWith2.size());
    }

    public static boolean m1959$r8$lambda$ML82R4Upi415aBPwcn5THVb4yw(Set set, AnalyzerReport.Evidence evidence) {
        if (set == null) {
            return false;
        }
        List<String> traits = evidence.getTraits();
        if ((traits instanceof Collection) && traits.isEmpty()) {
            return false;
        }
        Iterator<T> it = traits.iterator();
        while (it.hasNext()) {
            if (set.contains((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0002\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/PluginAnalysisReportSheet$Candidate;", _UrlKt.FRAGMENT_ENCODE_SET, "line", "Lcom/exteragram/messenger/plugins/ui/components/AnalysisEvidence$Line;", "severity", _UrlKt.FRAGMENT_ENCODE_SET, "capabilities", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "address", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lcom/exteragram/messenger/plugins/ui/components/AnalysisEvidence$Line;ILjava/util/List;Z)V", "getLine", "()Lcom/exteragram/messenger/plugins/ui/components/AnalysisEvidence$Line;", "getSeverity", "()I", "getCapabilities", "()Ljava/util/List;", "getAddress", "()Z", "setAddress", "(Z)V", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Candidate {
        private boolean address;
        private final List<String> capabilities;
        private final AnalysisEvidence.Line line;
        private final int severity;

        public Candidate(AnalysisEvidence.Line line, int i, List<String> list, boolean z) {
            this.line = line;
            this.severity = i;
            this.capabilities = list;
            this.address = z;
        }

        public final AnalysisEvidence.Line getLine() {
            return this.line;
        }

        public final int getSeverity() {
            return this.severity;
        }

        public final List<String> getCapabilities() {
            return this.capabilities;
        }

        public final boolean getAddress() {
            return this.address;
        }

        public final void setAddress(boolean z) {
            this.address = z;
        }
    }

    private final CharSequence describePayload(AnalyzerReport.Payload payload) {
        String fileSize = AndroidUtilities.formatFileSize(payload.getSize());
        if (payload.getKind().length() == 0) {
            return LocaleController.formatString(R.string.PluginAnalysisEncodedData, fileSize);
        }
        String upperCase = Intrinsics.areEqual(payload.getKind(), PluginsConstants.PYTHON) ? "Python" : payload.getKind().toUpperCase(Locale.ROOT);
        String strJoinToString$default = CollectionsKt.joinToString$default(payload.getTransforms(), " + ", null, null, 0, null, null, 62, null);
        if (strJoinToString$default.length() == 0) {
            return upperCase + " · " + fileSize;
        }
        return LocaleUtils.replaceArrows(getContext(), strJoinToString$default + " -> " + upperCase + " · " + fileSize, R.drawable.msg_mini_arrow_mediathin);
    }

    private final String groupTitle(int severity) {
        int i;
        if (severity == 1) {
            i = R.string.PluginAnalysisGroupCaution;
        } else if (severity == 2) {
            i = R.string.PluginAnalysisGroupDanger;
        } else if (severity == 3) {
            i = R.string.PluginAnalysisGroupCritical;
        } else {
            i = R.string.PluginAnalysisGroupInfo;
        }
        return LocaleController.getString(i);
    }
}
