package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.view.render.painter.Painter;
import io.github.yokigroup.view.notification.Notification;
import io.github.yokigroup.view.notification.NotificationVisitorImpl;
import io.github.yokigroup.view.render.RenderState;

import java.util.Set;
import java.util.function.Function;

/**
 * Implementation of {@link ModelObserver}.
 */
public final class ModelObserverImpl implements ModelObserver {
    private final DrawObserver drawObs;
    private final DrawSetObserver drawSetObs;
    private final FightObserver fightObserver;
    private final NotificationVisitorImpl notificationObs;
    private final RenderStateObserver renderStateObserver;

    private <T, E> EObserver<T> createConversionObserver(final EObserver<E> obs, final Function<T, E> conversionFun) {
        return (lastArg, arg) -> obs.update(conversionFun.apply(lastArg), conversionFun.apply(arg));
    }

    /**
     * @param painter {@link Painter} to issue draw requests to.
     */
    public ModelObserverImpl(final Painter painter) {
        this.drawObs = new DrawObserver(painter);
        this.drawSetObs = new DrawSetObserver(painter);
        this.fightObserver = new FightObserver(painter);
        this.notificationObs = new NotificationVisitorImpl(painter);
        this.renderStateObserver = new RenderStateObserver(painter);
        painter.setPaintState(RenderState.WORLD);
    }

    @Override
    public void addSpritePublisher(final RenderState state, final Publisher<SpriteData> spriteObs) {
        spriteObs.addObserver(createConversionObserver(drawObs, s -> new SpriteDataWithState(state, s)));
    }

    @Override
    public void addSpritePublishers(final RenderState state, final Publisher<Set<SpriteData>> spriteObs) {
        spriteObs.addObserver(createConversionObserver(drawSetObs, s -> new Pair<>(state, s)));
    }

    @Override
    public void addFightPublisher(final Publisher<Fight> fightObs) {
        fightObs.addObserver(fightObserver);
    }

    @Override
    public void addNotificationPublisher(final Publisher<Notification> notificationPub) {
        notificationPub.addObserver(notificationObs);
    }

    @Override
    public void addStateChangePublisher(final Publisher<RenderState> renderStatePublisher) {
        renderStatePublisher.addObserver(renderStateObserver);
    }
}
