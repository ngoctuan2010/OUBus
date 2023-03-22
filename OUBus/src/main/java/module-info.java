module com.oubus.oubus {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.oubus.oubus to javafx.fxml;
    exports com.oubus.oubus;
}
