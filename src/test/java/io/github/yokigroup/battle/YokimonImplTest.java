package io.github.yokigroup.battle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.*;
import java.util.HashMap;
import java.util.Map;

class YokimonImplTest {



    @Test
    void YokimonImpl(){
        YokimonImpl sasso = null;
        assertThrows(NullPointerException.class, () -> new YokimonImpl(sasso));

    }
    @Test
    void getAllStats() {
        Attack shadowBall = new AttackImpl("Shadow ball", Color.BLACK, 90, Attack.effect.NONE);
        Attack strongPunch = new AttackImpl("strong punch", Color.RED, 80, Attack.effect.NONE);
        Attack curse = new AttackImpl("curse", Color.PURPLE, 70, Attack.effect.NONE);
        Attack slap = new AttackImpl("slap", Color.WHITE, 40, Attack.effect.NONE);
        Map<Integer, Attack> stone= new HashMap<>();
        stone.put(1, slap);
        stone.put(3, strongPunch);
        stone.put(7, curse);
        stone.put(15, shadowBall);
        Map<Yokimon.Stats, Integer> baseStats= new HashMap<>();
        baseStats.put(Yokimon.Stats.HP, 90);
        baseStats.put(Yokimon.Stats.ATK, 80);
        baseStats.put(Yokimon.Stats.DEF, 100);
        baseStats.put(Yokimon.Stats.SPD, 40);

        Yokimon oni = new YokimonImpl("oni", Color.RED, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );
        oni.takeXp(125);
        assertEquals(5, oni.getLevel());
    }

    @Test
    void getAttacks() {
    }

    @Test
    void setExpNext() {
    }
}