// MainFrame class
package org.Homework;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class MainFrame extends BorderPane {
    DrawingPanel drawingPanel = new DrawingPanel(500,600);
    ConfigPanel configPanel = new ConfigPanel(drawingPanel);
    ControlPanel controlPanel = new ControlPanel();

    public void start(Stage primaryStage) {
        drawingPanel = new DrawingPanel(500, 500);
        drawingPanel.drawGrid(10, 10);

        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> startNewGame());

        VBox root = new VBox(drawingPanel, newGameButton);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dot and Boxes Game");
        primaryStage.show();
    }

    private void startNewGame() {
        drawingPanel.startNewGame();
    }

    public MainFrame() {
        drawingPanel = new DrawingPanel(500,600);
        configPanel = new ConfigPanel(drawingPanel);
        controlPanel = new ControlPanel();

        setTop(configPanel);
        setCenter(drawingPanel);
        setBottom(controlPanel);
    }

    public Parent getRoot() {
        return this;
    }


}
