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
    requires kryo;

    opens gui to javafx.fxml;
    exports gui;
    exports map;
    opens map to javafx.fxml;
}