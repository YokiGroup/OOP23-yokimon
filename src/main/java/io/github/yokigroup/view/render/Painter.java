package io.github.yokigroup.view.render;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Painter {

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
    }

    public RenderState getPaintState() {
        return paintState;
    }

    public abstract void paintEventText(String eventText);
    public abstract void repaint();
}
