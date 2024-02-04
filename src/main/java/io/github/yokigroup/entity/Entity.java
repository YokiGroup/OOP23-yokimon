package io.github.yokigroup.entity;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.hitbox.Hitbox;

/**
 * Dummy interface for the merge.
 */
public interface Entity {
    public Pair<Float, Float> getPosition();
    public String getName();
    public Hitbox getHitbox();
    public void update();
}
