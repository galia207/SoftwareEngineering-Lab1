package org.openjfx.lab2.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Holds the list of valid users of the system.
 *
 * The repository reads the input file line by line (the same logic as
 * UsersApp in Lab 1). Every line that produces a valid User is added to an
 * ArrayList; invalid lines are reported on System.err and skipped.
 * The login screen then uses {@link #authenticate(String, String)} to
 * check the details the user typed.
 */
public class UserRepository {

    /** Default name of the input file. */
    public static final String DEFAULT_FILE = "users.txt";

    private final ArrayList<User> users = new ArrayList<>();

    /**
     * Loads the users from the input file.
     *
     * The file is looked for first in the working directory (the project
     * folder when running from IntelliJ or Maven). If it is not there, the
     * copy packaged in the resources folder is used.
     *
     * @param fileName name or path of the input file
     * @return a repository with all the valid users of the file
     * @throws FileNotFoundException if the file cannot be found anywhere
     */
    public static UserRepository load(String fileName) throws FileNotFoundException {
        UserRepository repository = new UserRepository();
        File file = new File(fileName);

        if (file.exists()) {
            try (Scanner reader = new Scanner(file, "UTF-8")) {
                repository.readUsers(reader);
            }
        } else {
            InputStream resource = UserRepository.class.getResourceAsStream("/" + fileName);
            if (resource == null) {
                throw new FileNotFoundException("Cannot find input file: " + fileName);
            }
            try (Scanner reader = new Scanner(resource, "UTF-8")) {
                repository.readUsers(reader);
            }
        }

        return repository;
    }

    /**
     * Reads every line of the input, creates a User from each valid line
     * and adds it to the list. At the end the list is sorted by username.
     */
    private void readUsers(Scanner reader) {
        while (reader.hasNextLine()) {
            String line = reader.nextLine();

            // Remove the byte-order mark that Windows editors sometimes add.
            if (line.startsWith("﻿")) {
                line = line.substring(1);
            }

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.trim().split("\\s+");

            if (parts.length != 2) {
                System.err.println(line + " Each line must contain a username and a password");
                continue;
            }

            try {
                users.add(new User(parts[0], parts[1]));
            } catch (IllegalArgumentException e) {
                // The User constructor rejected the line - skip it.
                System.err.println(line + " " + e.getMessage());
            }
        }

        Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
    }

    /**
     * Checks whether a user with these exact details exists.
     *
     * @param username the username typed by the user
     * @param password the password typed by the user
     * @return the matching user, or null if there is no such user
     */
    public User authenticate(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        for (User user : users) {
            if (user.getName().equals(username.trim()) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /** Message shown when the details are valid but no such user exists. */
    public static final String NO_MATCH_MESSAGE = "user or password do not match";

    /**
     * Checks the details typed in the login screen and returns the message
     * to show, or null if the login succeeded.
     *
     * First the details are checked with the same rules as the input file
     * (the User constructor), so an invalid username or password gets the
     * matching message from the Lab 1 error table. Valid details that do not
     * belong to any user get "user or password do not match".
     *
     * @param username the username typed by the user
     * @param password the password typed by the user
     * @return the error message, or null if a matching user exists
     */
    public String checkLogin(String username, String password) {
        String name = (username == null) ? "" : username.trim();

        try {
            new User(name, password);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        return (authenticate(name, password) == null) ? NO_MATCH_MESSAGE : null;
    }

    /** @return a read-only view of the valid users */
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }
}
