package com.przemekwosko.verbs.controllers;

import com.przemekwosko.verbs.model.*;
import com.sun.source.tree.LambdaExpressionTree;
import com.sun.tools.javac.code.Attribute;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.CheckComboBox;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import rx.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.concurrent.ScheduledService.EXPONENTIAL_BACKOFF_STRATEGY;

/**
 * GUI Controller
 */
public class MainWindowController implements Initializable {

    @FXML
    private TextField buildingName;

    @FXML
    private ListView<Verb> buildingsList;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<VerbConjugation> conjustions;

    @FXML
    private Pane verbDescription;

    @FXML
    private Label tenseName;

    @FXML
    private Label first, second, third, mFirst, mSecond, mThird;

    /**
     * Search function, fired when hitting anykey
     *
     * @param event
     */
    public void search(Event event) {
        fillListViewWithBuildings();
    }

    private void fillListViewWithBuildings() {
        Collection<Verb> verbs;
        try (Session session = com.przemekwosko.verbs.model.SessionFactory.getSession()) {
            verbs = new LinkedHashSet(session.createCriteria(Verb.class).add(Restrictions.
                    like("baseVerb", "%" + searchField
                            .getText() + '%').ignoreCase()).addOrder(Order.asc("baseVerb")).list());
        }

        ObservableList<Verb> items = FXCollections.observableArrayList(verbs);
        buildingsList.setItems(items);

        buildingsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Verb>() {
            @Override
            public void changed(ObservableValue<? extends Verb> observable, Verb oldValue, Verb newValue) {
                    fillListWithConjuctions(newValue);
            }
        });
    }

    private  void fillListWithConjuctions(Verb verb) {
        ObservableList<VerbConjugation> items = FXCollections.observableArrayList(verbDetails(verb));
        conjustions.setItems(items);
        conjustions.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<VerbConjugation>() {

            @Override
            public void changed(ObservableValue<? extends VerbConjugation> observable, VerbConjugation oldValue, VerbConjugation newValue) {
                fillDetails(newValue);
            }
        });
    }

    public void fillDetails(VerbConjugation verb) {
        tenseName.setText(verb.tenseName());
        first.setText(verb.getSingular1());
        second.setText(verb.getSingular2());
        third.setText(verb.getSingular3());

        mFirst.setText(verb.getPlural1());
        mSecond.setText(verb.getPlural2());
        mThird.setText(verb.getPlural3());
    }

    /**
     * Method for deleting building from database
     * @param actionEvent
     */
    public void deleteItem(ActionEvent actionEvent) {

    }

    /**
     *
     * @param event
     */
    public void fillTypeList(Event event) {
//        ObservableList<Type> items = FXCollections.observableArrayList(buildingsList.getSelectionModel().
//                getSelectedItem().getTypeList());
//        typeList.setItems(items);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List types;
        try (Session session = SessionFactory.getSession()) {
//            types = session.createCriteria(Type.class).addOrder(Order.asc("typeName")).list();

        }
        types = new ArrayList();
        fillListViewWithBuildings();
    }

    /**
     * Button action for inserting new Building
     * @param actionEvent
     */
    public void addBuilding(ActionEvent actionEvent) {
//        Building building = new Building();
//        building.setBuildingName(buildingName.getText());
//        for (Type type : typeComboBox.getCheckModel().getCheckedItems()) {
//            building.addType(type);
//        }
//        try (Session session = SessionFactory.getSession()) {
//            session.saveOrUpdate(building);
//            session.beginTransaction().commit();
//        }
    }

    /**
     * Button action, for showing modal
     * @param actionEvent
     * @throws IOException
     */
    public void showUpdateDialog(ActionEvent actionEvent) throws IOException {
//        Stage detailStage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateModal.fxml"));
//        Parent root = loader.load();
//        UpdateModalController updateModalController = loader.getController();
//        updateModalController.setBuilding(buildingsList.getSelectionModel().getSelectedItem());
//        updateModalController.setProperties();
//        detailStage.setScene(new Scene(root));
//        detailStage.initModality(Modality.APPLICATION_MODAL);
//        detailStage.initStyle(StageStyle.UNDECORATED);
//        detailStage.showAndWait();
    }


    private LinkedHashSet<VerbConjugation> verbDetails(Verb verb) {
        LinkedHashSet<VerbConjugation> tenses = new LinkedHashSet<>();
        tenses.add(verb.getVerbConditionalPerfect());
        tenses.add(verb.getVerbConditionalPerfectProgressive());
        tenses.add(verb.getVerbConditionalPresent());
        tenses.add(verb.getVerbConditionalPresentProgressive());
        tenses.add(verb.getVerbFuture());
        tenses.add(verb.getVerbFutureContinuous());
        tenses.add(verb.getVerbSimplePast());
        tenses.add(verb.getVerbFuturePerfect());
        tenses.add(verb.getVerbConditionalPresent());
        tenses.add(verb.getVerbPastContinuous());
        tenses.add(verb.getVerbPastPerfectSubjunctive());
        tenses.add(verb.getVerbPresentPerfect());
        tenses.add(verb.getVerbFuturePerfetContinuous());
        tenses.add(verb.getVerbPresentContinuous());
        tenses.add(verb.getVerbPastSubjunctive());
        tenses.add(verb.getVerbPresentPerfectContinuous());
        return tenses;
    }
}

