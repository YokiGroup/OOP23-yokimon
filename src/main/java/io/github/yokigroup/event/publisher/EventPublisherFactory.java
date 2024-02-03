package io.github.yokigroup.event.publisher;

import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerPositionSubmodule;

public class EventPublisherFactory {

    private EventPublisherFactory(){}


    public static EventPublisher<FightSubmodule> generateFightPublisher(){
        return new EventPublisher<FightSubmodule>();
    }

    public static EventPublisher<PartySubmodule> generatePartyPublisher(){
        return new EventPublisher<PartySubmodule>();
    }

    public static EventPublisher<PlayerPositionSubmodule> generatePlayerPositionPublisher(){
        return new EventPublisher<PlayerPositionSubmodule>();
    }
}
