package Test;

@MyAnnotation(values = "Test")
public class Person {
    private String name;
    private int age;

    private Person(String name, int age) {
        this.name = name;
        this.setAge(age);
    }

    @MyAnnotation(values = {"Validate"})
    private void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    @MyAnnotation(values = {"Print", "Type"})
    @Override
    public String toString() {
        return "Name: " + this.name + ", age: " + this.age;
    }
}



