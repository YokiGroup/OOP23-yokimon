package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.view.render.RenderState;

/**
 * {@link SpriteData} associated with a {@link RenderState}.
 * @param state state the {@link SpriteData} is in
 * @param spriteData {@link SpriteData} associated with the given state
 */
public record SpriteDataWithState(RenderState state, SpriteData spriteData) {
}
