package Exercises.barracksWars.core.commands;

import Exercises.barracksWars.interfaces.Inject;
import Exercises.barracksWars.interfaces.Repository;

public class ReportCommand extends Command {
    @Inject
    private Repository repository;

    public ReportCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }
}
