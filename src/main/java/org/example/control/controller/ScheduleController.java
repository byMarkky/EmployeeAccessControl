package org.example.control.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.control.Main;
import org.example.control.model.Employee;
import org.example.control.model.Register;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ScheduleController {

    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);

    @FXML
    private Label employeeLabel;

    private Employee employee;

    @FXML
    public void initialize() {
    }

    @FXML
    protected void entry() {
        Register register = new Register();
        register.setEntry(LocalDateTime.of(LocalDate.now(), employee.getEntryDate()));
        log.info(register.toString());
    }

    @FXML
    protected void exit() {
        log.info("EMPLOYEE {} EXITED AT {}", employee.getDni(), LocalDateTime.now().toLocalTime());
    }

    @FXML
    protected void close() {
        Platform.exit();
    }

    @FXML
    protected void showEmployeeData() {
        PasswordController.show();
        if (PasswordController.isNotValid()) return;
        log.debug("SHOW EMPLOYEE ({} {}) DATA", employee.getName(), employee.getSurname());
    }

    @FXML
    protected void editEmployeesData() {

        // Ask for admin password
        PasswordController.show();
        if (PasswordController.isNotValid()) return;

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("employee-data-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());

            EmployeeDataController controller = loader.getController();
            controller.setEmployee(employee);

            stage.setTitle("Datos de " + employee.getName());
            stage.setResizable(false);
            stage.setScene(scene);

            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to load an employee in this class
     * so can be used.
     * @param employee Employee selected from the database
     */
    protected void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee != null) employeeLabel.setText(employee.getName() + " " + employee.getSurname());
        else log.info("NO EMPLOYEE");

    }

}
