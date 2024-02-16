package io.github.yokigroup.world.tile;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.Direction;

import java.util.Set;

/**
 * Interface for a tile builder class.
 */
public interface TileBuilder {
    enum EntityType {
        ENEMY,
        ALTAR
    }
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
     * @param entityType type of the entity to add
     *                   (initialized when {@link TileBuilder#build(MessageHandler)} is called)
     * @param position position of the entity to be added
     */
    TileBuilder addEntity(final EntityType entityType, final Position position);

    /**
     * Adds multiple entities to the tile.
     * @param entities The entities to add.
     */
    TileBuilder addAllEntities(final Set<Pair<EntityType, Position>> entities);

    /**
     * Adds an adjacency rule to the tile.
     * @param direction The adjacency to add to the tile.
     */
    TileBuilder addAdjacency(Direction direction);

    /**
     * Finalizes the tile object.
     * @param handler MessageHandler of the instanced game.
     * @return The built tile.
     */
    Tile build(MessageHandler handler);
}
