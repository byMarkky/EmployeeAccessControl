package org.example.control.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.example.control.Main;

import java.io.IOException;

public class PasswordController {

    @FXML
    private PasswordField passField;

    private static String passwd = "";

    @FXML
    protected void check(ActionEvent event) {
        passwd = passField.getText();

        if (validate()) {
            PasswordField field = ((PasswordField) event.getSource());
            field.getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Contraseña incorrecta, intentelo de nuevo", ButtonType.OK);
            alert.setTitle("Contraseña");
            alert.showAndWait();
        }

    }

    private boolean validate() {
        return passwd.length() >= 10;
    }

    @FXML
    protected void cancel(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.hide();
        passwd = "";
    }

    protected static void show() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("password-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());


            stage.setTitle("Valida contraseña");
            stage.setResizable(false);
            stage.setScene(scene);

            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to validate is the password is correct
     * before display de protected window
     * @return True if is correct, False if not.
     */
    protected static boolean valid() {
        boolean state = passwd.length() >= 10;
        passwd = "";
        return !state;
    }

}
