package com.przemekwosko.verbs.controllers;

import com.przemekwosko.verbs.model.SessionFactory;
import com.przemekwosko.verbs.model.Verb;
import com.przemekwosko.verbs.model.VerbConjugation;
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
    private ListSelectionView<VerbConjugation> typeSelection;
    @FXML
    private TextField buildingName;
    private Verb verb;

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    /**
     * Method for updating Building after button click
     * @param actionEvent
     */
    public void updateBuilding(ActionEvent actionEvent) {
//        verb.setBuildingName(buildingName.getText());
//        building.setTypeList(new HashSet<>(typeSelection.getTargetItems()));
//        try (Session session = SessionFactory.getSession()) {
//            session.saveOrUpdate(building);
//            session.beginTransaction().commit();
//        }
//        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void closeModal(ActionEvent actionEvent) {
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    void setProperties() {
//        buildingName.setText(building.getBuildingName());
//        List<Type> typeList;
//        try (Session session = SessionFactory.getSession()) {
//            typeList = session.createCriteria(Type.class).addOrder(Order.asc("typeName")).list();
//        }
//        typeList.removeAll(building.getTypeList());
//        typeSelection.getSourceItems().addAll(typeList);
//        typeSelection.getTargetItems().addAll(building.getTypeList());
    }
}
