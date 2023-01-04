package Exercises.FootballTeamGenerator;

public class Player {
    private String name;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;

    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
    }

    public static Player parsePlayer(String[] tokens) {
        String playerName = tokens[2];
        int endurance = Integer.parseInt(tokens[3]);
        int sprint = Integer.parseInt(tokens[4]);
        int dribble = Integer.parseInt(tokens[5]);
        int passing = Integer.parseInt(tokens[6]);
        int shooting = Integer.parseInt(tokens[7]);

        return new Player(playerName, endurance, sprint, dribble, passing, shooting);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
    }

    private void setEndurance(int endurance) {
        validateStat(endurance, "Endurance");
        this.endurance = endurance;
    }

    private void setSprint(int sprint) {
        validateStat(sprint, "Sprint");
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        validateStat(dribble, "Dribble");
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        validateStat(passing, "Passing");
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        validateStat(shooting, "Shooting");
        this.shooting = shooting;
    }

    public double overallSkillLevel() {
        return (double) (this.endurance
                + this.sprint
                + this.dribble
                + this.passing
                + this.shooting) / 5;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
    }

    private void validateStat(int stat, String statusName) {
        if (0 > stat || stat > 100) {
            throw new IllegalArgumentException(statusName + " should be between 0 and 100.");
        }
    }
}
