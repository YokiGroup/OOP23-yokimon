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

    /**
     * Simple constructor that initializes required fields.
     */
    public Painter() {
        drawQueues = new HashMap<>();
        Arrays.stream(RenderState.values()).forEach(
                s -> drawQueues.put(s, new DrawQueueImpl())
        );
    }

    /**
     * returns the {@link DrawQueue} reference relevant to the given state.
     * @param state state of the {@link DrawQueue} to get (there is a {@link DrawQueue} for every state)
     * @return the requested {@link DrawQueue}
     */
    public final DrawQueue drawQueue(final RenderState state) {
        return drawQueues.get(state);
    }

    /**
     * @param paintState to set the painter to (will change {@link DrawQueue} used)
     */
    public final void setPaintState(final RenderState paintState) {
        this.paintState = paintState;
        setEventText("");
    }

    /**
     * @return the current paint state in which the painter's in
     */
    public final RenderState getPaintState() {
        return paintState;
    }

    /**
     * Sets the global text label to the given string.
     * @param eventText String
     */
    public abstract void setEventText(String eventText);

    /**
     * Sets the fight label for the player's yokimon stats.
     * @param text String
     */
    public abstract void setPlayerYokimonLabel(String text);

    /**
     * Sets the fight label for the enemy's yokimon stats.
     * @param text String
     */
    public abstract void setEnemyYokimonLabel(String text);

    /**
     * repaints the screen.
     */
    public abstract void repaint();
}
