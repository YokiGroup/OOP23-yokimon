package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.Painter;
import javafx.application.Platform;

import java.util.Set;

/**
 * Observer for drawing sprites to screen.
 */
public final class DrawObserver extends ViewObserver<SpriteData> {

    /**
     * @param painter   painter to invoke
     * @param drawQueue draw queue to use
     */
    public DrawObserver(final Painter painter, final DrawQueue drawQueue) {
        super(painter, drawQueue);
    }

    @Override
    public void update(final SpriteData lastArg, final SpriteData arg) {
        final Painter painter = painter();
        final DrawQueue drawQueue = drawQueue();
        drawQueue.removeFromDrawQueue(lastArg);
        drawQueue.addToDrawQueue(arg);
        if (painter.getPaintState() == Painter.State.WORLD) {
            Platform.runLater(painter::repaint);
        }
    }
}
