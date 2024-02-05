package io.github.yokigroup.world.tile;

import io.github.yokigroup.entity.Entity;
import io.github.yokigroup.entity.Hitbox;

import java.util.HashSet;
import java.util.Set;

/**
 * A tile contains the data of an entire part of the map: its entities and hitboxes manly.
 */
public class TileImpl implements Tile {
    private Set<Hitbox> hitboxes;
    private Set<Entity> entities;

    public TileImpl(final Set<Hitbox> hitboxes, final Set<Entity> entities) {
        this.hitboxes = hitboxes;
        this.entities = entities;
    }

    public TileImpl() {
        new TileImpl(Set.of(), Set.of());
    }

    @Override
    public Set<Hitbox> getHitboxes() {
        return Set.copyOf(this.hitboxes);
    }

    @Override
    public Set<Entity> getEntities() {
        return Set.copyOf(this.entities);
    }

    @Override
    public void addHitbox(final Hitbox hitbox) {
        this.hitboxes.add(hitbox);
    }

    @Override
    public void addEntity(final Entity entity) {
        this.entities.add(entity);
    }
}
