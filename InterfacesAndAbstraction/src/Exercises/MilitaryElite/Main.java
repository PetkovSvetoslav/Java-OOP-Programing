package Exercises.MilitaryElite;

import Exercises.MilitaryElite.entities.*;
import Exercises.MilitaryElite.interfaces.Private;
import Exercises.MilitaryElite.interfaces.Soldier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Well I did everything on condition, but the solution do not satisfy judge

public class Main {
    private static final Map<Integer, Private> privatesById = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Soldier> soldiers = new ArrayList<>();

        String line;
        while (!"End".equals(line = reader.readLine())) {
            String[] data = line.split("\\s+");

            Soldier soldier = createSolder(data);

            if (soldier != null) {
                soldiers.add(soldier);
            }
        }

        for (Soldier soldier : soldiers) {
            System.out.println(soldier);
        }
    }

    public static Soldier createSolder(String[] data) {
        String type = data[0];

        Soldier soldier;
        switch (type) {
            case "Private": {
                soldier = PrivateImpl.createPrivate(data);
                privatesById.put(soldier.getId(), (Private) soldier);
                break;
            }
            case "LieutenantGeneral": {
                soldier = LieutenantGeneralImpl.createLieutenant(data, privatesById);
                break;
            }
            case "Engineer":
                soldier = EngineerImpl.createEngineer(data);
                break;
            case "Commando":
                soldier = CommandoImpl.createCommando(data);
                break;
            case "Spy":
                soldier = SpyImpl.createSpy(data);
                break;
            default:
                soldier = null;
                break;
        }
        return soldier;
    }
}
