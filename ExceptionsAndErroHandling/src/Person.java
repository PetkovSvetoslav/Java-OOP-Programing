public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
    }

    private void setFirstName(String firstName) {
        validateStringField(firstName, "first name");
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        validateStringField(lastName, "last name");
        this.lastName = lastName;
    }

    private void setAge(int age) {
        if (0 > age || age > 120) {
            throw new InvalidPersonNameException("Age should be in the range [0 ... 120]");
        }
        this.age = age;
    }

    private void validateStringField(String str, String fieldName) {
        if (str == null || str.trim().isEmpty()) {
            throw new InvalidPersonNameException("The " + fieldName + " cannot be null or empty!");
        }
    }
}
