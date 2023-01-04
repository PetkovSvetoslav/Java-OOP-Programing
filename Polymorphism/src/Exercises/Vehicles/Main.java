package Exercises.Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static Exercises.Vehicles.Car.parseCar;
import static Exercises.Vehicles.Truck.parseTruck;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Vehicle> vehiclesByType = new HashMap<>();

        String[] carData = reader.readLine().split("\\s+");
        Car car = parseCar(carData);
        vehiclesByType.put("Car", car);

        String[] truckData = reader.readLine().split("\\s+");
        Truck truck = parseTruck(truckData);
        vehiclesByType.put("Truck", truck);

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split("\\s+");
            String command = tokens[0];
            String vehicleType = tokens[1];

            switch (command) {
                case "Drive":
                    double distance = Double.parseDouble(tokens[2]);
                    System.out.println(vehiclesByType.get(vehicleType).drive(distance));
                    break;
                case "Refuel":
                    double liters = Double.parseDouble(tokens[2]);
                    vehiclesByType.get(vehicleType).refuel(liters);
                    break;
            }
        }

        vehiclesByType.values().forEach(System.out::println);
    }
}
