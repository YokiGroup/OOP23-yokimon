package io.github.yokigroup.event.publisher;

import io.github.yokigroup.event.context.FightContext;
import io.github.yokigroup.event.context.PartyContext;
import io.github.yokigroup.event.context.PlayerPositionContext;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class EventPublisherFactory {

    private EventPublisherFactory(){}

    public static class EventPublisher<T> {
        private final Set<T> contextSubscribers;

        public EventPublisher(){
            contextSubscribers = new HashSet<>();
        }
        public void subscribe(T c){
            contextSubscribers.add(c);
        }
        public void publish(Consumer<T> process) {
            contextSubscribers.forEach(process);
        }
    }

    public static EventPublisher<FightContext> generateFightPublisher(){
        return new EventPublisher<FightContext>();
    }

    public static EventPublisher<PartyContext> generatePartyPublisher(){
        return new EventPublisher<PartyContext>();
    }

    public static EventPublisher<PlayerPositionContext> generatePlayerPositionPublisher(){
        return new EventPublisher<PlayerPositionContext>();
    }
}
