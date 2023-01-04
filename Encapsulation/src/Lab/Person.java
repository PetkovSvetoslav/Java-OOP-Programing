package Lab;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setSalary(salary);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setFirstName(String firstName) {
        validateName(firstName, "First name");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateName(lastName, "Last name");
        this.lastName = lastName;
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    public void setSalary(double salary) {
        validateSalary(salary);
        this.salary = salary;
    }

    public void increaseSalary(double percentage) {
        if (this.age < 30) {
            this.setSalary(this.salary * (1 + percentage / 200));
        } else {
            this.setSalary(this.salary * (1 + percentage / 100));
        }
    }

    private void validateName(String name, String messagePrefix) {
        if (name.trim().length() < 3) {
            throw new IllegalArgumentException
                    (messagePrefix + " cannot be with less than 3 symbols");
        }
    }

    private void validateAge(int age) {
        if (age < 1) {
            throw new IllegalArgumentException("Age cannot be zero or negative integer");
        }
    }

    private void validateSalary(double salary) {
        if (salary < 460) {
            throw new IllegalArgumentException("Salary cannot be less than 460 leva");
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %s leva"
                , this.firstName, this.lastName, this.salary);
    }
}
