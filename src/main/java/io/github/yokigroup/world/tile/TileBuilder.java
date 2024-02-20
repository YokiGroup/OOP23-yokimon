package io.github.yokigroup.world.tile;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.Direction;

import java.util.Set;

/**
 * Interface for a tile builder class.
 */
public interface TileBuilder {
    /**
     * The type of entity on the tile.
     */
    enum EntityType {
        /**
         * Entity enemy type.
         */
        ENEMY,
        /**
         * Entity altar type.
         */
        ALTAR
    }
    /**
     * Adds a hitbox to the tile.
     * @param hitbox The hitbox to add.
     * @return The current TileBuilder.
     */
    TileBuilder addHitbox(Hitbox hitbox);

    /**
     * Adds multiple hitboxes to the tile.
     * @param hitboxes The hitboxes to add.
     * @return The current TileBuilder.
     */
    TileBuilder addAllHitboxes(Set<Hitbox> hitboxes);

    /**
     * Adds an entity to the tile.
     * @param entityType type of the entity to add
     *                   (initialized when {@link TileBuilder#build(MessageHandler)} is called)
     * @param position position of the entity to be added
     * @return The current TileBuilder.
     */
    TileBuilder addEntity(EntityType entityType, Position position);

    /**
     * Adds multiple entities to the tile.
     * @param entities The entities to add.
     * @return The current TileBuilder.
     */
    TileBuilder addAllEntities(Set<Pair<EntityType, Position>> entities);

    /**
     * Adds an adjacency rule to the tile.
     * @param direction The adjacency to add to the tile.
     * @return The current TileBuilder.
     */
    TileBuilder addAdjacency(Direction direction);

    /**
     * Adds adjacency rules to the tile.
     * @param directions The adjacencies to add to the tile.
     * @return The current TileBuilder.
     */
    TileBuilder addAllAdjacencies(Set<Direction> directions);

    /**
     * @return Current adjacency rules added to this builder
     */
    Set<Direction> getAdjacencies();

    /**
     * Finalizes the tile object.
     * @param handler MessageHandler of the instanced game.
     * @return The built tile.
     */
    Tile build(MessageHandler handler);
}
