package com.exteragram.messenger.plugins.analyzer;

import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u001d\u001e\u001fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\rJ\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\f\u001a\u00020\rJ\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0017\u0010\u0017\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0018\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0019J\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011H\u0002J\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SYMBOL_VALUES", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "entries", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog$Entry;", "category", "Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog$Category;", "statement", "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport$Statement;", "name", MediaTrack.ROLE_DESCRIPTION, "values", _UrlKt.FRAGMENT_ENCODE_SET, "report", "Lcom/exteragram/messenger/plugins/analyzer/AnalyzerReport;", "notes", "limits", "Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog$Limit;", "symbolName", "value", "symbolName$TMessagesProj", "tlMethod", "parts", "hostOf", "Category", "Entry", "Limit", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAnalysisCatalog.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AnalysisCatalog.kt\ncom/exteragram/messenger/plugins/analyzer/AnalysisCatalog\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,180:1\n1#2:181\n1#2:206\n1391#3:182\n1480#3,2:183\n777#3:185\n873#3,2:186\n1586#3:188\n1661#3,3:189\n1807#3,3:192\n1642#3,10:195\n1915#3:205\n1916#3:207\n1652#3:208\n1482#3,3:209\n777#3:212\n873#3,2:213\n1807#3,3:215\n1807#3,3:218\n*S KotlinDebug\n*F\n+ 1 AnalysisCatalog.kt\ncom/exteragram/messenger/plugins/analyzer/AnalysisCatalog\n*L\n106#1:206\n97#1:182\n97#1:183,2\n98#1:185\n98#1:186,2\n101#1:188\n101#1:189,3\n106#1:192,3\n106#1:195,10\n106#1:205\n106#1:207\n106#1:208\n97#1:209,3\n110#1:212\n110#1:213,2\n124#1:215,3\n125#1:218,3\n*E\n"})
public final class AnalysisCatalog {
    public static final AnalysisCatalog INSTANCE = new AnalysisCatalog();
    private static final Set<String> SYMBOL_VALUES = SetsKt.setOf((Object[]) new String[]{"telegram_api", "sensitive_requests", "intercepts_requests", "session_data", "java_hooks", "ui_hooks", "host_tampering"});
    private static final Map<String, Entry> entries;

