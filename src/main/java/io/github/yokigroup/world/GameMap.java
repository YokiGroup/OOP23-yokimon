package io.github.yokigroup.world;

import io.github.yokigroup.world.tile.Tile;

/**
 * A map containing tiles the player can traverse on.
 */
public interface GameMap {
    /**
     *
     * @param position The world positioan to take the tile from.
     * @return The tile at that position.
     */
    Tile getTileAt(final Vector2<Integer> position);

    /**
     *
     * @return The player's world coordinates (the tile the player's on)
     */
    Vector2<Integer> getPlayerWorldPosition();
}
