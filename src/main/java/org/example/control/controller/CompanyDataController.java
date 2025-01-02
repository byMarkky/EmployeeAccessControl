package org.example.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.control.model.Company;
import org.example.control.service.CompanyService;

public class CompanyDataController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cccLabel;

    @FXML
    private TextField cifLabel;

    @FXML
    private Button cleanButton;

    @FXML
    private TextField centerLabel;

    @FXML
    private TextField nameLabel;

    @FXML
    private Button saveButton;

    @FXML
    private Label titleLabel;

    private static final String emptyAlertTitle = "Campo vacío";
    private static final CompanyService companyService = new CompanyService();

    @FXML
    public void initialize() throws InterruptedException {
        // Aqui usaremos las clases de servicio
        // para coger los datos de la empresa
        // y ponerlos en los campos
        loadData();
    }

    private void loadData() {
        Company company = companyService.getFirst();
        if (company != null) {
            cifLabel.setText(company.getCif());
            centerLabel.setText(company.getWorkCenter());
            nameLabel.setText(company.getName());
            cccLabel.setText(company.getcAccount());
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type, content, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    protected void saveData(ActionEvent event) {
        Company company;
        if (cifLabel.getText().trim().isEmpty()) {
            showAlert(emptyAlertTitle, "El campo CIF esta vacío.", Alert.AlertType.WARNING);
            return;
        }
        if (nameLabel.getText().isEmpty()) {
            showAlert(emptyAlertTitle, "El campo del nombre esta vacío.", Alert.AlertType.WARNING);
            return;
        }
        if (cccLabel.getText().trim().isEmpty()) {
            showAlert(emptyAlertTitle, "El código de cuenta de cotización esta vacío.", Alert.AlertType.WARNING);
            return;
        }
        if (centerLabel.getText().isEmpty()) {
            showAlert(emptyAlertTitle, "El centro de trabajo esta vacío.", Alert.AlertType.WARNING);
            return;
        }

        company = new Company(cifLabel.getText().trim(), nameLabel.getText(),
                cccLabel.getText().trim(), centerLabel.getText());

        if (companyService.validate(company)) {
            companyService.createCompany(company);
            showAlert("Datos guardados", "Se han guardado los datos exitosamente", Alert.AlertType.CONFIRMATION);
            System.out.println("Company Account Saved: " + company);
        } else {
            showAlert("Error en los datos", "Revise si el CIF/NIF ó el C.C.C. son correctos.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    protected void cleanData(ActionEvent event) {
        cifLabel.setText("");
        nameLabel.setText("");
        cccLabel.setText("");
        centerLabel.setText("");
    }

    @FXML
    protected void cancel(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.hide();
    }

}