    private AnalysisCatalog() {
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog$Category;", _UrlKt.FRAGMENT_ENCODE_SET, "iconRes", _UrlKt.FRAGMENT_ENCODE_SET, "titleRes", "<init>", "(Ljava/lang/String;III)V", "getIconRes", "()I", "getTitleRes", "CODE", "ACCOUNT", "MESSAGES", "NETWORK", "FILES", "DEVICE", "APP", "PACKAGES", "OTHER", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Category {
        CODE(R.drawable.plugin_analysis_code, R.string.PluginAnalysisCategoryCode),
        ACCOUNT(R.drawable.plugin_analysis_account, R.string.PluginAnalysisCategoryAccount),
        MESSAGES(R.drawable.plugin_analysis_messages, R.string.PluginAnalysisCategoryMessages),
        NETWORK(R.drawable.plugin_analysis_network, R.string.PluginAnalysisCategoryNetwork),
        FILES(R.drawable.plugin_analysis_files, R.string.PluginAnalysisCategoryFiles),
        DEVICE(R.drawable.plugin_analysis_device, R.string.PluginAnalysisCategoryDevice),
        APP(R.drawable.plugin_analysis_app, R.string.PluginAnalysisCategoryApp),
        PACKAGES(R.drawable.plugin_analysis_packages, R.string.PluginAnalysisCategoryPackages),
        OTHER(R.drawable.plugin_analysis_info, R.string.PluginAnalysisCategoryOther);

        private static final EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final int iconRes;
        private final int titleRes;

        public static EnumEntries<Category> getEntries() {
            return $ENTRIES;
        }

        Category(int i, int i2) {
            this.iconRes = i;
            this.titleRes = i2;
        }

        public final int getIconRes() {
            return this.iconRes;
        }

        public final int getTitleRes() {
            return this.titleRes;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog$Entry;", _UrlKt.FRAGMENT_ENCODE_SET, "category", "Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog$Category;", "nameRes", _UrlKt.FRAGMENT_ENCODE_SET, "descriptionRes", "<init>", "(Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog$Category;II)V", "getCategory", "()Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog$Category;", "getNameRes", "()I", "getDescriptionRes", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Entry {
        private final Category category;
        private final int descriptionRes;
        private final int nameRes;

        public Entry(Category category, int i, int i2) {
            this.category = category;
            this.nameRes = i;
            this.descriptionRes = i2;
        }

        public final Category getCategory() {
            return this.category;
        }

        public final int getDescriptionRes() {
            return this.descriptionRes;
        }

        public final int getNameRes() {
            return this.nameRes;
        }
    }

    static {
        Category category = Category.CODE;
        Pair pair = TuplesKt.to("dynamic_code", new Entry(category, R.string.PluginAnalysisNameDynamicCode, R.string.PluginAnalysisStatementDynamicCode));
        Pair pair2 = TuplesKt.to("embedded_code", new Entry(category, R.string.PluginAnalysisNameEmbeddedCode, R.string.PluginAnalysisStatementEmbeddedCode));
        Pair pair3 = TuplesKt.to("loads_code", new Entry(category, R.string.PluginAnalysisCompiledCode, R.string.PluginAnalysisStatementLoadsCode));
        Pair pair4 = TuplesKt.to("reflection_dynamic", new Entry(category, R.string.PluginAnalysisNameReflectionDynamic, R.string.PluginAnalysisStatementReflectionDynamic));
        Pair pair5 = TuplesKt.to("native_code", new Entry(category, R.string.PluginAnalysisNameNativeCode, R.string.PluginAnalysisStatementNativeCode));
        Pair pair6 = TuplesKt.to("anti_analysis", new Entry(category, R.string.PluginAnalysisNameAntiAnalysis, R.string.PluginAnalysisStatementAntiAnalysis));
        Pair pair7 = TuplesKt.to("hidden_parts", new Entry(category, R.string.PluginAnalysisNameHiddenParts, R.string.PluginAnalysisStatementHiddenParts));
        Pair pair8 = TuplesKt.to("remote_code", new Entry(category, R.string.PluginAnalysisNameRemoteCode, R.string.PluginAnalysisStatementRemoteCode));
        Pair pair9 = TuplesKt.to("remote_control", new Entry(category, R.string.PluginAnalysisNameRemoteControl, R.string.PluginAnalysisStatementRemoteControl));
        Pair pair10 = TuplesKt.to("hides_behavior", new Entry(category, R.string.PluginAnalysisNameHidesBehavior, R.string.PluginAnalysisStatementHidesBehavior));
        Category category2 = Category.NETWORK;
        Pair pair11 = TuplesKt.to("network", new Entry(category2, R.string.PluginAnalysisNameNetwork, R.string.PluginAnalysisStatementNetwork));
        Pair pair12 = TuplesKt.to("exfiltrate_messages", new Entry(category2, R.string.PluginAnalysisNameExfiltrateMessages, R.string.PluginAnalysisStatementExfiltrateMessages));
        Pair pair13 = TuplesKt.to("device_id_upload", new Entry(category2, R.string.PluginAnalysisNameDeviceIdUpload, R.string.PluginAnalysisStatementDeviceIdUpload));
        Category category3 = Category.FILES;
        Pair pair14 = TuplesKt.to("files", new Entry(category3, R.string.PluginAnalysisNameFiles, R.string.PluginAnalysisStatementFiles));
        Pair pair15 = TuplesKt.to("telegram_files", new Entry(category3, R.string.PluginAnalysisNameTelegramFiles, R.string.PluginAnalysisStatementTelegramFiles));
        Category category4 = Category.APP;
        Pair pair16 = TuplesKt.to("processes", new Entry(category4, R.string.PluginAnalysisNameProcesses, R.string.PluginAnalysisStatementProcesses));
        Pair pair17 = TuplesKt.to("app_exit", new Entry(category4, R.string.PluginAnalysisNameAppExit, R.string.PluginAnalysisStatementAppExit));
        Pair pair18 = TuplesKt.to("host_tampering", new Entry(category4, R.string.PluginAnalysisNameHostTampering, R.string.PluginAnalysisStatementHostTampering));
        Pair pair19 = TuplesKt.to("java_hooks", new Entry(category4, R.string.PluginAnalysisNameJavaHooks, R.string.PluginAnalysisStatementJavaHooks));
        Pair pair20 = TuplesKt.to("ui_hooks", new Entry(category4, R.string.PluginAnalysisNameUiHooks, R.string.PluginAnalysisStatementUiHooks));
        Pair pair21 = TuplesKt.to("persistence", new Entry(category4, R.string.PluginAnalysisNamePersistence, R.string.PluginAnalysisStatementPersistence));
        Pair pair22 = TuplesKt.to("app_settings", new Entry(category4, R.string.PluginAnalysisNameAppSettings, R.string.PluginAnalysisStatementAppSettings));
        Category category5 = Category.DEVICE;
        Pair pair23 = TuplesKt.to("privacy", new Entry(category5, R.string.PluginAnalysisNamePrivacy, R.string.PluginAnalysisStatementPrivacy));
        Pair pair24 = TuplesKt.to("system_overlay", new Entry(category5, R.string.PluginAnalysisNameSystemOverlay, R.string.PluginAnalysisStatementSystemOverlay));
        Pair pair25 = TuplesKt.to("exfiltrate_files", new Entry(category2, R.string.PluginAnalysisNameExfiltrateFiles, R.string.PluginAnalysisStatementExfiltrateFiles));
        Category category6 = Category.MESSAGES;
        Pair pair26 = TuplesKt.to("telegram_leak", new Entry(category6, R.string.PluginAnalysisNameTelegramLeak, R.string.PluginAnalysisStatementTelegramLeak));
        Category category7 = Category.ACCOUNT;
        entries = MapsKt.mapOf(pair, pair2, pair3, pair4, pair5, pair6, pair7, pair8, pair9, pair10, pair11, pair12, pair13, pair14, pair15, pair16, pair17, pair18, pair19, pair20, pair21, pair22, pair23, pair24, pair25, pair26, TuplesKt.to("session_via_telegram", new Entry(category7, R.string.PluginAnalysisNameSessionViaTelegram, R.string.PluginAnalysisStatementSessionViaTelegram)), TuplesKt.to("sms", new Entry(category5, R.string.PluginAnalysisNameSms, R.string.PluginAnalysisStatementSms)), TuplesKt.to("screen_capture", new Entry(category5, R.string.PluginAnalysisNameScreenCapture, R.string.PluginAnalysisStatementScreenCapture)), TuplesKt.to("installs_apps", new Entry(category5, R.string.PluginAnalysisNameInstallsApps, R.string.PluginAnalysisStatementInstallsApps)), TuplesKt.to("media_library", new Entry(category5, R.string.PluginAnalysisNameMediaLibrary, R.string.PluginAnalysisStatementMediaLibrary)), TuplesKt.to("webview_bridge", new Entry(category2, R.string.PluginAnalysisNameWebviewBridge, R.string.PluginAnalysisStatementWebviewBridge)), TuplesKt.to("background", new Entry(category4, R.string.PluginAnalysisNameBackground, R.string.PluginAnalysisStatementBackground)), TuplesKt.to("intents", new Entry(category4, R.string.PluginAnalysisNameIntents, R.string.PluginAnalysisStatementIntents)), TuplesKt.to("notifications", new Entry(category6, R.string.PluginAnalysisNameNotifications, R.string.PluginAnalysisStatementNotifications)), TuplesKt.to("third_party_code", new Entry(Category.PACKAGES, R.string.PluginAnalysisNameThirdPartyCode, R.string.PluginAnalysisStatementThirdPartyCode)), TuplesKt.to("telegram_api", new Entry(category7, R.string.PluginAnalysisNameTelegramApi, R.string.PluginAnalysisStatementTelegramApi)), TuplesKt.to("sensitive_requests", new Entry(category7, R.string.PluginAnalysisNameSensitiveRequests, R.string.PluginAnalysisStatementSensitiveRequests)), TuplesKt.to("intercepts_requests", new Entry(category7, R.string.PluginAnalysisNameInterceptsRequests, R.string.PluginAnalysisStatementInterceptsRequests)), TuplesKt.to("session_data", new Entry(category7, R.string.PluginAnalysisNameSessionData, R.string.PluginAnalysisStatementSessionData)), TuplesKt.to("hidden_account_actions", new Entry(category7, R.string.PluginAnalysisNameHiddenAccountActions, R.string.PluginAnalysisStatementHiddenAccountActions)), TuplesKt.to("account_takeover", new Entry(category7, R.string.PluginAnalysisNameAccountTakeover, R.string.PluginAnalysisStatementAccountTakeover)), TuplesKt.to("wallet_access", new Entry(category7, R.string.PluginAnalysisNameWalletAccess, R.string.PluginAnalysisStatementWalletAccess)), TuplesKt.to("messages_read", new Entry(category6, R.string.PluginAnalysisNameMessagesRead, R.string.PluginAnalysisStatementMessagesRead)), TuplesKt.to("messages_send", new Entry(category6, R.string.PluginAnalysisNameMessagesSend, R.string.PluginAnalysisStatementMessagesSend)), TuplesKt.to("messages_delete", new Entry(category6, R.string.PluginAnalysisNameMessagesDelete, R.string.PluginAnalysisStatementMessagesDelete)), TuplesKt.to("contacts", new Entry(category6, R.string.PluginAnalysisNameContacts, R.string.PluginAnalysisStatementContacts)), TuplesKt.to("forward_messages", new Entry(category6, R.string.PluginAnalysisNameForwardMessages, R.string.PluginAnalysisStatementForwardMessages)));
    }

    public final Category category(AnalyzerReport.Statement statement) {
        Category category;
        Entry entry = entries.get(statement.getId());
        return (entry == null || (category = entry.getCategory()) == null) ? Category.OTHER : category;
    }

    public final String name(AnalyzerReport.Statement statement) {
        String string;
        Entry entry = entries.get(statement.getId());
        return (entry == null || (string = LocaleController.getString(entry.getNameRes())) == null) ? statement.getTitle() : string;
    }

    public final String description(AnalyzerReport.Statement statement) {
        Entry entry = entries.get(statement.getId());
        if (entry != null) {
            return LocaleController.getString(entry.getDescriptionRes());
        }
        return null;
    }

    public final List<String> values(AnalyzerReport report, AnalyzerReport.Statement statement) {
        int i;
        List<String> listEmptyList;
        List<AnalyzerReport.Finding> listFindingsOf = report.findingsOf(statement);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listFindingsOf.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            AnalyzerReport.Finding finding = (AnalyzerReport.Finding) it.next();
            List<String> capabilities = finding.getCapabilities();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : capabilities) {
                if (statement.getCapabilities().contains((String) obj)) {
                    arrayList2.add(obj);
                }
            }
            if (arrayList2.contains("network")) {
                if (StringsKt.startsWith$default(finding.getRule(), "net.", false, 2, (Object) null) && (StringsKt.contains$default((CharSequence) finding.getRule(), (CharSequence) "endpoints", false, 2, (Object) null) || Intrinsics.areEqual(finding.getRule(), "net.raw_ip"))) {
                    List<String> values = finding.getValues();
                    AnalysisCatalog analysisCatalog = INSTANCE;
                    listEmptyList = new ArrayList<>(CollectionsKt.collectionSizeOrDefault(values, 10));
                    Iterator<T> it2 = values.iterator();
                    while (it2.hasNext()) {
                        listEmptyList.add(analysisCatalog.hostOf((String) it2.next()));
                    }
                } else {
                    listEmptyList = CollectionsKt.emptyList();
                }
            } else if (!arrayList2.isEmpty()) {
                int size = arrayList2.size();
                while (true) {
                    if (i < size) {
                        Object obj2 = arrayList2.get(i);
                        i++;
                        if (SYMBOL_VALUES.contains((String) obj2)) {
                            List<String> values2 = finding.getValues();
                            AnalysisCatalog analysisCatalog2 = INSTANCE;
                            listEmptyList = new ArrayList<>();
                            Iterator<T> it3 = values2.iterator();
                            while (it3.hasNext()) {
                                String strSymbolName$TMessagesProj = analysisCatalog2.symbolName$TMessagesProj((String) it3.next());
                                if (strSymbolName$TMessagesProj != null) {
                                    listEmptyList.add(strSymbolName$TMessagesProj);
                                }
                            }
                        }
                    } else if (arrayList2.contains("third_party_code")) {
                        listEmptyList = finding.getValues();
                    } else {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                }
            } else if (arrayList2.contains("third_party_code")) {
                listEmptyList = finding.getValues();
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList, listEmptyList);
        }
        ArrayList arrayList3 = new ArrayList();
        int size2 = arrayList.size();
        while (i < size2) {
            Object obj3 = arrayList.get(i);
            i++;
            if (((String) obj3).length() > 0) {
                arrayList3.add(obj3);
            }
        }
        return CollectionsKt.distinct(arrayList3);
    }

