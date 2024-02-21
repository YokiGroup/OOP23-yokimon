package io.github.yokigroup.event;

public abstract class Updateable {
    private long sinceLastUpdate = -1;

    private long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    protected abstract void updateCode(double delta);

    public final void update() {
        if (sinceLastUpdate == -1) {
            sinceLastUpdate = getCurrentMillis();
        }
        updateCode(((double)getCurrentMillis() - sinceLastUpdate) / 1000.0);
        sinceLastUpdate = getCurrentMillis();
    }
}
