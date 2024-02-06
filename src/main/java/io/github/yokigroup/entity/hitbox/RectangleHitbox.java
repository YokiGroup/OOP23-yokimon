package io.github.yokigroup.entity.hitbox;

import org.dyn4j.geometry.Shape;

public class RectangleHitbox extends HitboxImpl {

    public RectangleHitbox() {

    }

    @Override
    public boolean collidesWith(Hitbox other) {
        return false;
    }

    @Override
    public Shape getShape() {
        return null;
    }
}
