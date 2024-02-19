package io.github.yokigroup.core.state;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;

public record SpriteData(String spriteURL, Vector2 position, Vector2 dim, int priority, boolean flipped) {
    private static final Vector2 tileDim = new Vector2Impl(GameMap.TILE_DIMENSIONS.x(), GameMap.TILE_DIMENSIONS.y());

    public SpriteData(String spriteURL, Vector2 position, Vector2 dim, int priority, boolean flipped) {
        this.spriteURL = spriteURL;
        this.position = position;
        this.dim = dim;
        this.priority = priority;
        this.flipped = flipped;
    }

    public SpriteData(String spriteURL, Vector2 position, Vector2 dim, int priority) {
        this(spriteURL, position, dim, priority, false);
    }

    /**
     * Returns normalized position of the sprite, used for our draw calls.
     * @return normalized position of the sprite ((0,0) represents top left, (1,1) top right).
     */
    public Vector2 getNormalizedPosition() {
        return position.divide(tileDim);
    }

    /**
     * Returns normalized dimensions of the sprite, used for our draw calls.
     * @return normalized dimension of the sprite ((0,0) represents top left, (1,1) top right).
     */
    public Vector2 getNormalizedDimension() {
        return dim.divide(tileDim);
    }
}
