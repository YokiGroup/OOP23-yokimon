package io.github.yokigroup.entity;

import com.almasb.fxgl.physics.HitBox;
import io.github.yokigroup.util.Pair;

/**
 * A fake implementation of the entity interface
 */
public class EntityImpl implements Entity {

    public EntityImpl() {

    }

    @Override
    public Pair getPosition() {
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
