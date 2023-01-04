package Lab.BorderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static Lab.BorderControl.Citizen.parseCitizen;
import static Lab.BorderControl.Robot.parseRobot;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Identifiable> inhabitants = new ArrayList<>();

        String input;
        while (!"End".equals(input = reader.readLine())) {
            String[] data = input.split("\\s+");

            switch (data.length) {
                case 2:
                    inhabitants.add(parseRobot(data));
                    break;
                case 3:
                    inhabitants.add(parseCitizen(data));
                    break;
            }
        }
        String lastDigits = reader.readLine();

        for (Identifiable inhabitant : inhabitants) {
            if (inhabitant.getId().endsWith(lastDigits)) {
                System.out.println(inhabitant.getId());
            }
        }
    }
}
