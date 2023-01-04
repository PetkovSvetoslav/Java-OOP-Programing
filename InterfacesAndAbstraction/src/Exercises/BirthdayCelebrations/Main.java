package Exercises.BirthdayCelebrations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Birthable> birthables = new ArrayList<>();

        String input;
        while (!"End".equals(input = reader.readLine())) {
            String[] data = input.split("\\s+");
            String type = data[0];

            switch (type) {
                case "Citizen": {
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String id = data[3];
                    String birthdate = data[4];

                    birthables.add(new Citizen(name, age, id, birthdate));
                    break;
                }
                case "Pet": {
                    String name = data[1];
                    String birthdate = data[2];

                    birthables.add(new Pet(name, birthdate));
                    break;
                }
            }

            String year = reader.readLine();

            boolean hasOutput = false;

            for (Birthable birthable : birthables) {
                if (birthable.getBirthDate().endsWith(year)) {
                    System.out.println(year);
                    hasOutput = true;
                }
            }

            if (!hasOutput) {
                System.out.println("<no output>");
            }
        }
    }
}
