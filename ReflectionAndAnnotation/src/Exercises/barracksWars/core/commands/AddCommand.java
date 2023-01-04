package Exercises.barracksWars.core.commands;

import Exercises.barracksWars.interfaces.Inject;
import Exercises.barracksWars.interfaces.Repository;
import Exercises.barracksWars.interfaces.Unit;
import Exercises.barracksWars.interfaces.UnitFactory;

public class AddCommand extends Command {
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = super.getData()[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        return unitType + " added!";
    }
}
