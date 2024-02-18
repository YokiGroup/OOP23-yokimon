package io.github.yokigroup.view;

import io.github.yokigroup.core.state.SpriteData;

import java.util.Set;

public interface Painter {
    void addToPersistentDrawQueue(SpriteData sprite);
    void removeFromPersistentDrawQueue(SpriteData sprite);
    void repaint();
}
