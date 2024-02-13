package io.github.yokigroup.core;

public interface Publisher<T> {

    /**
     * Add an observer to subscribe to this publisher.
     * @param obs the observer that must be added.
     */
    public void addObserver(EObserver<T> obs);

    /**
     * Notify observers about some changes.
     * @param arg the element that the observer is observing and that has just changed.
     */
    public void notifyObservers (T arg);
}
