package io.github.yokigroup.world.entity;


import io.github.yokigroup.battle.Yokimon;

import java.util.List;

/**
 * Interface GeneratorFactory is used for generate yokimon or list of them for
 * both giving a Yokimon to the player when it steps near to an Altar and
 * generate the foes party.
 */
public interface GenerationFactory {

    /**
     * Return a yokimon that will be put on an Altar. How strong and rare it will be is
     * based of the power integer passed.
     * @param power integer
     * @return Yokimon
     */
    Yokimon getYokimonAltar(int power);
    /**
     * Return a List of yokimon that will be used by an enemy. How strong and rare they will be is
     * based of the power integer passed.
     * @param power integer
     * @return Yokimon
     */
    List<Yokimon>  getEnemyParty(int power);

    /**
     * Return a legendary yokimon that will be put on an Altar. How strong and rare it will be is
     * based of the power integer passed.
     * @param power integer
     * @return Yokimon
     */
    Yokimon getLegendAltar(int power);
    /**
     * Return a List of yokimon that will be used by a Boss.
     * @return Yokimon
     */
    List<Yokimon> getBossParty();
}

