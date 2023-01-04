package Lab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();

        String input = scanner.nextLine();
        while (!input.equals("Exit")) {
            String result = studentSystem.parseCommand(input.split("\\s+"));
            if (result != null) {
                System.out.println(result);
            }
            input = scanner.nextLine();
        }
    }
}
