package io.github.yokigroup.event;

public abstract class Updateable {
    private long sinceLastUpdate = getCurrentMillis();

    private long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    protected abstract void updateCode(long delta);

    public final void update() {
        updateCode(sinceLastUpdate = getCurrentMillis() - sinceLastUpdate);
    }
}
