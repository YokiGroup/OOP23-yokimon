package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.view.render.Painter;
import io.github.yokigroup.view.render.RenderState;

public class RenderStateObserver extends ViewObserver<RenderState> {

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
