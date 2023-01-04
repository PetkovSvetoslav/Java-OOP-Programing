package Exercises.barracksWars.core.commands;

import Exercises.barracksWars.interfaces.Inject;
import Exercises.barracksWars.interfaces.Repository;

public class RetireCommand extends Command {
    @Inject
    Repository repository;

    public RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        try {
            String unitType = super.getData()[1];
            this.repository.removeUnit(unitType);
            return unitType + " retired!";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}
