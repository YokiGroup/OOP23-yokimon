package io.github.yokigroup.entity;

public interface Enemy extends People{

    /**
     *logic call this method when the player is
     * to close to the player
     */
    public Entity.message follow();
}
