package Exercises.MilitaryElite.entities;

import Exercises.MilitaryElite.interfaces.LieutenantGeneral;
import Exercises.MilitaryElite.interfaces.Private;

import java.util.*;
import java.util.stream.Collectors;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {
    private final List<Private> privates;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new ArrayList<>();
    }

    public static LieutenantGeneralImpl createLieutenant(String[] data, Map<Integer, Private> privatesById) {

        int id = Integer.parseInt(data[1]);
        String firstName = data[2];
        String lastName = data[3];
        double salary = Double.parseDouble(data[4]);

        LieutenantGeneralImpl lieutenantGeneral = new LieutenantGeneralImpl(id, firstName, lastName, salary);

        for (int i = 5; i < data.length; i++) {
            int privateId = Integer.parseInt(data[i]);
            lieutenantGeneral.addPrivate(privatesById.get(privateId));
        }

        return lieutenantGeneral;
    }

    @Override
    public void addPrivate(Private priv) {
        this.privates.add(priv);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(super.toString())
                .append(System.lineSeparator())
                .append("Privates:");

        List<Private> sortedPrivates =
                this.privates
                        .stream()
                        .sorted((p1, p2) -> Integer.compare(p2.getId(), p1.getId()))
                        .collect(Collectors.toList());

        for (Private priv : sortedPrivates) {
            out.append(System.lineSeparator())
                    .append("  ").append(priv);
        }

        return out.toString();
    }
}
