package io.github.yokigroup.view.render.painter;

import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.view.render.drawqueue.DrawQueue;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Implementation of {@link Painter} specific to JavaFX.
 */
public class CanvasPainter extends Painter {
    private final Supplier<Vector2> getCanvasDim;
    private final Runnable scaleFonts;
    private final Consumer<String> setGlobalLabelText;
    private final Consumer<String> setEnemyYokimonLabelText;
    private final Consumer<String> setPlayerYokimonLabelText;
    private final BiConsumer<Image, Pair<Vector2, Vector2>> drawImage;
    private final Map<String, Image> imageCache = new HashMap<>();
    private Pair<Long, String> currentNotification; // notification with timestamp
    private static final int CANVAS_DIM_DIVISOR = 20;

    private Image consultCache(final String resourceURL) {
        if (!imageCache.containsKey(resourceURL)) {
            imageCache.put(resourceURL, new Image(resourceURL));
        }
        return imageCache.get(resourceURL);
    }

    /**
     * @param gc                 {@link GraphicsContext} to draw to.
     * @param eventLabel         global label used to announce events to
     * @param playerYokimonLabel label used for displaying the player's yokimon's stats during combat
     * @param enemyYokimonLabel  label used for displaying the enemy's yokimon's stats during combat
     */
    public CanvasPainter(final GraphicsContext gc, final Label eventLabel,
                         final Label playerYokimonLabel, final Label enemyYokimonLabel) {
        super();
        gc.setImageSmoothing(false);
        gc.setTextAlign(TextAlignment.CENTER);
        this.getCanvasDim = () -> {
            final Canvas gcCanvas = gc.getCanvas();
            return new Vector2Impl(gcCanvas.getWidth(), gcCanvas.getHeight());
        };
        this.drawImage = (i, p) -> {
            final Vector2 pos = p.x(), dim = p.y();
            gc.drawImage(
                    i,
                    pos.getX(),
                    pos.getY(),
                    dim.getX(),
                    dim.getY()
            );
        };
        this.scaleFonts = () -> {
            final Font labelFont = new Font(this.getCanvasDim.get().getX() / CANVAS_DIM_DIVISOR);
            eventLabel.setFont(labelFont);
            playerYokimonLabel.setFont(labelFont);
            enemyYokimonLabel.setFont(labelFont);
        };
        this.setEnemyYokimonLabelText = enemyYokimonLabel::setText;
        this.setPlayerYokimonLabelText = playerYokimonLabel::setText;
        this.setGlobalLabelText = eventLabel::setText;
    }

    private void paint(final SpriteData sprite) {
        if (currentNotification != null
                && currentNotification.x() < System.currentTimeMillis()) {
            currentNotification = null;
            setGlobalLabelText.accept("");
        }
        final Vector2 canvasDim = getCanvasDim.get();
        final Vector2 absSpriteDim = sprite.getNormalizedDimension().times(canvasDim);
        final Vector2 absSpritePos = sprite.getNormalizedPosition().times(canvasDim).minus(absSpriteDim.scale(.5));

        scaleFonts.run();

        this.drawImage.accept(
                consultCache(sprite.spriteURL()),
                new Pair<>(
                        new Vector2Impl(
                                absSpritePos.getX() + (sprite.flipped() ? absSpriteDim.getX() : 0),
                                absSpritePos.getY()
                        ),
                        new Vector2Impl(
                                absSpriteDim.getX() * (sprite.flipped() ? -1 : 1),
                                absSpriteDim.getY()
                        )
                )
        );
        if (currentNotification != null) {
            setGlobalLabelText.accept(currentNotification.y());
        }
    }

    @Override
    public final void setEventText(final String eventText) {
        final long waitTime = 3000; // 3 seconds
        currentNotification = new Pair<>(System.currentTimeMillis() + waitTime, eventText);
    }

    private void doWithJavaFXThread(final Runnable runnable) {
        if (Thread.currentThread().getName().startsWith("JavaFX")) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }

    @Override
    public final void setPlayerYokimonLabel(final String text) {
        doWithJavaFXThread(() -> setPlayerYokimonLabelText.accept(text));
    }

    @Override
    public final void setEnemyYokimonLabel(final String text) {
        doWithJavaFXThread(() -> setEnemyYokimonLabelText.accept(text));
    }

    @Override
    public final void repaint() {
        final DrawQueue drawQueue = drawQueue(getPaintState());
        synchronized (drawQueue) {
            drawQueue.stream().forEach(this::paint);
        }
    }

    @Override
    public final void safeDraw() {
        Platform.runLater(this::repaint);
    }
}
