package commands;

import repositories.DataStore;
import repositories.SingletonContainer;

public abstract class BaseCommand implements Command {
    protected static DataStore dataStore;

    public BaseCommand() {
        dataStore = SingletonContainer.getDataStore();
    }
}
