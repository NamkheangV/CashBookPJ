package com.cashbook.Controller;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StatementController implements Initializable {

    @FXML
    private ComboBox<String> cbMonth;

    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Income", 40),
                        new PieChart.Data("Expenses", 52));

        pieChart.setTitle("Statement of Month");
        pieChart.setData(pieChartData);

        ObservableList<String> month =
                FXCollections.observableArrayList("January", "Apirl", "December");
        cbMonth.setItems(month);
    }
}

