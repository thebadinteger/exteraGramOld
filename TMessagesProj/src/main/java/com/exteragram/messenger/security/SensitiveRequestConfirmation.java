package com.exteragram.messenger.security;

import android.app.Activity;
import android.app.Dialog;
import android.util.SparseIntArray;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_payments;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_wallet;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.LaunchActivity;

public final class SensitiveRequestConfirmation {
    private static final SparseIntArray CATEGORIES;
    private static final HashSet<Integer> MERGEABLE;
    private static SensitiveRequestSheet currentSheet;

    private static native int[] native_protectedConstructors();

    public static native void native_resolve(int i, int i2, long j, boolean z);

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        CATEGORIES = sparseIntArray;
        HashSet<Integer> hashSet = new HashSet<>();
        MERGEABLE = hashSet;
        sparseIntArray.put(TL_stars.transferStarGift.constructor, R.string.ProfileGifts);
        sparseIntArray.put(TL_stars.updateStarGiftPrice.constructor, R.string.ProfileGifts);
        sparseIntArray.put(TL_stars.convertStarGift.constructor, R.string.ProfileGifts);
        sparseIntArray.put(TL_stars.upgradeStarGift.constructor, R.string.ProfileGifts);
        sparseIntArray.put(TL_stars.getStarGiftWithdrawalUrl.constructor, R.string.ProfileGifts);
        sparseIntArray.put(TL_stars.withdrawStarGift.constructor, R.string.ProfileGifts);
        sparseIntArray.put(TL_stars.deleteStarGift.constructor, R.string.ProfileGifts);
        sparseIntArray.put(TL_stars.craftStarGift.constructor, R.string.ProfileGifts);
        sparseIntArray.put(TL_stars.TL_payments_sendStarsForm.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TLRPC.TL_payments_sendPaymentForm.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TLRPC.TL_messages_sendPaidReaction.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TL_stars.TL_fulfillStarsSubscription.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TL_stars.TL_changeStarsSubscription.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TLRPC.TL_auth_acceptLoginToken.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TLRPC.TL_auth_exportAuthorization.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TLRPC.TL_auth_resetAuthorizations.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.resetAuthorization.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.resetWebAuthorization.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.resetWebAuthorizations.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.deleteAccount.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.updatePasswordSettings.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.resetPassword.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.changePhone.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.updateConnectedBot.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.acceptAuthorization.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TLRPC.TL_channels_editCreator.constructor, R.string.SensitiveRequestCategoryChatManagement);
        sparseIntArray.put(TLRPC.TL_channels_editAdmin.constructor, R.string.SensitiveRequestCategoryChatManagement);
        sparseIntArray.put(TLRPC.TL_channels_deleteChannel.constructor, R.string.SensitiveRequestCategoryChatManagement);
        sparseIntArray.put(TLRPC.TL_channels_updateUsername.constructor, R.string.SensitiveRequestCategoryChatManagement);
        sparseIntArray.put(TLRPC.TL_channels_toggleUsername.constructor, R.string.SensitiveRequestCategoryChatManagement);
        sparseIntArray.put(TL_account.updateUsername.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.toggleUsername.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TL_account.setAuthorizationTTL.constructor, R.string.SecurityTitle);
        sparseIntArray.put(TLRPC.TL_payments_getStarsRevenueWithdrawalUrl.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TL_wallet.sendTransfer.constructor, R.string.WalletTitle);
        sparseIntArray.put(TL_wallet.exportSecretPhrase.constructor, R.string.WalletTitle);
        sparseIntArray.put(TL_wallet.replaceWallet.constructor, R.string.WalletTitle);
        sparseIntArray.put(TL_wallet.enableBackup.constructor, R.string.WalletTitle);
        sparseIntArray.put(TL_wallet.disableBackup.constructor, R.string.WalletTitle);
        sparseIntArray.put(TL_wallet.tonConnectSubmitConnectResult.constructor, R.string.WalletTitle);
        sparseIntArray.put(TL_wallet.tonConnectSubmitResponse.constructor, R.string.WalletTitle);
        sparseIntArray.put(TLRPC.TL_messages_sendMessage.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TLRPC.TL_messages_sendMedia.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TLRPC.TL_messages_forwardMessages.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TLRPC.TL_messages_sendMultiMedia.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TLRPC.TL_messages_sendInlineBotResult.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TLRPC.TL_channels_searchPosts.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TL_phone.sendGroupCallMessage.constructor, R.string.SensitiveRequestCategoryPayments);
        sparseIntArray.put(TL_payments.TL_sendStarGiftOffer.constructor, R.string.ProfileGifts);
        hashSet.add(Integer.valueOf(TLRPC.TL_messages_sendPaidReaction.constructor));
        hashSet.add(Integer.valueOf(TLRPC.TL_messages_sendMessage.constructor));
        hashSet.add(Integer.valueOf(TLRPC.TL_messages_sendMedia.constructor));
        hashSet.add(Integer.valueOf(TLRPC.TL_messages_sendMultiMedia.constructor));
        hashSet.add(Integer.valueOf(TLRPC.TL_messages_forwardMessages.constructor));
        hashSet.add(Integer.valueOf(TLRPC.TL_messages_sendInlineBotResult.constructor));
    }

    public static void verifyPolicy() {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int i2 : native_protectedConstructors()) {
            hashSet.add(Integer.valueOf(i2));
            if (CATEGORIES.indexOfKey(i2) < 0) {
                sb.append(String.format(Locale.US, " native-only 0x%08x", Integer.valueOf(i2)));
            }
        }
        while (true) {
            SparseIntArray sparseIntArray = CATEGORIES;
            if (i >= sparseIntArray.size()) {
                break;
            }
            if (!hashSet.contains(Integer.valueOf(sparseIntArray.keyAt(i)))) {
                sb.append(String.format(Locale.US, " java-only 0x%08x", Integer.valueOf(sparseIntArray.keyAt(i))));
            }
            i++;
        }
        if (sb.length() <= 0) {
            return;
        }
        throw new IllegalStateException("SensitiveRequestPolicy is out of sync with TL constructors:" + sb);
    }

    public static void request(final int i, final int i2, final int i3, final long j, final long[] jArr, final String[] strArr, final String str) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean();
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                SensitiveRequestConfirmation.$r8$lambda$E_glLP63pazgHXUDMz3wK0626vM(i, i2, j, atomicBoolean, i3, jArr, strArr, str);
            }
        });
    }

    public static void $r8$lambda$E_glLP63pazgHXUDMz3wK0626vM(final int i, final int i2, final long j, final AtomicBoolean atomicBoolean, int i3, long[] jArr, String[] strArr, String str) {
        try {
            BaseFragment lastFragment = LaunchActivity.getLastFragment();
            Activity parentActivity = lastFragment != null ? lastFragment.getParentActivity() : null;
            if (parentActivity != null && !parentActivity.isFinishing()) {
                Utilities.Callback<Boolean> callback = new Utilities.Callback() {
                    @Override // org.telegram.messenger.Utilities.Callback
                    public final void run(Object obj) {
                        SensitiveRequestConfirmation.m2094$r8$lambda$QDdh97xMHcfKYDO3nK96CTiM(i, i2, j, atomicBoolean, (Boolean) obj);
                    }
                };
                if (currentSheet != null && MERGEABLE.contains(Integer.valueOf(i3)) && currentSheet.merge(i, i3, jArr, strArr, callback)) {
                    return;
                }
                SensitiveRequestSheet sensitiveRequestSheet = new SensitiveRequestSheet(parentActivity, lastFragment.getResourceProvider(), i, i3, getCategoryText(i3), str, jArr, strArr, callback);
                currentSheet = sensitiveRequestSheet;
                sensitiveRequestSheet.show();
                return;
            }
            resolve(i, i2, j, false, atomicBoolean);
        } catch (Exception e) {
            FileLog.e(e);
            resolve(i, i2, j, false, atomicBoolean);
        }
    }

    public static void m2094$r8$lambda$QDdh97xMHcfKYDO3nK96CTiM(int i, int i2, long j, AtomicBoolean atomicBoolean, Boolean bool) {
        SensitiveRequestSheet sensitiveRequestSheet = currentSheet;
        if (sensitiveRequestSheet != null && sensitiveRequestSheet.isAnswered()) {
            currentSheet = null;
        }
        resolve(i, i2, j, bool.booleanValue(), atomicBoolean);
    }

    public static void onDialogShown(Dialog dialog) {
        SensitiveRequestSheet sensitiveRequestSheet = currentSheet;
        if (sensitiveRequestSheet == null || sensitiveRequestSheet == dialog) {
            return;
        }
        sensitiveRequestSheet.bringToFront();
    }

    private static void resolve(final int i, final int i2, final long j, final boolean z, AtomicBoolean atomicBoolean) {
        if (atomicBoolean.compareAndSet(false, true)) {
            Utilities.stageQueue.postRunnable(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    SensitiveRequestConfirmation.native_resolve(i, i2, j, z);
                }
            });
        }
    }

    private static String getCategoryText(int i) {
        int i2 = CATEGORIES.get(i, 0);
        if (i2 != 0) {
            return LocaleController.getString(i2);
        }
        return null;
    }
}
