package com.filipcygan.buidlingsOntology;

import com.filipcygan.buidlingsOntology.model.SessionFactory;
import com.filipcygan.buidlingsOntology.service.FillDatabaseTask;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        final Session session = SessionFactory.getSession();
//        try {
//            Transaction tx;
//            tx = session.beginTransaction();
//            Type type = new Type();
//            type.setType("aaa");
//            session.save(type);
//            tx.commit();
//
//        } finally {
//            session.close();
//        }


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
        primaryStage.show();
    }
}
