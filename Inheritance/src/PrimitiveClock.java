public class PrimitiveClock {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> printTime());
        thread.start();
    }

    private static void printTime() {
        byte seconds = 0;
        byte min = 0;
        byte hours = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            System.out.printf("%02d:%02d:%02d%n", hours, min, seconds);
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                min++;
            }
            if (min == 60) {
                min = 0;
                hours++;
            }
            if (hours == 24) {
                hours = 0;
            }
        }
    }
}
