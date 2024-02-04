package io.github.yokigroup.world.hitbox;

import io.github.yokigroup.entity.Entity;
import io.github.yokigroup.world.Hitbox;
import javafx.util.Pair;

/**
 * A circular hitbox type.
 */
public class CircleHitbox implements Hitbox  {
    @Override
    public boolean collidesWith(Hitbox hitbox) {
        return false;
    }

    @Override
    public Pair<Float, Float> fixBounds(Entity entity) {
        return null;
    }
}
