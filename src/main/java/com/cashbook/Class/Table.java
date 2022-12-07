package com.cashbook.Class;

import javafx.beans.property.SimpleStringProperty;

public class Table {
    private SimpleStringProperty date;
    private SimpleStringProperty nameList;
    private SimpleStringProperty income;
    private SimpleStringProperty expenses;
    private SimpleStringProperty total;

    public Table(String date, String nameList, String income, String expenses, String total) {
        this.date = new SimpleStringProperty(date);
        this.nameList = new SimpleStringProperty(nameList);
        this.income = new SimpleStringProperty(income);
        this.expenses = new SimpleStringProperty(expenses);
        this.total = new SimpleStringProperty(total);
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public SimpleStringProperty nameListProperty() {
        return nameList;
    }

    public SimpleStringProperty incomeProperty() {
        return income;
    }

    public SimpleStringProperty expensesProperty() {
        return expenses;
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }
}
