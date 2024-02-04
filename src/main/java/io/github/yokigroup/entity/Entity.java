package io.github.yokigroup.entity;

import io.github.yokigroup.world.Hitbox;
import javafx.util.Pair;

/**
 * Dummy interface for the merge.
 */
public interface Entity {
    public Pair<Float, Float> getPosition();
    public String getName();
    public Hitbox getHitbox();
    public void update();
}
