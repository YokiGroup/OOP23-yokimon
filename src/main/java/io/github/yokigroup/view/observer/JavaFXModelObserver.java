package io.github.yokigroup.view.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.view.observer.notification.Notification;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashSet;
import java.util.Set;

public class JavaFXModelObserver implements ModelObserver {
    private Set<Publisher<SpriteData>> spritePublishers = new HashSet<>();

    public void init(GraphicsContext gc) {
        JavaFXDrawObserver drawObs = new JavaFXDrawObserver(gc);
        spritePublishers.forEach(p -> p.addObserver(drawObs));
    }

    @Override
    public void addWorldSpritePublisher(int priority, Publisher<SpriteData> spriteObs) {
        spritePublishers.add(spriteObs);
    }

    @Override
    public void addWorldSpritePublishers(int priority, Publisher<Set<SpriteData>> spriteObs) {

    }

    @Override
    public void addFightPublisher(Publisher<Fight> fightObs) {

    }

    @Override
    public void addNotificationPublisher(Publisher<? extends Notification> notificationPub) {

    }
}
