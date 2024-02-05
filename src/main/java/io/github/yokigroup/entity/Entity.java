package io.github.yokigroup.entity;

import com.almasb.fxgl.physics.HitBox;
import io.github.yokigroup.util.Pair;

/**
 * Dummy interface for the merge.
 */
public interface Entity {
    public Pair getPosition();
    public String getName();
    public HitBox getHitbox();
    public void update();
}
