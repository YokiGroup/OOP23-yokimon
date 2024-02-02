package io.github.yokigroup.event.publisher;

import io.github.yokigroup.battle.Fight;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.context.FightSubmodule;
import io.github.yokigroup.event.context.PartySubmodule;
import io.github.yokigroup.event.context.PlayerPositionSubmodule;
import io.github.yokigroup.event.context.Submodule;
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

    @Test
    void generatePartyPublisher() {
        final int partySubNumber = 2;
        final int yokimonNumber = partySubNumber+1;
        EventPublisher<PartySubmodule> partyPublisher = EventPublisherFactory.generatePartyPublisher();
        List<Yokimon> yokimonList;
        List<PartySubmodule> partySubList;

        yokimonList = create(yokimonNumber, i -> {
            return new Yokimon() {};
        });

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
        EventPublisher<PlayerPositionSubmodule> pPositionPublisher = EventPublisherFactory.generatePlayerPositionPublisher();
    }
}