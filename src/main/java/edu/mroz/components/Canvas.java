package edu.mroz.components;

import edu.mroz.interpreter.CanvasCurrentPointer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import static edu.mroz.AppConstants.CANVAS_HEIGHT;
import static edu.mroz.AppConstants.COMPONENTS_WIDTH;

public class Canvas extends JPanel {

    private final CanvasCurrentPointer canvasCurrentPointer = CanvasCurrentPointer.getInstance();
    private final List<ColoredShape> shapes = new ArrayList<>();

    public Canvas() {
        super();
        setMinimumSize(new Dimension(COMPONENTS_WIDTH, CANVAS_HEIGHT));
        setMaximumSize(new Dimension(COMPONENTS_WIDTH, CANVAS_HEIGHT));
        setPreferredSize(new Dimension(COMPONENTS_WIDTH, CANVAS_HEIGHT));
        setSize(new Dimension(COMPONENTS_WIDTH, CANVAS_HEIGHT));
        setBackground(Color.white);
    }

    public void drawLine(int x2, int y2) {
        Line2D line = new Line2D.Float(canvasCurrentPointer.getCurrentPointPosition().x, canvasCurrentPointer.getCurrentPointPosition().y, x2, y2);
        shapes.add(new ColoredShape(line, canvasCurrentPointer.getDrawingColor()));
        canvasCurrentPointer.setCurrentPointPosition(new Point(x2, y2));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (ColoredShape coloredShape : shapes) {
            g2d.setColor(coloredShape.getColor());
            g2d.draw(coloredShape.getShape());
        }
    }
}
