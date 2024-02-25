package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.GameStateSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.InputSubmoduleAbs;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.Direction;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Input submodule.
 */
public final class InputSubmodule extends InputSubmoduleAbs {
    private final Set<Direction> moveEvents = new HashSet<>();
    private boolean clickedConfirmEvent;
    private GameStateSubmoduleAbs.GameState lastQueriedState;

    /**
     * @param handler         MessageHandler to call in order to query other submodules.
     * @param ignoredModelObs ModelObserver
     */
    public InputSubmodule(final MessageHandler handler, final ModelObserver ignoredModelObs) {
        super(handler, ignoredModelObs);
    }

    private boolean readDirEvent(final String keyText, final Consumer<Direction> ifPresent) {
        final Direction dir = switch (keyText.toLowerCase(Locale.ROOT)) {
            case "w" -> Direction.UP;
            case "a" -> Direction.LEFT;
            case "s" -> Direction.DOWN;
            case "d" -> Direction.RIGHT;
            default -> null;
        };
        if (dir != null) {
            synchronized (this) {
                ifPresent.accept(dir);
            }
            return true;
        }
        return false;
    }

    private boolean readConfirmationEvent(final String keyText, final Runnable ifPresent) {
        final boolean confirmed = switch (keyText) {
            case "\n", "\r", " " -> true;
            default -> false;
        };
        if (confirmed) {
            synchronized (this) {
                ifPresent.run();
            }
        }
        return confirmed;
    }

    private <T> void cycleUntilTrue(final List<Predicate<T>> predicates, final T input) {
        for (final Predicate<T> event : predicates) {
            if (event.test(input)) {
                return;
            }
        }
    }

    /**
     * Register a keyPress event to the submodule.
     *
     * @param keyText String
     */
    @Override
    public void registerKeyPress(final String keyText) {
        cycleUntilTrue(
                List.of(
                        k -> this.readDirEvent(k, moveEvents::add),
                        k -> this.readConfirmationEvent(k, () -> clickedConfirmEvent = true)
                ),
                keyText
        );
    }

    /**
     * Register a keyRelease event to the submodule.
     *
     * @param keyText String
     */
    @Override
    public void registerKeyRelease(final String keyText) {
        cycleUntilTrue(
                List.of(
                        k -> this.readDirEvent(k, moveEvents::remove)
                ),
                keyText
        );
    }

    private Pair<Integer, Integer> sumPairs(final Pair<Integer, Integer> pairOne,
                                            final Pair<Integer, Integer> pairTwo) {
        Objects.requireNonNull(pairOne);
        Objects.requireNonNull(pairTwo);
        return new Pair<>(pairOne.x() + pairTwo.x(), pairOne.y() + pairTwo.y());
    }

    private void handlePlayerPositionChange(final double delta) {
        final double velocity = 52.;
        Pair<Integer, Integer> dirSum;
        synchronized (this) {
            dirSum = moveEvents.stream().map(Direction::getOffset).reduce(new Pair<>(0, 0), this::sumPairs);
        }
        final Vector2 moveOffset = new Vector2Impl(dirSum.x(), dirSum.y()).normalize().scale(delta * velocity);
        if (!moveOffset.equals(Vector2Impl.NULL_VECTOR)) {
            handler().handle(PlayerCharacterSubmodule.class, s -> {
                s.movePlayerBy(moveOffset);
            });
        }
    }

    private void handleFightInputs() {
        handler().handle(FightSubmodule.class, s -> {
            synchronized (this) {
                moveEvents.forEach(e -> {
                    switch (e) {
                        case UP -> s.nextAttack();
                        case DOWN -> s.prevAttack();
                        default -> {
                        }
                    }
                });
            }
        });

        synchronized (this) {
            if (clickedConfirmEvent) {
                handler().handle(FightSubmodule.class, FightSubmodule::confirmAttack);
            }

            moveEvents.clear();
            clickedConfirmEvent = false;
        }
    }

    private void handleGameFinishedInputs() {
        synchronized (this) {
            if (clickedConfirmEvent) {
                handler().handle(GameEndSubmodule.class, GameEndSubmodule::killGame);
            }
        }
    }

    @Override
    protected void updateCode(final double delta) {
        final GameStateSubmoduleAbs.GameState currentState = handler()
                .handle(GameStateSubmodule.class, GameStateSubmodule::getGameState);
        synchronized (this) {
            if (currentState != lastQueriedState) {
                clickedConfirmEvent = false;
                lastQueriedState = currentState;
            }
        }
        switch (currentState) {
            case WORLD -> handlePlayerPositionChange(delta);
            case FIGHT -> handleFightInputs();
            case GAMEOVER, VICTORY -> handleGameFinishedInputs();
            default -> {
            }
        }
    }
}
