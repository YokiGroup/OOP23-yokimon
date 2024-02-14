package io.github.yokigroup.core;

/**
 * A class must implement this interface in order to subscribe to an observable.
 * @param <T> the type of the object that the observer wants to be notified about (e.g. Fight).
 */
public interface EObserver<T> {

    /**
     * Update the observer about some changes.
     * @param publisher the publisher responsible about notifying this observer.
     * @param arg the element that underwent some changes.
     */
    void update(PublisherImpl<T> publisher, T arg);
}
