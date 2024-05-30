module com.example.openendedlab {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.openendedlab to javafx.fxml;
    exports com.example.openendedlab;
}