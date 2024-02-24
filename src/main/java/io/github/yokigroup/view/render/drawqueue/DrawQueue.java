package io.github.yokigroup.view.render.drawqueue;

import io.github.yokigroup.view.render.drawable.SpriteData;

import java.util.Collection;

/**
 * Queue used to draw to the screen, drawing elements in ascending priority.
 */
public interface DrawQueue extends DrawQueueReader {
    /**
     * @param sprite sprite to add to the draw queue
     */
    void addToDrawQueue(SpriteData sprite);

    /**
     * @param sprites sprite collection to add to the queue.
     */
    void addToDrawQueue(Collection<SpriteData> sprites);

    /**
     * @param sprite sprite to remove to the draw queue
     */
    void removeFromDrawQueue(SpriteData sprite);

    /**
     * @param sprites sprite collection to remove from the queue
     */
    void removeFromDrawQueue(Collection<SpriteData> sprites);

    /**
     * empties the queue.
     */
    void empty();

}
