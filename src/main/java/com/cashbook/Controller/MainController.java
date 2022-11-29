package com.cashbook.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("overview.fxml"));
        }catch (IOException e) {

        }
        bpMain.setCenter(root);
    }

    @FXML
    private BorderPane bpMain;

    @FXML
    private Button btnOW;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnRecord;

    @FXML
    private Button btnStatement;

    @FXML
    private void onbtnOW(ActionEvent event) {
        loadPage("overview");
    }

    @FXML
    void onbtnLogout(ActionEvent event) throws IOException {

        //go back to loginPage
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = (Stage) btnLogout.getScene().getWindow();

        stage.setScene(scene);
        stage.centerOnScreen();
    }

    @FXML
    void onbtnRecord(ActionEvent event) {
        loadPage("record");
    }

    @FXML
    void onbtnStatement(ActionEvent event) {
        loadPage("statement");
    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        }catch (IOException e) {

        }
        bpMain.setCenter(root);
    }
}
