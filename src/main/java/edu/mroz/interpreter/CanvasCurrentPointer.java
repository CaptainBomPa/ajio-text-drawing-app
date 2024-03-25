package edu.mroz.interpreter;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static edu.mroz.AppConstants.CANVAS_HEIGHT;
import static edu.mroz.AppConstants.COMPONENTS_WIDTH;

public class CanvasCurrentPointer {

    private static CanvasCurrentPointer instance;

    @Getter
    @Setter
    private Point currentPointPosition;
    @Getter
    @Setter
    private Integer direction;
    @Getter
    @Setter
    private Color drawingColor;

    private CanvasCurrentPointer() {
        currentPointPosition = new Point(COMPONENTS_WIDTH / 2, CANVAS_HEIGHT / 2);
        direction = 0;
        drawingColor = Color.BLACK;
    }

    public static synchronized CanvasCurrentPointer getInstance() {
        if (instance == null) {
            instance = new CanvasCurrentPointer();
        }
        return instance;
    }
}
