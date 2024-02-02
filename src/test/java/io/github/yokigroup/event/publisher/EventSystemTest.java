package io.github.yokigroup.event.publisher;

import io.github.yokigroup.event.context.PartySubmodule;
import io.github.yokigroup.event.context.PlayerPositionSubmodule;
import org.junit.jupiter.api.Test;

class EventSystemTest {

    @Test
    void generateFightPublisher() {

    }

    @Test
    void generatePartyPublisher() {
        EventPublisher<PartySubmodule> partyPublisher = EventPublisherFactory.generatePartyPublisher();
    }

    @Test
    void generatePlayerPositionPublisher() {
        EventPublisher<PlayerPositionSubmodule> pPositionPublisher = EventPublisherFactory.generatePlayerPositionPublisher();
    }
}