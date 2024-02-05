package io.github.yokigroup.world.tile;

import com.almasb.fxgl.physics.HitBox;
import io.github.yokigroup.entity.Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * A tile contains the data of an entire part of the map: its entities and hitboxes manly.
 */
public class TileImpl implements Tile {
    private Set<HitBox> hitboxes;
    private Set<Entity> entities;

    public Tile() {
        this.hitboxes = new HashSet<>();
        this.entities = new HashSet<>();
    }

    @Override
    public Set<HitBox> getHitboxes() {
        return Set.copyOf(this.hitboxes);
    }

    @Override
    public Set<Entity> getEntities() {
        return Set.copyOf(this.entities);
    }

    @Override
    public void addHitbox(final HitBox hitbox) {
        this.hitboxes.add(hitbox);
    }

    @Override
    public void addEntity(final Entity entity) {
        this.entities.add(entity);
    }
}
