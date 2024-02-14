package io.github.yokigroup.core;

import java.util.Set;
import java.util.HashSet;

/**
 * A publisher, responsible for adding and updating subscribers to a certain argument.
 * @param <T> the argument type (e.g. Fight).
 */
public final class PublisherImpl<T> implements Publisher<T> {

    /* The observers subscribed to this publisher */
    private final Set<EObserver<T>> observerSet = new HashSet<>();

    @Override
    public void addObserver(final EObserver<T> obs) {
        observerSet.add(obs);
    }

    @Override
    public void notifyObservers(final T arg) {
        for (final var obs : observerSet) {
            obs.update(this, arg);
        }
    }
}
