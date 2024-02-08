package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Shape;

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
        super(new Rectangle(dimensions.getX(), dimensions.getY()), position);
        this.dimensions = dimensions;
    }

    /**
     * Creates a rectangular hitbox at the origin.
     * @param dimensions The dimensions of the rectangle.
     */
    public RectangularHitbox(final Vector2 dimensions) {
        this(new Vector2Impl(0.0d, 0.0d), dimensions);
    }

    @Override
    public final Shape getShape() {
        final Shape shapeCopy = new Rectangle(dimensions.getX(), dimensions.getY());
        shapeCopy.translate(0.0d, 0.0d);
        shapeCopy.translate(this.getPosition().getX(), this.getPosition().getY());
        return shapeCopy;
    }
}
