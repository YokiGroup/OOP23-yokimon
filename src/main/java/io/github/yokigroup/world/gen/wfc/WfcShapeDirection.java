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
    UP(new PairImpl<>(0, 1), 1),
    /**
     * Can connect to tiles that have the shape "UP".
     */
    DOWN(new PairImpl<>(0, -1), 0),
    /**
     * Can connect to tiles that have the shape "RIGHT".
     */
    LEFT(new PairImpl<>(-1, 0), 3),
    /**
     * Can connect to tiles that have the shape "LEFT".
     */
    RIGHT(new PairImpl<>(1, 0), 2);

    private final WfcShapeDirection connector;
    private final Pair<Integer, Integer> offset;

    /**
     *
     * @return The opposite direction.
     */
    public WfcShapeDirection getConnection() {
        return this.connector;
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
     * @param connector the index of the opposite direction of the shape.
     */
    WfcShapeDirection(final Pair<Integer, Integer> offset, final int connector) {
        this.offset = offset;
        this.connector = WfcShapeDirection.values()[connector];
    }
}
