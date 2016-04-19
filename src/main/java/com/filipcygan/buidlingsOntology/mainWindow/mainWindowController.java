package com.filipcygan.buidlingsOntology.mainWindow;

import com.filipcygan.buidlingsOntology.model.Building;
import com.filipcygan.buidlingsOntology.model.Type;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class mainWindowController {
    @FXML
    public TreeView buildingsTree;
    @FXML
    private TextField searchField;


    public void search(Event event) {
        List<Building> buildings = new ArrayList();
        TreeItem<String> root = new TreeItem<>();
        try (Session session = com.filipcygan.buidlingsOntology.model.SessionFactory.getSession()) {
            buildings = session.createCriteria(Building.class).add(Restrictions.like("buildingName", searchField
                    .getText() + "%")).list();
            System.out.println(buildings.toString());
        }

        for (Building building : buildings) {
            TreeItem<String> buildingRoot = new TreeItem<>(building.getBuildingName());
            for (Type type : building.getTypeList()) {
                buildingRoot.getChildren().add(new TreeItem<String>(type.getTypeName()));
            }
            root.getChildren().add(buildingRoot);
            buildingsTree.setRoot(root);
        }
    }
}
