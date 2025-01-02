module org.example.control {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires jakarta.persistence;
    requires mysql.connector.j;
    requires org.hibernate.orm.core;

    requires java.naming;  // Java Naming and Directory Interface (JNDI) API.

    opens org.example.control to javafx.fxml;
    exports org.example.control;
    exports org.example.control.controller;
    opens org.example.control.controller to javafx.fxml;

    // This avoids a reflection error with the POJOs
    opens org.example.control.model to org.hibernate.orm.core;

}