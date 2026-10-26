package com.exteragram.messenger.plugins.catalog.ui;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.TabIconsMode;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.plugins.Plugin;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.plugins.catalog.CatalogPlugin;
import com.exteragram.messenger.plugins.catalog.PluginCatalogController;
import com.exteragram.messenger.plugins.catalog.PluginCategory;
import com.exteragram.messenger.plugins.catalog.PluginRepositoryLinks;
import com.exteragram.messenger.plugins.catalog.db.PluginMetadataEntity;
import com.exteragram.messenger.plugins.catalog.db.PluginRepositoryEntity;
import com.exteragram.messenger.plugins.ui.components.EmptyPluginsView;
import com.exteragram.messenger.plugins.utils.PluginsWatchdog;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.utils.AppUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.exteragram.messenger.utils.ui.ChatHeaderUiHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.enums.EnumEntries;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationBadge;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.FilterTabsView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PopupSwipeBackLayout;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;

@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 i2\u00020\u00012\u00020\u0002:\u0001iB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010/\u001a\u00020\u000fH\u0016J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0014J\b\u00104\u001a\u000205H\u0002J\u0010\u00106\u001a\u0002052\u0006\u00107\u001a\u00020\u0014H\u0002J\u001c\u00108\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0018\u0010:\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u0010;\u001a\u00020<H\u0014J\u0010\u0010=\u001a\u0002052\u0006\u0010>\u001a\u00020\fH\u0002J\b\u0010?\u001a\u00020\fH\u0016J\b\u0010@\u001a\u000205H\u0016J5\u0010A\u001a\u0002052\u0006\u0010B\u001a\u00020\u00142\u0006\u0010C\u001a\u00020\u00142\u0016\u0010D\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010F0E\"\u0004\u0018\u00010FH\u0016¢\u0006\u0002\u0010GJ\b\u0010H\u001a\u000205H\u0002J\b\u0010I\u001a\u00020\fH\u0016J\b\u0010J\u001a\u000205H\u0002J\n\u0010K\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010L\u001a\u000205H\u0002J\u0012\u0010M\u001a\u0002052\b\b\u0002\u0010>\u001a\u00020\fH\u0002J(\u0010N\u001a\u0002052\u0016\u0010O\u001a\u0012\u0012\u0004\u0012\u00020Q0Pj\b\u0012\u0004\u0012\u00020Q`R2\u0006\u0010S\u001a\u00020TH\u0014J(\u0010U\u001a\u0002052\u0016\u0010O\u001a\u0012\u0012\u0004\u0012\u00020Q0Pj\b\u0012\u0004\u0012\u00020Q`R2\u0006\u0010V\u001a\u00020\u0014H\u0002J \u0010W\u001a\u00020Q2\u0006\u0010X\u001a\u00020\t2\u0006\u0010Y\u001a\u00020\u00142\u0006\u0010Z\u001a\u00020\fH\u0002J\u0010\u0010[\u001a\u0002052\u0006\u0010X\u001a\u00020\\H\u0002J\u001c\u0010]\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0010\u0010^\u001a\u00020\u00142\u0006\u0010X\u001a\u00020\tH\u0002J\b\u0010_\u001a\u00020\u0011H\u0002J0\u0010`\u001a\u0002052\u0006\u0010a\u001a\u00020Q2\u0006\u0010b\u001a\u0002012\u0006\u0010c\u001a\u00020\u00142\u0006\u0010d\u001a\u00020$2\u0006\u0010e\u001a\u00020$H\u0014J0\u0010f\u001a\u00020\f2\u0006\u0010a\u001a\u00020Q2\u0006\u0010b\u001a\u0002012\u0006\u0010c\u001a\u00020\u00142\u0006\u0010d\u001a\u00020$2\u0006\u0010e\u001a\u00020$H\u0014J\u0010\u0010g\u001a\u00020\f2\u0006\u0010h\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020$X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020$X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u0014\u0010)\u001a\u00020$X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0014\u0010+\u001a\u00020,8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.¨\u0006j"}, d2 = {"Lcom/exteragram/messenger/plugins/catalog/ui/PluginCatalogActivity;", "Lcom/exteragram/messenger/preferences/BasePreferencesActivity;", "Lorg/telegram/messenger/NotificationCenter$NotificationCenterDelegate;", "channelId", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(J)V", PluginsConstants.PLUGINS, _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/plugins/catalog/CatalogPlugin;", "sortedPlugins", "loaded", _UrlKt.FRAGMENT_ENCODE_SET, "searching", "query", _UrlKt.FRAGMENT_ENCODE_SET, "emptyView", "Lcom/exteragram/messenger/plugins/ui/components/EmptyPluginsView;", "requested", "Ljava/util/HashSet;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/HashSet;", "installing", "repositoryUsername", "otherItem", "Lorg/telegram/ui/ActionBar/ActionBarMenuItem;", "avatarContainer", "Lorg/telegram/ui/Components/ChatAvatarContainer;", "filterTabsView", "Lorg/telegram/ui/Components/FilterTabsView;", "tabCategories", "Lcom/exteragram/messenger/plugins/catalog/PluginCategory;", "selectedCategory", "sortOrder", "refreshing", "pinnedHeaderHeight", _UrlKt.FRAGMENT_ENCODE_SET, "getPinnedHeaderHeight", "()F", "pinnedHeaderMargin", "getPinnedHeaderMargin", "pinnedHeaderGap", "getPinnedHeaderGap", "controller", "Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController;", "getController", "()Lcom/exteragram/messenger/plugins/catalog/PluginCatalogController;", "getTitle", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "showMenu", _UrlKt.FRAGMENT_ENCODE_SET, "setSortOrder", "order", "sortPlugins", "source", "createPinnedHeader", "glassFactory", "Lorg/telegram/ui/Components/blur3/BlurredBackgroundDrawableViewFactory;", "updateTabs", "animated", "onFragmentCreate", "onFragmentDestroy", "didReceivedNotification", "id", "account", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(II[Ljava/lang/Object;)V", "reloadCatalog", "needDelayOpenAnimation", "refresh", "repositoryLink", "updateHeader", "updateList", "fillItems", PluginsConstants.Settings.ITEMS, "Ljava/util/ArrayList;", "Lorg/telegram/ui/Components/UItem;", "Lkotlin/collections/ArrayList;", "adapter", "Lorg/telegram/ui/Components/UniversalAdapter;", "addSkeletons", NotificationBadge.NewHtcHomeBadger.COUNT, "createItem", "plugin", "state", "detailsAvailable", "confirmDelete", "Lcom/exteragram/messenger/plugins/Plugin;", "filterByQuery", "getState", "getEmptyView", "onClick", PluginsConstants.Settings.ITEM, PluginsConstants.Settings.VIEW, "position", "x", "y", "onLongClick", "onBackPressed", "invoked", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPluginCatalogActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginCatalogActivity.kt\ncom/exteragram/messenger/plugins/catalog/ui/PluginCatalogActivity\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,594:1\n14143#2,3:595\n1#3:598\n1080#4:599\n1080#4:600\n1068#4:601\n777#4:602\n873#4:603\n1807#4,3:604\n874#4:607\n1924#4,3:608\n1834#4,4:611\n777#4:615\n873#4,2:616\n777#4:618\n873#4,2:619\n1834#4,4:621\n1300#4,2:625\n1315#4,4:627\n777#4:631\n873#4,2:632\n1915#4,2:634\n777#4:636\n873#4,2:637\n1915#4,2:639\n777#4:641\n873#4,2:642\n1807#4,3:644\n777#4:647\n873#4:648\n1807#4,3:649\n874#4:652\n296#4,2:653\n*S KotlinDebug\n*F\n+ 1 PluginCatalogActivity.kt\ncom/exteragram/messenger/plugins/catalog/ui/PluginCatalogActivity\n*L\n174#1:595,3\n224#1:599\n225#1:600\n226#1:601\n269#1:602\n269#1:603\n269#1:604,3\n269#1:607\n277#1:608,3\n371#1:611,4\n390#1:615\n390#1:616,2\n391#1:618\n391#1:619,2\n392#1:621,4\n404#1:625,2\n404#1:627,4\n405#1:631\n405#1:632,2\n408#1:634,2\n411#1:636\n411#1:637,2\n414#1:639,2\n417#1:641\n417#1:642,2\n426#1:644,3\n481#1:647\n481#1:648\n484#1:649,3\n481#1:652\n295#1:653,2\n*E\n"})
public final class PluginCatalogActivity extends BasePreferencesActivity implements NotificationCenter.NotificationCenterDelegate {
    private static final float HEADER_INSET = 6.0f;
    private static final int MAX_SKELETONS = 4;
    private static final int NEW_PERIOD = 86400;
    private static final int OTHER_ITEM_ID = 3;
    private static final long PROGRESS_DELAY = 300;
    private static final int SEARCH_ITEM_ID = 0;
    private static final int SKELETON_ID_START = 100;
    private static final int SORT_NAME = 3;
    private static final int SORT_NEWEST = 2;
    private static final int SORT_POPULAR = 1;
    private static final int SORT_UPDATED = 0;
    private static final float TABS_HEIGHT = 36.0f;
    private static final float TABS_PADDING = 7.0f;
    private ChatAvatarContainer avatarContainer;
    private final long channelId;
    private EmptyPluginsView emptyView;
    private FilterTabsView filterTabsView;
    private boolean loaded;
    private ActionBarMenuItem otherItem;
    private final float pinnedHeaderGap;
    private String query;
    private boolean refreshing;
    private String repositoryUsername;
    private boolean searching;
    private PluginCategory selectedCategory;
    private static final int[] SORT_TITLES = {R.string.PluginCatalogSortUpdated, R.string.PluginCatalogSortPopular, R.string.PluginCatalogSortNewest, R.string.PluginCatalogSortName};
    private static final Regex VERSION_NUMBER = new Regex("\\d+(?:\\.\\d+)*");
    private List<CatalogPlugin> plugins = CollectionsKt.emptyList();
    private List<CatalogPlugin> sortedPlugins = CollectionsKt.emptyList();
    private final HashSet<Integer> requested = new HashSet<>();
    private final HashSet<Integer> installing = new HashSet<>();
    private List<? extends PluginCategory> tabCategories = CollectionsKt.emptyList();
    private int sortOrder = PluginCatalogController.INSTANCE.getSortOrder();
    private final float pinnedHeaderHeight = 50.0f;
    private final float pinnedHeaderMargin = 4.0f;

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean needDelayOpenAnimation() {
        return true;
    }

