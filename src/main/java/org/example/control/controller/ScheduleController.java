package org.example.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.control.Main;
import org.example.control.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;

public class ScheduleController {

    private static final Logger log = LoggerFactory.getLogger(ScheduleController.class);

    @FXML
    private Button entryButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label employeeLabel;

    private Employee employee;

    @FXML
    public void initialize() {
    }

    @FXML
    protected void entry() {
        log.info("ENTRY DATE {}", this.employee.getEntryDate());
        log.info("ENTRY DATE OF {} AT {}", this.employee.getDni(), LocalDateTime.now());
    }

    @FXML
    protected void exit() {
        log.info("EXIT DATE {}", this.employee.getExitDate());
        log.info("EXIT DATE OF {} AT {}", this.employee.getDni(), LocalDateTime.now());
    }

    @FXML
    protected void showEmployeeData() {
        PasswordController.show();
        if (PasswordController.valid()) return;
        log.debug("SHOW EMPLOYEE ({} {}) DATA", employee.getName(), employee.getSurname());
    }

    @FXML
    protected void editEmployeesData(ActionEvent event) {

        // Ask for admin password
        PasswordController.show();
        if (PasswordController.valid()) return;

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

    protected void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee != null) employeeLabel.setText(employee.getName() + " " + employee.getSurname());
        else log.info("NO EMPLOYEE");

    }

}
