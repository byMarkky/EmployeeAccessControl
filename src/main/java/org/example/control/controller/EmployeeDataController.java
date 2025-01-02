package org.example.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.control.model.Company;
import org.example.control.model.Employee;

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

    @FXML
    public void initialize() {
        // Aquí usaremos las clases de servicio
        // para coger los datos de la empresa
        // y ponerlos en los campos
    }

    @FXML
    protected void saveData(ActionEvent event) {
        Employee employee;

        if (dniLabel.getText().trim().isEmpty() ||
            nameLabel.getText().isEmpty() ||
            surnameLabel.getText().isEmpty() ||
            affiliateLabel.getText().trim().isEmpty() ||
            eveningLabel.getText().isEmpty() ||
            morningLabel.getText().isEmpty()) {
            System.err.println("There is some data missing");
            return;
        }

        employee = new Employee();
        employee.setDni(dniLabel.getText());
        employee.setName(nameLabel.getText());
        employee.setSurname(surnameLabel.getText());
        employee.setAffiliateNumber(affiliateLabel.getText());

        System.out.println("Employee Account Created: " + employee);
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
