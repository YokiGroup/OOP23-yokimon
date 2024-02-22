package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.InputSubmoduleAbs;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.Direction;

import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * Input submodule.
 */
public class InputSubmodule extends InputSubmoduleAbs {
    private final Set<Direction> moveEvents = new HashSet<>();

    /**
     * @param handler         MessageHandler to call in order to query other submodules.
     * @param ignoredModelObs ModelObserver
     */
    public InputSubmodule(final MessageHandler handler, final ModelObserver ignoredModelObs) {
        super(handler, ignoredModelObs);
    }

    private Direction keyTextToDirection(final String keyText) {
        return switch (keyText.toLowerCase(Locale.ROOT)) {
            case "w" -> Direction.UP;
            case "a" -> Direction.LEFT;
            case "s" -> Direction.DOWN;
            case "d" -> Direction.RIGHT;
            default -> null;
        };
    }

    /**
     * Manage the input press, adding a moveEvents.
     *
     * @param keyText String
     */
    @Override
    public void registerKeyPress(final String keyText) {
        final Direction dir = keyTextToDirection(keyText);
        synchronized (this) {
            if (dir != null) {
                moveEvents.add(dir);
            }
        }
    }

    /**
     * Manage the input press, removing a moveEvents.
     *
     * @param keyText String
     */
    @Override
    public void registerKeyRelease(final String keyText) {
        final Direction dir = keyTextToDirection(keyText);
        synchronized (this) {
            if (dir != null) {
                moveEvents.remove(dir);
            }
        }
    }

    private Pair<Integer, Integer> sumPairs(final Pair<Integer, Integer> pairOne,
                                            final Pair<Integer, Integer> pairTwo) {
        Objects.requireNonNull(pairOne);
        Objects.requireNonNull(pairTwo);
        return new Pair<>(pairOne.x() + pairTwo.x(), pairOne.y() + pairTwo.y());
    }

    /**
     * Update position.
     * @param delta the movement offset.
     */
    @Override
    protected void updateCode(final double delta) {
        final double velocity = 52.;
        Pair<Integer, Integer> dirSum;
        synchronized (this) {
            dirSum = moveEvents.stream().map(Direction::getOffset).reduce(new Pair<>(0, 0), this::sumPairs);
        }
        final Vector2 moveOffset = new Vector2Impl(dirSum.x(), dirSum.y()).normalize().scale(delta * velocity);
        if (!moveOffset.equals(Vector2Impl.NULL_VECTOR)) {
            handler().handle(PlayerCharacterSubmodule.class, s -> {
                s.movePlayerBy(moveOffset);
            });
        }
    }
}
