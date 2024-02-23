package io.github.yokigroup.view.render.drawqueue;

import io.github.yokigroup.view.render.drawable.SpriteData;

import java.util.Collection;

public interface DrawQueue extends DrawQueueReader {
    void addToDrawQueue(SpriteData sprite);
    void addToDrawQueue(Collection<SpriteData> sprite);
    void removeFromDrawQueue(SpriteData sprite);
    void removeFromDrawQueue(Collection<SpriteData> sprite);

}
