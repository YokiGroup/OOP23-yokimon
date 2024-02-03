package entity;

public interface Player extends People{

    /**
     * given a vector, it changes the position of the player
     * around the map
     * @param v vector
     * @return message for collision
     */
    public Entity.message move(Vector v);
}
