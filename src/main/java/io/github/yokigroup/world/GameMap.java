package io.github.yokigroup.world;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.tile.Tile;

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
     * @throws IllegalStateException If the player moved out of bounds.
     */
    boolean movePlayerTileMapPosition(Direction direction);
}
