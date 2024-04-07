// ControlPanel class
package org.Homework;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlPanel extends HBox {
    public ControlPanel() {
        setSpacing(10);
        setAlignment(Pos.CENTER);

        Button loadButton = new Button("Load");
        Button saveButton = new Button("Save");
        Button exitButton = new Button("Exit");

        getChildren().addAll(loadButton, saveButton, exitButton);

        exitButton.setOnAction(e -> System.exit(0));
    }
}
