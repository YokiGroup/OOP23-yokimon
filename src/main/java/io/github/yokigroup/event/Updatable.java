package io.github.yokigroup.event;


/**
 * Superclass of anything that necessitates being deactivated or activated, along with requiring being updated \
 * periodically by the game logic.
 */
public abstract class Updatable implements Deactivatable {
    private long sinceLastUpdate = -1;
    private boolean isActive = true;

    /**
     * You should extend this if you want your object to perform additional instructions upon object activation.
     */
    @Override
    public void activate() {
        isActive = true;
    }

    /**
     * You should extend this if you want your object to perform additional instructions upon object deactivation.
     */
    @Override
    public void deactivate() {
        isActive = false;
    }

    /**
     * Resets the delta time of this object, used if the update function is not going to be called for a while.
     */
    public final void resetDTime() {
        sinceLastUpdate = -1;
    }

    @Override
    public final boolean isActive() {
        return isActive;
    }

    private long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Code that's called upon {@link Updatable#update()} invocation.
     * @param delta time in seconds passed from last {@link Updatable#update()} call
     */
    protected abstract void updateCode(double delta);

    /**
     * Updates the object.
     */
    public final void update() {
        if (!isActive) {
            return;
        }
        if (sinceLastUpdate == -1) {
            sinceLastUpdate = getCurrentMillis();
        }
        updateCode(((double) getCurrentMillis() - sinceLastUpdate) / 1000.0);
        sinceLastUpdate = getCurrentMillis();
    }
}
