package com.exteragram.messenger.security;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.TableView;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

public class SensitiveRequestSheet extends BottomSheet {
    private final ButtonWithCounterView allowButton;
    private boolean answered;
    private final ArrayList<Utilities.Callback<Boolean>> callbacks;
    private final CharSequence category;
    private final int constructor;
    private long[] details;
    private final Runnable enableAllowButton;
    private final String method;
    private final SensitivePaymentForms.Payment payment;
    private final TextView subtitle;
    private final TableView tableView;
    private final String[] texts;

    public SensitiveRequestSheet(Context context, Theme.ResourcesProvider resourcesProvider, int i, int i2, CharSequence charSequence, String str, long[] jArr, String[] strArr, Utilities.Callback<Boolean> callback) {
        super(context, false, resourcesProvider);
        this.callbacks = new ArrayList<>();
        this.currentAccount = i;
        this.constructor = i2;
        this.method = str;
        this.details = jArr != null ? jArr : new long[0];
        this.texts = strArr;
        this.category = charSequence;
        String str2 = null;
        int i3 = 0;
        long j = 0;
        while (true) {
            int i4 = i3 + 1;
            long[] jArr2 = this.details;
            if (i4 >= jArr2.length) {
                break;
            }
            long j2 = jArr2[i4];
            long j3 = jArr2[i3];
            if (j3 == 8) {
                j = j2;
            } else if (j3 == 9 && strArr != null && j2 >= 0 && j2 < strArr.length) {
                str2 = strArr[(int) j2];
            }
            i3 += 2;
        }
        this.payment = SensitivePaymentForms.get(this.currentAccount, j, str2);
        this.callbacks.add(callback);
        fixNavigationBar();
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f));
        imageView.setImageResource(R.drawable.sensitive_request_stop);
        imageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_featuredStickers_buttonText), PorterDuff.Mode.SRC_IN));
        imageView.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), getThemedColor(Theme.key_featuredStickers_addButton)));
        linearLayout.addView(imageView, LayoutHelper.createLinear(80, 80, 1, 0, 24, 0, 0));
        TextView textView = new TextView(context);
        textView.setGravity(1);
        textView.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        textView.setTextSize(1, 20.0f);
        textView.setTypeface(AndroidUtilities.bold());
        textView.setText(LocaleController.getString(R.string.SensitiveRequestTitle));
        linearLayout.addView(textView, LayoutHelper.createLinear(-1, -2, 0, 24, 16, 24, 0));
        TextView textView2 = new TextView(context);
        this.subtitle = textView2;
        textView2.setGravity(1);
        int i5 = Theme.key_windowBackgroundWhiteGrayText;
        textView2.setTextColor(getThemedColor(i5));
        textView2.setTextSize(1, 14.0f);
        textView2.setTypeface(AndroidUtilities.regular());
        textView2.setText(charSequence);
        textView2.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        linearLayout.addView(textView2, LayoutHelper.createLinear(-1, -2, 0, 24, 6, 24, 0));
        TableView tableView = new TableView(context, resourcesProvider);
        this.tableView = tableView;
        fillTable();
        linearLayout.addView(tableView, LayoutHelper.createLinear(-1, -2, 0, 16, 20, 16, 0));
        TextView textView3 = new TextView(context);
        textView3.setGravity(1);
        textView3.setTextColor(getThemedColor(i5));
        textView3.setTextSize(1, 13.0f);
        textView3.setTypeface(AndroidUtilities.regular());
        textView3.setText(LocaleController.getString(R.string.SensitiveRequestWarning));
        linearLayout.addView(textView3, LayoutHelper.createLinear(-1, -2, 0, 24, 14, 24, 0));
        ButtonWithCounterView buttonWithCounterView = new ButtonWithCounterView(context, true, resourcesProvider);
        this.allowButton = buttonWithCounterView;
        buttonWithCounterView.setRound();
        buttonWithCounterView.setText(LocaleController.getString(R.string.Allow), false);
        buttonWithCounterView.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0(view);
            }
        });
        this.enableAllowButton = new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$1();
            }
        };
        linearLayout.addView(buttonWithCounterView, LayoutHelper.createLinear(-1, 48, 0, 16, 20, 16, 8));
        ButtonWithCounterView buttonWithCounterView2 = new ButtonWithCounterView(context, false, resourcesProvider);
        buttonWithCounterView2.setRound();
        buttonWithCounterView2.setNeutral();
        buttonWithCounterView2.setText(LocaleController.getString(R.string.Decline), false);
        buttonWithCounterView2.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$2(view);
            }
        });
        linearLayout.addView(buttonWithCounterView2, LayoutHelper.createLinear(-1, 48, 0, 16, 0, 16, 8));
        ScrollView scrollView = new ScrollView(context);
        scrollView.addView(linearLayout);
        setCustomView(scrollView);
        setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.lambda$new$3(dialogInterface);
            }
        });
    }

    public void lambda$new$0(View view) {
        finish(true);
        lambda$new$0();
    }

    public void lambda$new$1() {
        this.allowButton.setEnabled(true);
    }

    public void lambda$new$2(View view) {
        finish(false);
        lambda$new$0();
    }

    public void lambda$new$3(DialogInterface dialogInterface) {
        AndroidUtilities.cancelRunOnUIThread(this.enableAllowButton);
        finish(false);
    }

    public boolean merge(int i, int i2, long[] jArr, String[] strArr, Utilities.Callback<Boolean> callback) {
        if (this.answered || !isShowing() || i != this.currentAccount || i2 != this.constructor || !Arrays.equals(this.texts, strArr)) {
            return false;
        }
        long[] jArr2 = this.details;
        if (jArr == null) {
            jArr = new long[0];
        }
        long[] jArrMergeDetails = mergeDetails(jArr2, jArr);
        if (jArrMergeDetails == null) {
            return false;
        }
        this.details = jArrMergeDetails;
        this.callbacks.add(callback);
        this.subtitle.setText(TextUtils.isEmpty(this.category) ? String.format(Locale.US, "×%d", Integer.valueOf(this.callbacks.size())) : String.format(Locale.US, "%s ×%d", this.category, Integer.valueOf(this.callbacks.size())));
        this.subtitle.setVisibility(0);
        this.tableView.clear();
        fillTable();
        this.allowButton.setEnabled(false);
        AndroidUtilities.cancelRunOnUIThread(this.enableAllowButton);
        AndroidUtilities.runOnUIThread(this.enableAllowButton, 1000L);
        return true;
    }

    private static long[] mergeDetails(long[] jArr, long[] jArr2) {
        if (jArr.length != jArr2.length) {
            return null;
        }
        long[] jArr3 = (long[]) jArr.clone();
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (i2 >= jArr.length) {
                return jArr3;
            }
            long j = jArr[i];
            if (j != jArr2[i]) {
                return null;
            }
            if (j == 5 || j == 6) {
                try {
                    jArr3[i2] = Math.addExact(jArr[i2], jArr2[i2]);
                } catch (ArithmeticException unused) {
                    return null;
                }
            } else if (jArr[i2] != jArr2[i2]) {
                return null;
            }
            i += 2;
        }
    }

    public boolean isAnswered() {
        return this.answered;
    }

    public void bringToFront() {
        Window window;
        if (this.answered || !isShowing() || (window = getWindow()) == null) {
            return;
        }
        View decorView = window.getDecorView();
        ViewGroup.LayoutParams layoutParams = decorView.getLayoutParams();
        if (!decorView.isAttachedToWindow() || layoutParams == null) {
            return;
        }
        WindowManager windowManager = window.getWindowManager();
        try {
            windowManager.removeViewImmediate(decorView);
            windowManager.addView(decorView, layoutParams);
        } catch (Exception e) {
            FileLog.e(e);
        }
    }

    private void finish(boolean z) {
        if (this.answered) {
            return;
        }
        this.answered = true;
        ArrayList arrayList = new ArrayList(this.callbacks);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Utilities.Callback) obj).run(Boolean.valueOf(z));
        }
    }

    private void fillTable() {
        addPeerRow(this.tableView, LocaleController.getString(R.string.Account), UserConfig.getInstance(this.currentAccount).getClientUserId());
        int i = 0;
        while (true) {
            int i2 = i + 1;
            long[] jArr = this.details;
            if (i2 >= jArr.length) {
                break;
            }
            addDetailRow(this.tableView, (int) jArr[i], jArr[i2], this.texts);
            i += 2;
        }
        int i3 = this.constructor;
        if (i3 == 2040056084 || i3 == 755192367) {
            int i4 = 0;
            boolean z = false;
            while (true) {
                int i5 = i4 + 1;
                long[] jArr2 = this.details;
                if (i5 >= jArr2.length) {
                    break;
                }
                z |= jArr2[i4] == 8;
                i4 += 2;
            }
            if (!z) {
                this.tableView.addRow(LocaleController.getString(R.string.SensitiveRequestDebit), LocaleController.getString(R.string.SensitiveRequestUnknownAmount));
            }
        }
        if (TextUtils.isEmpty(this.method)) {
            return;
        }
        this.tableView.addRowMonospaced(LocaleController.getString(R.string.SensitiveRequestMethod), this.method, 13, null);
    }

    private void addDetailRow(TableView tableView, int i, long j, String[] strArr) {
        switch (i) {
            case 1:
                addPeerRow(tableView, LocaleController.getString(R.string.WalletRecipient), j);
                break;
            case 2:
                addPeerRow(tableView, LocaleController.getString(R.string.SensitiveRequestChat), j);
                break;
            case 3:
                addPeerRow(tableView, LocaleController.getString(R.string.SensitiveRequestUser), j);
                break;
            case 4:
                addPeerRow(tableView, LocaleController.getString(R.string.StarsSubscriptionBot), j);
                break;
            case 5:
                tableView.addRow(LocaleController.getString(R.string.SensitiveRequestAmount), StarsIntroActivity.replaceStars(false, (CharSequence) ("XTR " + LocaleController.formatNumber(j, ',')), 0.85f));
                break;
            case 6:
                tableView.addRow(LocaleController.getString(R.string.SensitiveRequestAmount), StarsIntroActivity.replaceStars(true, (CharSequence) ("XTR " + com.exteragram.messenger.inline.math.MathFormat.stripTrailingZerosSafe(BigDecimal.valueOf(j, 9)).toPlainString()), 0.85f));
                break;
            case 7:
                if (strArr != null && j >= 0 && j < strArr.length) {
                    int i2 = (int) j;
                    if (!TextUtils.isEmpty(strArr[i2])) {
                        tableView.addRow(LocaleController.getString(R.string.ActionStarGift), strArr[i2]);
                    }
                    break;
                }
                break;
            case 8:
                CharSequence string = LocaleController.getString(R.string.SensitiveRequestUnknownAmount);
                SensitivePaymentForms.Payment payment = this.payment;
                if (payment != null && "XTR".equals(payment.currency)) {
                    string = StarsIntroActivity.replaceStars(false, (CharSequence) ("XTR " + LocaleController.formatNumber(this.payment.amount, ',')), 0.85f);
                } else {
                    SensitivePaymentForms.Payment payment2 = this.payment;
                    if (payment2 != null && "TON".equals(payment2.currency)) {
                        string = StarsIntroActivity.replaceStars(true, (CharSequence) ("XTR " + com.exteragram.messenger.inline.math.MathFormat.stripTrailingZerosSafe(BigDecimal.valueOf(this.payment.amount, 9)).toPlainString()), 0.85f);
                    }
                }
                tableView.addRow(LocaleController.getString(R.string.SensitiveRequestDebit), string);
                break;
            case 10:
                TL_stars.StarGift starGift = StarsController.getInstance(this.currentAccount).getStarGift(j);
                tableView.addRow(LocaleController.getString(R.string.ActionStarGift), (starGift == null || TextUtils.isEmpty(starGift.title)) ? "ID " + j : starGift.title + " · ID " + j);
                break;
            case 11:
                tableView.addRow(LocaleController.getString(R.string.SensitiveRequestAnonymous), LocaleController.getString(j != 0 ? R.string.CheckPhoneNumberYes : R.string.CheckPhoneNumberNo));
                break;
            case 12:
                tableView.addRow(LocaleController.getString(R.string.SensitiveRequestUpgradeIncluded), LocaleController.getString(j != 0 ? R.string.CheckPhoneNumberYes : R.string.CheckPhoneNumberNo));
                break;
            case 13:
                tableView.addRow(LocaleController.getString(R.string.SensitiveRequestGiftMessage), Long.toString(j));
                break;
            case 14:
                tableView.addRow(LocaleController.getString(R.string.SensitiveRequestSavedGift), Long.toString(j));
                break;
        }
    }

    private void addPeerRow(TableView tableView, CharSequence charSequence, long j) {
        if (j == 0) {
            j = UserConfig.getInstance(this.currentAccount).getClientUserId();
        }
        long j2 = j;
        if (tableView.addRowUser(charSequence, this.currentAccount, j2, null) == null) {
            tableView.addRow(charSequence, "ID " + j2);
        }
    }
}
