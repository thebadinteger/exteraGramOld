package com.exteragram.messenger.ai.network.backend;

public interface BackendSink {
    boolean isActive();

    void onCancellable(Runnable runnable);

    void onChunk(String str);

    void onComplete(String str);

    void onError(int i, String str);

    void onThinking();
}
