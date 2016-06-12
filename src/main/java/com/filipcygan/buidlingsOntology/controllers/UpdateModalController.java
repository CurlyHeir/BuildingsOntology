package com.filipcygan.buidlingsOntology.controllers;

import com.filipcygan.buidlingsOntology.model.Building;
import com.filipcygan.buidlingsOntology.model.SessionFactory;
import com.filipcygan.buidlingsOntology.model.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.ListSelectionView;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.HashSet;
import java.util.List;

public class UpdateModalController {
    @FXML
    private ListSelectionView<Type> typeSelection;
    @FXML
    private TextField buildingName;
    private Building building;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void updateBuilding(ActionEvent actionEvent) {
        building.setBuildingName(buildingName.getText());
        building.setTypeList(new HashSet<Type>(typeSelection.getTargetItems()));
        try (Session session = SessionFactory.getSession()) {
            session.saveOrUpdate(building);
            session.beginTransaction().commit();
        }
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void closemodal(ActionEvent actionEvent) {
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    void setProperties() {
        buildingName.setText(building.getBuildingName());
        List<Type> typeList;
        try (Session session = SessionFactory.getSession()) {
            typeList = session.createCriteria(Type.class).addOrder(Order.asc("typeName")).list();
        }
        typeList.removeAll(building.getTypeList());
        typeSelection.getSourceItems().addAll(typeList);
        typeSelection.getTargetItems().addAll(building.getTypeList());
    }
}
