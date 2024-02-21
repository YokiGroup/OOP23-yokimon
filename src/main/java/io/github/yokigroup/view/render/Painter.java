package io.github.yokigroup.view.render;

import io.github.yokigroup.view.render.observer.ModelObserver;

public abstract class Painter {

    private DrawQueue drawQueue;
    private RenderState paintState;

    public Painter(final DrawQueue drawQueue) {
        this.drawQueue = drawQueue;
    }

    public final void changeDrawQueue(final DrawQueue queue) {
        synchronized (drawQueue) {
            drawQueue = queue;
        }
        synchronized (drawQueue) {
            paintEventText("");
            repaint();
        }
    }

    public final DrawQueue drawQueue() {
        return drawQueue;
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
