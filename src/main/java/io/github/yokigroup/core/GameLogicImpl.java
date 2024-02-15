package io.github.yokigroup.core;

import io.github.yokigroup.event.MessageHandler;

public final class GameLogicImpl implements GameLogic {
    private final MessageHandler handler = new GameMessageHandler();

    @Override
    public void start() {

    }
}
