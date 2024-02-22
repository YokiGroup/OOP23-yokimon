package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.view.render.Painter;

public abstract class ViewObserver<T> implements EObserver<T> {
    private final Painter painter;

    /**
     * @param painter painter to invoke
     */
    public ViewObserver(final Painter painter) {
        this.painter = painter;
    }

    protected Painter painter() {
        return painter;
    }
}
