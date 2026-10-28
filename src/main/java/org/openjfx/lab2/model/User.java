package org.openjfx.lab2.model;

public class User {

    private final String username;
    private final String password;

    public User(String username, String password) {

        if (username == null) {
            throw new IllegalArgumentException(
                    "Please enter a valid Email as username");
        }

        if (username.length() > 50) {
            throw new IllegalArgumentException(
                    "Username is too long, try something shorter");
        }

        String emailPattern =
                "[A-Za-z0-9._%+-]+@[A-Za-z0-9][A-Za-z0-9.-]*\\.[A-Za-z]{2,}";

        if (!username.matches(emailPattern)) {
            throw new IllegalArgumentException(
                    "Please enter a valid Email as username");
        }

        if (password == null) {
            throw new IllegalArgumentException(
                    "Please enter a valid password");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException(
                    "Your password is too short, add more characters");
        }

        if (password.length() > 12) {
            throw new IllegalArgumentException(
                    "Your password is too long, try a shorter one");
        }

        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if ((c >= 'a' && c <= 'z') ||
                    (c >= 'A' && c <= 'Z')) {

                hasLetter = true;

            } else if (c >= '0' && c <= '9') {

                hasDigit = true;

            } else if ((c >= '!' && c <= '+') ||
                    c == '@' || c == '^') {
                hasSymbol = true;
            } else {
                throw new IllegalArgumentException(
                        "Please enter a valid password");
            }
        }

        if (!hasLetter || !hasDigit || !hasSymbol) {
            throw new IllegalArgumentException(
                    "Please enter a valid password");
        }

        this.username = username;
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return username + " " + password;
    }
}
