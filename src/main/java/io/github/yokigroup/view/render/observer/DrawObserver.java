package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.view.render.drawqueue.DrawQueue;
import io.github.yokigroup.view.render.painter.Painter;

/**
 * Observer for drawing sprites to screen.
 */
public final class DrawObserver extends ViewObserver<SpriteDataWithState> {

    /**
     * @param painter   painter to invoke
     */
    public DrawObserver(final Painter painter) {
        super(painter);
    }

    @Override
    public void update(final SpriteDataWithState lastArg, final SpriteDataWithState arg) {
        final Painter painter = painter();
        final DrawQueue drawQueue = painter.drawQueue(arg.state());
        drawQueue.removeFromDrawQueue(lastArg.spriteData());
        drawQueue.addToDrawQueue(arg.spriteData());
    }
}
