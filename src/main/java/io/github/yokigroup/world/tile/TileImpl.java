package io.github.yokigroup.world.tile;

import io.github.yokigroup.entity.Entity;
import io.github.yokigroup.entity.hitbox.Hitbox;

import java.util.Set;

/**
 * A tile contains the data of an entire part of the map: its entities and hitboxes manly.
 */
public class TileImpl implements Tile {
    private Set<Hitbox> hitboxes;
    private Set<Entity> entities;

    /**
     * Creates a tile with static and dynamic entities.
     * @param hitboxes The invisible walls in a tile.
     * @param entities The entities in the tile.
     */
    public TileImpl(final Set<Hitbox> hitboxes, final Set<Entity> entities) {
        this.hitboxes = hitboxes;
        this.entities = entities;
    }

    /**
     * Creates a tile with no hitboxes and no entities.
     */
    public TileImpl() {
        new TileImpl(Set.of(), Set.of());
    }

    /**
     *
     * @return All the hitboxes in a tile.
     */
    @Override
    public Set<Hitbox> getHitboxes() {
        return Set.copyOf(this.hitboxes);
    }

    /**
     *
     * @return All the entities in a tile.
     */
    @Override
    public Set<Entity> getEntities() {
        return Set.copyOf(this.entities);
    }

    /**
     * Adds a hitbox to the tile.
     * @param hitbox The hitbox to add to the tile.
     */
    @Override
    public void addHitbox(final Hitbox hitbox) {
        this.hitboxes.add(hitbox);
    }

    /**
     * Adds an entity to the tile.
     * @param entity The entity to add to the tile.
     */
    @Override
    public void addEntity(final Entity entity) {
        this.entities.add(entity);
    }
}
