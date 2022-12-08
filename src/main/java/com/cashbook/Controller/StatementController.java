package com.cashbook.Controller;

import com.cashbook.Class.Database;
import com.cashbook.Class.currAccount;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import se.alipsa.ymp.YearMonthPickerCombo;

import java.net.URL;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;

public class StatementController implements Initializable {
    private String curr_acc = currAccount.getAcc_ID();

    @FXML
    void onSelectedMonth(ActionEvent event) {
        initialize(null,null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String pattern = "#,###,###.##";
        DecimalFormat df = new DecimalFormat(pattern);

        YearMonth selectedYearMonth = selectedMonth.getValue();
//        System.out.println(selectedYearMonth);

        //subString to get the year and month
        String year = selectedYearMonth.toString().substring(0,4);
        String month = selectedYearMonth.toString().substring(5,7);
        String date = year + "-" + month + "%";


        Database db = new Database();
        try {
            //query by month
            String sql = String.format("SELECT `Amount`, `Type_ID` FROM `transaction` WHERE acc_ID = '%s' AND TS_Date LIKE '%s'", curr_acc, date);
//            System.out.println(sql);
            ResultSet rs = db.getResultSet(sql);
//            System.out.println(rs);


            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            int totalExp = 0, totalInc = 0;
            while (rs.next()) {
                if (rs.getString("Type_ID").equals("0")) {
                    totalInc += Integer.parseInt(rs.getString(1));
                } else if (rs.getString("Type_ID").equals("1")) {
                    totalExp += Integer.parseInt(rs.getString(1));
                }
            }
//            System.out.println(totalExp+" "+totalInc);
            pieChartData.add(new PieChart.Data("Income", totalInc));
            pieChartData.add(new PieChart.Data("Expenses", totalExp));
            pieChart.setData(pieChartData);
            Expenses.setText(df.format(totalExp));
            Income.setText(df.format(totalInc));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private YearMonthPickerCombo selectedMonth;

    @FXML
    private Label Expenses;

    @FXML
    private Label Income;

    @FXML
    private PieChart pieChart;
}