    public final List<String> notes(AnalyzerReport.Statement statement) {
        String string = null;
        String string2 = statement.getRules().contains("tl.unresolved_request") ? LocaleController.getString(R.string.PluginAnalysisUnresolvedRequests) : null;
        if (Intrinsics.areEqual(statement.getId(), "java_hooks") && (statement.getRules().contains("hooks.unresolved") || statement.getRules().contains("hooks.external"))) {
            string = LocaleController.getString(R.string.PluginAnalysisUnresolvedHooks);
        }
        return CollectionsKt.listOfNotNull((Object[]) new String[]{string2, string});
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/exteragram/messenger/plugins/analyzer/AnalysisCatalog$Limit;", _UrlKt.FRAGMENT_ENCODE_SET, "iconRes", _UrlKt.FRAGMENT_ENCODE_SET, "title", _UrlKt.FRAGMENT_ENCODE_SET, MediaTrack.ROLE_SUBTITLE, "<init>", "(ILjava/lang/String;Ljava/lang/String;)V", "getIconRes", "()I", "getTitle", "()Ljava/lang/String;", "getSubtitle", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Limit {
        private final int iconRes;
        private final String subtitle;
        private final String title;

        public Limit(int i, String str, String str2) {
            this.iconRes = i;
            this.title = str;
            this.subtitle = str2;
        }

        public final int getIconRes() {
            return this.iconRes;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }

        public final String getTitle() {
            return this.title;
        }
    }

