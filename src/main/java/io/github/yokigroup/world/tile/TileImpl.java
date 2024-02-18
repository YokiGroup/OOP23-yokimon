package io.github.yokigroup.world.tile;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.Direction;

import java.util.Objects;
import java.util.Set;

/**
 * A tile contains the data of an entire part of the map: its entities and hitboxes manly.
 */
class TileImpl implements Tile {
    private final int id;
    private final Set<Direction> adjacencies;
    private final Set<Hitbox> hitboxes;
    private final Set<Entity> entities;
    private final String resourceURL;

    /**
     * Creates a tile with static and dynamic entities.
     * @param id The id of the tile.
     * @param adjacencies The connections this tile has.
     * @param hitboxes The invisible walls in a tile.
     * @param entities The entities on the tile.
     * @throws IllegalArgumentException If any of the arguments are null.
     */
    TileImpl(final int id, final Set<Direction> adjacencies, final Set<Hitbox> hitboxes, final Set<Entity> entities, final String resourceURL) {
        if (hitboxes == null) {
            throw new IllegalArgumentException("The hitboxes set is null.");
        } else if (entities == null) {
            throw new IllegalArgumentException("The entities set is null.");
        } else if (adjacencies == null) {
            throw new IllegalArgumentException("The entities set is null.");
        }
        this.id = id;
        this.adjacencies = Set.copyOf(adjacencies);
        this.hitboxes = Set.copyOf(hitboxes);
        this.entities = Set.copyOf(entities);
        this.resourceURL = resourceURL;
    }

    @Override
    public final Set<Hitbox> getHitboxes() {
        return Set.copyOf(this.hitboxes);
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
    public Set<Direction> getAdjacencies() {
        return Set.copyOf(this.adjacencies);
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
        return this.id == tile.id && Objects.equals(hitboxes, tile.hitboxes)
                && Objects.equals(entities, tile.entities) && Objects.equals(adjacencies, tile.adjacencies);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, hitboxes, adjacencies, entities);
    }

    @Override
    public SpriteData getSpriteData() {
        Vector2 mapDim = new Vector2Impl(GameMap.TILE_DIMENSIONS.x(), GameMap.TILE_DIMENSIONS.y());
        return new SpriteData(resourceURL, mapDim.scale(0.5), mapDim);
    }
}
