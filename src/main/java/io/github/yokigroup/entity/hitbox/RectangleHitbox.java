package io.github.yokigroup.entity.hitbox;

import org.dyn4j.geometry.Shape;

/**
 * A rectangular hitbox implementation.
 */
public class RectangleHitbox extends HitboxImpl {

    public RectangleHitbox() {

    }

    @Override
    public boolean collidesWith(Hitbox other) {
        return false;
    }
}
