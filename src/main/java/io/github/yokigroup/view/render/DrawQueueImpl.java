package io.github.yokigroup.view.render;

import io.github.yokigroup.core.state.SpriteData;


import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class DrawQueueImpl implements DrawQueue {
    private final Queue<SpriteData> queue = new PriorityQueue<>(Comparator.comparingInt(SpriteData::priority));

    /**
     * ...
     */
    public DrawQueueImpl() {
    }

    /**
     * ...
     * @param drawQueue ...
     */
    public DrawQueueImpl(final DrawQueueReader drawQueue) {
        addToDrawQueue(drawQueue.stream().collect(Collectors.toUnmodifiableSet()));
    }

    /**
     * ...
     * @param arg ...
     * @return ...
     */
    private Collection<SpriteData> preprocessCollection(final Collection<SpriteData> arg) {
        if (arg == null) {
            return null;
        }
        return arg.stream().filter(Objects::nonNull).collect(Collectors.toUnmodifiableSet());
    }

    private <T> void synDoIfNotNull(final Consumer<T> operation, final T arg) {
        if (arg != null) {
            synchronized (this) {
                operation.accept(arg);
            }
        }
    }

    @Override
    public void addToDrawQueue(final SpriteData sprite) {
        synDoIfNotNull(queue::add, sprite);
    }

    @Override
    public void addToDrawQueue(final Collection<SpriteData> sprites) {
        synDoIfNotNull(queue::addAll, preprocessCollection(sprites));
    }

    @Override
    public void removeFromDrawQueue(final SpriteData sprite) {
        synDoIfNotNull(queue::remove, sprite);
    }

    @Override
    public void removeFromDrawQueue(final Collection<SpriteData> sprites) {
        synDoIfNotNull(queue::removeAll, preprocessCollection(sprites));
    }

    @Override
    public Stream<SpriteData> stream() {
        return queue.stream();
    }
}
