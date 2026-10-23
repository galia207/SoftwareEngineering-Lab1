
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UsersApp {

    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();
        File input = new File(args.length > 0 ? args[0] : "users.txt");

        try (Scanner reader = new Scanner(input, "UTF-8")) {

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
                    users.add(new User(parts[0], parts[1]));

                } catch (IllegalArgumentException e) {
                    System.err.println(line + " " + e.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println(
                    "Cannot find input file: " + input.getPath());
            return;
        }

        Collections.sort(users, (u1, u2) ->
                u1.getName().compareTo(u2.getName()));

        for (User user : users) {
            System.out.println(user);
        }
    }
}
