package io.github.yokigroup.world.entity;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.util.*;

import java.util.Objects;

/**
 * Implementation of Position, provides many useful methods
 * for managing positions.
 */
public class PositionImpl implements Position{

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

    @Override
    public final boolean isValid(final MessageHandler messageHandler) {
        Objects.requireNonNull(messageHandler, "MessageHandler was null");
        messageHandler.handle(GameMapSubmodule.class, map -> {
            if (map.getGameMap().getTileDimensions().x() < this.pos.getY() || this.pos.getX() < 0
                || map.getGameMap().getTileDimensions().y() < this.pos.getY() || this.pos.getY() < 0) {
                //TODO
            }

        });
        return true;
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
