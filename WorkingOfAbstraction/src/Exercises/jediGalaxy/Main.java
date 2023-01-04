package Exercises.jediGalaxy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        Galaxy galaxy = new Galaxy(Array.generateMatrix(rows, cols));

        long starPowerCollected = 0;
        String positions = scanner.nextLine();
        while (!positions.equals("Let the Force be with you")) {

            int[] jediCoordinates = Array.parseArray(positions, "\\s+");

            int jediRow = jediCoordinates[0];
            int jediCol = jediCoordinates[1];

            int[] sithCoordinates = Array.parseArray(scanner.nextLine(), "\\s+");

            int sithRow = sithCoordinates[0];
            int sithCol = sithCoordinates[1];

            galaxy.moveSith(sithRow, sithCol);

            starPowerCollected += galaxy.moveJedi(jediRow, jediCol);

            positions = scanner.nextLine();
        }
        System.out.println(starPowerCollected);
    }
}
