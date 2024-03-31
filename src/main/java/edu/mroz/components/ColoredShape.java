package edu.mroz.components;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.Serializable;

@Getter
public class ColoredShape implements Serializable {

    private final Shape shape;
    private final Color color;
    @Setter
    private boolean shouldDraw;
    private final Point lastPointerPosition;
    private final Integer direction;

    public ColoredShape(Shape shape, Color color, boolean shouldDraw, Point lastPointerPosition,
                        Integer direction) {
        this.shape = shape;
        this.color = color;
        this.shouldDraw = shouldDraw;
        this.lastPointerPosition = lastPointerPosition;
        this.direction = direction;
    }
}
