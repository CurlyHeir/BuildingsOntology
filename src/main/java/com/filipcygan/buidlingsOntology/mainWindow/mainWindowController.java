package com.filipcygan.buidlingsOntology.mainWindow;

import com.filipcygan.buidlingsOntology.model.Building;
import com.filipcygan.buidlingsOntology.model.SessionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class mainWindowController {
    @FXML
    public TableView table;
    public TableColumn buildingNameCol;
    public TableColumn typeNameCol;
    @FXML
    private TextField searchField;

    public void search(ActionEvent actionEvent) {
        final Session session = SessionFactory.getSession();
        ObservableList<Building> buildings = FXCollections.observableList(session.createCriteria(Building.class)
                                                                                 .add(Restrictions.like("buildingName",
                                                                                                        "%" +
                                                                                                        searchField.getText() +
                                                                                                        "%"))
                                                                                 .list());
        buildingNameCol.setCellValueFactory(new PropertyValueFactory<Building, String>("buildingName"));
        typeNameCol.setCellValueFactory((new PropertyValueFactory<Building, String>("typeName")));
        table.setItems(buildings);
        System.out.println(buildings.toString());
    }
}
