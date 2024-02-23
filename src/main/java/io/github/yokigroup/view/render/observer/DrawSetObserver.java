package io.github.yokigroup.view.render.observer;

import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.view.render.drawqueue.DrawQueue;
import io.github.yokigroup.view.render.painter.Painter;
import io.github.yokigroup.view.render.RenderState;
import java.util.Set;


public class DrawSetObserver extends ViewObserver<Pair<RenderState, Set<SpriteData>>> {

    public DrawSetObserver(final Painter painter) {
        super(painter);
    }

    @Override
    public void update(final Pair<RenderState, Set<SpriteData>> lastArg, final Pair<RenderState, Set<SpriteData>> arg) {
        final Painter painter = painter();
        final DrawQueue drawQueue = painter.drawQueue(arg.x());
        drawQueue.removeFromDrawQueue(lastArg.y());
        drawQueue.addToDrawQueue(arg.y());
    }
}
