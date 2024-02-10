package io.github.yokigroup.world.gen.wfc;

/**
 * The shapes allowed by the wave function collapse algorithm, and their opposites.
 */
public enum WfcShapeDirection {
    /**
     * Can connect to tiles that have the shape "DOWN".
     */
    UP(1),
    /**
     * Can connect to tiles that have the shape "UP".
     */
    DOWN(0),
    /**
     * Can connect to tiles that have the shape "RIGHT".
     */
    LEFT(3),
    /**
     * Can connect to tiles that have the shape "LEFT".
     */
    RIGHT(2);

    private final WfcShapeDirection connector;

    /**
     *
     * @return The opposite direction.
     */
    public WfcShapeDirection getConnection() {
        return this.connector;
    }

    /**
     *
     * @param connector the index of the opposite direction of the shape.
     */
    WfcShapeDirection(final int connector) {
        this.connector = WfcShapeDirection.values()[connector];
    }
}
