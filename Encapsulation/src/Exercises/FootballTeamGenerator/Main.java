package Exercises.FootballTeamGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Team> teams = new LinkedHashMap<>();

        String input = reader.readLine();
        while (!"END".equals(input)) {
            String[] tokens = input.split(";");

            String command = tokens[0];
            String teamName = tokens[1];

            try {
                switch (command) {
                    case "Team": {
                        Team team = new Team(teamName);
                        teams.put(teamName, team);
                        break;
                    }
                    case "Add": {
                        validateTeam(teams, teamName);
                        Player player = Player.parsePlayer(tokens);
                        teams.get(teamName).addPlayer(player);
                        break;
                    }
                    case "Remove": {
                        validateTeam(teams, teamName);
                        String playerName = tokens[2];
                        teams.get(teamName).removePlayer(playerName);
                        break;
                    }
                    case "Rating": {
                        validateTeam(teams, teamName);
                        System.out.println(teams.get(teamName));
                        break;
                    }
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
            input = reader.readLine();
        }
    }

    private static void validateTeam(Map<String, Team> teams, String teamName) {
        if (!teams.containsKey(teamName)) {
            throw new IllegalArgumentException("Team " + teamName + " does not exist.");
        }
    }
}
