package io.github.yokigroup.world.tile;

import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.Direction;

import java.util.Set;

/**
 * A single tile of the map, containing entities and hitboxes for the player to interact with.
 */
public interface Tile {
    /**
     *
     * @return All the hitboxes in a tile.
     */
    Set<Hitbox> getHitboxes();

    /**
     *
     * @return The entities on the tile.
     */
    Set<Entity> getEntities();

    /**
     *
     * @return The id of the tile.
     */
    int getId();

    /**
     * Runs the update method on all the entities on the tile.
     */
    void updateEntities();

    /**
     *
     * @return The connected directions of the tile.
     */
    Set<Direction> getAdjacencies();

    @Override
    boolean equals(Object other);

    @Override
    int hashCode();
}
