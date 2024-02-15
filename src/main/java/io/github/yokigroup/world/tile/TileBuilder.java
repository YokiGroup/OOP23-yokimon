package io.github.yokigroup.world.tile;

import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.Hitbox;

import java.util.Set;

/**
 * Interface for a tile builder class.
 */
public interface TileBuilder {
    /**
     * Adds a hitbox to the tile.
     * @param hitbox The hitbox to add.
     */
    void addHitbox(Hitbox hitbox);

    /**
     * Adds multiple hitboxes to the tile.
     * @param hitboxes The hitboxes to add.
     */
    void addAllHitboxes(Set<Hitbox> hitboxes);

    /**
     * Adds an entity to the tile.
     * @param entity The entity to add.
     */
    void addEntity(Entity entity);

    /**
     * Adds multiple entities to the tile.
     * @param entities The entities to add.
     */
    void addAllEntities(Set<Entity> entities);

    /**
     * Finalizes the tile object.
     * @param id The id of the tile.
     * @return The built tile.
     */
    Tile build(final int id);
}
