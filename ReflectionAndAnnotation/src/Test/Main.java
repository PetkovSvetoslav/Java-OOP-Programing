package Test;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> clazz = Class.forName("Test.Person");

        MyAnnotation myAnnotation = clazz.getAnnotation(MyAnnotation.class);

        for (String value : myAnnotation.values()) {
            if (value.equals("Test")) {
                System.out.println("Running tests");
            }
        }
    }
}



