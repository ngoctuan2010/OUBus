module com.oubus.oubus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.oubus.oubus to javafx.fxml;
    exports com.oubus.oubus;
    exports com.oubus.services;
}
