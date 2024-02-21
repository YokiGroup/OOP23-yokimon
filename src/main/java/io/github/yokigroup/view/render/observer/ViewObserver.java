package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.Painter;

public abstract class ViewObserver<T> implements EObserver<T> {
    private final Painter painter;
    private final DrawQueue drawQueue;

    /**
     * @param painter painter to invoke
     * @param drawQueue draw queue to use
     */
    public ViewObserver(final Painter painter, final DrawQueue drawQueue) {
        this.painter = painter;
        this.drawQueue = drawQueue;
    }

    protected Painter painter() {
        return painter;
    }

    protected DrawQueue drawQueue() {
        return drawQueue;
    }
}
