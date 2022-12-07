package com.cashbook.Controller;

import com.cashbook.Class.Database;
import com.cashbook.Class.currAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Date;



public class RecordController implements Initializable {
    private String curr_acc = currAccount.getAcc_ID();
    private String selectedType = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

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
    private TextField txtNote;

    @FXML
    void onbtnDone(ActionEvent event) {
        Database db = new Database();
        try {
            String date = datePick.getValue().toString();
            String amount = txtAmount.getText();
            String category = cbCategory.getValue();
            String note = txtNote.getText();

            String sql = "",sqlBalance = "";

            java.sql.Date sqlDate = java.sql.Date.valueOf(date);

            if (selectedType.equals("0")) {
                sql = String.format("INSERT INTO `transaction` (`TS_Date`, `TS_Des`, `Amount`, `Type_ID`, `Acc_ID`, `CT_ID`) " +
                        "VALUES ('%s','%s','%s','%s','%s',(SELECT CT_ID FROM category WHERE CT_Des = '%s' ))", sqlDate, note, amount, "0", curr_acc, category);
            } else if (selectedType.equals("1")) {
                sql = String.format("INSERT INTO `transaction` (`TS_Date`, `TS_Des`, `Amount`, `Type_ID`, `Acc_ID`, `CT_ID`) " +
                        "VALUES ('%s','%s','%s','%s','%s',(SELECT CT_ID FROM category WHERE CT_Des = '%s' ))", sqlDate, note, amount, "1", curr_acc, category);
            }

            if (db.execute(sql)) {

                if (selectedType.equals("0")) {
                    sqlBalance = String.format("UPDATE `account` SET `Acc_Balance`=((SELECT Acc_Balance FROM account WHERE Acc_ID = '%s')+'%s') WHERE Acc_ID = '%s' ", curr_acc, amount, curr_acc);
                } else if (selectedType.equals("1")) {
                    sqlBalance = String.format("UPDATE `account` SET `Acc_Balance`=((SELECT Acc_Balance FROM account WHERE Acc_ID = '%s')-'%s') WHERE Acc_ID = '%s' ", curr_acc, amount, curr_acc);
                }
                db.execute(sqlBalance);
//                System.out.println(sqlBalance);

                datePick.setValue(null);
                txtAmount.setText(null);
                txtNote.setText(null);
                cbCategory.setValue(null);

                System.out.println("Record Success");

            } else {
                System.out.println("Record Fail!");
            }

//            ResultSet rs = db.getResultSet(sql);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onbtnExp(ActionEvent event) {
        ObservableList<String> Expenses = FXCollections.observableArrayList("Food & Drink", "Transportation", "Dept", "Shopping" );
        cbCategory.setItems(Expenses);
        selectedType = "1";
    }

    @FXML
    void onbtnIncome(ActionEvent event) {
        ObservableList<String> Income = FXCollections.observableArrayList("Salary", "Sell", "Gift", "Other");
        cbCategory.setItems(Income);
        selectedType = "0";
    }





}
