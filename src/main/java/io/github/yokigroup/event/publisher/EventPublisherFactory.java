package io.github.yokigroup.event.publisher;

import io.github.yokigroup.event.context.FightSubmodule;
import io.github.yokigroup.event.context.PartySubmodule;
import io.github.yokigroup.event.context.PlayerPositionSubmodule;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

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
