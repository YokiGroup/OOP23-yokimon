package io.github.yokigroup.view;

import io.github.yokigroup.core.state.SpriteData;

import java.util.Set;

public interface Painter {
    void addToPersistentDrawQueue(SpriteData sprite);
    void addToPersistentDrawQueue(Set<SpriteData> sprites);
    void removeFromPersistentDrawQueue(SpriteData sprite);
    void removeFromPersistentDrawQueue(Set<SpriteData> sprite);
    void repaint();
}
