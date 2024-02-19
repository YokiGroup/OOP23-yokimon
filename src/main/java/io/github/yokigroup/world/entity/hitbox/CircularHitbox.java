package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;

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
        super(makeCircle(radius), position);
        this.radius = radius;
    }

    /**
     * Creates a circular hitbox at the origin.
     * @param radius The radius of the circle.
     */
    public CircularHitbox(final double radius) {
        super(makeCircle(radius));
        this.radius = radius;
    }

    @Override
    public final Hitbox copyOf() {
        return new CircularHitbox(this.getPosition(), this.radius);
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof CircularHitbox hitbox)) {
            return false;
        }
        return hitbox.radius == this.radius && super.equals(o);
    }

    /**
     *
     * @param radius The radius of the circle.
     * @return A body with a circular fixture for collision calculation.
     */
    private static Body makeCircle(final double radius) {
        final Body body = new Body();
        final Convex shape = new Circle(radius);
        body.addFixture(shape);
        return body;
    }
}
