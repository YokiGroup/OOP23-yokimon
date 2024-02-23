package io.github.yokigroup.event.submodule;

import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.GameOverSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.GameStateSubmoduleAbs;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.notification.DeathNotification;
import io.github.yokigroup.view.notification.DeathNotificationImpl;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.GameMap;

public class GameOverSubmodule extends GameOverSubmoduleAbs {
    private final Publisher<SpriteData> deathScreenPub = new PublisherImpl<>();
    private final Publisher<Notification> notificationPub = new PublisherImpl<>();
    private final Vector2 gameMapVec = Vector2Impl.castPair(GameMap.TILE_DIMENSIONS);
    private final SpriteData deathScreenSprite =
            new SpriteData(
                    "io/github/yokigroup/view/textures/death_screen.png",
                    gameMapVec.scale(0.5),
                    gameMapVec,
                    -100
            );

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     * @param modelObs {@link ModelObserver} used to instantiate the submodule
     */
    public GameOverSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
        modelObs.addSpritePublisher(RenderState.DEATH, deathScreenPub);
        modelObs.addNotificationPublisher(notificationPub);
        deathScreenPub.notifyObservers(deathScreenSprite);
    }

    private void comunicateStateChange() {
        handler().<GameStateSubmodule>handle(GameStateSubmodule.class, s -> s.setGameState(GameStateSubmoduleAbs.GameState.GAMEOVER));
    }

    @Override
    public void triggerDeathGameGO() {
        comunicateStateChange();
        notificationPub.notifyObservers(new DeathNotificationImpl(DeathNotification.Cause.DEFEATED_IN_BATTLE));
    }

    @Override
    public void triggerBattleWithNoYokimonsGO() {
        comunicateStateChange();
        notificationPub.notifyObservers(new DeathNotificationImpl(DeathNotification.Cause.UNPREPARED_FOR_BATTLE));
    }
}
