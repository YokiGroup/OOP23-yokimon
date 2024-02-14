package io.github.yokigroup.world;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.tile.Tile;

/**
 * A map containing tiles the player can traverse on.
 */
public interface GameMap {
    /**
     *
     * @param position The world position to take the tile from.
     * @return The tile at that position.
     */
    Tile getTileAt(Pair<Integer, Integer> position);

    /**
     *
     * @return The dimensions of the tile.
     */
    Pair<Integer, Integer> getTileDimensions();

    /**
     *
     * @return The player's world coordinates.
     */
    Pair<Integer, Integer> getPlayerWorldPosition();

    /**
     *
     * @return The tile the player is on.
     */
    Tile getPlayerTile();
}
