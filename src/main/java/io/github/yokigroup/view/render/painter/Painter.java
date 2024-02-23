package io.github.yokigroup.view.render.painter;

import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.drawqueue.DrawQueue;
import io.github.yokigroup.view.render.drawqueue.DrawQueueImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation-independent abstract class used for painting to a screen.
 */
public abstract class Painter implements DrawCallable {

    private final Map<RenderState, DrawQueue> drawQueues;
    private RenderState paintState;

    public Painter() {
        drawQueues = new HashMap<>();
        Arrays.stream(RenderState.values()).forEach(
                s -> drawQueues.put(s, new DrawQueueImpl())
        );
    }

    public final DrawQueue drawQueue(final RenderState state) {
        return drawQueues.get(state);
    }

    public void setPaintState(final RenderState paintState) {
        this.paintState = paintState;
        setEventText("");
    }

    public RenderState getPaintState() {
        return paintState;
    }

    public abstract void setEventText(String eventText);

    public abstract void repaint();
}
