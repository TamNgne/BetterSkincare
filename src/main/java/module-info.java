module com.betterskincare2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.betterskincare to javafx.fxml;
    exports com.betterskincare;
    exports com.betterskincare.Controller;
    opens com.betterskincare.Controller to javafx.fxml;
}