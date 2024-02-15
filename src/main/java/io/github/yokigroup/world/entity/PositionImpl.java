package io.github.yokigroup.world.entity;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;

import java.util.Objects;

/**
 * Implementation of Position, provides many useful methods
 * for managing positions.
 */
public class PositionImpl implements Position {

    private Vector2 pos;

    /**
     * Constructor of the PositionImpl class, with the specified values.
     * @param pos pos = Pair<>
     */
    public PositionImpl(final Vector2 pos) {
        Objects.requireNonNull(pos, "Pos passed in PositionImpl was null");
        this.pos = new Vector2Impl(pos);
    }

    @Override
    public final Vector2 getPosition() {
        return new Vector2Impl(this.pos);
    }

    @Override
    public final void setPosition(final Vector2 pos) {
        this.pos = new Vector2Impl(pos);
    }

    @Override
    public final void movePosition(final Vector2 vector) {
        this.pos = this.pos.plus(vector);
    }

    @Override
    public final Position testMovePosition(final Vector2 vector) {
        Objects.requireNonNull(vector, "Vector in testMovePosition was null");
        return new PositionImpl(this.pos.plus(vector));
    }

    /**
     * Return true if the position passed is correct in the current tile
     * false if it's out of range of the map.
     * @return boolean
     */
    @Override
    public final boolean isValid() {
        return this.pos.getX() >= 0 && this.pos.getY() >= 0
                && this.pos.getX() < GameMap.TILE_DIMENSIONS.x()
                && this.pos.getY() < GameMap.TILE_DIMENSIONS.y();
    }

    /**
     * Return true if the distance between two vectors is less than radius,
     * false if it's more.
     * @param otherPos the other pair to check
     * @param radius min distance
     * @return boolean
     */
    @Override
    public final boolean inRadius(final Position otherPos, final double radius) {
        Objects.requireNonNull(otherPos, "Pos passed in inRadius method was null");
        return this.pos.minus(otherPos.getPosition()).length() <= radius;
    }
}
