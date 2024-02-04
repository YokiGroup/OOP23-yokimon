package io.github.yokigroup.entity.people;

import io.github.yokigroup.entity.people.People;

public interface Enemy extends People {

    /**
     *logic call this method when the player is
     * to close to the player
     */
    public message follow();
}
