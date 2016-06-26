package com.przemekwosko.verbs;

import com.przemekwosko.verbs.service.HTMLContentService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main javaFX class
 */
public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);
    }

    private HTMLContentService service;

    /**
     * Start method for JavaFX that sts window size, and fire fillDatabaseService
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        Scene scene = new Scene(root, 800, 600);
        service = new HTMLContentService();
        service.startFetch(); // we should handle this by subscribing observable, and periodically emit results
        primaryStage.setTitle("koniugacja czasownikow w języku angielskim");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
