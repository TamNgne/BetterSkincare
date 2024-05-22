module com.betterskincare2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.betterskincare2 to javafx.fxml;
    exports com.betterskincare2;
    exports com.betterskincare2.Controller;
    opens com.betterskincare2.Controller to javafx.fxml;
}