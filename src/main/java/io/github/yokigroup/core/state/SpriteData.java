package io.github.yokigroup.view.render.drawable;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;

/**
 * Record for storing sprites.
 *
 * @param spriteURL
 * @param position
 * @param dim
 * @param priority
 * @param flipped
 */
public record SpriteData(String spriteURL, Vector2 position, Vector2 dim, int priority, boolean flipped) {
    private static final Vector2 TILE_DIM = new Vector2Impl(GameMap.TILE_DIMENSIONS.x(), GameMap.TILE_DIMENSIONS.y());

    /**
     * Constructor of this record.
     * @param spriteURL String
     * @param position Vector2
     * @param dim Vector2
     * @param priority int
     * @param flipped boolean
     */
    public SpriteData(final String spriteURL, final Vector2 position,
                      final Vector2 dim, final int priority, final boolean flipped) {
        this.spriteURL = spriteURL;
        this.position = position;
        this.dim = dim;
        this.priority = priority;
        this.flipped = flipped;
    }

    /**
     * Constructor of this record with default flipped parameter.
     * @param spriteURL String
     * @param position Vector2
     * @param dim Vector2
     * @param priority int
     */
    public SpriteData(final String spriteURL, final Vector2 position, final Vector2 dim, final int priority) {
        this(spriteURL, position, dim, priority, false);
    }

    /**
     * Returns normalized position of the sprite, used for our draw calls.
     *
     * @return normalized position of the sprite ((0,0) represents top left, (1,1) top right).
     */
    public Vector2 getNormalizedPosition() {
        return position.divide(TILE_DIM);
    }

    /**
     * Returns normalized dimensions of the sprite, used for our draw calls.
     *
     * @return normalized dimension of the sprite ((0,0) represents top left, (1,1) top right).
     */
    public Vector2 getNormalizedDimension() {
        return dim.divide(TILE_DIM);
    }
}
