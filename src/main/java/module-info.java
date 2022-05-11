module com.example.carrace12 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.jetbrains.annotations;
    requires com.google.gson;
    requires java.desktop;
    requires kryonet;

    opens com.example.carrace12 to javafx.fxml;
    exports com.example.carrace12;
    exports map;
    opens map to javafx.fxml;
}