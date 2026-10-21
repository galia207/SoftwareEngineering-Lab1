
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UsersApp {

    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();

        try (Scanner reader =
                     new Scanner(new File("users.txt"))) {

            while (reader.hasNextLine()) {

                String line = reader.nextLine();
                String[] parts = line.trim().split("\\s+");

                if (parts.length != 2) {
                    System.err.println(line +
                            " Each line must contain a username and a password");
                    continue;
                }

                try {
                    User user = new User(parts[0], parts[1]);
                    users.add(user);

                } catch (IllegalArgumentException e) {
                    System.err.println(
                            line + " " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println(
                    "Cannot find users.txt in the project folder.");
            return;
        }

        Collections.sort(users, (u1, u2) ->
                u1.getName().compareTo(u2.getName()));

        for (User user : users) {
            System.out.println(user);
        }
    }
}
