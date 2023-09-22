module com.example.sanmak {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sanmak to javafx.fxml;
    exports com.example.sanmak;
}