package Exercises.VehicleExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import static Exercises.VehicleExtension.Bus.parseBus;
import static Exercises.VehicleExtension.Car.parseCar;
import static Exercises.VehicleExtension.Truck.parseTruck;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Vehicle> vehiclesByType = new LinkedHashMap<>();

        for (int i = 0; i < 3; i++) {
            String[] vehicleData = reader.readLine().split("\\s+");

            String type = vehicleData[0];
            Vehicle vehicle;
            switch (type) {
                case "Car":
                    vehicle = parseCar(vehicleData);
                    break;
                case "Truck":
                    vehicle = parseTruck(vehicleData);
                    break;
                case "Bus":
                    vehicle = parseBus(vehicleData);
                    break;
                default:
                    continue;
            }

            vehiclesByType.put(type, vehicle);
        }

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String command = tokens[0];
            String vehicleType = tokens[1];

            switch (command) {
                case "Drive": {
                    double distance = Double.parseDouble(tokens[2]);
                    System.out.println(((Bus) vehiclesByType.get(vehicleType)).driveWithPassengers(distance));
                    break;
                }
                case "Refuel": {
                    double liters = Double.parseDouble(tokens[2]);
                    try {
                        vehiclesByType.get(vehicleType).refuel(liters);
                    } catch (IllegalArgumentException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                }
                case "DriveEmpty": {
                    if (vehicleType.equals("Bus")) {
                        double distance = Double.parseDouble(tokens[2]);
                        System.out.println(vehiclesByType.get(vehicleType).drive(distance));
                    }
                    break;
                }
            }
        }

        vehiclesByType.values().forEach(System.out::println);
    }
}
