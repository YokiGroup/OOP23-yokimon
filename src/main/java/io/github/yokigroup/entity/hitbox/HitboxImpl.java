package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Shape;

/**
 * An abstract binding to the Shape class
 */
public class HitboxImpl implements Hitbox {
    private final Shape shape;
    private Pair<Float, Float> position;

    /**
     * Creates a circular hitbox
     * @param position the position of the circle
     * @param radius the radius of the circle
     */
    public HitboxImpl(final Pair<Float, Float> position, final float radius) {
        this.shape = new Circle(radius);
        this.setPosition(position);
    }

    /**
     * Creates a rectangular hitbox
     * @param position the position of the rectangle
     * @param dimensions the width and height of the rectangle
     */
    public HitboxImpl(final Pair<Float, Float> position, final Pair<Float, Float> dimensions) {
        this.shape = new Rectangle(dimensions.getX(), dimensions.getY());
        this.setPosition(position);
    }

    /**
     * Creates a circular hitbox at the origin
     * @param radius the radius of the circle
     */
    public HitboxImpl(final float radius) {
        this(new PairImpl<>(0.0f, 0.0f), radius);
    }

    /**
     * Creates a rectangular hitbox at the origin
     * @param dimensions the width and height of the rectangle
     */
    public HitboxImpl(final Pair<Float, Float> dimensions) {
        this(new PairImpl<>(0.0f, 0.0f), dimensions);
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
