package io.github.yokigroup.entity;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.physics.HitBox;

/**
 * A fake implementation of the entity interface
 */
public class EntityImpl implements Entity {

    public EntityImpl() {

    }

    @Override
    public Vec2 getPosition() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public HitBox getHitbox() {
        return null;
    }

    @Override
    public void update() {

    }
}
