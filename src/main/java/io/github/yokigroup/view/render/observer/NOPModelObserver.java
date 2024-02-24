package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.drawable.SpriteData;

import java.util.Set;

/**
 * ModelObserver that does not process the method calls it receives. used for initializing a game logic that does not \
 * comunicate with the view.
 */
public class NOPModelObserver implements ModelObserver {

    @Override
    public void addSpritePublisher(final RenderState state, final Publisher<SpriteData> spriteObs) {
    }

    @Override
    public void addSpritePublishers(final RenderState state, final Publisher<Set<SpriteData>> spriteObs) {
    }

    @Override
    public void addFightPublisher(final Publisher<Fight> fightObs) {
    }

    @Override
    public void addNotificationPublisher(final Publisher<Notification> notificationPub) {
    }

    @Override
    public void addStateChangePublisher(final Publisher<RenderState> renderStatePublisher) {
    }
}
