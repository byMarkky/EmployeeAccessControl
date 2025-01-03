package org.example.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import org.example.control.Main;
import org.example.control.service.EmployeeService;

import java.io.IOException;

public class MainController {

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private Label mainTitle;

    private boolean haveEmployees = true;

    private static final EmployeeService empService = new EmployeeService();

    @FXML
    public void initialize() {

        loadEmployees();

        if (!haveEmployees) {
            mainTitle.setText("No hay empleados, añade empleados al sistema");
        } else {
            mainTitle.setText("Seleccione un empleado/a");
        }
    }

    private void loadEmployees() {
        haveEmployees = empService.haveEmployees();
    }

    @FXML
    protected void selectEmployee(ActionEvent event) {
        try {
            Button button = (Button) event.getSource();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("schedule-option-view.fxml"));
            Scene scene = new Scene(loader.load());

            ScheduleController controller = loader.getController();
            controller.setEmployee(button.getText());

            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void editCompanyData(ActionEvent event) {
        startEditWindow("company-data-view.fxml", "Datos de la empresa");
    }

    @FXML
    protected void editEmployeesData(ActionEvent event) {
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