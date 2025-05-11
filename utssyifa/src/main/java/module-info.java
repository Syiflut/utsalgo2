module com.example.utssyifa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.utssyifa to javafx.fxml;
    exports com.example.utssyifa;
}