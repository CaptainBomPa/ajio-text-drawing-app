package edu.mroz.components;

import lombok.Getter;

import java.awt.*;

@Getter
public class ColoredShape {

    private final Shape shape;
    private final Color color;

    public ColoredShape(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
    }
}
