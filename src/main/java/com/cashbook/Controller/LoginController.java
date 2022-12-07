package com.cashbook.Controller;

import com.cashbook.Class.Database;
import com.cashbook.Class.currAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    private Button btnClose;

    @FXML
    private Button btnLogin;

    @FXML
    private Label btnReg;

    @FXML
    private TextField txtID;

    @FXML
    private Label txtError;

    @FXML
    private PasswordField txtPass;

    @FXML
    void onbtnClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void onbtnLogin(ActionEvent event) {
        Database db = new Database();
        try {
            String acc_ID = txtID.getText();
            String acc_Pass = txtPass.getText();
            String sql = String.format("SELECT `acc_ID`, `acc_Pass` FROM `account` WHERE acc_ID='%s' AND acc_Pass='%s'",
                    acc_ID,
                    acc_Pass);
            ResultSet rs = db.getResultSet(sql);

            if (rs.next()) {
                currAccount.setAcc_ID(acc_ID);

                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
            } else
                throw new IOException("Wrong ID or Password!");

            // if (acc_ID.equals("") && acc_Pass.equals("")) {
            // throw new IOException("Enter ID or Password!");
            // } else if (acc_ID.equals("admin") && acc_Pass.equals("admin")) {
            // System.out.println("Login success!");
            // }

        } catch (IOException e) {
            txtError.setText(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onbtnReg(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registerPage.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = (Stage) btnReg.getScene().getWindow();

        stage.setScene(scene);
    }

}