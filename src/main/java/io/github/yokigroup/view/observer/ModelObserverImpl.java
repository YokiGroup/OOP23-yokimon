package io.github.yokigroup.view.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.view.CanvasPainter;
import io.github.yokigroup.view.Painter;
import io.github.yokigroup.view.observer.notification.Notification;
import io.github.yokigroup.view.observer.notification.NotificationVisitor;
import io.github.yokigroup.view.observer.notification.NotificationVisitorImpl;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashSet;
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
