// MainFrame class
package org.Homework;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainFrame extends BorderPane {
    public MainFrame() {
        DrawingPanel drawingPanel = new DrawingPanel(500,600);
        ConfigPanel configPanel = new ConfigPanel(drawingPanel);
        ControlPanel controlPanel = new ControlPanel();

        setTop(configPanel);
        setCenter(drawingPanel);
        setBottom(controlPanel);
    }

    public Parent getRoot() {
        return this;
    }
}
