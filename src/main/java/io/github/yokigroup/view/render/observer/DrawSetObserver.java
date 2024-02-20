package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.Painter;
import javafx.application.Platform;

import java.util.Set;

public class DrawSetObserver extends ViewObserver<Set<SpriteData>> {

    /**
     * @param painter painter to invoke
     */
    public DrawSetObserver(final Painter painter) {
        super(painter);
    }

    @Override
    public void update(final PublisherImpl<Set<SpriteData>> publisher, final Set<SpriteData> lastArg, final Set<SpriteData> arg) {
        final Painter painter = getPainter();
        DrawQueue drawQueue = painter.drawQueue();
        if (lastArg != null) {
            drawQueue.removeFromDrawQueue(lastArg);
        }
        drawQueue.addToDrawQueue(arg);
        painter.changeDrawQueue(drawQueue);
        Platform.runLater(painter::repaint);
    }
}
