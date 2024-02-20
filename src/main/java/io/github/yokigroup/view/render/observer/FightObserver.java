package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.render.Painter;

public class FightObserver extends ViewObserver<Fight> {
    /**
     * @param painter painter to invoke
     */
    public FightObserver(final Painter painter) {
        super(painter);
    }

    @Override
    public void update(final PublisherImpl<Fight> publisher, final Fight lastArg, final Fight arg) {

    }
}
