package com.exteragram.messenger.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.ui.PopupUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.Theme;

@SuppressLint({"ViewConstructor"})
public abstract class TranslateBeforeSendWrapper extends ActionBarMenuSubItem {
    public abstract void onClick();

    public TranslateBeforeSendWrapper(final Context context, boolean z, boolean z2, Theme.ResourcesProvider resourcesProvider) {
        super(context, z, z2, resourcesProvider);
        setTextAndIcon(LocaleController.getString(R.string.TranslateTo), R.drawable.msg_translate);
        setSubtext(TranslatorUtils.getSendTargetLanguageTitle());
        setMinimumWidth(AndroidUtilities.dp(196.0f));
        setItemHeight(56);
        setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0(view);
            }
        });
        setOnLongClickListener(new View.OnLongClickListener() {
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view) {
                return this.f$0.lambda$new$1(context, view);
            }
        });
        setRightIcon(R.drawable.msg_arrowright);
        getRightIcon().setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$2(context, view);
            }
        });
    }

    public void lambda$new$0(View view) {
        onClick();
    }

    public boolean lambda$new$1(Context context, View view) {
        return showDialog(context);
    }

    public void lambda$new$2(Context context, View view) {
        showDialog(context);
    }

    private boolean showDialog(Context context) {
        CharSequence[] targetLanguageTitles = TranslatorUtils.getTargetLanguageTitles();
        CharSequence[] charSequenceArr = new CharSequence[targetLanguageTitles.length];
        System.arraycopy(targetLanguageTitles, 0, charSequenceArr, 0, targetLanguageTitles.length);
        PopupUtils.showDialog(charSequenceArr, LocaleController.getString(R.string.Language), TranslatorUtils.getSendTargetLanguageIndex(), context, new PopupUtils.OnItemClickListener() {
            @Override // com.exteragram.messenger.utils.ui.PopupUtils.OnItemClickListener
            public final void onClick(int i) {
                this.f$0.lambda$showDialog$3(i);
            }
        });
        return true;
    }

    public void lambda$showDialog$3(int i) {
        TranslatorUtils.setSendTargetLanguage(TranslatorUtils.getTargetLanguageCodeByIndex(i));
        setSubtext(TranslatorUtils.getSendTargetLanguageTitle());
    }
}
