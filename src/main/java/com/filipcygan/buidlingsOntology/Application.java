package com.filipcygan.buidlingsOntology;

import com.filipcygan.buidlingsOntology.model.Building;
import com.filipcygan.buidlingsOntology.model.SessionFactory;
import com.filipcygan.buidlingsOntology.model.Type;
import com.filipcygan.buidlingsOntology.service.FillDatabaseTask;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
//        try( Session session = SessionFactory.getSession()){}
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mainWindow.fxml"));
        Scene scene = new Scene(root, 300, 275);
//        Service fillDatabaseService = new FillDatabaseService();
//        fillDatabaseService.start();
        Task task = new FillDatabaseTask();
        new Thread(task).start();
        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        Type t = new Type();
        t.setTypeName("aaaa");
        ArrayList<Building> buildings = new ArrayList<>();
        buildings.add(new Building());
        buildings.add(new Building());
        t.setBuildingList(buildings);
        try (Session session = SessionFactory.getSession()) {
            Transaction tx = session.beginTransaction();
            session.save(t);
            tx.commit();
        }

        primaryStage.show();
    }
}