    public PluginCatalogActivity(long j) {
        this.channelId = j;
        setPinnedHeaderVisible(false, false);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public float getPinnedHeaderHeight() {
        return this.pinnedHeaderHeight;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public float getPinnedHeaderMargin() {
        return this.pinnedHeaderMargin;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public float getPinnedHeaderGap() {
        return this.pinnedHeaderGap;
    }

    private final PluginCatalogController getController() {
        return PluginCatalogController.INSTANCE.getInstance(this.currentAccount);
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public String getTitle() {
        String str;
        TLRPC.Chat chat = getMessagesController().getChat(Long.valueOf(this.channelId));
        return (chat == null || (str = chat.title) == null) ? LocaleController.getString(R.string.PluginCatalog) : str;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context context) {
        View viewCreateView = super.createView(context);
        ActionBarMenuItem actionBarMenuItemSearchListener = this.actionBar.menu.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new ActionBarMenuItem.ActionBarMenuItemSearchListener() {
            @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
            public void onSearchExpand() {
                PluginCatalogActivity.this.searching = true;
                PluginCatalogActivity.this.setPinnedHeaderVisible(false, true);
                ActionBarMenuItem actionBarMenuItem = PluginCatalogActivity.this.otherItem;
                if (actionBarMenuItem != null) {
                    actionBarMenuItem.setVisibility(8);
                }
                ChatAvatarContainer chatAvatarContainer = PluginCatalogActivity.this.avatarContainer;
                if (chatAvatarContainer != null) {
                    chatAvatarContainer.setVisibility(8);
                }
                PluginCatalogActivity.this.getListView$TMessagesProj().adapter.update(true);
                PluginCatalogActivity.this.getListView$TMessagesProj().scrollToPosition(0);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
            public void onSearchCollapse() {
                PluginCatalogActivity.this.searching = false;
                PluginCatalogActivity.this.query = null;
                PluginCatalogActivity.this.updateTabs(true);
                ActionBarMenuItem actionBarMenuItem = PluginCatalogActivity.this.otherItem;
                if (actionBarMenuItem != null) {
                    actionBarMenuItem.setVisibility(0);
                }
                ChatAvatarContainer chatAvatarContainer = PluginCatalogActivity.this.avatarContainer;
                if (chatAvatarContainer != null) {
                    chatAvatarContainer.setVisibility(0);
                }
                PluginCatalogActivity.this.getListView$TMessagesProj().adapter.update(true);
                PluginCatalogActivity.this.getListView$TMessagesProj().scrollToPosition(0);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
            public void onTextChanged(EditText editText) {
                PluginCatalogActivity.this.query = editText.getText().toString();
                PluginCatalogActivity.this.getListView$TMessagesProj().adapter.update(true);
                PluginCatalogActivity.this.getListView$TMessagesProj().scrollToPosition(0);
            }
        });
        actionBarMenuItemSearchListener.setSearchPaddingStart(7);
        actionBarMenuItemSearchListener.setSearchFieldHint(LocaleController.getString(R.string.Search));
        ChatAvatarContainer chatAvatarContainer = new ChatAvatarContainer(context, null, false, this.resourceProvider);
        ChatHeaderUiHelper.setupGlassAvatarContainer(chatAvatarContainer);
        chatAvatarContainer.setOccupyStatusBar(!AndroidUtilities.isTablet());
        chatAvatarContainer.setEnabled(false);
        this.avatarContainer = chatAvatarContainer;
        this.actionBar.setTitle(null);
        this.actionBar.addView(this.avatarContainer, 0, LayoutHelper.createFrame(-2, -1.0f, 51, ChatHeaderUiHelper.getAvatarContainerLeftMargin(false) + HEADER_INSET, 0.0f, 106.0f, 0.0f));
        updateHeader();
        ActionBarMenuItem actionBarMenuItemAddItem = this.actionBar.menu.addItem(3, R.drawable.ic_ab_other);
        actionBarMenuItemAddItem.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.otherItem = actionBarMenuItemAddItem;
        this.actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick() {
            @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
            public void onItemClick(int id) {
                if (id == -1) {
                    PluginCatalogActivity.this.finishFragment();
                } else {
                    if (id != 3) {
                        return;
                    }
                    PluginCatalogActivity.this.showMenu();
                }
            }
        });
        getListView$TMessagesProj().setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 1) {
                    AndroidUtilities.hideKeyboard(PluginCatalogActivity.this.getParentActivity().getCurrentFocus());
                }
            }
        });
        this.fragmentView = viewCreateView;
        updateTabs(false);
        return viewCreateView;
    }

