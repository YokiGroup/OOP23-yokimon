package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import org.dyn4j.geometry.Rectangle;

/**
 * A rectangular hitbox implementation.
 */
public class RectangularHitbox extends HitboxImpl {

    /**
     * Creates a rectangular hitbox.
     * @param position The position of the rectangle.
     * @param dimensions The dimensions of the rectangle.
     */
    public RectangularHitbox(final Pair<Float, Float> position, final Pair<Float, Float> dimensions) {
        super(new Rectangle(dimensions.getX(), dimensions.getY()), position);
    }

    /**
     * Creates a rectangular hitbox at the origin.
     * @param dimensions The dimensions of the rectangle.
     */
    public RectangularHitbox(final Pair<Float, Float> dimensions) {
        super(new Rectangle(dimensions.getX(), dimensions.getY()));
    }
}
