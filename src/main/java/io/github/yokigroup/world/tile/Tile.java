package io.github.yokigroup.world.tile;

import io.github.yokigroup.entity.Entity;
import io.github.yokigroup.world.hitbox.Hitbox;

/**
 * A single tile of the map, containing entities and hitboxes for the player to interact with.
 */
public interface Tile {
    /**
     *
     * @return All the hitboxes in a tile
     */
    Hitbox getHitboxes();

    /**
     *
     * @return All the entities in a tile.
     */
    Entity getEntities();
}
