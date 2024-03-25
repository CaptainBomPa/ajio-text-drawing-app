package edu.mroz.components;

import java.awt.*;
import java.awt.geom.*;

public class CanvasPointer implements Shape {
    private Path2D path;
    private double direction = 0.0;
    private double x, y;

    public CanvasPointer(double x, double y, double direction) {
        setPositionAndDirection(x, y, direction);
    }

    public void setPositionAndDirection(double x, double y, double direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        updateShape();
    }

    private void updateShape() {
        path = new Path2D.Double();
        double radius = 20;
        path.append(new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius), false);
        double adjustedDirection = Math.toRadians(270 + direction);
        double endX = x + radius * Math.cos(adjustedDirection);
        double endY = y + radius * Math.sin(adjustedDirection);
        path.moveTo(x, y);
        path.lineTo(endX, endY);
    }


    @Override
    public Rectangle getBounds() {
        return path.getBounds();
    }

    @Override
    public Rectangle2D getBounds2D() {
        return path.getBounds2D();
    }

    @Override
    public boolean contains(double x, double y) {
        return path.contains(x, y);
    }

    @Override
    public boolean contains(Point2D p) {
        return path.contains(p);
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return path.intersects(x, y, w, h);
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return path.intersects(r);
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return path.contains(x, y, w, h);
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return path.contains(r);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return path.getPathIterator(at);
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return path.getPathIterator(at, flatness);
    }
}
