package io.github.yokigroup.view.render.drawqueue;

import io.github.yokigroup.view.render.drawable.SpriteData;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Queue used to draw to the screen, drawing elements in ascending priority.
 */
public interface DrawQueue {
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

    /**
     * @return the draw queue's contents, in a stream format
     */
    Stream<SpriteData> stream();

}
