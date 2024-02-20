package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.render.Painter;

public abstract class ViewObserver<T> implements EObserver<T> {
    private final Painter painter;

    /**
     * @param painter painter to invoke
     */
    public ViewObserver(final Painter painter) {
        this.painter = painter;
    }

    protected Painter getPainter() {
        return painter;
    }
}
