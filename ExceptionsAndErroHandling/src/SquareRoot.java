import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SquareRoot {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int num = Integer.parseInt(reader.readLine());
            System.out.println(sqrt(num));
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (NumberFormatException exception) {
            System.err.println("Invalid number");
        } catch (IllegalArgumentException exception) {
            System.err.println("Error: " + exception.getMessage());
        } finally {
            System.out.println("Good bye");
        }
    }

    private static double sqrt(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Sqrt for negative number is undefined!");
        }
        return Math.sqrt(value);
    }
}
