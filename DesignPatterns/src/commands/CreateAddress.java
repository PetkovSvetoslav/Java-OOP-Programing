package commands;

import entities.Address;

public class CreateAddress extends BaseCommand {
    private static long id;

    @Override
    public String execute(String data) {
        String[] personData = data.split(", ");

        Address address = Address
                .Builder.newBuilder(personData[0], personData[1])
                .withEmail(personData[2])
                .build();

        dataStore.crateAddress(++id, address);

        return "Created address: " + id + " -> " + address.toString();
    }
}
