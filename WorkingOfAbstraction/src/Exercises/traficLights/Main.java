package Exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] trafficLights = reader.readLine().split("\\s+");
        int updatesCount = Integer.parseInt(reader.readLine());

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < updatesCount; i++) {
            for (int j = 0; j < trafficLights.length; j++) {
                TrafficLights light = TrafficLights.valueOf(trafficLights[j]);
                trafficLights[j] = light.getNextLight();

                output.append(trafficLights[j]);
                if (j != trafficLights.length - 1) {
                    output.append(" ");
                }
            }
            output.append(System.lineSeparator());
        }
        System.out.println(output);
    }
}
