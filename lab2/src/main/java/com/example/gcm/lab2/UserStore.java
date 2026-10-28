
package com.example.gcm.lab2;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class UserStore {

    public static ArrayList<User> loadUsers() {

        ArrayList<User> users = new ArrayList<>();

        InputStream input = UserStore.class
                .getResourceAsStream("/users.txt");

        if (input == null) {
            System.err.println("Cannot find users.txt");
            return users;
        }

        try (Scanner reader = new Scanner(input, StandardCharsets.UTF_8)) {

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                if (line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.trim().split("\\s+");

                if (parts.length != 2) {
                    System.err.println(
                            line + " Each line must contain a username and a password");
                    continue;
                }

                try {
                    User user = new User(parts[0], parts[1]);
                    users.add(user);

                } catch (IllegalArgumentException e) {
                    System.err.println(line + " " + e.getMessage());
                }
            }
        }

        return users;
    }
}

