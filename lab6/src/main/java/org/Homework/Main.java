// Main class
package org.Homework;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainFrame frame = new MainFrame();
        Scene scene = new Scene(frame.getRoot());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Positional Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
