package io.github.yokigroup.view.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.view.observer.notification.Notification;

import java.util.Set;

public interface ModelObserver {
    enum State {
        WORLD,
        BATTLE
    }

    void addWorldSpritePublisher(Publisher<SpriteData> spriteObs);
    void addWorldSpritePublishers(Publisher<Set<SpriteData>> spriteObs);
    void addFightPublisher(Publisher<Fight> fightObs);
    void addNotificationPublisher(Publisher<? extends Notification> notificationPub);
}
