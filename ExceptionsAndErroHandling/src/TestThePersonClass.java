public class TestThePersonClass {
    private final static String FIRSTNAME = "Franco";
    private final static String LASTNAME = "Aimee";
    private final static int AGE = 19;

    public static void main(String[] args) {
        Person peter = new Person(FIRSTNAME, LASTNAME, AGE);

        Person noName = new Person("    ", LASTNAME, AGE);
        Person noLastName = new Person(FIRSTNAME, "    ", AGE);
        Person negativeAge = new Person(FIRSTNAME, LASTNAME, -1);
        Person tooOldForThisProgram = new Person(FIRSTNAME, LASTNAME, 120);
    }
}
