package org.Homework;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public double getLength() {
        return start.distance(end);
    }

    public Point getMidpoint() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    public boolean isHorizontal() {
        return start.getY() == end.getY();
    }

    public boolean isVertical() {
        return start.getX() == end.getX();
    }

    public double getSlope() {
        if (isVertical()) {
            throw new ArithmeticException("Cannot calculate slope for a vertical line.");
        }
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    public boolean isParallel(Line other) {
        if (isVertical() && other.isVertical()) {
            return true;
        }
        if (isVertical() || other.isVertical()) {
            return false;
        }
        return Math.abs(getSlope() - other.getSlope()) < 1e-6;
    }

    public boolean isPerpendicular(Line other) {
        if (isVertical() && other.isHorizontal()) {
            return true;
        }
        if (isHorizontal() && other.isVertical()) {
            return true;
        }
        if (isVertical() || isHorizontal() || other.isVertical() || other.isHorizontal()) {
            return false;
        }
        return Math.abs(getSlope() * other.getSlope() + 1) < 1e-6;
    }

    public Point getIntersection(Line other) {
        if (isParallel(other)) {
            return null;
        }
        if (isVertical()) {
            double x = start.getX();
            double slope = other.getSlope();
            double yIntercept = other.start.getY() - slope * other.start.getX();
            double y = slope * x + yIntercept;
            return new Point(x, y);
        }
        if (other.isVertical()) {
            return other.getIntersection(this);
        }
        double slope1 = getSlope();
        double slope2 = other.getSlope();
        double yIntercept1 = start.getY() - slope1 * start.getX();
        double yIntercept2 = other.start.getY() - slope2 * other.start.getX();
        double x = (yIntercept2 - yIntercept1) / (slope1 - slope2);
        double y = slope1 * x + yIntercept1;
        return new Point(x, y);
    }
}