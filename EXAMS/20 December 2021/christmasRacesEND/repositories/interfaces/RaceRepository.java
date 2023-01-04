package christmasRaces.repositories.interfaces;

import christmasRaces.entities.races.Race;

import java.util.*;

public class RaceRepository implements Repository<Race> {
    private Map<String, Race> races;

    public RaceRepository() {
        this.races = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        return races.get(name);
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(races.values());
    }

    @Override
    public void add(Race model) {
        races.put(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        if (races.remove(model.getName()) == null) {
            return true;
        }
        return false;
    }
}