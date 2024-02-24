package io.github.yokigroup.event.submodule;

import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.GameEndSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.GameStateSubmoduleAbs;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.notification.DeathNotification;
import io.github.yokigroup.view.notification.DeathNotificationImpl;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.GameMap;

/**
 * Implementation of {@link GameEndSubmoduleAbs}.
 */
public final class GameEndSubmodule extends GameEndSubmoduleAbs {
    private final Publisher<SpriteData> deathScreenPub = new PublisherImpl<>();
    private final Publisher<SpriteData> victoryScreenPub = new PublisherImpl<>();
    private final Publisher<Notification> notificationPub = new PublisherImpl<>();
    private final Vector2 gameMapVec = Vector2Impl.castPair(GameMap.TILE_DIMENSIONS);
    private static String gameScreenURLRoot = "io/github/yokigroup/view/textures/";
    private final SpriteData deathScreenSprite =
            new SpriteData(
                    gameScreenURLRoot + "death_screen.png",
                    gameMapVec.scale(0.5),
                    gameMapVec,
                    -100
            );
    private final SpriteData victoryScreenSprite =
            new SpriteData(
                    gameScreenURLRoot + "victory_screen.png",
                    gameMapVec.scale(0.5),
                    gameMapVec,
                    -100
            );
    private boolean gameEnded;

    /**
     * @param handler  MessageHandler to call in order to query other submodules.
     * @param modelObs {@link ModelObserver} used to instantiate the submodule
     */
    public GameEndSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
        gameEnded = false;
        modelObs.addSpritePublisher(RenderState.DEATH, deathScreenPub);
        modelObs.addSpritePublisher(RenderState.VICTORY, victoryScreenPub);
        modelObs.addNotificationPublisher(notificationPub);
        deathScreenPub.notifyObservers(deathScreenSprite);
        victoryScreenPub.notifyObservers(victoryScreenSprite);
    }

    private void comunicateStateChange(final GameStateSubmoduleAbs.GameState state) {
        handler().<GameStateSubmodule>handle(GameStateSubmodule.class, s -> s.setGameState(state));
    }

    @Override
    public void triggerDeathGameGO() {
        comunicateStateChange(GameStateSubmoduleAbs.GameState.GAMEOVER);
        notificationPub.notifyObservers(new DeathNotificationImpl(DeathNotification.Cause.DEFEATED_IN_BATTLE));
    }

    @Override
    public void triggerBattleWithNoYokimonsGO() {
        comunicateStateChange(GameStateSubmoduleAbs.GameState.GAMEOVER);
        notificationPub.notifyObservers(new DeathNotificationImpl(DeathNotification.Cause.UNPREPARED_FOR_BATTLE));
    }

    @Override
    public boolean gameEnded() {
        return gameEnded;
    }

    @Override
    public void killGame() {
        gameEnded = true;
    }

    @Override
    public void triggerVictory() {
        comunicateStateChange(GameStateSubmoduleAbs.GameState.VICTORY);
    }
}
