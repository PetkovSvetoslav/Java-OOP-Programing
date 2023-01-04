public class Test {
    public static record Pair(int first, int second) {
    }

    public static void main(String[] args) {

        Pair pair = new Pair(13, 69);

        System.out.println(pair.first() + " " + pair.second());
    }
}
