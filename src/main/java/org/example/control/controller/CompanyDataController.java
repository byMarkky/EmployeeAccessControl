package org.example.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    public void initialize() {
        // Aqui usaremos las clases de servicio
        // para coger los datos de la empresa
        // y ponerlos en los campos
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
