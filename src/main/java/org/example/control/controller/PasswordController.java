package org.example.control.controller;

import com.google.common.hash.Hashing;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Class responsible for the authorization
 * logic to access a section that requires
 * the administrator password.
 * @author byMarky
 */
public class PasswordController {

    private static final Logger log = LoggerFactory.getLogger(PasswordController.class);

    @FXML
    private PasswordField passField;

    private static String passwd = "";
    private static final byte PASSWD_MAX_LENGTH = 5;

    @FXML
    protected void check(ActionEvent event) {
        passwd = passField.getText();

        if (validate()) {
            PasswordField field = ((PasswordField) event.getSource());
            field.getScene().getWindow().hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Contraseña incorrecta, inténtelo de nuevo", ButtonType.OK);
            alert.setTitle("Contraseña");
            alert.showAndWait();
        }
    }

    @FXML
    protected void cancel(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.hide();
        passwd = "";
    }

    private boolean validate() {
        return passwd.length() >= PASSWD_MAX_LENGTH && hashString(passwd).equals(getPasswordProperty());
    }

    /**
     * Method to show the password
     * window and grant access to the target window.
     * After call this method, you have to call the method
     * valid() to check if the password is correct.
     */
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
     * Method to get the encoded password from the application.properties
     * @return Encoded password
     */
    private static String getPasswordProperty() {
        String passwd = null;
        try (InputStream input = new FileInputStream("application.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            passwd = prop.getProperty("SU_PASSWD");

        } catch (IOException ex) {
            log.error("ERROR WHILE GETTING PASSWORD FROM application.properties. StackTrace: {}", (Object) ex.getStackTrace());
        }

        return passwd;
    }

    /**
     * This method use Google Guava library to encode
     * a string using SHA-256
     * @param string String to encode
     * @return input string encoded to SHA-256
     */
    private static String hashString(String string) {
        return Hashing.sha256().hashString(string.trim(), StandardCharsets.UTF_8).toString();
    }

    /**
     * Method to validate is the password is correct
     * before display de protected window
     * @return True if is correct, False if not.
     */
    protected static boolean isNotValid() {
        String coded = hashString(passwd);
        boolean state = passwd.length() >= PASSWD_MAX_LENGTH && coded.equals(getPasswordProperty());

        // This prevents to access the target
        // window if the password window is closed
        passwd = "";
        return !state;
    }

}
