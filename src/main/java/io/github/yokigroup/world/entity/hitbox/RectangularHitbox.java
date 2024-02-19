package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Rectangle;

import java.util.Objects;

/**
 * A rectangular hitbox implementation.
 */
public class RectangularHitbox extends HitboxImpl {
    private final Vector2 dimensions;

    /**
     * Creates a rectangular hitbox.
     * @param position The position of the rectangle.
     * @param dimensions The dimensions of the rectangle.
     */
    public RectangularHitbox(final Vector2 position, final Vector2 dimensions) {
        super(makeRectangle(dimensions), position);
        this.dimensions = dimensions;
    }

    /**
     * Creates a rectangular hitbox at the origin.
     * @param dimensions The dimensions of the rectangle.
     */
    public RectangularHitbox(final Vector2 dimensions) {
        super(makeRectangle(dimensions));
        this.dimensions = dimensions;
    }

    @Override
    public final Hitbox copyOf() {
        return new RectangularHitbox(this.getPosition(), this.dimensions);
    }

    @Override
    public final boolean equals(final Object o) {
        if (!(o instanceof RectangularHitbox hitbox)) {
            return false;
        }
        return hitbox.dimensions.getX() == this.dimensions.getX()
                && hitbox.dimensions.getY() == this.dimensions.getY() && super.equals(o);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(super.hashCode(), dimensions);
    }

    /**
     *
     * @param dimensions The dimensions of the rectangle.
     * @return A body with a rectangle fixture for collision calculation.
     */
    private static Body makeRectangle(final Vector2 dimensions) {
        final Body body = new Body();
        final Convex shape = new Rectangle(dimensions.getX(), dimensions.getY());
        body.addFixture(shape);
        return body;
    }
}
