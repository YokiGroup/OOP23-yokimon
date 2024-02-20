package io.github.yokigroup.view.render;

import io.github.yokigroup.core.state.SpriteData;

import java.util.Collection;

public interface DrawQueue extends DrawQueueReader {
    void addToDrawQueue(SpriteData sprite);
    void addToDrawQueue(Collection<SpriteData> sprite);
    void removeFromDrawQueue(SpriteData sprite);
    void removeFromDrawQueue(Collection<SpriteData> sprite);

}
