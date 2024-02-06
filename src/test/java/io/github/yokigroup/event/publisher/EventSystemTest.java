package io.github.yokigroup.event.publisher;

import io.github.yokigroup.battle.Fight;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.YokimonImpl;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerPositionSubmodule;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.tile.TileMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventSystemTest {

    private <T> List<T> create(int num, Function<Integer, T> creator){
        List<T> retList = new ArrayList<>();
        for(int i=0; i<num; i++){
            retList.add(creator.apply(i));
        }
        return retList;
    }

    @Test
    void generateFightPublisher() {
        PartySubmodule partySub = new PartySubmodule();
        EventPublisher<FightSubmodule> fightPublisher = EventPublisherFactory.generateFightPublisher();
        List<FightSubmodule> fightSubList;

        fightSubList = create(5, i -> {
            FightSubmodule retSub = new FightSubmodule(partySub);
            fightPublisher.subscribe(retSub);
            return retSub;
        });

        fightPublisher.publish(f -> {
            f.addEncounter(new Fight() {});
        });

        for(var fSub : fightSubList){
            assertTrue(fSub.getLastAnnouncedFight().isPresent());
        }
    }

    //@Test
    void generatePartyPublisher() {
        final int partySubNumber = 2;
        final int yokimonNumber = partySubNumber+1;
        EventPublisher<PartySubmodule> partyPublisher = EventPublisherFactory.generatePartyPublisher();
        List<Yokimon> yokimonList;
        List<PartySubmodule> partySubList;

        yokimonList = List.of();
/*
        yokimonList = create(yokimonNumber, i -> {
            // TODO load a yokimon in
            return new YokimonImpl();
        });
*/

        partySubList = create(partySubNumber, i -> {
            PartySubmodule retSub = new PartySubmodule();
            partyPublisher.subscribe(retSub);
            retSub.addYokimon(yokimonList.get(i));
            return retSub;
        });

        partyPublisher.publish(p -> {
            p.addYokimon(yokimonList.getLast());
        });

        for(int i=0; i<partySubNumber; i++){
            assertEquals(List.of(yokimonList.get(i), yokimonList.getLast()),
                    partySubList.get(i).listYokimons());
        }
    }

    @Test
    void generatePlayerPositionPublisher() {
        EventPublisher<PlayerPositionSubmodule> playerPositionPub = EventPublisherFactory.generatePlayerPositionPublisher();
        Entity playerChar = null;
        TileMap gameMap = new TileMap(){};
        // it makes no sense to have more than one PlayerPositionSubmodule
        PlayerPositionSubmodule playerPositionSub = new PlayerPositionSubmodule(playerChar, gameMap);

        // TODO finish test after merge
        playerPositionPub.subscribe(playerPositionSub);
        playerPositionPub.publish(pps -> {

        });
    }
}