package io.github.yokigroup.world.gen.wfc;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;

/**
 * The shapes allowed by the wave function collapse algorithm, and their opposites.
 */
public enum WfcShapeDirection {
    /**
     * Can connect to tiles that have the shape "DOWN".
     */
    UP(new PairImpl<>(0, 1)),
    /**
     * Can connect to tiles that have the shape "UP".
     */
    DOWN(new PairImpl<>(0, -1)),
    /**
     * Can connect to tiles that have the shape "RIGHT".
     */
    LEFT(new PairImpl<>(-1, 0)),
    /**
     * Can connect to tiles that have the shape "LEFT".
     */
    RIGHT(new PairImpl<>(1, 0));

    private final Pair<Integer, Integer> offset;

    /**
     *
     * @return The opposite direction.
     */
    public WfcShapeDirection getConnection() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            default -> throw new IllegalStateException("Unexpected value: " + this);
        };
    }

    /**
     *
     * @return The coordinate offset.
     */
    public Pair<Integer, Integer> getOffset() {
        return this.offset;
    }

    /**
     *
     * @param offset The coordinate offset of the direction.
     */
    WfcShapeDirection(final Pair<Integer, Integer> offset) {
        this.offset = offset;
    }
}
