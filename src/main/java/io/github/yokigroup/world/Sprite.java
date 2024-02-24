package io.github.yokigroup.world;

import io.github.yokigroup.view.render.drawable.SpriteData;

/**
 * Interface used for denoting that an implementing class has a sprite representation, with a method used to get said \
 * sprite representation.
 */
public interface Sprite {
    /**
     * @return Sprite representation of the object.
     */
    SpriteData getSpriteData();
}
