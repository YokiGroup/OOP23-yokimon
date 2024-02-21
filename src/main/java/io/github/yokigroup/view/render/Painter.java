package io.github.yokigroup.view.render;

public abstract class Painter {

    private DrawQueue drawQueue;
    public enum State {
        FIGHT,
        WORLD
    }
    private State paintState;

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
        this.paintState = paintState;
    }

    public State getPaintState() {
        return paintState;
    }

    public abstract void paintEventText(String eventText);
    public abstract void repaint();
}
