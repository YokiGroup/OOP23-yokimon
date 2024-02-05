package io.github.yokigroup.entity;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.physics.HitBox;

/**
 * Dummy interface for the merge.
 */
public interface Entity {
    public Vec2 getPosition();
    public String getName();
    public HitBox getHitbox();
    public void update();
}
