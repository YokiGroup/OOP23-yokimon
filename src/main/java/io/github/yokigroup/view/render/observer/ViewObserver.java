package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.view.render.painter.Painter;

/**
 * Generic observer used by the view for screen updates.
 * @param <T> type of the observer
 */
public abstract class ViewObserver<T> implements EObserver<T> {
    private final Painter painter;

    /**
     * @param painter painter to invoke
     */
    protected ViewObserver(final Painter painter) {
        this.painter = painter;
    }

    /**
     * @return the painter associated with the observer.
     */
    protected final Painter painter() {
        return painter;
    }
}
