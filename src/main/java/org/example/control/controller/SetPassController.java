package org.example.control.controller;

import com.google.common.hash.Hashing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.example.control.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class SetPassController {

    private static final Logger log = LoggerFactory.getLogger(SetPassController.class);
    @FXML
    private PasswordField passField;
    @FXML
    private PasswordField repPassField;

    private static final String FAIL_PASSWD_TITLE = "Contraseña Incorrecta";

    @FXML
    protected void cancel(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.hide();
    }

    @FXML
    protected void accept() {
        if (repPassField == null || repPassField.getText().isEmpty()) return;
        if (passField == null || passField.getText().isEmpty()) return;

        if (passField.getText().trim().isEmpty() || passField.getText().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, FAIL_PASSWD_TITLE,
                    "Contraseña con espacios",
                    "Asegura que la contraseña no contenga espacios ni este vacía.");
            return;
        }

        if (!repPassField.getText().equals(passField.getText())) {
            showAlert(Alert.AlertType.WARNING, FAIL_PASSWD_TITLE,
                    "Las contraseñas no coinciden",
                    "Confirme que las contraseñas son iguales");
            return;
        }

        String newPassword = encode(passField.getText());
        if (save(newPassword)) {
            log.info("NEW PASSWORD SAVED");
        } else {
            log.error("NEW PASSWORD CAN NOT BEEN SAVED");
        }

    }

    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Boolean save(String newPassword) {
        Properties props = new Properties();
        String file = "application.properties";
        boolean flag;

        try (InputStream input = new FileInputStream(file)) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (OutputStream output = new FileOutputStream(file)) {
            flag = props.replace("SU_PASSWD", newPassword) != null;    // Change password
            props.store(output, null);                        // Save file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return flag;
    }

    private String encode(String pass) {
        return Hashing.sha256().hashString(pass.trim(), StandardCharsets.UTF_8).toString();
    }

    /**
     * Method to show the new password
     * window and set it in the properties file.
     */
    protected static void show() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("set-pass-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());

            stage.setTitle("Cambiar contraseña");
            stage.setResizable(false);
            stage.setScene(scene);

            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
