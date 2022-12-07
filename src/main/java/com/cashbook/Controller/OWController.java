package com.cashbook.Controller;

import com.cashbook.Class.Database;
import com.cashbook.Class.Table;
import com.cashbook.Class.currAccount;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Date;

public class OWController implements Initializable {
    private String curr_acc = currAccount.getAcc_ID();
    private volatile boolean stop = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date();
        Time();
        tsTable.setPlaceholder(new Label("Transaction not found"));

         colDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
         colList.setCellValueFactory(cellData -> cellData.getValue().nameListProperty());
         colCategory.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
         colIncome.setCellValueFactory(cellData -> cellData.getValue().incomeProperty());
         colExpenses.setCellValueFactory(cellData -> cellData.getValue().expensesProperty());


        // Get ID, Set name
        Database db = new Database();
        try {
            String sql = String.format("SELECT `Acc_Balance` FROM `account` WHERE Acc_ID = '%s'", curr_acc);
            ResultSet rs = db.getResultSet(sql);

            String pattern = "#,###,###.##";
            DecimalFormat df = new DecimalFormat(pattern);

            if (rs.next()) {
                Balance.setText(df.format(rs.getDouble(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void Date() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String datenow = df.format(new Date());
        Date.setText(datenow);
    }

    private void Time() {
        Thread th = new Thread(() -> {
            SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                final String timenow = df.format(new Date());
                Platform.runLater(() -> {
                    Time.setText(timenow);
                });
            }
        });
        th.start();
    }

    @FXML
    private TableView<Table> tsTable;

    @FXML
    private TableColumn<Table, String> colDate;

    @FXML
    private TableColumn<Table, String> colExpenses;

    @FXML
    private TableColumn<Table, String> colIncome;

    @FXML
    private TableColumn<Table, String> colList;

    @FXML
    private TableColumn<Table, String> colCategory;

    @FXML
    private Label Balance;

    @FXML
    private Label Date;

    @FXML
    private Label Time;

    @FXML
    private Button btnAll;

    @FXML
    private Button btnExpenese;

    @FXML
    private Button btnIncome;

    @FXML
    void onbtnAll(ActionEvent event) {
        Database db = new Database();
        try {
            String sql = String.format("SELECT `TS_Date`, `TS_Des` , `Amount`, `Type_ID`, category.CT_Des FROM `transaction` INNER JOIN category ON transaction.CT_ID = category.CT_ID WHERE acc_ID = '%s' ",
                    curr_acc);
//            System.out.println(sql);
            ResultSet rs = db.getResultSet(sql);

            ObservableList<Table> data = FXCollections.observableArrayList();
            while (rs.next()) {
                String date = rs.getString(1);
                String nameList = (rs.getString(2));
                String amount = (rs.getString(3));
                String type = rs.getString(4);
                String category = rs.getString(5);

                if (type.equals("1")) {
                    data.add(new Table(date, nameList, "0", amount, category));
                } else {
                    data.add(new Table(date, nameList, amount, "0",  category));
                }
            }
            tsTable.setItems(data);
            tsTable.setVisible(true);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @FXML
    void onbtnExpenses(ActionEvent event) {
        Database db = new Database();
        try {
            //SELECT category.CT_Des FROM `transaction` INNER JOIN category ON transaction.CT_ID = category.CT_ID
            String sql = String.format("SELECT `TS_Date`, `TS_Des` , `Amount`, `Type_ID`, category.CT_Des FROM `transaction` INNER JOIN category ON transaction.CT_ID = category.CT_ID WHERE Type_ID='1' AND acc_ID = '%s' ", curr_acc);
//            System.out.println(sql);
            ResultSet rs = db.getResultSet(sql);

            ObservableList<Table> data = FXCollections.observableArrayList();
            while (rs.next()) {
                String date = rs.getString(1);
                String nameList = (rs.getString(2));
                String amount = (rs.getString(3));
                String type = rs.getString(4);
                String category = rs.getString(5);

                data.add(new Table(date, nameList, "0", amount, category));
            }
            tsTable.setItems(data);
            tsTable.setVisible(true);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @FXML
    void onbtnIncome(ActionEvent event) {
        Database db = new Database();
        try {
            //SELECT category.CT_Des FROM `transaction` INNER JOIN category ON transaction.CT_ID = category.CT_ID
            String sql = String.format("SELECT `TS_Date`, `TS_Des` , `Amount`, `Type_ID`, category.CT_Des FROM `transaction` INNER JOIN category ON transaction.CT_ID = category.CT_ID WHERE Type_ID='0' AND acc_ID = '%s' ", curr_acc);
//            System.out.println(sql);
            ResultSet rs = db.getResultSet(sql);

            ObservableList<Table> data = FXCollections.observableArrayList();
            while (rs.next()) {
                String date = rs.getString(1);
                String nameList = (rs.getString(2));
                String amount = (rs.getString(3));
                String type = rs.getString(4);
                String category = rs.getString(5);

                data.add(new Table(date, nameList, amount, "0", category));
            }
            tsTable.setItems(data);
            tsTable.setVisible(true);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

}
