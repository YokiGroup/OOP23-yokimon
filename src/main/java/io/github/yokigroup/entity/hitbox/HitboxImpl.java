package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;
import org.dyn4j.geometry.Shape;

/**
 * An abstract binding to the Shape class
 */
public abstract class HitboxImpl implements Hitbox {
    private final Shape shape;
    private Pair<Float, Float> position;

    /**
     * Creates a hitbox
     * @param shape The shape of the hitbox
     * @param position The position of the hitbox
     */
    protected HitboxImpl(final Shape shape, final Pair<Float, Float> position) {
        this.shape = shape;
        this.setPosition(position);
    }

    /**
     * Creates hitbox at the origin
     * @param shape The shape of the hitbox
     */
    protected HitboxImpl(final Shape shape) {
        this(shape, new PairImpl<>(0.0f, 0.0f));
    }

    @Override
    public boolean collidesWith(final Hitbox other) {
        return this.shape.createAABB().overlaps(other.getShape().createAABB());
    }

    @Override
    public void setPosition(final Pair<Float, Float> pos) {
        this.position = pos;
        this.shape.translate(0, 0);
        this.shape.translate(pos.getX(), pos.getY());
    }

    @Override
    public Pair<Float, Float> getPosition() {
        return this.position;
    }

    @Override
    public Shape getShape() {
        return this.shape;
    }
}
