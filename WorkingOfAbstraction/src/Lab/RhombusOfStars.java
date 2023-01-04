package Lab;

import java.util.Scanner;

public class RhombusOfStars {
    private static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        System.out.println(getTriangleOfStars(n, "up"));
        System.out.println(getTriangleOfStars(n, "down"));
    }

    /**
     * @param size      length of the sides of the triangle
     * @param direction "up" or "down"
     * @return triangle from "* " as String
     */
    private static String getTriangleOfStars(int size, String direction) {
        StringBuilder out = new StringBuilder();

        for (int r = 1; r <= size; r++) {
            int spacesCount = direction.equals("up") ? size - r : r;
            int starsCount = direction.equals("up") ? r : size - r;
            out.append(repeatString(spacesCount, " "));
            out.append(repeatString(starsCount, "* "));
            if (r < size) {
                out.append(System.lineSeparator());
            }
        }
        return out.toString();
    }

    private static String repeatString(int count, String toRepeat) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < count; i++) {
            out.append(toRepeat);
        }
        return out.toString();
    }
}
