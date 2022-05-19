module com.example.battleshipfinalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.battleshipfinalproject to javafx.fxml;
    exports com.example.battleshipfinalproject;
}