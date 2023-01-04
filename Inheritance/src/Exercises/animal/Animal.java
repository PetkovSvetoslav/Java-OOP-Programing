package Exercises.animal;

public class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        setName(name);
        setAge(age);
        setGender(gender);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        ensureNotEmpty(name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        ensureNotEmpty(gender);
        this.gender = gender;
    }

    protected String produceSound() {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()
                + System.lineSeparator()
                + this.name + " " + this.age + " " + this.gender
                + System.lineSeparator()
                + this.produceSound();
    }

    private void ensureNotEmpty(String str) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }

    private void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Invalid input!");
        }
    }
}
