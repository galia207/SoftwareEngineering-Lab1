module com.example.gcm.lab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gcm.lab2 to javafx.fxml;
    exports com.example.gcm.lab2;
}