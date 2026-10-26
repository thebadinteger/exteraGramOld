package com.exteragram.messenger.ai.network.backend;

public interface Backend {
    void execute(BackendRequest backendRequest, BackendSink backendSink);

    int getHistoryCharsLimit();

    int getHistoryMessagesLimit();

    int getMaxOutputTokens();

    float getMaxTemperature();

    boolean supportsImages();
}
