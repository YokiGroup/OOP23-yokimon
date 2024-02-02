package io.github.yokigroup.world.tile;

public interface Tile {
    /**
     *
     * @return All the hitboxes in a tile
     */
    Hitbox getHitboxes();

    /**
     *
     * @return All the entities in a tile.
     */
    Entity getEntities();
}
