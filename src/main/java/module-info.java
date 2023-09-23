module com.example.sanmak {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sanmak to javafx.fxml;
    exports com.example.sanmak;
}