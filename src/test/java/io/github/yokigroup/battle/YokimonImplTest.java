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
        Attack mock = new AttackImpl("mock", Color.WHITE, 0, Attack.effect.NONE);
        /*
        Attack  = new AttackImpl("", Color.WHITE, , Attack.effect.NONE);
        */
        Map<Integer, Attack> stone= new HashMap<>();
        stone.put(1, slap);
        stone.put(3, strongPunch);
        stone.put(7, curse);
        stone.put(15, shadowBall);
        stone.put(50, mock);
        Map<Yokimon.Stats, Integer> baseStats= new HashMap<>();
        baseStats.put(Yokimon.Stats.HP, 90);
        baseStats.put(Yokimon.Stats.ATK, 80);
        baseStats.put(Yokimon.Stats.DEF, 100);
        baseStats.put(Yokimon.Stats.SPD, 40);

        Yokimon oni = new YokimonImpl("oni", Color.RED, baseStats, Yokimon.GrowthRate.MEDIUM, 1, stone );


        oni.takeXp(1000);
        oni.setLevel(10);
       // assertEquals(oni.getActualHp(), oni.getStat(Yokimon.Stats.HP));
        assertEquals(1000, oni.getXp());
        assertEquals(1331, oni.getNextXp());
        assertEquals(true, oni.getAttacks().contains(curse));
    }

    @Test
    void getAttacks() {
    }

    @Test
    void setExpNext() {
        Attack shadowBall = new AttackImpl("Shadow ball", Color.BLACK, 90, Attack.effect.NONE);
        Attack strongPunch = new AttackImpl("strong punch", Color.RED, 80, Attack.effect.NONE);
        Attack curse = new AttackImpl("curse", Color.PURPLE, 70, Attack.effect.NONE);
        Attack slap = new AttackImpl("slap", Color.WHITE, 40, Attack.effect.NONE);
        Attack flameThrow = new AttackImpl("flame throw", Color.RED, 95, Attack.effect.NONE);
        Attack headBut = new AttackImpl("headBut", Color.WHITE, 50, Attack.effect.NONE);
        Attack tailSwipe = new AttackImpl("TailSwipe ", Color.WHITE, 60 , Attack.effect.NONE);
        Attack whirlpool = new AttackImpl("whirlpool ", Color.PURPLE, 60 , Attack.effect.NONE);
        Attack bite = new AttackImpl("bite ", Color.BLACK, 60, Attack.effect.NONE);
        //effect is stronger if the yokimon is already been hit in this turn
        Attack revenge = new AttackImpl("revenge ", Color.RED, 60 , Attack.effect.NONE);
        Attack confusion = new AttackImpl("confusion", Color.PURPLE, 50, Attack.effect.NONE);
        //effect lower defence of the opponent
        Attack illusion = new AttackImpl("Illusion", Color.PURPLE, 0, Attack.effect.NONE);
        Attack scratch = new AttackImpl("scratch", Color.WHITE, 40 , Attack.effect.NONE);
        //temporary transform in woman for 3 turns
        Attack transformation = new AttackImpl("transformation", Color.PURPLE, 0 , Attack.effect.NONE);
        //regenerate 75% of the max HP, but lower the speed
        Attack grannyStew = new AttackImpl("grannyStew", Color.WHITE, 0, Attack.effect.NONE);
        Attack tsunami = new AttackImpl("tsunami", Color.RED, 100 , Attack.effect.NONE);
        Attack psychic = new AttackImpl("psychic", Color.PURPLE, 90, Attack.effect.NONE);
        //lower the attack
        Attack sub = new AttackImpl("sub", Color.WHITE, 70, Attack.effect.NONE);
        //decrease all the other yokimon's stats
        Attack mock = new AttackImpl("mock", Color.WHITE, 0, Attack.effect.NONE);


        //works only if one or more of the yokimon other team is dead (for nekomata)
        Attack soulAttack = new AttackImpl("Soul Attack", Color.BLACK, 100, Attack.effect.NONE);
        //regenerate half of the damage (for baku)
        Attack dreamEater = new AttackImpl("dreamEater", Color.PURPLE, 60, Attack.effect.NONE);
        //only for kitsune
        Attack nineTailSmash = new AttackImpl("nineTailSmash", Color.WHITE, 140, Attack.effect.NONE);
        //raise a lot the attack (only for tanuki)
        Attack drumAttributes = new AttackImpl("drumAttributes", Color.WHITE, 0, Attack.effect.NONE);
        //effect has only %20 to work (for sazae Oni)
        Attack cutAttributes = new AttackImpl("cut attributes", Color.WHITE, 1000 , Attack.effect.NONE);
        //only for tengu
        Attack katanaSlash = new AttackImpl("katanaSlash", Color.RED, 90, Attack.effect.NONE);
        //lower the speed, only tsuchigumo
        Attack web = new AttackImpl("web", Color.BLACK, 20, Attack.effect.NONE);
        //only for wani
        Attack dragonRage = new AttackImpl("dragonRage", Color.RED, 120, Attack.effect.NONE);
        //hit always fist
        Attack shadowSneak = new AttackImpl("shadowSneak", Color.BLACK, 40, Attack.effect.NONE);
        //hit always fist only Yatagarasu
        Attack extremeSpeed = new AttackImpl("extremeSpeed", Color.WHITE, 80, Attack.effect.NONE);
        //raise attack, defense and speed only for inugami
        Attack possession = new AttackImpl("possession", Color.BLACK, 0, Attack.effect.NONE);
        //the yokimon rest the next turn only sonWukong
        Attack celestialGun = new AttackImpl("celestialGun", Color.WHITE, 175, Attack.effect.NONE);

        /*
        Attack  = new AttackImpl("", Color.WHITE, , Attack.effect.NONE);
        */
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


        oni.takeXp(216);
        assertEquals(6, oni.getLevel());
    }
}