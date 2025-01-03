package org.example.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.control.model.Employee;
import org.example.control.service.EmployeeService;

public class EmployeeDataController {

    @FXML
    private TextField affiliateLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private Button cleanButton;

    @FXML
    private TextField dniLabel;

    @FXML
    private TextField eveningLabel;

    @FXML
    private TextField morningLabel;

    @FXML
    private TextField nameLabel;

    @FXML
    private Button saveButton;

    @FXML
    private TextField surnameLabel;

    private static final EmployeeService empService = new EmployeeService();

    @FXML
    public void initialize() {
        // Aquí usaremos las clases de servicio
        // para coger los datos de la empresa
        // y ponerlos en los campos
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    protected void saveData(ActionEvent event) {
        Employee employee;

        if (dniLabel.getText().trim().isEmpty()) {
            showAlert("Datos incorrectos", "El DNI está vacío", Alert.AlertType.WARNING);
            return;
        }

        if (nameLabel.getText().isEmpty()) {
            showAlert("Datos incorrectos", "El Nombre está vacío", Alert.AlertType.WARNING);
            return;
        }

        if (surnameLabel.getText().isEmpty()) {
            showAlert("Datos incorrectos", "El Apellido está vacío", Alert.AlertType.WARNING);
            return;
        }

        if (affiliateLabel.getText().trim().isEmpty()) {
            showAlert("Datos incorrectos", "El Número de Afiliado está vacío", Alert.AlertType.WARNING);
            return;
        }

        if (morningLabel.getText().isEmpty()) {
            showAlert("Datos incorrectos", "El Horario de mañana está vacío", Alert.AlertType.WARNING);
            return;
        }

        employee = new Employee(dniLabel.getText().trim(), nameLabel.getText().trim(),
                surnameLabel.getText().trim(), affiliateLabel.getText().trim(),
                morningLabel.getText().trim(), eveningLabel.getText().trim());

        if (empService.validate(employee)) {
            empService.createEmployee(employee);
            showAlert("Datos correctos",
                    "Empleado guardado correctamente.\nSi no aparece en la pantalla principal, cierre y abra de nuevo el programa",
                    Alert.AlertType.CONFIRMATION);
            System.out.println(employee);
        } else {
            showAlert("Datos incorrectos", "Los datos introducidos no son correctos, revíselos", Alert.AlertType.ERROR);
        }

    }

    @FXML
    protected void cleanData() {
        dniLabel.setText("");
        nameLabel.setText("");
        surnameLabel.setText("");
        morningLabel.setText("");
        eveningLabel.setText("");
        affiliateLabel.setText("");
    }

    @FXML
    protected void cancel(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.hide();
    }

}
