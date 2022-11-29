module com.example.cashbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.cashbook.Controller to javafx.fxml;
    exports com.cashbook.Controller;

}