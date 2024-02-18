package io.github.yokigroup.view.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.Painter;

/**
 * Observer for drawing sprites to screen.
 */
public final class DrawObserver implements EObserver<SpriteData> {

    private final Painter painter;

    /**
     * @param painter painter to invoke
     */
    public DrawObserver(Painter painter) {
        this.painter = painter;
    }

    @Override
    public void update(final PublisherImpl<SpriteData> publisher, final SpriteData arg) {
        painter.paint(arg);
    }
}
