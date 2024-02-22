package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.view.render.Painter;

public class FightObserver extends ViewObserver<Fight> {
    public FightObserver(final Painter painter) {
        super(painter);
    }

    @Override
    public void update(final Fight lastArg, final Fight arg) {
    }
}
