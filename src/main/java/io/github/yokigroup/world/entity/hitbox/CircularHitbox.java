package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Shape;

/**
 * A circular hitbox implementation.
 */
public class CircularHitbox extends HitboxImpl {
    private final float radius;

    /**
     * Creates a circular hitbox.
     * @param position The position of the circle.
     * @param radius The radius of the circle.
     */
    public CircularHitbox(final Pair<Float, Float> position, final float radius) {
        super(new Circle(radius), position);
        this.radius = radius;
    }

    /**
     * Creates a circular hitbox at the origin.
     * @param radius The radius of the circle.
     */
    public CircularHitbox(final float radius) {
        this(new PairImpl<>(0.0f, 0.0f), radius);
    }

    /**
     *
     * @return a copy of the shape used for collision checking.
     */
    @Override
    public Shape getShape() {
        final Shape shapeCopy = new Circle(radius);
        shapeCopy.translate(0.0f, 0.0f);
        shapeCopy.translate(this.getPosition().getX(), this.getPosition().getY());
        return shapeCopy;
    }
}
