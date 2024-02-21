package io.github.yokigroup.event;

public abstract class Updateable implements Deactivatable {
    private long sinceLastUpdate = -1;
    private boolean isActive = true;

    @Override
    public void activate() {
        isActive = true;
    }

    @Override
    public void deactivate() {
        isActive = false;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    private long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    protected abstract void updateCode(double delta);

    public final void update() {
        if (!isActive) {
            return;
        }
        if (sinceLastUpdate == -1) {
            sinceLastUpdate = getCurrentMillis();
        }
        updateCode(((double)getCurrentMillis() - sinceLastUpdate) / 1000.0);
        sinceLastUpdate = getCurrentMillis();
    }
}
