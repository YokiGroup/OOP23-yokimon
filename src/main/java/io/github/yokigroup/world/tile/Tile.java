package io.github.yokigroup.world.tile;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.Entity;

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
     * @return All the spawn locations in a tile.
     */
    Set<Vector2> getEntitySpawnLocations();

    /**
     *
     * @param hitbox The hitbox to add to the tile.
     */
    void addHitbox(Hitbox hitbox);

    /**
     *
     * @param pos The spawn position to add to the tile.
     */
    void addSpawnLocation(Vector2 pos);

    /**
     * Spawns all the entities in the tile.
     * @param entityPool The pool of entities to randomly add to the tile.
     */
    void spawnEntities(WeightedPool<Entity> entityPool);

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

    @Override
    boolean equals(Object other);

    @Override
    int hashCode();
}
