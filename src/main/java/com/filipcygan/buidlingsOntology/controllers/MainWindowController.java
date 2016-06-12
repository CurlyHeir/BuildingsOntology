package com.filipcygan.buidlingsOntology.controllers;

import com.filipcygan.buidlingsOntology.model.Building;
import com.filipcygan.buidlingsOntology.model.SessionFactory;
import com.filipcygan.buidlingsOntology.model.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.CheckComboBox;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;

/**
 * GUI Controller
 */
public class MainWindowController implements Initializable {
    @FXML
    private TextField buildingName;
    @FXML
    private ListView<Building> buildingsList;
    @FXML
    private ListView<Type> typeList;
    @FXML
    private CheckComboBox<Type> typeComboBox;
    @FXML
    private TextField searchField;

    /**
     * Search function, fired when hitting anykey
     *
     * @param event
     */
    public void search(Event event) {
        fillListViewWithBuildings();
    }

    private void fillListViewWithBuildings() {
        Collection<Building> buildings;
        try (Session session = com.filipcygan.buidlingsOntology.model.SessionFactory.getSession()) {
            buildings = new LinkedHashSet(session.createCriteria(Building.class).add(Restrictions.
                    like("buildingName", "%" + searchField
                            .getText() + '%').ignoreCase()).addOrder(Order.asc("buildingName")).list());
        }

        ObservableList<Building> items = FXCollections.observableArrayList(buildings);
        buildingsList.setItems(items);
    }

    public void deleteItem(ActionEvent actionEvent) {
        try (Session session = SessionFactory.getSession()) {
            Query query = session.createQuery("delete Building where buildingId = :ID");
            query.setParameter("ID", buildingsList.getSelectionModel().getSelectedItem().getBuildingId());
            query.executeUpdate();
            buildingsList.getItems().remove(buildingsList.getSelectionModel().getSelectedIndex());
        }
    }

    public void fillTypeList(Event event) {
        ObservableList<Type> items = FXCollections.observableArrayList(buildingsList.getSelectionModel().
                getSelectedItem().getTypeList());
        typeList.setItems(items);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List types;
        try (Session session = SessionFactory.getSession()) {
            types = session.createCriteria(Type.class).addOrder(Order.asc("typeName")).list();

        }
        typeComboBox.getItems().addAll(types);
    }

    public void addBuilding(ActionEvent actionEvent) {
        Building building = new Building();
        building.setBuildingName(buildingName.getText());
        for (Type type : typeComboBox.getCheckModel().getCheckedItems()) {
            building.addType(type);
        }
        try (Session session = SessionFactory.getSession()) {
            session.saveOrUpdate(building);
            session.beginTransaction().commit();
        }
    }

    public void showUpdateDialog(ActionEvent actionEvent) throws IOException {
        Stage detailStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateModal.fxml"));
        Parent root = loader.load();
        UpdateModalController updateModalController = loader.getController();


        updateModalController.setBuilding(buildingsList.getSelectionModel().getSelectedItem());
        updateModalController.setProperties();
        detailStage.setScene(new Scene(root));
        detailStage.initModality(Modality.APPLICATION_MODAL);
        detailStage.initStyle(StageStyle.UNDECORATED);
        detailStage.showAndWait();


    }
}

