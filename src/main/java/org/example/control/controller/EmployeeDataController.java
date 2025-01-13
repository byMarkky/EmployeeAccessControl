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
    private TextField dniLabel;

    @FXML
    private TextField eveningLabel;

    @FXML
    private TextField morningLabel;

    @FXML
    private TextField nameLabel;

    @FXML
    private TextField surnameLabel;

    private Employee employee = null;

    private static final EmployeeService empService = new EmployeeService();

    @FXML
    public void initialize() {
    }

    private void loadData() {
        dniLabel.setText(employee.getDni());
        nameLabel.setText(employee.getName());
        surnameLabel.setText(employee.getSurname());
        affiliateLabel.setText(employee.getAffiliateNumber());
        morningLabel.setText(employee.getSchedule());
        eveningLabel.setText(employee.getAfternoonShift());
    }

    protected void setEmployee(Employee employee) {
        this.employee = employee;
        loadData(); // Load the employee data in the labels
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    protected void saveData() {

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

        if (this.employee == null) {
            this.employee = new Employee(dniLabel.getText().trim(), nameLabel.getText().trim(),
                    surnameLabel.getText().trim(), affiliateLabel.getText().trim(),
                    morningLabel.getText().trim(), eveningLabel.getText().trim());
        } else {
            this.employee.setDni(dniLabel.getText());
            this.employee.setName(nameLabel.getText());
            this.employee.setAffiliateNumber(affiliateLabel.getText());
            this.employee.setSchedule(morningLabel.getText());
            this.employee.setAfternoonShift(eveningLabel.getText());
        }

        if (empService.validate(this.employee)) {
            empService.createEmployee(this.employee);
            showAlert("Datos correctos",
                    "Empleado guardado correctamente.\nSi no aparece en la pantalla principal, cierre y abra de nuevo el programa",
                    Alert.AlertType.CONFIRMATION);
            System.out.println(this.employee);
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
