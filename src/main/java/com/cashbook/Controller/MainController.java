package com.cashbook.Controller;

import com.cashbook.Class.Database;
import com.cashbook.Class.currAccount;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private String curr_acc = currAccount.getAcc_ID();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database db = new Database();

        try {
            String sql = String.format("SELECT `Acc_Name` FROM `account` WHERE Acc_ID = '%s'", curr_acc);
//            System.out.println(sql);
            ResultSet rs = db.getResultSet(sql);

            if (rs.next()) {
                Name.setText(rs.getString(1));
            } else
                System.out.println("Not found!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("overview.fxml"));
        } catch (IOException e) {
            System.out.print("Can't load");
        }
        bpMain.setCenter(root);

    }

    @FXML
    private Circle cirImage;

    @FXML
    private Label Name;

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
        // go back to loginPage
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
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException e) {

        }
        bpMain.setCenter(root);
    }
}
