package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.render.RenderState;

import java.util.Set;

public interface ModelObserver {
    void addSpritePublisher(RenderState state, Publisher<SpriteData> spriteObs);
    void addSpritePublishers(RenderState state, Publisher<Set<SpriteData>> spriteObs);
    void addFightPublisher(Publisher<Fight> fightObs);
    void addNotificationPublisher(Publisher<Notification> notificationPub);
}
