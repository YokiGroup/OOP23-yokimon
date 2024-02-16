package io.github.yokigroup.world;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Pair;

/**
 * Interface for a builder to create a GameMap object.
 */
public interface GameMapBuilder {

    /**
     * Sets the dimensions of the map.
     * @param dimensions The width and height dimensions of the map.
     * @return The current GameMapBuilder.
     */
    GameMapBuilder changeMapDimensions(Pair<Integer, Integer> dimensions);

    /**
     * Sets the starting position of the player on the map.
     * @param position The position of the player.
     * @return The current GameMapBuilder.
     */
    GameMapBuilder changePlayerTileMapPosition(Pair<Integer, Integer> position);

    /**
     * Sets the home tile as a static tile in the map at position {@param position}.
     * @param position The position to set the tile at.
     * @return The current GameMapBuilder.
     */
    GameMapBuilder putHomeTileAt(Pair<Integer, Integer> position);

    /**
     * Finalizes the GameMap object.
     * @param handler The MessageHandler.
     * @return The built GameMap.
     */
    GameMap build(MessageHandler handler);
}
