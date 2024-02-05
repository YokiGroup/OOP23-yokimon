package io.github.yokigroup.world.tile;

import com.almasb.fxgl.physics.HitBox;
import io.github.yokigroup.entity.Entity;

import java.util.Set;

/**
 * A single tile of the map, containing entities and hitboxes for the player to interact with.
 */
public interface Tile {
    /**
     *
     * @return All the hitboxes in a tile.
     */
    Set<HitBox> getHitboxes();

    /**
     *
     * @return All the entities in a tile.
     */
    Set<Entity> getEntities();

    /**
     *
     * @param hitbox The hitbox to add to the tile.
     */
    void addHitbox(final HitBox hitbox);

    /**
     *
     * @param entity The entity to add to the tile.
     */
    void addEntity(final Entity entity);
}
