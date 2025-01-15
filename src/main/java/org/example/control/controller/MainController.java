package org.example.control.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.control.Main;
import org.example.control.model.Employee;
import org.example.control.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    @FXML
    private Label mainTitle;

    @FXML
    private TilePane empTilePane;

    private static final EmployeeService empService = new EmployeeService();

    @FXML
    public void initialize() {
        if (empService.haveEmployees()) {
            mainTitle.setText("Seleccione un empleado/a");
            loadEmployees();
        } else {
            mainTitle.setText("No hay empleados, añade empleados al sistema");
        }
    }

    private void loadEmployees() {
        List<Employee> employees = empService.getAll();
        for (Employee emp : employees) {

            // Name + Surname truncated
            String name = emp.getName();
            String surname = emp.getSurname().substring(0, 3) + ".";

            Button button = new Button(name + " " + surname);
            button.setFont(new Font("System Bold", 13));
            button.setPadding(new Insets(20, 20, 20, 20));

            // Using this approach is not necessary create a new ActionEvent
            button.setOnAction(event -> selectEmployee(event, emp));

            empTilePane.getChildren().add(button);
        }
    }

    @FXML
    protected void setNewPassword() {
        PasswordController.show();
        if (PasswordController.isNotValid()) return;

        SetPassController.show();
    }

    @FXML
    protected void selectEmployee(ActionEvent event, Employee employee) {
        try {
            Button button = (Button) event.getSource();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("schedule-option-view.fxml"));
            Scene scene = new Scene(loader.load());

            ScheduleController controller = loader.getController();
            controller.setEmployee(employee);

            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void close() { Platform.exit(); }

    @FXML
    protected void editCompanyData() {
        PasswordController.show();
        if (PasswordController.isNotValid()) return;
        startEditWindow("company-data-view.fxml", "Datos de la empresa");
    }

    @FXML
    protected void editEmployeesData() {
        PasswordController.show();
        if (PasswordController.isNotValid()) return;
        startEditWindow("employee-data-view.fxml", "Datos de empleado");
    }

    private void startEditWindow(String fxml, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());

            stage.setTitle(title);
            stage.setResizable(false);
            stage.setScene(scene);

            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}