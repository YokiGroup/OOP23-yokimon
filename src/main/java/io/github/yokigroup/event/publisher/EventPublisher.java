package io.github.yokigroup.event.publisher;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class EventPublisher<T> {
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
