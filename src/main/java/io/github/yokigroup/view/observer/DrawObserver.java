package io.github.yokigroup.view.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.Painter;
import javafx.application.Platform;

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
    public void update(PublisherImpl<SpriteData> publisher, SpriteData lastArg, SpriteData arg) {
        painter.removeFromPersistentDrawQueue(lastArg);
        painter.addToPersistentDrawQueue(arg);
        Platform.runLater(painter::repaint);
    }
}
