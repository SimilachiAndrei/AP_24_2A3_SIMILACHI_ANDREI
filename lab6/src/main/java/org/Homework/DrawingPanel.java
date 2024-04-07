package org.Homework;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingPanel extends BorderPane {
    private Canvas canvas;
    private List<Point> points;
    private List<Point> selectedPoints;
    private Color currentPlayerColor;
    private boolean gameOver;

    public DrawingPanel(double width, double height) {
        canvas = new Canvas(width, height);
        setCenter(canvas);
        points = new ArrayList<>();
        selectedPoints = new ArrayList<>();
        currentPlayerColor = Color.RED;
        gameOver = false;

        canvas.setOnMouseClicked(this::handleMouseClick);
    }


    public void drawGrid(int gridLength, int gridWidth) {
            double cellWidth = canvas.getWidth() / gridWidth;
            double cellHeight = canvas.getHeight() / gridLength;
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setStroke(Color.BLACK);
            Random random = new Random();

            points.clear(); // Clear the existing points

            // Generate points at the intersections of the grid lines
            for (int i = 1; i <= gridLength - 1; i++) {
                for (int j = 1; j <= gridWidth - 1; j++) {
                    double x = j * cellWidth;
                    double y = i * cellHeight;
                    points.add(new Point(x, y));
                }
            }


        // Draw grid lines
        gc.setLineWidth(1);
        for (int i = 0; i <= gridLength; i++) {
            double y = i * cellHeight;
            gc.strokeLine(0, y, canvas.getWidth(), y);
        }

        for (int i = 0; i <= gridWidth; i++) {
            double x = i * cellWidth;
            gc.strokeLine(x, 0, x, canvas.getHeight());
        }

        double circleRadius = Math.min(cellWidth, cellHeight) * 0.1;
        for (Point point : points) {
            double x = point.getX();
            double y = point.getY();
            gc.strokeOval(x - circleRadius, y - circleRadius, 2 * circleRadius, 2 * circleRadius);
        }

        // Draw random lines between nearby points
        gc.setLineWidth(4);
        for (int i = 0; i < points.size(); i++) {
            Point startPoint = points.get(i);
            List<Point> nearbyPoints = getNearbyPoints(startPoint, points, cellWidth, cellHeight);
            if (!nearbyPoints.isEmpty()) {
                Point endPoint = nearbyPoints.get(random.nextInt(nearbyPoints.size()));
                gc.strokeLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
            }
        }
    }

    private List<Point> getNearbyPoints(Point point, List<Point> points, double cellWidth, double cellHeight) {
        List<Point> nearbyPoints = new ArrayList<>();
        double threshold = Math.min(cellWidth, cellHeight) * 1.5; // Adjust the threshold as desired
        for (Point p : points) {
            if (!p.equals(point) && p.distance(point) <= threshold) {
                nearbyPoints.add(p);
            }
        }
        return nearbyPoints;
    }
    private void handleMouseClick(MouseEvent event) {
        if (gameOver) {
            return;
        }

        Point clickedPoint = getClickedPoint(event.getX(), event.getY());
        if (clickedPoint != null) {
            if (selectedPoints.isEmpty() || isAdjacentPoint(clickedPoint, selectedPoints.get(selectedPoints.size() - 1))) {
                selectedPoints.add(clickedPoint);
                fillPoint(clickedPoint, currentPlayerColor);
                if (!hasAvailableMoves()) {
                    gameOver = true;
                    System.out.println("Player " + (currentPlayerColor == Color.RED ? "Red" : "Blue") + " wins!");
                } else {
                    currentPlayerColor = (currentPlayerColor == Color.RED) ? Color.BLUE : Color.RED;
                }
            }
        }
    }

    private Point getClickedPoint(double x, double y) {
        for (Point point : points) {
            if (point.contains(x, y)) {
                return point;
            }
        }
        return null;
    }

    private boolean isAdjacentPoint(Point point1, Point point2) {
        return getNearbyPoints(point1, points, canvas.getWidth() / 10, canvas.getHeight() / 10).contains(point2);
    }

    private void fillPoint(Point point, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        double circleRadius = Math.min(canvas.getWidth() / 10, canvas.getHeight() / 10) * 0.1;
        gc.fillOval(point.getX() - circleRadius, point.getY() - circleRadius, 2 * circleRadius, 2 * circleRadius);
    }

    private boolean hasAvailableMoves() {
        Point lastSelectedPoint = selectedPoints.get(selectedPoints.size() - 1);
        List<Point> nearbyPoints = getNearbyPoints(lastSelectedPoint, points, canvas.getWidth() / 10, canvas.getHeight() / 10);
        for (Point point : nearbyPoints) {
            if (!selectedPoints.contains(point)) {
                return true;
            }
        }
        return false;
    }
}
