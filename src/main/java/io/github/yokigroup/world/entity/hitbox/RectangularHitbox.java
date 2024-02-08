package io.github.yokigroup.world.entity.hitbox;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.PairImpl;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Shape;

/**
 * A rectangular hitbox implementation.
 */
public class RectangularHitbox extends HitboxImpl {
    private final Pair<Float, Float> dimensions;

    /**
     * Creates a rectangular hitbox.
     * @param position The position of the rectangle.
     * @param dimensions The dimensions of the rectangle.
     */
    public RectangularHitbox(final Pair<Float, Float> position, final Pair<Float, Float> dimensions) {
        super(new Rectangle(dimensions.getX(), dimensions.getY()), position);
        this.dimensions = dimensions;
    }

    /**
     * Creates a rectangular hitbox at the origin.
     * @param dimensions The dimensions of the rectangle.
     */
    public RectangularHitbox(final Pair<Float, Float> dimensions) {
        this(new PairImpl<>(0.0f, 0.0f), dimensions);
    }

    /**
     *
     * @return a copy of the shape used for collision checking.
     */
    @Override
    public Shape getShape() {
        final Shape shapeCopy = new Rectangle(dimensions.getX(), dimensions.getY());
        shapeCopy.translate(0.0f, 0.0f);
        shapeCopy.translate(this.getPosition().getX(), this.getPosition().getY());
        return shapeCopy;
    }
}
