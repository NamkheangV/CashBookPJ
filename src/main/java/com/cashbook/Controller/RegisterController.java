package com.cashbook.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class RegisterController {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSubmit;

    @FXML
    private  TextField txtPass;

    @FXML
    private TextField txtCon;

    @FXML
    private Label txtError;

    @FXML
    private TextField txtFname;

    @FXML
    private PasswordField txtID;

    @FXML
    private PasswordField txtID1;

    @FXML
    private TextField txtLname;

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
    void onbtnSubmit(ActionEvent event) throws IOException {
        String Fname = txtFname.getText();
        String Lname = txtLname.getText();
        String ID = txtID.getText();
        String Password = txtPass.getText();
        String ConPass = txtCon.getText();

        System.out.println("Register success!");
        Parent root = FXMLLoader.load(getClass().getResource("balance.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        Stage popup = new Stage();
        popup.initStyle(StageStyle.TRANSPARENT);
        popup.setScene(scene);
        popup.show();





//        popup.initStyle(StageStyle.TRANSPARENT);
//        Label lb = new Label("Register success!");
//        popup.showAndWait();
//        popup.initStyle(StageStyle.TRANSPARENT);
//        Label label1= new Label("Your balance");
//        Button bl = new Button("Balance");
//        TextField txtBl = new TextField();
//        bl.setOnAction(e -> popup.close());
//        VBox layout = new VBox(10);
//        layout.getChildren().addAll(label1, bl);
//        layout.setAlignment(Pos.CENTER);
//        Scene scene1= new Scene(layout, 300, 250);
//        popup.setScene(scene1);
//        popup.showAndWait();
    }

}
