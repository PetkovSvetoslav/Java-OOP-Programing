package Lab.BorderControl;

public class Citizen implements Identifiable {
    private String name;
    private int age;
    private String id;

    public Citizen(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public static Citizen parseCitizen(String[] data) {
        if (data.length != 3) {
            throw new IllegalArgumentException("The citizen must contains only name, age and ID");
        }
        String name = data[0];
        int age = Integer.parseInt(data[1]);
        String id = data[2];

        return new Citizen(name, age, id);
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
