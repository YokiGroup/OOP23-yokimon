package io.github.yokigroup.world;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.world.tile.Tile;

import javax.mail.Message;

public interface GameMapBuilder {

    /**
     * Sets the dimensions of the map.
     * @param dimensions The width and height dimensions of the map.
     */
    GameMapBuilder setMapDimensions(Pair<Integer, Integer> dimensions);

    /**
     * Sets the starting position of the player on the map.
     * @param position The position of the player.
     */
    GameMapBuilder setPlayerTileMapPosition(Pair<Integer, Integer> position);

    /**
     * Sets a static tile in the map (won't be overridden by the generation).
     * @param position The position to set the tile at.
     * @param tile The tile to set it to.
     */
    GameMapBuilder setStaticTileAt(Pair<Integer, Integer> position, Tile tile);

    /**
     * Finalizes the GameMap object.
     * @return The built GameMap.
     */
    GameMap build(MessageHandler handler);
}
