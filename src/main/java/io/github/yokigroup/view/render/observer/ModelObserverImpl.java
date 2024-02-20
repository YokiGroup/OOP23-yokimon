package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.view.render.Painter;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.notification.NotificationVisitorImpl;

import java.util.Set;

public class ModelObserverImpl implements ModelObserver {
    private final DrawObserver drawObs;
    private final DrawSetObserver drawSetObs;
    private final NotificationVisitorImpl notificationObs;

    public DrawObserver getDrawObs() {
        return drawObs;
    }

    public ModelObserverImpl(Painter painter) {
        drawObs = new DrawObserver(painter);
        drawSetObs = new DrawSetObserver(painter);
        notificationObs = new NotificationVisitorImpl(painter);
    }

    @Override
    public void addWorldSpritePublisher(Publisher<SpriteData> spriteObs) {
        spriteObs.addObserver(drawObs);
    }

    @Override
    public void addWorldSpritePublishers(Publisher<Set<SpriteData>> spriteObs) {
        spriteObs.addObserver(drawSetObs);
    }

    @Override
    public void addFightPublisher(Publisher<Fight> fightObs) {

    }

    @Override
    public void addNotificationPublisher(Publisher<Notification> notificationPub) {
        notificationPub.addObserver(notificationObs);
    }
}
