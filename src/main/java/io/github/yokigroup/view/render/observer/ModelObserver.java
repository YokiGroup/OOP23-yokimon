package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.render.RenderState;

import java.util.Set;

/**
 * Used for hooking {@link Publisher}s coming from the view to Observers contained in the view.
 */
public interface ModelObserver {

    /**
     * Adds a sprite publisher to listen to.
     * @param state {@link RenderState} to render the sprite in
     * @param spriteObs observer to listen to
     */
    void addSpritePublisher(RenderState state, Publisher<SpriteData> spriteObs);

    /**
     * Adds a set of sprite publisher to listen to.
     * @param state {@link RenderState} to render the sprite in
     * @param spriteObs observers to listen to
     */
    void addSpritePublishers(RenderState state, Publisher<Set<SpriteData>> spriteObs);

    /**
     * Adds a fight publisher to listen to, Has to be updated every time the fight advances.
     * @param fightObs fight observer to listen to
     */
    void addFightPublisher(Publisher<Fight> fightObs);

    /**
     * Adds a notification publisher to listen to.
     * @param notificationPub notification observer to listen to
     */
    void addNotificationPublisher(Publisher<Notification> notificationPub);

    /**
     * Adds a {@link RenderState} publisher to listen to.
     * @param renderStatePublisher render state publisher to listen to
     */
    void addStateChangePublisher(Publisher<RenderState> renderStatePublisher);
}
