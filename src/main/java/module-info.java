module org.example.employeeaccesscontrol {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.employeeaccesscontrol to javafx.fxml;
    exports org.example.employeeaccesscontrol;
}