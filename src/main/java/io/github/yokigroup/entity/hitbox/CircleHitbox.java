package io.github.yokigroup.entity.hitbox;

import io.github.yokigroup.util.Pair;
import org.dyn4j.geometry.Circle;

/**
 * A circular hitbox implementation.
 */
public class CircleHitbox extends HitboxImpl {

    public CircleHitbox(final Pair<Float, Float> position, final float radius) {
        super(position);
        this.shape = new Circle(radius);
        this.setPosition(position);
    }

    @Override
    public boolean collidesWith(final Hitbox other) {
        return false;
    }
}
