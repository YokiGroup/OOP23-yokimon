package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.DrawQueueImpl;
import io.github.yokigroup.view.render.Painter;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.notification.NotificationVisitorImpl;
import io.github.yokigroup.view.render.RenderState;

import java.util.Set;

public class ModelObserverImpl implements ModelObserver {
    private final DrawObserver drawObs;
    private final DrawSetObserver drawSetObs;
    private final FightObserver fightObserver;
    private final NotificationVisitorImpl notificationObs;
    private final DrawQueue worldDrawQueue = new DrawQueueImpl();
    private final DrawQueue fightDrawQueue = new DrawQueueImpl();

    public DrawObserver getDrawObs() {
        return drawObs;
    }

    public ModelObserverImpl(final Painter painter) {
        this.drawObs = new DrawObserver(painter, worldDrawQueue);
        this.drawSetObs = new DrawSetObserver(painter, worldDrawQueue);
        this.fightObserver = new FightObserver(painter, fightDrawQueue);
        this.notificationObs = new NotificationVisitorImpl(painter);
        painter.setPaintState(RenderState.WORLD);
        painter.changeDrawQueue(worldDrawQueue);
    }

    @Override
    public void addSpritePublisher(final RenderState state, final Publisher<SpriteData> spriteObs) {
        spriteObs.addObserver(drawObs);
    }

    @Override
    public void addSpritePublishers(final RenderState state, final Publisher<Set<SpriteData>> spriteObs) {
        spriteObs.addObserver(drawSetObs);
    }

    @Override
    public void addFightPublisher(final Publisher<Fight> fightObs) {
        fightObs.addObserver(fightObserver);
    }

    @Override
    public void addNotificationPublisher(final Publisher<Notification> notificationPub) {
        notificationPub.addObserver(notificationObs);
    }
}
