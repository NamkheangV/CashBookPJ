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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

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
//        DB db = new DB();
        try {
            String acID = txtID.getText();
            String acPass = txtPass.getText();
            String sql = String.format("select permission from users where user_id='%s' and user_pass='%s'", acID, acPass);

            if (acID.equals("")&&acPass.equals("")){
                throw new IOException("Enter ID or Password!");
            } else if (acID.equals("admin") && acPass.equals("admin")) {
                System.out.println("Login success!");

                Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
//                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//                stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
//                stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

            } else throw new IOException("Wrong ID or Password!");

        } catch (IOException e) {
            txtError.setText(e.getMessage());
        }


            //JOptionPane.showMessageDialog(null, sql);
//            ResultSet rs = db.getResultSet(sql); //ตัวเก็บข้อมูลที่ได้จากการเลือกบน Database
//            if (rs.next()) {
//                if (rs.getString(1).equals("user")) {
//                    UserCheckHour user = new UserCheckHour(get_current_id()); //โยนชื่อ Method เข้าไป
//                    this.setVisible(false); //ซ่อนหน้าต่าง ข้อมูลไม่หาย
//                    user.setVisible(true);
//                }else {
//                    AdCheckHour admin01 = new AdCheckHour(get_current_id()); //ดึงไอดีที่ใส่เข้ามา
//                    this.setVisible(false);
//                    admin01.setVisible(true);
//                }
//            }
//            else throw new Exception("Fail");
//        }
//        catch(Exception e) {
//            txtError.setText("Wrong ID or Password!");
//        }
    }

    @FXML
    public void onbtnReg(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("registerPage.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = (Stage) btnReg.getScene().getWindow();

        stage.setScene(scene);
    }

//    @FXML
//    private void hanbleClose(ActionEvent event) {
//
//    }
}