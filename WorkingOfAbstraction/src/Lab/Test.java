package Lab;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DayOfWeek day = DayOfWeek.valueOf(scanner.nextLine());

        for (DayOfWeek value : DayOfWeek.values()) {
            System.out.println(value.ordinal());
        }
    }
}
