package com.cashbook.Controller;

import com.cashbook.Class.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtID;

    @FXML
    private Label txtError;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtCon;

    @FXML
    private TextField txtBalance;

    @FXML
    void onbtnClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onbtnCancel(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = (Stage) btnCancel.getScene().getWindow();

        stage.setScene(scene);
    }

    @FXML
    void onbtnSubmit(ActionEvent event) {
        Database db = new Database();

        try {
            String Name = txtName.getText();
            String ID = txtID.getText();
            String Password = txtPass.getText();
            String ConPass = txtCon.getText();
            String Balance = txtBalance.getText();

            String sql = String.format(
                    "INSERT INTO `account`(`Acc_ID`, `Acc_Pass`, `Acc_Name`, Acc_Balance) VALUES ('%s','%s','%s','%s')",
                    ID, Password, Name, Balance);
            String sql2 = String.format("SELECT `Acc_ID` FROM `account` WHERE Acc_ID='%s';", ID);

            ResultSet rs = db.getResultSet(sql2);
            if (!rs.next()) {
                if (Password.equals(ConPass)) {
                    if (db.execute(sql)) {
                        System.out.print("Login success");
                        Parent root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
                        Scene scene = new Scene(root);
                        scene.setFill(Color.TRANSPARENT);
                        Stage stage = (Stage) btnSubmit.getScene().getWindow();

                        stage.setScene(scene);
                    } else
                        txtError.setText("Something wrong!");
                } else
                    txtError.setText("Your ID do not match!");
            } else if (rs.next()) {
                txtError.setText("This ID is already used!");
            }

        } catch (Exception e) {
            System.out.print("Error");
        }

        // Parent root = FXMLLoader.load(getClass().getResource("balance.fxml"));
        // Scene scene = new Scene(root);
        // scene.setFill(Color.TRANSPARENT);
        //
        // Stage popup = new Stage();
        // popup.initStyle(StageStyle.TRANSPARENT);
        // popup.setScene(scene);
        // popup.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
