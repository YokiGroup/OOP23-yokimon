package io.github.yokigroup.battle.fight;

/**
 * A class must implement this interface in order to subscribe to a fight.
 */
public interface FightObserver {
    void update (Fight fight, Fight.State state);
}
