package org.Homework;

public class Point {
    private double x;
    private double y;
    private boolean clickable;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.clickable = true;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public double distance(Point other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Point midpoint(Point other) {
        double midX = (x + other.x) / 2;
        double midY = (y + other.y) / 2;
        return new Point(midX, midY);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0;
    }

    public int hashCode() {
        int result = 17;
        long xBits = Double.doubleToLongBits(x);
        long yBits = Double.doubleToLongBits(y);
        result = 31 * result + (int) (xBits ^ (xBits >>> 32));
        result = 31 * result + (int) (yBits ^ (yBits >>> 32));
        return result;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    public boolean contains(double x, double y) {
        double threshold = 10.0; // Adjust the threshold as needed
        double dx = Math.abs(this.x - x);
        double dy = Math.abs(this.y - y);
        return dx <= threshold && dy <= threshold;
    }

}