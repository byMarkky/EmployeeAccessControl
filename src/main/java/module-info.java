module org.example.control {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;


    opens org.example.control to javafx.fxml;
    exports org.example.control;
    exports org.example.control.controller;
    opens org.example.control.controller to javafx.fxml;
}