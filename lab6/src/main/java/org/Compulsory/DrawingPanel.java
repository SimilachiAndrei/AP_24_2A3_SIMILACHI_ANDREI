// DrawingPanel class
package org.Compulsory;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class DrawingPanel extends BorderPane {
    private Canvas canvas;

    public DrawingPanel() {
        canvas = new Canvas(500, 500);
        setCenter(canvas);
    }

    public void drawGrid(int gridLength, int gridWidth) {
        double cellWidth = canvas.getWidth()/gridWidth ;
        double cellLength = canvas.getHeight()/gridLength ;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);

        // Draw vertical lines
        for (int i = 0; i <= gridLength; i++) {
            double x = i * cellLength;
            gc.strokeLine(x, 0, x, canvas.getHeight());
        }

        // Draw horizontal lines
        for (int i = 0; i <= gridWidth; i++) {
            double y = i * cellWidth;
            gc.strokeLine(0, y, canvas.getWidth(), y);
        }
    }
}
