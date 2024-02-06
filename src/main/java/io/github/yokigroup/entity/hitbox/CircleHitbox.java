package io.github.yokigroup.entity.hitbox;

import org.dyn4j.geometry.Shape;

public class CircleHitbox extends HitboxImpl {

    public CircleHitbox() {

    }

    @Override
    public boolean collidesWith(Hitbox other) {
        return false;
    }
}
