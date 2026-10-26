package com.exteragram.messenger.feed.ui;

import android.view.View;
import com.exteragram.messenger.feed.FeedConfig;
import com.exteragram.messenger.feed.FeedController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;

public abstract class FeedEmptyState {
    public static void bind(final BaseFragment baseFragment, StickerEmptyView stickerEmptyView) {
        int currentAccount = baseFragment.getCurrentAccount();
        FeedController feedController = FeedController.getInstance(currentAccount);
        FeedConfig feedConfig = FeedConfig.getInstance(currentAccount);
        stickerEmptyView.subtitle.setVisibility(8);
        stickerEmptyView.button.setVisibility(8);
        if (!feedController.hasChannels() || feedController.hasIncludedChannels()) {
            stickerEmptyView.title.setText(LocaleController.getString(R.string.FeedNoPosts));
            return;
        }
        final FeedConfig.Category selectedCategory = feedConfig.getSelectedCategory();
        SpoilersTextView spoilersTextView = stickerEmptyView.title;
        if (selectedCategory != null) {
            spoilersTextView.setText(LocaleController.getString(R.string.FeedCategoryEmpty));
            stickerEmptyView.subtitle.setText(LocaleController.getString(R.string.FeedCategoryEmptyInfo));
            stickerEmptyView.subtitle.setVisibility(0);
            setButton(stickerEmptyView, LocaleController.getString(R.string.FeedCategoryAddChannels), new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    baseFragment.presentFragment(new FeedCategoryEditActivity(selectedCategory.getId(), false));
                }
            });
            return;
        }
        spoilersTextView.setText(LocaleController.getString(R.string.FeedAllChannelsExcluded));
        if (!feedConfig.getVisibleCategories().isEmpty()) {
            stickerEmptyView.subtitle.setText(LocaleController.getString(R.string.FeedAllChannelsExcludedInfo));
            stickerEmptyView.subtitle.setVisibility(0);
        }
        setButton(stickerEmptyView, LocaleController.getString(R.string.FeedSettings), new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                baseFragment.presentFragment(new FeedChannelsActivity());
            }
        });
    }

    private static void setButton(StickerEmptyView stickerEmptyView, CharSequence charSequence, final Runnable runnable) {
        stickerEmptyView.button.setText(charSequence, false);
        stickerEmptyView.button.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                runnable.run();
            }
        });
        stickerEmptyView.button.setVisibility(0);
    }
}
