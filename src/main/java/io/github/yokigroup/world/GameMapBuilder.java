package io.github.yokigroup.world;

import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.tile.Tile;

public interface GameMapBuilder {

    /**
     * Sets the dimensions of the map.
     * @param dimensions The width and height dimensions of the map.
     */
    void setMapDimensions(Pair<Integer, Integer> dimensions);

    /**
     * Sets the starting position of the player on the map.
     * @param position The position of the player.
     */
    void setPlayerTileMapPosition(Pair<Integer, Integer> position);

    /**
     * Sets a static tile in the map (won't be overridden by the generation).
     * @param position The position to set the tile at.
     * @param tile The tile to set it to.
     */
    void setStaticTileAt(Pair<Integer, Integer> position, Tile tile);

    /**
     * Finalizes the GameMap object.
     * @return The built GameMap.
     */
    GameMap build();
}
