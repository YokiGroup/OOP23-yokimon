package io.github.yokigroup.battle;

import io.github.yokigroup.battle.dmgcalculator.BasicImplDmgCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.*;
import java.util.HashMap;
import java.util.Map;

class YokimonImplTest {



    @Test
    void YokimonImpl(){
        YokimonImpl stone = null;
        assertThrows(NullPointerException.class, () -> new YokimonImpl(stone));

    }
    @Test
    void getAllStats() {
        // FIXME we use loaders now
/*
        Attack shadowBall = new AttackImpl("Shadow ball", Color.BLACK, 90, Attack.Effect.NONE);
        Attack strongPunch = new AttackImpl("strong punch", Color.RED, 80, Attack.Effect.NONE);
        Attack curse = new AttackImpl("curse", Color.PURPLE, 70, Attack.Effect.NONE);
        Attack slap = new AttackImpl("slap", Color.WHITE, 40, Attack.Effect.NONE);
        Attack mock = new AttackImpl("mock", Color.WHITE, 0, Attack.Effect.NONE);
*/
        /*
        Attack  = new AttackImpl("", Color.WHITE, , Attack.effect.NONE);
        */
        Map<Integer, Attack> stone = new HashMap<>();
/*
        stone.put(1, slap);
        stone.put(3, strongPunch);
        stone.put(7, curse);
        stone.put(15, shadowBall);
        stone.put(50, mock);
*/
        Map<Yokimon.Stats, Integer> baseStats= new HashMap<>();
        baseStats.put(Yokimon.Stats.HP, 90);
        baseStats.put(Yokimon.Stats.ATK, 80);
        baseStats.put(Yokimon.Stats.DEF, 100);
        baseStats.put(Yokimon.Stats.SPD, 40);

/*
        Yokimon oni = new YokimonImpl("oni", Color.RED, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );


        oni.takeXp(1000);
        oni.setLevel(10);
       // assertEquals(oni.getActualHp(), oni.getStat(Yokimon.Stats.HP));
        assertEquals(1000, oni.getXp());
        assertEquals(1331, oni.getNextXp());
        //assertTrue( oni.getAttacks().contains(curse));
*/
    }

    @Test
    void getAttacks() {
    }

    @Test
    void setExpNext() {
/*
        Attack shadowBall = new AttackImpl("Shadow ball", Color.BLACK, 90, Attack.Effect.NONE);
        Attack strongPunch = new AttackImpl("strong punch", Color.RED, 80, Attack.Effect.NONE);
        Attack curse = new AttackImpl("curse", Color.PURPLE, 70, Attack.Effect.NONE);
        Attack slap = new AttackImpl("slap", Color.WHITE, 40, Attack.Effect.NONE);
*/

        /*
        Attack  = new AttackImpl("", Color.WHITE, , Attack.effect.NONE);
        */
        Map<Integer, Attack> stone= new HashMap<>();
/*
        stone.put(1, slap);
        stone.put(3, strongPunch);
        stone.put(7, curse);
        stone.put(15, shadowBall);
        Map<Yokimon.Stats, Integer> baseStats1= new HashMap<>();
        baseStats1.put(Yokimon.Stats.HP, 90);
        baseStats1.put(Yokimon.Stats.ATK, 80);
        baseStats1.put(Yokimon.Stats.DEF, 100);
        baseStats1.put(Yokimon.Stats.SPD, 40);
        Map<Yokimon.Stats, Integer> baseStats2= new HashMap<>();
        baseStats2.put(Yokimon.Stats.HP, 120);
        baseStats2.put(Yokimon.Stats.ATK, 100);
        baseStats2.put(Yokimon.Stats.DEF, 60);
        baseStats2.put(Yokimon.Stats.SPD, 70);
        BasicImplDmgCalculator calc = new BasicImplDmgCalculator();
        Yokimon oni = new YokimonImpl("oni", Color.RED, baseStats1, Yokimon.GrowthRate.MEDIUM, 10, stone );
        Yokimon wani = new YokimonImpl("wani", Color.RED, baseStats2, Yokimon.GrowthRate.SLOW, 10, stone );
        assertEquals(42, oni.getStat(Yokimon.Stats.HP));
        assertEquals(12, calc.getDMG(oni, wani, slap));
*/
    }
}