    public final List<Limit> limits(AnalyzerReport report) {
        boolean z;
        List<String> limits = report.getLimits();
        boolean z2 = true;
        if ((limits instanceof Collection) && limits.isEmpty()) {
            z = false;
        } else {
            Iterator<T> it = limits.iterator();
            while (true) {
                if (it.hasNext()) {
                    String str = (String) it.next();
                    if (StringsKt.startsWith$default(str, "timeout", false, 2, (Object) null) || StringsKt.startsWith$default(str, "unanalyzed", false, 2, (Object) null)) {
                        z = true;
                    }
                } else {
                    z = false;
                }
            }
        }
        List<String> limits2 = report.getLimits();
        if (!(limits2 instanceof Collection) || !limits2.isEmpty()) {
            Iterator<T> it2 = limits2.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z2 = false;
                    break;
                }
                String str2 = (String) it2.next();
                if (!StringsKt.startsWith$default(str2, "timeout", false, 2, (Object) null) && !StringsKt.startsWith$default(str2, "unanalyzed", false, 2, (Object) null)) {
                    break;
                }
            }
        } else {
            z2 = false;
            break;
        }
        return CollectionsKt.listOfNotNull((Object[]) new Limit[]{z ? new Limit(R.drawable.plugin_analysis_timer, LocaleController.getString(R.string.PluginAnalysisLimitTime), LocaleController.getString(R.string.PluginAnalysisLimitTimeInfo)) : null, z2 ? new Limit(R.drawable.plugin_analysis_question, LocaleController.getString(R.string.PluginAnalysisLimitIncomplete), LocaleController.getString(R.string.PluginAnalysisLimitIncompleteInfo)) : null});
    }

    public final String symbolName$TMessagesProj(String value) {
        String string = StringsKt.trim((CharSequence) StringsKt.substringAfter$default(StringsKt.substringBefore$default(value, " (", (String) null, 2, (Object) null), ':', (String) null, 2, (Object) null)).toString();
        if (string.length() == 0 || StringsKt.startsWith$default(string, "<", false, 2, (Object) null) || Intrinsics.areEqual(string, "?")) {
            return null;
        }
        String strSubstringBefore$default = StringsKt.substringBefore$default(string, '#', (String) null, 2, (Object) null);
        String strSubstringAfter = StringsKt.substringAfter(string, '#', _UrlKt.FRAGMENT_ENCODE_SET);
        List<String> listSplit$default = StringsKt.split$default((CharSequence) StringsKt.substringAfterLast$default(strSubstringBefore$default, '.', (String) null, 2, (Object) null), new char[]{Typography.dollar}, false, 0, 6, (Object) null);
        String strTlMethod = tlMethod(listSplit$default);
        if (strTlMethod != null) {
            if (strSubstringAfter.length() == 0 || Intrinsics.areEqual(strSubstringAfter, "<init>")) {
                return strTlMethod;
            }
            return strTlMethod + "." + strSubstringAfter;
        }
        String strJoinToString$default = CollectionsKt.joinToString$default(listSplit$default, ".", null, null, 0, null, null, 62, null);
        if (strSubstringAfter.length() == 0 || Intrinsics.areEqual(strSubstringAfter, "<init>")) {
            return strJoinToString$default;
        }
        return strJoinToString$default + "." + strSubstringAfter;
    }

    private final String tlMethod(List<String> parts) {
        String strRemovePrefix;
        String str = (String) CollectionsKt.last((List) parts);
        if (StringsKt.startsWith$default(str, "TL_", false, 2, (Object) null)) {
            strRemovePrefix = StringsKt.removePrefix(str, (CharSequence) "TL_");
        } else {
            if (parts.size() < 2 || !StringsKt.startsWith$default(parts.get(parts.size() - 2), "TL_", false, 2, (Object) null)) {
                return null;
            }
            strRemovePrefix = StringsKt.removePrefix(parts.get(parts.size() - 2), (CharSequence) "TL_") + "_" + str;
        }
        String str2 = strRemovePrefix;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str2, '_', 0, false, 6, (Object) null);
        if (iIndexOf$default <= 0 || iIndexOf$default >= str2.length() - 1) {
            return str2;
        }
        return str2.substring(0, iIndexOf$default) + "." + str2.substring(iIndexOf$default + 1);
    }

    private final String hostOf(String value) {
        return StringsKt.trim((CharSequence) StringsKt.substringBefore$default(StringsKt.substringBefore$default(StringsKt.substringBefore$default(StringsKt.substringAfter$default(value, "://", (String) null, 2, (Object) null), '/', (String) null, 2, (Object) null), '?', (String) null, 2, (Object) null), ':', (String) null, 2, (Object) null)).toString();
    }
}
