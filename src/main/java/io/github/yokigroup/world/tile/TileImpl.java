package io.github.yokigroup.world.tile;

import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A tile contains the data of an entire part of the map: its entities and hitboxes manly.
 */
public class TileImpl implements Tile {
    private final int id;
    private Set<Hitbox> hitboxes;
    private Set<Entity> entities;

    /**
     * Creates a tile with static and dynamic entities.
     * @param id The id of the tile.
     * @param hitboxes The invisible walls in a tile.
     */
    public TileImpl(final int id, final Set<Hitbox> hitboxes) {
        this.id = id;
        this.entities = new HashSet<>();
        this.hitboxes = hitboxes;
    }

    /**
     * Creates a tile with no hitboxes and no entities.
     * @param id The id of the tile.
     */
    public TileImpl(final int id) {
        this(id, new HashSet<>());
    }

    @Override
    public final Set<Hitbox> getHitboxes() {
        return Set.copyOf(this.hitboxes);
    }

    @Override
    public final void addHitbox(final Hitbox hitbox) {
        this.hitboxes.add(hitbox);
    }

    @Override
    public final Set<Entity> getEntities() {
        return Set.copyOf(this.entities);
    }

    @Override
    public final int getId() {
        return this.id;
    }

    @Override
    public final void updateEntities() {
        this.entities.forEach(Entity::update);
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TileImpl tile = (TileImpl) o;
        return this.id == tile.id && Objects.equals(hitboxes, tile.hitboxes) && Objects.equals(entities, tile.entities);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, hitboxes, entities);
    }
}
