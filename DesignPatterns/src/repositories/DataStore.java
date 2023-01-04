package repositories;

import entities.Address;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataStore {

    public static class EntityExistException extends RuntimeException {
    }

    private Map<Long, Address> addresses;
    private static DataStore instance;

    private DataStore() {
        this.addresses = new LinkedHashMap<>();
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public void crateAddress(long userId, Address address) {
        if (this.addresses.containsKey(userId)) {
            throw new EntityExistException();
        }
        this.addresses.put(userId, address);
    }

    public void overwriteAddress(long userId, Address address) {
        if (this.addresses.containsKey(userId)) {
            this.addresses.put(userId, address);
        } else {
            throw new IllegalArgumentException("Non-existent ID: " + userId);
        }
    }

    public void removeAddress(long id) {
        this.addresses.remove(id);
    }

    public Address getUserAddress(long id) {
        return this.addresses.get(id);
    }
}
