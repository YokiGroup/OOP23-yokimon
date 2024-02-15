package io.github.yokigroup.world.gen;

import io.github.yokigroup.util.Pair;

public enum TileDirections {
    /**
     * Can connect to tiles that have the shape "DOWN".
     */
    UP(new Pair<>(0, 1)),
    /**
     * Can connect to tiles that have the shape "UP".
     */
    DOWN(new Pair<>(0, -1)),
    /**
     * Can connect to tiles that have the shape "RIGHT".
     */
    LEFT(new Pair<>(-1, 0)),
    /**
     * Can connect to tiles that have the shape "LEFT".
     */
    RIGHT(new Pair<>(1, 0));

    private final Pair<Integer, Integer> offset;

    /**
     *
     * @return The opposite direction.
     */
    public TileDirections getComplementary() {
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
        return new Pair<>(this.offset.x(), this.offset.y());
    }

    /**
     *
     * @param offset The coordinate offset of the direction.
     */
    TileDirections(final Pair<Integer, Integer> offset) {
        this.offset = offset;
    }
}
