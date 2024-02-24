package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.view.render.painter.Painter;
import io.github.yokigroup.view.render.RenderState;

/**
 * Observer whose exclusive function is to listen for and update the painter in case of {@link RenderState} changes.
 */
public final class RenderStateObserver extends ViewObserver<RenderState> {

    /**
     * @param painter painter to invoke
     */
    public RenderStateObserver(final Painter painter) {
        super(painter);
    }

    @Override
    public void update(final RenderState lastArg, final RenderState arg) {
        painter().setPaintState(arg);
    }
}
