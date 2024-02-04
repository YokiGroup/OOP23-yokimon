package io.github.yokigroup.entity.people;

import io.github.yokigroup.entity.Vector;
import io.github.yokigroup.entity.people.People;

public interface Player extends People {

    /**
     * given a vector, it changes the position of the player
     * around the map
     * @param v vector
     * @return message for collision
     */
    public message move(Vector v);
}
