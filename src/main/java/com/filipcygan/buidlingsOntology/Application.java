package com.filipcygan.buidlingsOntology;

import com.filipcygan.buidlingsOntology.service.FillDatabaseService;
import javafx.concurrent.Service;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        Scene scene = new Scene(root, 800, 600);
        Service fillDatabaseService = new FillDatabaseService();
        fillDatabaseService.start();
        primaryStage.setTitle("Ontologia budynków");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
