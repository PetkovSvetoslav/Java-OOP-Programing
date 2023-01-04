package Lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Team team = new Team("Eagles");

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] playerData = reader.readLine().split("\\s+");
            String firstName = playerData[0];
            String lastName = playerData[1];
            int age = Integer.parseInt(playerData[2]);
            double salary = Double.parseDouble(playerData[3]);

            Person person = new Person(firstName, lastName, age, salary);
            team.addPlayer(person);
        }

        System.out.println("First team have " + team.getFirstTeam().size() + " players");
        System.out.println("Reserve team have " + team.getReserveTeam().size() + " players");
    }
}
