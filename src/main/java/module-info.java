module edt.sdialprojet {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    exports model;
    opens model to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}