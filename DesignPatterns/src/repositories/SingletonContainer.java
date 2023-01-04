package repositories;

import core.Engine;
import repositories.PopulationTracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Collectors;

public class SingletonContainer {
    private static PopulationTracker tracker;
    private static DataStore dataStore;
    private static Engine engine;
    private static CommandRepository repository;
    private static boolean isInitialized;

    private SingletonContainer() {
        isInitialized = false;
    }

    public static void init() {
        if (!isInitialized) {
            isInitialized = true;
            tracker = PopulationTracker.getInstance();
            dataStore = DataStore.getInstance();
            repository = CommandRepository.getInstance();
            engine = Engine.getInstance(
                    new BufferedReader(new InputStreamReader(System.in)),
                    new BufferedWriter(new OutputStreamWriter(System.out))
            );
        }
    }

    public static DataStore getDataStore() {
        validateInitialization();
        return dataStore;
    }

    public static CommandRepository getCommandRepository() {
        validateInitialization();
        return repository;
    }

    public static Engine getEngine() {
        validateInitialization();
        return engine;
    }

    private static void validateInitialization() {
        if (!isInitialized) {
            throw new NullPointerException(
                    "repositories.SingletonContainer not initialized call. init() first."
            );
        }
    }

    public static PopulationTracker getTracker() {
        validateInitialization();
        return tracker;
    }

    public static String info() {
        return tracker.getCounts().entrySet()
                .stream()
                .map(e -> String.format("%s -> %d", e.getKey(), e.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
