package com.przemekwosko.verbs;

import com.przemekwosko.verbs.service.FillDatabaseService;
import javafx.concurrent.ScheduledService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.concurrent.ScheduledService.EXPONENTIAL_BACKOFF_STRATEGY;

/**
 * Main javaFX class
 */
public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start method for JavaFX that sts window size, and fire fillDatabaseService
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        Scene scene = new Scene(root, 800, 600);
        ScheduledService fillDatabaseService = new FillDatabaseService();
        fillDatabaseService.setRestartOnFailure(true);
        fillDatabaseService.setBackoffStrategy(EXPONENTIAL_BACKOFF_STRATEGY);
        fillDatabaseService.start();
        primaryStage.setTitle("Ontologia budynków");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
