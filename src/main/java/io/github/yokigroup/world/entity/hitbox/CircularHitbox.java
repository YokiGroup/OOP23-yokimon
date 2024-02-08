package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Shape;

/**
 * A circular hitbox implementation.
 */
public class CircularHitbox extends HitboxImpl {
    private final double radius;

    /**
     * Creates a circular hitbox.
     * @param position The position of the circle.
     * @param radius The radius of the circle.
     */
    public CircularHitbox(final Vector2 position, final double radius) {
        super(new Circle(radius), position);
        this.radius = radius;
    }

    /**
     * Creates a circular hitbox at the origin.
     * @param radius The radius of the circle.
     */
    public CircularHitbox(final double radius) {
        this(new Vector2Impl(0.0d, 0.0d), radius);
    }

    /**
     *
     * @return a copy of the shape used for collision checking.
     */
    @Override
    public Shape getShape() {
        final Shape shapeCopy = new Circle(radius);
        shapeCopy.translate(0.0d, 0.0d);
        shapeCopy.translate(this.getPosition().getX(), this.getPosition().getY());
        return shapeCopy;
    }
}
