package Exercises.FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private final List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String playerName) {
        if (!this.players.removeIf(p -> p.getName().equals(playerName))) {
            throw new IllegalArgumentException
                    ("Player " + playerName + " is not in " + this.name + " team.");
        }
    }

    public double getRating() {
        return this.players.stream()
                .mapToDouble(Player::overallSkillLevel)
                .average()
                .orElse(0.00);
    }

    @Override
    public String toString() {
        return String.format("%s - %d", this.name, Math.round(this.getRating()));
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
    }
}
