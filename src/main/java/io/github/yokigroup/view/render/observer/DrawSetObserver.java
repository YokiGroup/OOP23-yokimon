package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.Painter;
import javafx.application.Platform;

import java.util.Set;

public class DrawSetObserver extends ViewObserver<Set<SpriteData>> {

    public DrawSetObserver(Painter painter, DrawQueue drawQueue) {
        super(painter, drawQueue);
    }

    @Override
    public void update(final Set<SpriteData> lastArg, final Set<SpriteData> arg) {
        final Painter painter = painter();
        DrawQueue drawQueue = drawQueue();
        if (lastArg != null) {
            drawQueue.removeFromDrawQueue(lastArg);
        }
        drawQueue.addToDrawQueue(arg);
        painter.changeDrawQueue(drawQueue);
        if (painter.getPaintState() == Painter.State.WORLD) {
            Platform.runLater(painter::repaint);
        }
    }
}
