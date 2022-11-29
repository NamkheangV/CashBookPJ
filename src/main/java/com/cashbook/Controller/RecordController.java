package com.cashbook.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RecordController implements Initializable {

    @FXML
    private Button btnDone;

    @FXML
    private Button btnExp;

    @FXML
    private Button btnIncome;

    @FXML
    private DatePicker datePick;

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextArea txtNote;

    @FXML
    void onbtnDone(ActionEvent event) {

    }

    @FXML
    void onbtnExp(ActionEvent event) {

    }

    @FXML
    void onbtnIncome(ActionEvent event) {

    }

    ObservableList<String> list = FXCollections.observableArrayList("Food & Drink", "Transportation", "Dept");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbCategory.setItems(list);
    }

}
