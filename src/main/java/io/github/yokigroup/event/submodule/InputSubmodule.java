package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.InputSubmoduleAbs;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.observer.ModelObserver;
import io.github.yokigroup.world.Direction;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class InputSubmodule extends InputSubmoduleAbs {
    Set<Direction> moveEvents = new HashSet<>();

    /**
     * @param handler         MessageHandler to call in order to query other submodules.
     * @param ignoredModelObs
     */
    public InputSubmodule(MessageHandler handler, ModelObserver ignoredModelObs) {
        super(handler, ignoredModelObs);
    }

    private Direction keyTextToDirection(String keyText) {
        return switch (keyText.toLowerCase(Locale.ROOT)) {
            case "w" -> Direction.UP;
            case "a" -> Direction.LEFT;
            case "s" -> Direction.DOWN;
            case "d" -> Direction.RIGHT;
            default -> null;
        };
    }

    @Override
    public void registerKeyPress(String keyText) {
        Direction dir = keyTextToDirection(keyText);
        synchronized (this) {
            if (dir != null) moveEvents.add(dir);
        }
    }

    @Override
    public void registerKeyRelease(String keyText) {
        Direction dir = keyTextToDirection(keyText);
        synchronized (this) {
            if (dir != null) moveEvents.remove(dir);
        }
    }

    private Pair<Integer, Integer> sumPairs(final Pair<Integer, Integer> pairOne, final Pair<Integer, Integer> pairTwo) {
        return new Pair<>(pairOne.x() + pairTwo.x(), pairOne.y() + pairTwo.y());
    }

    @Override
    protected void updateCode(double delta) {
        final double velocity = 30.;
        Pair<Integer, Integer> dirSum;
        synchronized (this) {
            dirSum = moveEvents.stream().map(Direction::getOffset).reduce(new Pair<>(0, 0), this::sumPairs);
        }
        final Vector2 moveOffset = new Vector2Impl(dirSum.x(), dirSum.y()).scale(delta*velocity);
        if(!moveOffset.equals(Vector2Impl.NULL_VECTOR)) {
            handler().handle(PlayerCharacterSubmodule.class, s -> {
                s.movePlayerBy(moveOffset);
            });
        }
    }
}
