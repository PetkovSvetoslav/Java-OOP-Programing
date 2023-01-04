package commands;

import entities.Address;

public class RemoveAddress extends BaseCommand {
    @Override
    public String execute(String data) {
        long id = Long.parseLong(data);

        Address removedAddress = dataStore.getUserAddress(id);

        dataStore.removeAddress(id);

        return "Removed address: " + id + removedAddress.toString();
    }
}
