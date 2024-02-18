package io.github.yokigroup.event;

public abstract class Updateable {
    private long sinceLastUpdate = getCurrentMillis();

    private long getCurrentMillis() {
        return System.currentTimeMillis();
    }

    protected abstract void updateCode(double delta);

    public final void update() {
        sinceLastUpdate = getCurrentMillis() - sinceLastUpdate;
        updateCode(((double)sinceLastUpdate) / 1000.0);
    }
}
