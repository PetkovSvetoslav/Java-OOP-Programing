package commands;

import entities.Address;

public class UpdateAddress extends BaseCommand {

    @Override
    public String execute(String data) {
        String[] personData = data.split(", ");
        long id = Long.parseLong(personData[0]);

        dataStore.getUserAddress(id);

        Address newAddress = Address
                .Builder.newBuilder(personData[1], personData[2])
                .withEmail(personData[3])
                .build();

        dataStore.overwriteAddress(id, newAddress);

        return "Rewritten address: " + id + " -> " + newAddress.toString();
    }
}
