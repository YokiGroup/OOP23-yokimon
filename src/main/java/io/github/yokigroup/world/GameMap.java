package io.github.yokigroup.world;

import io.github.yokigroup.world.tile.Tile;

public interface GameMap {
    /**
     *
     * @param position The world position to take the tile from.
     * @return The tile at that position.
     */
    Tile getTileAt(final Vector2<Integer> position);
}
