package io.github.yokigroup.view.render;

/**
 * Abstract class
 */
public abstract class Painter {

    private DrawQueue drawQueue;

    public enum State {
        FIGHT,
        WORLD
    }

    private State paintState;

    /**
     * constructor of this class
     * @param drawQueue DrawQueue
     */
    public Painter(final DrawQueue drawQueue) {
        this.drawQueue = drawQueue;
    }

    public final void changeDrawQueue(final DrawQueue queue) {
        synchronized (this) {
            drawQueue = queue;
        }
    }

    public final DrawQueue drawQueue() {
        return drawQueue;
    }

    public void setPaintState(final State paintState) {
        // FIXME temporary
        // this.paintState = paintState;
        this.paintState = State.WORLD;
    }

    public State getPaintState() {
        return paintState;
    }

    public abstract void paintEventText(String eventText);

    public abstract void repaint();
}
