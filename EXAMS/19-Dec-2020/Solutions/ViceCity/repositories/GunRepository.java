package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {
    Map<String, Gun> models;

    public GunRepository() {
        this.models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return Collections.unmodifiableCollection(this.models.values());
    }

    @Override
    public void add(Gun model) {
        this.models.putIfAbsent(model.getName(), model);
    }

    @Override
    public boolean remove(Gun model) {
        Gun removedGun = this.models.remove(model.getName());

        return removedGun != null;
    }

    @Override
    public Gun find(String name) {
        return this.models.get(name);
    }
}
