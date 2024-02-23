package io.github.yokigroup.view.render.painter;

/**
 * Implementation-independent interface used to safely issue a draw call to the view.
 */
public interface DrawCallable {
    /**
     * thread and implementation safe draw call that can be called from any thread.
     */
    void safeDraw();
}
