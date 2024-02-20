package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.Painter;

public class FightObserver extends ViewObserver<Fight> {
    public FightObserver(final Painter painter, final DrawQueue drawQueue) {
        super(painter, drawQueue);
    }

    @Override
    public void update(final Fight lastArg, final Fight arg) {

    }
}
