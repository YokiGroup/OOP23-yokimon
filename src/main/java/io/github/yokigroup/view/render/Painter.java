package io.github.yokigroup.view.render;

import io.github.yokigroup.core.state.SpriteData;

import java.util.stream.Stream;

public abstract class Painter {

    private DrawQueueReader drawQueue;

    public Painter(final DrawQueueReader drawQueue) {
        this.drawQueue = drawQueue;
    }

    public final void changeDrawQueue(final DrawQueueReader queue) {
        synchronized (this) {
            drawQueue = queue;
        }
    }

    public final DrawQueue drawQueue() {
        return new DrawQueueImpl(drawQueue);
    }

    public abstract void paintEventText(String eventText);
    public abstract void repaint();
}
