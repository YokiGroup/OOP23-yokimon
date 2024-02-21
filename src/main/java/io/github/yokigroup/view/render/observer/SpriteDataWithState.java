package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.view.render.RenderState;

public record SpriteDataWithState(RenderState state, SpriteData spriteData) {
}
