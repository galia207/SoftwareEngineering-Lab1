package org.openjfx.lab2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.openjfx.lab2.LoginApp;
import org.openjfx.lab2.model.User;
import org.openjfx.lab2.model.UserRepository;

import java.io.IOException;

/**
 * Controller of the login screen (login.fxml).
 *
 * Fields marked with @FXML are injected by the FXMLLoader according to the
 * fx:id attributes in the FXML file. {@link #onLoginButtonClick(ActionEvent)}
 * is the event handler (listener) of the login button. The button is the
 * default button, so pressing Enter in the window triggers it too.
 */
public class LoginController {

    @FXML
    private AnchorPane loginScreen;

    /** Error message shown inside the window (not a pop-up). Hidden at start. */
    @FXML
    private Label notext;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    /** The valid users read from the input file. */
    private UserRepository repository;

    /** Called by LoginApp after the FXML is loaded. */
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Called automatically by the FXMLLoader after all @FXML fields are injected.
     * Clears the error message as soon as the user starts editing again.
     */
    @FXML
    private void initialize() {
        notext.setText("");
        username.textProperty().addListener((obs, oldValue, newValue) -> notext.setText(""));
        password.textProperty().addListener((obs, oldValue, newValue) -> notext.setText(""));
    }

    /**
     * Handles a click on the login button.
     * If a user with these details exists, the welcome screen is loaded into
     * the same window. Otherwise an error message is shown in the login screen:
     * the matching message from the error table if the username or password is
     * invalid, or "user or password do not match" if no such user exists.
     */
    @FXML
    void onLoginButtonClick(ActionEvent event) {
        if (repository == null) {
            notext.setText(UserRepository.NO_MATCH_MESSAGE);
            return;
        }

        String error = repository.checkLogin(username.getText(), password.getText());

        if (error != null) {
            // Clear the password first: clearing it fires the text listener,
            // which would otherwise erase the error message right after it is shown.
            password.clear();
            notext.setText(error);
            return;
        }

        User user = repository.authenticate(username.getText(), password.getText());

        try {
            openWelcomeScreen(user);
        } catch (IOException e) {
            notext.setText("Could not open the welcome screen");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads welcome.fxml and replaces the current scene of the window with it.
     */
    private void openWelcomeScreen(User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginApp.class.getResource("welcome.fxml"));
        Parent root = loader.load();

        WelcomeController controller = loader.getController();
        controller.setUser(user);

        Stage stage = (Stage) loginScreen.getScene().getWindow();
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root));
    }
}
