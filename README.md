# Software Engineering – Lab 1 + Lab 2 (GCM Login)

Branch `main` – Lab 1 (console validation of users).
Branch `lab2` – Lab 2: JavaFX login window built on the Lab 1 code.

## Run
* IntelliJ: open the folder as a Maven project, then run `Launcher` (or `LoginApp`).
* Command line: `mvn clean javafx:run`
* Lab 1 console program: run `org.openjfx.lab2.UsersApp`.

The input file `users.txt` is read from the project folder (a copy is also in `src/main/resources`).
Example valid login: `hello@world.net` / `01041972^H`.

## Structure
```
src/main/java/org/openjfx/lab2/
    LoginApp.java              JavaFX Application – loads users, opens login screen, X ends program
    Launcher.java              plain main() for running from the IDE
    UsersApp.java              Lab 1 console program (unchanged)
    model/User.java            Lab 1 User class (unchanged)
    model/UserRepository.java  reads the input file into an ArrayList<User> of valid users, login check
    controller/LoginController.java    handles the login button
    controller/WelcomeController.java  fills the welcome screen
src/main/resources/org/openjfx/lab2/
    login.fxml, welcome.fxml
```
