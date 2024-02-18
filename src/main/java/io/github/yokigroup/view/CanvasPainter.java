package io.github.yokigroup.view;

import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CanvasPainter implements Painter {
    private final Map<String, Image> imageCache = new HashMap<>();
    private final GraphicsContext gc;

    private Image consultCache(String resourceURL) {
        if (!imageCache.containsKey(resourceURL)) {
            imageCache.put(resourceURL, new Image(resourceURL));
        }
        return imageCache.get(resourceURL);
    }

    public CanvasPainter(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void paint(SpriteData sprite) {
        final Canvas canvas = gc.getCanvas();
        final Vector2 canvasDim = new Vector2Impl(canvas.getWidth(), canvas.getHeight());
        final Vector2 absSpriteDim = sprite.getNormalizedDimension().times(canvasDim);
        final Vector2 absSpritePos = sprite.getNormalizedPosition().times(canvasDim).minus(absSpriteDim.scale(.5));

        gc.drawImage(
                consultCache(sprite.spriteURL()),
                absSpritePos.getX(),
                absSpritePos.getY(),
                absSpriteDim.getX(),
                absSpriteDim.getY()
        );
    }
}
