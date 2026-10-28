package org.openjfx.lab2;

/**
 * Plain launcher class.
 *
 * Running a class that does not extend Application lets the program start
 * from IntelliJ (Run 'Launcher.main()') even when the JavaFX runtime is
 * on the classpath instead of the module path.
 */
public class Launcher {

    public static void main(String[] args) {
        LoginApp.main(args);
    }
}
