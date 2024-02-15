package io.github.yokigroup.world.tile;

import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.Direction;

import java.util.Set;

/**
 * Interface for a tile builder class.
 */
public interface TileBuilder {
    /**
     * Adds a hitbox to the tile.
     * @param hitbox The hitbox to add.
     */
    TileBuilder addHitbox(Hitbox hitbox);

    /**
     * Adds multiple hitboxes to the tile.
     * @param hitboxes The hitboxes to add.
     */
    TileBuilder addAllHitboxes(Set<Hitbox> hitboxes);

    /**
     * Adds an entity to the tile.
     * @param entity The entity to add.
     */
    TileBuilder addEntity(Entity entity);

    /**
     * Adds multiple entities to the tile.
     * @param entities The entities to add.
     */
    TileBuilder addAllEntities(Set<Entity> entities);

    /**
     * Adds an adjacency rule to the tile.
     * @param direction The adjacency to add to the tile.
     */
    TileBuilder addAdjacency(Direction direction);

    /**
     * Finalizes the tile object.
     * @param id The id of the tile.
     * @return The built tile.
     */
    Tile build(final int id);
}
