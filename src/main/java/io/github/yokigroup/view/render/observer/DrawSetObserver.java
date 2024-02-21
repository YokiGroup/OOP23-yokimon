package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.view.render.DrawQueue;
import io.github.yokigroup.view.render.Painter;
import io.github.yokigroup.view.render.RenderState;
import javafx.application.Platform;

import java.util.Set;


public class DrawSetObserver extends ViewObserver<Pair<RenderState, Set<SpriteData>>> {

    public DrawSetObserver(Painter painter) {
        super(painter);
    }

    @Override
    public void update(Pair<RenderState, Set<SpriteData>> lastArg, Pair<RenderState, Set<SpriteData>> arg) {
        final Painter painter = painter();
        DrawQueue drawQueue = painter.drawQueue(arg.x());
        drawQueue.removeFromDrawQueue(lastArg.y());
        drawQueue.addToDrawQueue(arg.y());
    }
}
