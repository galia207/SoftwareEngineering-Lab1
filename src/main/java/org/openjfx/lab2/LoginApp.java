package org.openjfx.lab2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.lab2.controller.LoginController;
import org.openjfx.lab2.model.UserRepository;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Entry point of the GCM login application (Lab 2).
 *
 * Flow:
 *  1. Read the input file and build the ArrayList of valid users.
 *  2. Open the login screen (login.fxml) in the main window (Stage).
 *  3. The LoginController checks the details; on success it replaces the
 *     scene with the welcome screen (welcome.fxml), otherwise it shows an
 *     error message inside the login screen.
 *  4. Clicking the X of the window closes it and ends the program.
 */
public class LoginApp extends Application {

    /** The valid users, loaded once when the program starts. */
    private UserRepository repository;

    @Override
    public void init() {
        // init() runs before start(), so the users are ready before any window opens.
        try {
            repository = UserRepository.load(UserRepository.DEFAULT_FILE);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            Platform.exit();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(LoginApp.class.getResource("login.fxml"));
        Parent root = loader.load();

        // Give the controller access to the users list.
        LoginController controller = loader.getController();
        controller.setRepository(repository);

        stage.setTitle("Users Login");
        stage.setScene(new Scene(root));

        // Both screens live in this one Stage, so this handler covers
        // the X button of the login window and of the welcome window.
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
