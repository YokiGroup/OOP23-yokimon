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
    public static final Pair<Integer, Integer> TILE_DIMENSIONS = new Pair<>(1280, 720);
    
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
    Pair<Integer, Integer> getPlayerWorldPosition();

    /**
     *
     * @return The tile the player is on.
     */
    Tile getPlayerTile();
}