    public final void showMenu() {
        PopupSwipeBackLayout swipeBack;
        ActionBarMenuItem actionBarMenuItem = this.otherItem;
        if (actionBarMenuItem == null) {
            return;
        }
        final ItemOptions itemOptionsMakeOptions = ItemOptions.makeOptions((BaseFragment) this, (View) actionBarMenuItem, true);
        final ItemOptions itemOptionsMakeSwipeback = itemOptionsMakeOptions.makeSwipeback();
        itemOptionsMakeSwipeback.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), itemOptionsMakeOptions::dismiss);
        itemOptionsMakeSwipeback.addGap();
        int[] iArr = SORT_TITLES;
        int length = iArr.length;
        int i = 0;
        final int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            itemOptionsMakeSwipeback.addChecked(this.sortOrder == i2, LocaleController.getString(iArr[i]), new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogActivity.$r8$lambda$95wWcPDTwOsjiWX7198dOAv7Cew(itemOptionsMakeOptions, this, i2);
                }
            });
            i++;
            i2 = i3;
        }
        int i4 = R.drawable.msg_filter;
        String string = LocaleController.getString(R.string.SortBy);
        int[] iArr2 = SORT_TITLES;
        int i5 = this.sortOrder;
        itemOptionsMakeOptions.add(i4, string, LocaleController.getString((i5 < 0 || i5 >= iArr2.length) ? iArr2[0] : iArr2[i5]), new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                itemOptionsMakeOptions.openSwipeback(itemOptionsMakeSwipeback);
            }
        });
        ActionBarMenuSubItem last = itemOptionsMakeOptions.getLast();
        if (last != null) {
            last.setRightIcon(R.drawable.msg_arrowright);
        }
        itemOptionsMakeOptions.addGap();
        itemOptionsMakeOptions.add(R.drawable.msg_retry, LocaleController.getString(R.string.Refresh), new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.refresh();
            }
        });
        itemOptionsMakeOptions.add(R.drawable.msg_channel, LocaleController.getString(R.string.OpenChannel2), new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogActivity pluginCatalogActivity = this.f$0;
                pluginCatalogActivity.presentFragment(ChatActivity.of(-pluginCatalogActivity.channelId));
            }
        });
        final String strRepositoryLink = repositoryLink();
        if (strRepositoryLink != null) {
            itemOptionsMakeOptions.add(R.drawable.msg_share, LocaleController.getString(R.string.ShareLink), new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.showShareLinkAlert(strRepositoryLink, R.string.PluginRepositoryLinkToSavedMessages, R.string.PluginRepositoryLinkToUser, R.string.PluginRepositoryLinkToChats);
                }
            });
            itemOptionsMakeOptions.add(R.drawable.msg_copy, LocaleController.getString(R.string.CopyLink), new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogActivity.$r8$lambda$7OidOmaUXzXxe7EiQSbswcBT3Oo(strRepositoryLink, this);
                }
            });
        }
        itemOptionsMakeOptions.setGravity(5);
        itemOptionsMakeOptions.setSwipebackGravity(false, false);
        ViewGroup layout = itemOptionsMakeOptions.getLayout();
        ActionBarPopupWindow.ActionBarPopupWindowLayout actionBarPopupWindowLayout = layout instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout ? (ActionBarPopupWindow.ActionBarPopupWindowLayout) layout : null;
        if (actionBarPopupWindowLayout != null && (swipeBack = actionBarPopupWindowLayout.getSwipeBack()) != null) {
            swipeBack.setStickToRight(true);
        }
        itemOptionsMakeOptions.setOnTopOfScrim();
        Rect rect = new Rect();
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.popup_fixed_alert4);
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        itemOptionsMakeOptions.translate(0.0f, -rect.top);
        itemOptionsMakeOptions.show();
    }

    public static void $r8$lambda$95wWcPDTwOsjiWX7198dOAv7Cew(ItemOptions itemOptions, PluginCatalogActivity pluginCatalogActivity, int i) {
        itemOptions.dismiss();
        pluginCatalogActivity.setSortOrder(i);
    }

    public static void $r8$lambda$7OidOmaUXzXxe7EiQSbswcBT3Oo(String str, PluginCatalogActivity pluginCatalogActivity) {
        if (AndroidUtilities.addToClipboard(str)) {
            BulletinFactory.of(pluginCatalogActivity).createCopyBulletin(LocaleController.getString(R.string.LinkCopied)).show();
        }
    }

    private final void setSortOrder(int order) {
        if (this.sortOrder == order) {
            return;
        }
        this.sortOrder = order;
        PluginCatalogController.INSTANCE.setSortOrder(order);
        this.sortedPlugins = sortPlugins(this.plugins);
        getListView$TMessagesProj().adapter.update(true);
        getListView$TMessagesProj().scrollToPosition(0);
    }

    private final List<CatalogPlugin> sortPlugins(List<CatalogPlugin> source) {
        int i = this.sortOrder;
        if (i == 1) {
            return CollectionsKt.sortedWith(source, new Comparator() {
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(((CatalogPlugin) t2).getViews()), Integer.valueOf(((CatalogPlugin) t).getViews()));
                }
            });
        }
        if (i != 2) {
            return i != 3 ? source : CollectionsKt.sortedWith(source, new Comparator() {
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    String fileName;
                    String fileName2;
                    CatalogPlugin catalogPlugin = (CatalogPlugin) t;
                    PluginMetadataEntity metadata = catalogPlugin.getMetadata();
                    if (metadata == null || (fileName = metadata.getName()) == null) {
                        fileName = catalogPlugin.getFileName();
                    }
                    Locale locale = Locale.ROOT;
                    String lowerCase = fileName.toLowerCase(locale);
                    CatalogPlugin catalogPlugin2 = (CatalogPlugin) t2;
                    PluginMetadataEntity metadata2 = catalogPlugin2.getMetadata();
                    if (metadata2 == null || (fileName2 = metadata2.getName()) == null) {
                        fileName2 = catalogPlugin2.getFileName();
                    }
                    return ComparisonsKt.compareValues(lowerCase, fileName2.toLowerCase(locale));
                }
            });
        }
        return CollectionsKt.sortedWith(source, new Comparator() {
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((CatalogPlugin) t2).getPublishedAt()), Integer.valueOf(((CatalogPlugin) t).getPublishedAt()));
            }
        });
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public View createPinnedHeader(Context context, BlurredBackgroundDrawableViewFactory glassFactory) {
        FilterTabsView filterTabsView = new FilterTabsView(context, this.resourceProvider);
        BlurredBackgroundDrawable blurredBackgroundDrawableCreate = glassFactory.create(filterTabsView, BlurredBackgroundProviderImpl.topPanel(this.resourceProvider));
        blurredBackgroundDrawableCreate.setRadius(AndroidUtilities.dp(18.0f));
        blurredBackgroundDrawableCreate.setPadding(AndroidUtilities.dp(6.666f));
        filterTabsView.setPadding(0, AndroidUtilities.dp(TABS_PADDING), 0, AndroidUtilities.dp(TABS_PADDING));
        filterTabsView.setBlurredBackground(blurredBackgroundDrawableCreate);
        filterTabsView.setFillWidth(true);
        filterTabsView.setForcedTabIcons(TabIconsMode.ICONS_AND_TITLES);
        filterTabsView.setDelegate(new FilterTabsView.FilterTabsViewDelegate() {
            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public boolean didSelectTab(FilterTabsView.TabView tabView, boolean selected) {
                return false;
            }

            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public int getTabCounter(int tabId) {
                return 0;
            }

            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public boolean isTabMenuVisible() {
                return false;
            }

            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public void onDeletePressed(int id) {
            }

            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public void onPageReorder(int fromId, int toId) {
            }

            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public void onPageScrolled(float progress) {
            }

            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public void onTabSelected(FilterTabsView.Tab tab, boolean z, boolean z2) {
                super.onTabSelected(tab, z, z2);
            }

            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public void onPageSelected(FilterTabsView.Tab tab, boolean forward) {
                PluginCatalogActivity pluginCatalogActivity = this.this$0;
                pluginCatalogActivity.selectedCategory = (PluginCategory) CollectionsKt.getOrNull(pluginCatalogActivity.tabCategories, tab.id);
                this.this$0.updateHeader();
                this.this$0.getListView$TMessagesProj().adapter.update(true);
                this.this$0.getListView$TMessagesProj().scrollToPosition(0);
            }

            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public void onSamePageSelected() {
                this.this$0.getListView$TMessagesProj().smoothScrollToPosition(0);
            }

            @Override // org.telegram.ui.Components.FilterTabsView.FilterTabsViewDelegate
            public boolean canPerformActions() {
                return !this.this$0.searching;
            }
        });
        this.filterTabsView = filterTabsView;
        return filterTabsView;
    }

    public final void updateTabs(boolean animated) {
        FilterTabsView filterTabsView = this.filterTabsView;
        if (filterTabsView == null) {
            return;
        }
        EnumEntries<PluginCategory> entries = PluginCategory.getEntries();
        ArrayList arrayList = new ArrayList();
        for (PluginCategory pluginCategory : entries) {
            PluginCategory pluginCategory2 = pluginCategory;
            List<CatalogPlugin> list = this.plugins;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if (((CatalogPlugin) it.next()).getCategories().contains(pluginCategory2)) {
                        arrayList.add(pluginCategory);
                        break;
                    }
                }
            }
        }
        if (!CollectionsKt.contains(arrayList, this.selectedCategory)) {
            this.selectedCategory = null;
        }
        List<? extends PluginCategory> listEmptyList = arrayList.isEmpty() ? CollectionsKt.emptyList() : CollectionsKt.plus((Collection) CollectionsKt.listOf((Object) null), (Iterable) arrayList);
        if (!Intrinsics.areEqual(listEmptyList, this.tabCategories)) {
            this.tabCategories = listEmptyList;
            filterTabsView.removeTabs();
            int i = 0;
            for (Object obj : listEmptyList) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                PluginCategory pluginCategory3 = (PluginCategory) obj;
                if (pluginCategory3 == null) {
                    filterTabsView.addTab(i, 0, LocaleController.getString(R.string.FilterAllChatsShort), R.drawable.filter_plugins);
                } else {
                    filterTabsView.addTab(i, pluginCategory3.ordinal() + 1, LocaleController.getString(pluginCategory3.getTitleRes()), pluginCategory3.getIconRes());
                }
                i = i2;
            }
            PluginCategory pluginCategory4 = this.selectedCategory;
            filterTabsView.selectTabWithStableId(pluginCategory4 != null ? pluginCategory4.ordinal() + 1 : 0);
            filterTabsView.finishAddingTabs(animated);
        }
        setPinnedHeaderVisible((listEmptyList.isEmpty() || this.searching) ? false : true, animated);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.pluginCatalogUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.pluginsUpdated);
        reloadCatalog();
        getController().loadRepositories(new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogActivity.$r8$lambda$9Fu3eNrQwtszbVFfGMx2vUaYl80(this.f$0, (List) obj);
            }
        });
        PluginCatalogController.syncRepository$default(getController(), this.channelId, false, true, null, 8, null);
        return super.onFragmentCreate();
    }

    public static Unit $r8$lambda$9Fu3eNrQwtszbVFfGMx2vUaYl80(PluginCatalogActivity pluginCatalogActivity, List list) {
        Object next;
        Iterator it = list.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((PluginRepositoryEntity) next).getChannelId() != pluginCatalogActivity.channelId);
        PluginRepositoryEntity pluginRepositoryEntity = (PluginRepositoryEntity) next;
        pluginCatalogActivity.repositoryUsername = pluginRepositoryEntity != null ? pluginRepositoryEntity.getUsername() : null;
        updateList$default(pluginCatalogActivity, false, 1, null);
        return Unit.INSTANCE;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.pluginCatalogUpdated);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.pluginsUpdated);
        super.onFragmentDestroy();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.pluginCatalogUpdated) {
            if (Intrinsics.areEqual(ArraysKt.getOrNull(args, 0), Long.valueOf(this.channelId))) {
                reloadCatalog();
            }
        } else if (id == NotificationCenter.pluginsUpdated) {
            updateList$default(this, false, 1, null);
        }
    }

    private final void reloadCatalog() {
        getController().loadCatalog(this.channelId, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogActivity.m1917$r8$lambda$GPv9Tp7Nj05VI4L7R1PgUVtuqI(this.f$0, (List) obj);
            }
        });
    }

    public static Unit m1917$r8$lambda$GPv9Tp7Nj05VI4L7R1PgUVtuqI(PluginCatalogActivity pluginCatalogActivity, List list) {
        pluginCatalogActivity.plugins = list;
        pluginCatalogActivity.sortedPlugins = pluginCatalogActivity.sortPlugins(list);
        boolean z = pluginCatalogActivity.loaded;
        pluginCatalogActivity.loaded = true;
        pluginCatalogActivity.updateList(z);
        if (!z) {
            pluginCatalogActivity.resumeDelayedFragmentAnimation();
        }
        return Unit.INSTANCE;
    }

    public final void refresh() {
        if (this.refreshing) {
            return;
        }
        this.refreshing = true;
        updateHeader();
        getController().syncRepository(this.channelId, true, true, new Function1() {
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PluginCatalogActivity.$r8$lambda$wJJzob2DYmJmkcKHBdC61XQjudE(this.f$0, (Integer) obj);
            }
        });
    }

    public static Unit $r8$lambda$wJJzob2DYmJmkcKHBdC61XQjudE(PluginCatalogActivity pluginCatalogActivity, Integer num) {
        Bulletin bulletinCreateSimpleBulletin;
        pluginCatalogActivity.refreshing = false;
        if (pluginCatalogActivity.fragmentView == null) {
            return Unit.INSTANCE;
        }
        pluginCatalogActivity.updateHeader();
        if (num == null) {
            bulletinCreateSimpleBulletin = BulletinFactory.of(pluginCatalogActivity).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.PluginCatalogRefreshFailed));
        } else {
            bulletinCreateSimpleBulletin = num.intValue() == 0 ? BulletinFactory.of(pluginCatalogActivity).createSimpleBulletin(R.raw.done, LocaleController.getString(R.string.PluginCatalogUpToDate)) : BulletinFactory.of(pluginCatalogActivity).createSimpleBulletin(R.raw.done, LocaleController.formatPluralString("PluginCatalogNewVersions", num.intValue(), new Object[0]));
        }
        bulletinCreateSimpleBulletin.show();
        return Unit.INSTANCE;
    }

    private final String repositoryLink() {
        String publicUsername;
        TLRPC.Chat chat = getMessagesController().getChat(Long.valueOf(this.channelId));
        if (chat == null || (publicUsername = ChatObject.getPublicUsername(chat)) == null) {
            publicUsername = this.repositoryUsername;
        }
        if (publicUsername == null) {
            return null;
        }
        if (publicUsername.length() <= 0) {
            publicUsername = null;
        }
        if (publicUsername != null) {
            return PluginRepositoryLinks.INSTANCE.build(publicUsername);
        }
        return null;
    }

    public final void updateHeader() {
        int size;
        ChatAvatarContainer chatAvatarContainer = this.avatarContainer;
        if (chatAvatarContainer == null) {
            return;
        }
        TLRPC.Chat chat = getMessagesController().getChat(Long.valueOf(this.channelId));
        if (chat != null) {
            chatAvatarContainer.setTitle(chat.title, chat.scam, chat.fake, chat.verified, false, BadgesController.INSTANCE.getBadge(chat), chat.emoji_status, false);
            chatAvatarContainer.setChatAvatar(chat);
        } else {
            chatAvatarContainer.setTitle(getTitle());
        }
        PluginCategory pluginCategory = this.selectedCategory;
        List<CatalogPlugin> list = this.plugins;
        if (pluginCategory == null) {
            size = list.size();
        } else {
            List<CatalogPlugin> list2 = list;
            if ((list2 instanceof Collection) && list2.isEmpty()) {
                size = 0;
            } else {
                Iterator<T> it = list2.iterator();
                int i = 0;
                while (it.hasNext()) {
                    if (((CatalogPlugin) it.next()).getCategories().contains(pluginCategory) && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
                size = i;
            }
        }
        chatAvatarContainer.setSubtitle((!this.loaded || this.refreshing) ? LocaleController.getString(R.string.PluginCatalogUpdating) : LocaleController.formatPluralString("PluginCatalogPlugins", size, new Object[0]));
    }

    public static void updateList$default(PluginCatalogActivity pluginCatalogActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        pluginCatalogActivity.updateList(z);
    }

    private final void updateList(boolean animated) {
        if (this.fragmentView != null) {
            updateTabs(animated);
            updateHeader();
            boolean zCanScrollVertically = getListView$TMessagesProj().canScrollVertically(-1);
            getListView$TMessagesProj().adapter.update(animated);
            if (zCanScrollVertically) {
                return;
            }
            getListView$TMessagesProj().scrollToPosition(0);
        }
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void fillItems(ArrayList<UItem> items, UniversalAdapter adapter) {
        ArrayList arrayList;
        int iMin;
        List arrayList2;
        List arrayList3;
        String string;
        int i;
        boolean zIsMetadataAvailable = getController().isMetadataAvailable();
        PluginCategory pluginCategory = this.selectedCategory;
        if (pluginCategory == null || this.searching) {
            pluginCategory = null;
        }
        List<CatalogPlugin> listFilterByQuery = filterByQuery(this.sortedPlugins);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj : listFilterByQuery) {
            CatalogPlugin catalogPlugin = (CatalogPlugin) obj;
            if (pluginCategory == null || catalogPlugin.getCategories().contains(pluginCategory)) {
                arrayList4.add(obj);
            }
        }
        int i2 = 0;
        if (zIsMetadataAvailable) {
            arrayList = new ArrayList();
            int size = arrayList4.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj2 = arrayList4.get(i3);
                i3++;
                if (!((CatalogPlugin) obj2).isPending()) {
                    arrayList.add(obj2);
                }
            }
        } else {
            arrayList = arrayList4;
        }
        if (zIsMetadataAvailable && !this.searching && pluginCategory == null) {
            if (arrayList4.isEmpty()) {
                i = 0;
            } else {
                int size2 = arrayList4.size();
                i = 0;
                int i4 = 0;
                while (i4 < size2) {
                    Object obj3 = arrayList4.get(i4);
                    i4++;
                    if (((CatalogPlugin) obj3).isPending() && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            iMin = Math.min(i, 4);
        } else {
            iMin = 0;
        }
        if (arrayList.isEmpty() && iMin == 0) {
            if (this.searching || (this.loaded && !getController().isSyncing(this.channelId))) {
                items.add(UItem.asFullscreenCustom(getEmptyView(), AndroidUtilities.dp(this.searching ? 148.0f : 74.0f), true).setTransparent(true));
                return;
            }
            items.add(UItem.asHeader(LocaleController.getString(R.string.PluginCatalogAllPlugins)));
            addSkeletons(items, 4);
            items.add(UItem.asShadow());
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList, 10)), 16));
        int size3 = arrayList.size();
        int i5 = 0;
        while (i5 < size3) {
            Object obj4 = arrayList.get(i5);
            i5++;
            linkedHashMap.put(obj4, Integer.valueOf(getState((CatalogPlugin) obj4)));
        }
        if (this.searching) {
            arrayList2 = CollectionsKt.emptyList();
        } else {
            arrayList2 = new ArrayList();
            int size4 = arrayList.size();
            int i6 = 0;
            while (i6 < size4) {
                Object obj5 = arrayList.get(i6);
                i6++;
                Integer num = (Integer) linkedHashMap.get((CatalogPlugin) obj5);
                if (num != null && num.intValue() == 1) {
                    arrayList2.add(obj5);
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            items.add(UItem.asHeader(LocaleController.getString(R.string.PluginCatalogUpdates)));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                items.add(createItem((CatalogPlugin) it.next(), 1, zIsMetadataAvailable));
            }
            items.add(UItem.asShadow());
        }
        if (this.searching) {
            arrayList3 = CollectionsKt.emptyList();
        } else {
            arrayList3 = new ArrayList();
            int size5 = arrayList.size();
            int i7 = 0;
            while (i7 < size5) {
                Object obj6 = arrayList.get(i7);
                i7++;
                Integer num2 = (Integer) linkedHashMap.get((CatalogPlugin) obj6);
                if (num2 != null && num2.intValue() == 2) {
                    arrayList3.add(obj6);
                }
            }
        }
        if (!arrayList3.isEmpty()) {
            items.add(UItem.asHeader(LocaleController.getString(R.string.PluginCatalogInstalled)));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                items.add(createItem((CatalogPlugin) it2.next(), 2, zIsMetadataAvailable));
            }
            items.add(UItem.asShadow());
        }
        ArrayList arrayList5 = new ArrayList();
        int size6 = arrayList.size();
        int i8 = 0;
        while (i8 < size6) {
            Object obj7 = arrayList.get(i8);
            i8++;
            CatalogPlugin catalogPlugin2 = (CatalogPlugin) obj7;
            if (!arrayList2.contains(catalogPlugin2) && !arrayList3.contains(catalogPlugin2)) {
                arrayList5.add(obj7);
            }
        }
        if (arrayList5.isEmpty() && iMin == 0) {
            return;
        }
        if (pluginCategory == null || (string = LocaleController.getString(pluginCategory.getTitleRes())) == null) {
            string = LocaleController.getString(R.string.PluginCatalogAllPlugins);
        }
        items.add(UItem.asHeader(string));
        int size7 = arrayList5.size();
        int i9 = 0;
        while (i9 < size7) {
            Object obj8 = arrayList5.get(i9);
            i9++;
            CatalogPlugin catalogPlugin3 = (CatalogPlugin) obj8;
            Integer num3 = (Integer) linkedHashMap.get(catalogPlugin3);
            items.add(createItem(catalogPlugin3, num3 != null ? num3.intValue() : 0, zIsMetadataAvailable));
        }
        addSkeletons(items, iMin);
        if (!zIsMetadataAvailable && !arrayList5.isEmpty()) {
            int size8 = arrayList5.size();
            while (i2 < size8) {
                Object obj9 = arrayList5.get(i2);
                i2++;
                if (((CatalogPlugin) obj9).isPending()) {
                    items.add(UItem.asShadow(LocaleController.getString(R.string.PluginCatalogEngineUnavailable)));
                    return;
                }
            }
        }
        items.add(UItem.asShadow());
    }

    private final void addSkeletons(ArrayList<UItem> items, int count) {
        for (int i = 0; i < count; i++) {
            items.add(CatalogPluginSkeletonCell.Factory.INSTANCE.of(i + 100, i));
        }
    }

    private final UItem createItem(CatalogPlugin plugin, int state, boolean detailsAvailable) {
        ?? ReplaceArrows;
        PluginMetadataEntity metadata = plugin.getMetadata();
        if (metadata == null) {
            ReplaceArrows = 0;
        } else {
            if (metadata.getError() != null) {
                metadata = null;
            }
            if (metadata != null) {
                ReplaceArrows = metadata.getVersion();
            } else {
                ReplaceArrows = 0;
            }
        }
        String pluginId = plugin.getPluginId();
        final Plugin plugin2 = pluginId != null ? PluginsController.INSTANCE.getInstance().getPlugins().get(pluginId) : null;
        if (state == 1) {
            if ((plugin2 != null ? plugin2.getVersion() : null) != null && ReplaceArrows != 0) {
                ReplaceArrows = LocaleUtils.replaceArrows(getContext(), plugin2.getVersion() + " -> " + ReplaceArrows, R.drawable.msg_mini_arrow_mediathin);
            }
        }
        return CatalogPluginCell.Factory.INSTANCE.of(plugin, state, ReplaceArrows, state == 0 && plugin.getPublishedAt() >= getConnectionsManager().getCurrentTime() - NEW_PERIOD, this.installing.contains(Integer.valueOf(plugin.getMessageId())), detailsAvailable, plugin2 != null ? new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.confirmDelete(plugin2);
            }
        } : null);
    }

    public final void confirmDelete(final Plugin plugin) {
        if (plugin.getIsNotResponding()) {
            PluginsWatchdog.INSTANCE.showNotRespondingAlert(plugin);
            return;
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(getParentActivity(), this.resourceProvider).setTitle(LocaleController.getString(R.string.PluginDelete)).setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.PluginDeleteInfo, plugin.getName()))).setPositiveButton(LocaleController.getString(R.string.Delete), new AlertDialog.OnButtonClickListener() {
            @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
            public final void onClick(AlertDialog alertDialog, int i) {
                PluginsController.INSTANCE.getInstance().deletePlugin(plugin.getId(), new Utilities.Callback() {
                    @Override // org.telegram.messenger.Utilities.Callback
                    public final void run(Object obj) {
                        AndroidUtilities.runOnUIThread(new Runnable() {
                            @Override // java.lang.Runnable
                            public final void run() {
                                PluginCatalogActivity.m1918$r8$lambda$cM4PboU5balkDLMmOWWNWeEGF0(this.f$0, str);
                            }
                        });
                    }
                });
            }
        }).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        showDialog(alertDialogCreate);
        View button = alertDialogCreate.getButton(-1);
        TextView textView = button instanceof TextView ? (TextView) button : null;
        if (textView != null) {
            textView.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        }
    }

    public static void m1918$r8$lambda$cM4PboU5balkDLMmOWWNWeEGF0(PluginCatalogActivity pluginCatalogActivity, String str) {
        if (pluginCatalogActivity.fragmentView == null || str == null) {
            return;
        }
        BulletinFactory.of(pluginCatalogActivity).createSimpleBulletin(R.raw.error, str).show();
    }

    private final List<CatalogPlugin> filterByQuery(List<CatalogPlugin> source) {
        String string;
        String str = this.query;
        String lowerCase = (str == null || (string = StringsKt.trim((CharSequence) str).toString()) == null) ? null : string.toLowerCase(Locale.ROOT);
        if (!this.searching || lowerCase == null || lowerCase.length() == 0) {
            return source;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : source) {
            CatalogPlugin catalogPlugin = (CatalogPlugin) obj;
            PluginMetadataEntity metadata = catalogPlugin.getMetadata();
            List listListOfNotNull = CollectionsKt.listOfNotNull((Object[]) new String[]{metadata != null ? metadata.getName() : null, metadata != null ? metadata.getAuthor() : null, metadata != null ? metadata.getDescription() : null, catalogPlugin.getFileName()});
            if (!(listListOfNotNull instanceof Collection) || !listListOfNotNull.isEmpty()) {
                Iterator it = listListOfNotNull.iterator();
                while (it.hasNext()) {
                    if (StringsKt.contains$default((CharSequence) ((String) it.next()).toLowerCase(Locale.ROOT), (CharSequence) lowerCase, false, 2, (Object) null)) {
                        arrayList.add(obj);
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    private final int getState(CatalogPlugin plugin) {
        Plugin plugin2;
        String version;
        String version2;
        String pluginId = plugin.getPluginId();
        if (pluginId == null || (plugin2 = PluginsController.INSTANCE.getInstance().getPlugins().get(pluginId)) == null) {
            return 0;
        }
        PluginMetadataEntity metadata = plugin.getMetadata();
        if (metadata != null && (version = metadata.getVersion()) != null) {
            Regex regex = VERSION_NUMBER;
            MatchResult matchResultFind$default = Regex.find$default(regex, version, 0, 2, null);
            String value = matchResultFind$default != null ? matchResultFind$default.getValue() : null;
            if (value != null && (version2 = plugin2.getVersion()) != null) {
                MatchResult matchResultFind$default2 = Regex.find$default(regex, version2, 0, 2, null);
                String value2 = matchResultFind$default2 != null ? matchResultFind$default2.getValue() : null;
                if (value2 != null) {
                    try {
                        if (AppUtils.compareVersionValues(value, value2) > 0) {
                            return 1;
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        }
        return 2;
    }

    private final EmptyPluginsView getEmptyView() {
        EmptyPluginsView emptyPluginsView = this.emptyView;
        if (emptyPluginsView == null) {
            emptyPluginsView = new EmptyPluginsView(getContext(), this.resourceProvider);
            this.emptyView = emptyPluginsView;
        }
        int i = this.searching ? 1 : 2;
        if (!Intrinsics.areEqual(emptyPluginsView.getTag(), Integer.valueOf(i))) {
            MediaDataController.getInstance(this.currentAccount).setPlaceholderImage(emptyPluginsView.getBackupImageView(), "AnimatedEmojies", this.searching ? "🔎" : "📂", "100_100");
            emptyPluginsView.setText(LocaleController.getString(this.searching ? R.string.PluginsNotFound : R.string.PluginCatalogEmpty));
            emptyPluginsView.setTag(Integer.valueOf(i));
        }
        return emptyPluginsView;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public void onClick(UItem item, View view, int position, float x, float y) {
        Object obj = item.object;
        final CatalogPlugin catalogPlugin = obj instanceof CatalogPlugin ? (CatalogPlugin) obj : null;
        if (catalogPlugin != null && this.requested.add(Integer.valueOf(catalogPlugin.getMessageId()))) {
            final Runnable runnable = new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    PluginCatalogActivity.$r8$lambda$Fk8tAccDhXrq3IDUEZ_cBjR5heU(this.f$0, catalogPlugin);
                }
            };
            AndroidUtilities.runOnUIThread(runnable, PROGRESS_DELAY);
            getController().downloadPlugin(catalogPlugin, new Function2() {
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return PluginCatalogActivity.$r8$lambda$bF0mqo8V4Ho3jLi4zL7JLIdyI9M(runnable, this, catalogPlugin, (File) obj2, (MessageObject) obj3);
                }
            });
        }
    }

    public static void $r8$lambda$Fk8tAccDhXrq3IDUEZ_cBjR5heU(PluginCatalogActivity pluginCatalogActivity, CatalogPlugin catalogPlugin) {
        pluginCatalogActivity.installing.add(Integer.valueOf(catalogPlugin.getMessageId()));
        updateList$default(pluginCatalogActivity, false, 1, null);
    }

    public static Unit $r8$lambda$bF0mqo8V4Ho3jLi4zL7JLIdyI9M(Runnable runnable, PluginCatalogActivity pluginCatalogActivity, CatalogPlugin catalogPlugin, File file, MessageObject messageObject) {
        AndroidUtilities.cancelRunOnUIThread(runnable);
        pluginCatalogActivity.requested.remove(Integer.valueOf(catalogPlugin.getMessageId()));
        if (pluginCatalogActivity.fragmentView == null) {
            return Unit.INSTANCE;
        }
        if (pluginCatalogActivity.installing.remove(Integer.valueOf(catalogPlugin.getMessageId()))) {
            updateList$default(pluginCatalogActivity, false, 1, null);
        }
        if (file == null || messageObject == null) {
            BulletinFactory.of(pluginCatalogActivity).createSimpleBulletin(R.raw.error, LocaleController.formatString(R.string.PluginCatalogDownloadFailed, catalogPlugin.getFileName())).show();
            return Unit.INSTANCE;
        }
        PluginsController.INSTANCE.getInstance().showInstallDialog(pluginCatalogActivity, messageObject, file.getAbsolutePath());
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.preferences.BasePreferencesActivity
    public boolean onLongClick(UItem item, View view, int position, float x, float y) {
        Object obj = item.object;
        final CatalogPlugin catalogPlugin = obj instanceof CatalogPlugin ? (CatalogPlugin) obj : null;
        if (catalogPlugin == null) {
            return false;
        }
        ItemOptions itemOptionsMakeOptions = ItemOptions.makeOptions(this, view);
        itemOptionsMakeOptions.add(R.drawable.msg_message, LocaleController.getString(R.string.ViewInChat), new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                PluginCatalogActivity pluginCatalogActivity = this.f$0;
                pluginCatalogActivity.presentFragment(ChatActivity.of(-pluginCatalogActivity.channelId, catalogPlugin.getMessageId()));
            }
        });
        itemOptionsMakeOptions.setScrimViewBackground(getListView$TMessagesProj().getClipBackground(view));
        itemOptionsMakeOptions.setGravity(LocaleController.isRTL ? 3 : 5);
        itemOptionsMakeOptions.setDismissOnMoveOutside(true);
        itemOptionsMakeOptions.show();
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean invoked) {
        if (!this.searching) {
            return super.onBackPressed(invoked);
        }
        if (!invoked) {
            return false;
        }
        this.actionBar.closeSearchField();
        return false;
    }
}
