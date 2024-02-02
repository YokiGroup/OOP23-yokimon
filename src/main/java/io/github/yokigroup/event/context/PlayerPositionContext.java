package io.github.yokigroup.event.context;

import io.github.yokigroup.util.Vector2;

public interface PlayerPositionContext extends Context{
    enum Direction{
        UP,DOWN,LEFT,RIGHT
    }
    void movePlayerBy(Vector2 delta);
    void changeTile(Direction dir);
}
