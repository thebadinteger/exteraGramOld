package com.exteragram.messenger.speech.utils;

import com.exteragram.messenger.utils.chats.ChatUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLRPC;

public class MediaLoader implements NotificationCenter.NotificationCenterDelegate {
    private int copiedFiles;
    private final AccountInstance currentAccount;
    private final HashMap<String, MessageObject> loadingMessageObjects = new HashMap<>();
    private final ArrayList<MessageObject> messageObjects;
    private final MessagesStorage.IntCallback onFinishRunnable;
    private CountDownLatch waitingForFile;

    private MediaLoader(AccountInstance accountInstance, ArrayList<MessageObject> arrayList, MessagesStorage.IntCallback intCallback) {
        this.currentAccount = accountInstance;
        this.messageObjects = arrayList;
        this.onFinishRunnable = intCallback;
        accountInstance.getNotificationCenter().addObserver(this, NotificationCenter.fileLoaded);
        accountInstance.getNotificationCenter().addObserver(this, NotificationCenter.fileLoadFailed);
    }

    public static void loadFiles(AccountInstance accountInstance, ArrayList<MessageObject> arrayList, MessagesStorage.IntCallback intCallback) {
        new MediaLoader(accountInstance, arrayList, intCallback).start();
    }

    public void start() {
        new Thread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$start$0();
            }
        }).start();
    }

    public void lambda$start$0() {
        try {
            ChatUtils chatUtils = ChatUtils.getInstance();
            int size = this.messageObjects.size();
            for (int i = 0; i < size; i++) {
                MessageObject messageObject = this.messageObjects.get(i);
                if (chatUtils.getPathToMessage(messageObject) == null) {
                    this.waitingForFile = new CountDownLatch(1);
                    addMessageToLoad(messageObject);
                    this.waitingForFile.await();
                }
                if (chatUtils.getPathToMessage(messageObject) != null) {
                    this.copiedFiles++;
                }
            }
            checkIfFinished();
        } catch (Exception e) {
            FileLog.e(e);
        }
    }

    private void checkIfFinished() {
        if (this.loadingMessageObjects.isEmpty()) {
            AndroidUtilities.runOnUIThread(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$checkIfFinished$2();
                }
            });
        }
    }

    public void lambda$checkIfFinished$2() {
        try {
            if (this.onFinishRunnable != null) {
                AndroidUtilities.runOnUIThread(new Runnable() {
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$checkIfFinished$1();
                    }
                });
            }
        } catch (Exception e) {
            FileLog.e(e);
        }
        this.currentAccount.getNotificationCenter().removeObserver(this, NotificationCenter.fileLoaded);
        this.currentAccount.getNotificationCenter().removeObserver(this, NotificationCenter.fileLoadFailed);
    }

    public void lambda$checkIfFinished$1() {
        this.onFinishRunnable.run(this.copiedFiles);
    }

    private void addMessageToLoad(final MessageObject messageObject) {
        AndroidUtilities.runOnUIThread(new Runnable() {
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$addMessageToLoad$3(messageObject);
            }
        });
    }

    public void lambda$addMessageToLoad$3(MessageObject messageObject) {
        TLRPC.Document document = messageObject.getDocument();
        if (document == null) {
            return;
        }
        this.loadingMessageObjects.put(FileLoader.getAttachFileName(document), messageObject);
        this.currentAccount.getFileLoader().loadFile(document, messageObject, 0, messageObject.shouldEncryptPhotoOrVideo() ? 2 : 0);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int i, int i2, Object... objArr) {
        if (i == NotificationCenter.fileLoaded || i == NotificationCenter.fileLoadFailed) {
            if (this.loadingMessageObjects.remove((String) objArr[0]) != null) {
                this.waitingForFile.countDown();
            }
        }
    }
}
