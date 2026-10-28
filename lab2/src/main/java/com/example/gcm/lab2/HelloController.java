
package com.example.gcm.lab2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    // Load valid users from users.txt
    private final ArrayList<User> users = UserStore.loadUsers();

    @FXML
    private void handleLogin() {

        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        // Check for empty fields
        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Please enter your email and password.", Color.RED);
            return;
        }

        // Search for matching user credentials
        for (User user : users) {

            if (user.getName().equals(username)
                    && user.getPassword().equals(password)) {

                openWelcomeScreen();
                return;
            }
        }

        // Invalid login
        showMessage("Invalid username or password.", Color.RED);
    }

    // Switch from Login to Welcome screen
    private void openWelcomeScreen() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/gcm/lab2/welcome-view.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) usernameField
                    .getScene().getWindow();

            Scene scene = new Scene(root, 520, 460);

            stage.setScene(scene);
            stage.setTitle("GCM - Welcome");
            stage.show();

        } catch (IOException e) {
            showMessage("Unable to open Welcome screen.", Color.RED);
            e.printStackTrace();
        }
    }

    // Display error messages on the login screen
    private void showMessage(String message, Color color) {
        errorLabel.setText(message);
        errorLabel.setTextFill(color);
        errorLabel.setVisible(true);
    }
}
