package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import org.dyn4j.geometry.Circle;

/**
 * A circular hitbox implementation.
 */
public class CircularHitbox extends HitboxImpl {

    /**
     * Creates a circular hitbox.
     * @param position The position of the circle.
     * @param radius The radius of the circle.
     */
    public CircularHitbox(final Pair<Float, Float> position, final float radius) {
        super(new Circle(radius), position);
    }

    /**
     * Creates a circular hitbox at the origin.
     * @param radius The radius of the circle.
     */
    public CircularHitbox(final float radius) {
        super(new Circle(radius));
    }
}
