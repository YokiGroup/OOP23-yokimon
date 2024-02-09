package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import org.dyn4j.geometry.Shape;

/**
 * An abstract binding to the Shape class.
 */
public abstract class HitboxImpl implements Hitbox {
    private final Shape shape;
    private Vector2 position;

    /**
     * Creates a hitbox.
     * @param shape The shape of the hitbox.
     * @param position The position of the hitbox.
     */
    protected HitboxImpl(final Shape shape, final Vector2 position) {
        this.shape = shape;
        this.setPosition(position);
    }

    /**
     * Creates hitbox at the origin.
     * @param shape The shape of the hitbox.
     */
    protected HitboxImpl(final Shape shape) {
        this(shape, new Vector2Impl(0.0d, 0.0d));
    }

    /**
     * Checks the collision between two hitboxes.
     * @param other the other static hitbox to check.
     * @return true if two hitboxes intersect, false otherwise.
     */
    @Override
    public boolean collidesWith(final Hitbox other) {
        return this.shape.createAABB().overlaps(other.getShape().createAABB());
    }

    /**
     * Changes the position of the hitbox and the shape itself.
     * @param pos the positions of the hitbox.
     */
    @Override
    public void setPosition(final Vector2 pos) {
        this.position = pos;
        this.shape.translate(0.0d, 0.0d);
        this.shape.translate(pos.getX(), pos.getY());
    }

    /**
     *
     * @return the position of the hitbox.
     */
    @Override
    public Vector2 getPosition() {
        return this.position;
    }
}
