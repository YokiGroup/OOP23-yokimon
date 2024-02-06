package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import org.dyn4j.geometry.Rectangle;

/**
 * A rectangular hitbox implementation.
 */
public class RectangleHitbox extends HitboxImpl {

    public RectangleHitbox(final Pair<Float, Float> position, final Pair<Float, Float> dimensions) {
        super(position);
        this.shape = new Rectangle(dimensions.getX(), dimensions.getY());
        this.setPosition(position);
    }

    @Override
    public boolean collidesWith(final Hitbox other) {
        return false;
    }
}
