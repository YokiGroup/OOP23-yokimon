package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.DrawQueueImpl;
import io.github.yokigroup.view.render.Painter;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.notification.NotificationVisitorImpl;
import io.github.yokigroup.view.render.RenderState;

import java.util.Set;
import java.util.function.Function;

/**
 * ...
 */
public class ModelObserverImpl implements ModelObserver {
    private final DrawObserver drawObs;
    private final DrawSetObserver drawSetObs;
    private final FightObserver fightObserver;
    private final NotificationVisitorImpl notificationObs;
    private final DrawQueue worldDrawQueue = new DrawQueueImpl();
    private final DrawQueue fightDrawQueue = new DrawQueueImpl();
    private <T, E> EObserver<T> createConversionObserver(final EObserver<E> obs, final Function<T, E> conversionFun) {
        return (lastArg, arg) -> obs.update(conversionFun.apply(lastArg), conversionFun.apply(arg));
    }

    /**
     * ...
     * @param painter ...
     */
    public ModelObserverImpl(final Painter painter) {
        this.drawObs = new DrawObserver(painter);
        this.drawSetObs = new DrawSetObserver(painter);
        this.fightObserver = new FightObserver(painter);
        this.notificationObs = new NotificationVisitorImpl(painter);
        painter.setPaintState(RenderState.WORLD);
    }

    /**
     * ...
     * @param state ...
     * @param spriteObs ...
     */
    @Override
    public void addSpritePublisher(final RenderState state, final Publisher<SpriteData> spriteObs) {
        spriteObs.addObserver(createConversionObserver(drawObs, s -> new SpriteDataWithState(state, s)));
    }

    /**
     * ...
     * @param state ...
     * @param spriteObs ...
     */
    @Override
    public void addSpritePublishers(final RenderState state, final Publisher<Set<SpriteData>> spriteObs) {
        spriteObs.addObserver(createConversionObserver(drawSetObs, s -> new Pair<>(state, s)));
    }

    /**
     * ...
     * @param fightObs ...
     */
    @Override
    public void addFightPublisher(final Publisher<Fight> fightObs) {
        fightObs.addObserver(fightObserver);
    }

    /**
     * ...
     * @param notificationPub ...
     */
    @Override
    public void addNotificationPublisher(final Publisher<Notification> notificationPub) {
        notificationPub.addObserver(notificationObs);
    }
}
