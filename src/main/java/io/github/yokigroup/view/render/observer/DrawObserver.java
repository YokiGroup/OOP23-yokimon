package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.Painter;
import javafx.application.Platform;

/**
 * Observer for drawing sprites to screen.
 */
public final class DrawObserver extends ViewObserver<SpriteData> {
    /**
     * @param painter painter to invoke
     */
    public DrawObserver(final Painter painter) {
        super(painter);
    }

    @Override
    public void update(final PublisherImpl<SpriteData> publisher, final SpriteData lastArg, final SpriteData arg) {
        Painter painter = getPainter();
        DrawQueue drawQueue = painter.drawQueue();
        drawQueue.removeFromDrawQueue(lastArg);
        drawQueue.addToDrawQueue(arg);
        painter.changeDrawQueue(drawQueue);
        Platform.runLater(painter::repaint);
    }
}
