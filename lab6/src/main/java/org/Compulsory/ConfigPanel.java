// ConfigPanel class
package org.Compulsory;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ConfigPanel extends HBox {
    private TextField gridLengthField;
    private TextField gridWidthField;
    private Button newGameButton;

    public ConfigPanel(DrawingPanel drawingPanel) {
        setSpacing(10);
        setAlignment(Pos.CENTER);

        Label gridLengthLabel = new Label("Grid length:");
        Label gridWidthLabel = new Label("Grid width:");

        gridLengthField = new TextField("10");
        gridWidthField = new TextField("10");
        newGameButton = new Button("New Game");

        newGameButton.setOnAction(e -> {
            int gridLengthSize = Integer.parseInt(gridLengthField.getText());
            int gridWidthSize = Integer.parseInt(gridWidthField.getText());
            drawingPanel.drawGrid(gridLengthSize,gridWidthSize);
        });
        getChildren().addAll(gridLengthLabel, gridLengthField,gridWidthLabel,gridWidthField ,newGameButton);
    }
}
