import java.util.Scanner;

public class EnterNumber {
    public record Pair(int first, int second) {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                Pair pair = readRange(scanner);
                printNumbersInRange(pair);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static Pair readRange(Scanner scanner) {
        int start = Integer.parseInt(scanner.nextLine());
        int end = Integer.parseInt(scanner.nextLine());

        validateRange(start, end);

        return new Pair(start, end);
    }

    private static void printNumbersInRange(Pair pair) {
        int start = pair.first();
        int end = pair.second();

        for (int i = start; i <= end; i++) {
            System.out.print(i + " ");
        }
    }

    private static void validateRange(int start, int end) {
        if (1 >= start || start >= end || end >= 100) {
            throw new IllegalArgumentException("The range must be 1 < start < end < 100");
        }
    }
}
