package io.github.yokigroup.core.state;

import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;

import java.net.URL;

public final class SpriteData implements EObserver<Vector2> {
    private final URL spriteURL;
    private Vector2 position;
    private final Vector2 dim;
    private static final Vector2 tileDim = new Vector2Impl(GameMap.TILE_DIMENSIONS.x(), GameMap.TILE_DIMENSIONS.y());

    /**
     * @param spriteURL URL of the sprite image to render.
     */
    public SpriteData(final URL spriteURL, final Vector2 initPos, final Vector2 dim) {
        this.spriteURL = spriteURL;
        this.position = initPos;
        this.dim = dim;
    }

    /**
     * @return URL of the sprite
     */
    public URL getSpriteURL() {
        return spriteURL;
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

    @Override
    public void update(PublisherImpl<Vector2> publisher, Vector2 arg) {
        position = arg;
    }
}
