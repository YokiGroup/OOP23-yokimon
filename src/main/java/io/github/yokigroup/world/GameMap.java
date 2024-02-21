package io.github.yokigroup.world;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.entity.people.Enemy;
import io.github.yokigroup.world.tile.Tile;

import java.util.Set;

/**
 * A map containing tiles the player can traverse on.
 */
public interface GameMap {
    /**
     * The dimensions of the tile using the 16:9 aspect ratio.
     */
    Pair<Integer, Integer> TILE_DIMENSIONS = new Pair<>(1280, 720);

    /**
     *
     * @param position The world position to take the tile from.
     * @return The tile at that position.
     */
    Tile getTileAt(Pair<Integer, Integer> position);

    /**
     * This function returns if the player has slain all the enemies on the map.
     * @return True if all the enemies have been disabled.
     */
    boolean areAllEnemiesSlain();

    /**
     *
     * @return The player's world coordinates.
     */
    Pair<Integer, Integer> getPlayerTileMapPosition();

    /**
     *
     * @return The tile the player is on.
     */
    Tile getPlayerTile();

    /**
     * Moves the player position in that direction.
     * @param direction The direction to move the player position.
     * @return True if the player can move in that direction, false otherwise.
     */
    boolean movePlayerTileMapPosition(Direction direction);
}
