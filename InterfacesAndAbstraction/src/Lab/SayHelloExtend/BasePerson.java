package Lab.SayHelloExtend;

public abstract class BasePerson implements Person {
    private String name;

    protected BasePerson(String name) {
        this.setName(name);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        ensureNotEmpty(name);
        this.name = name;
    }

    private void ensureNotEmpty(String str) {
        if (str == null || str.trim().isEmpty()){
            throw new IllegalArgumentException(
                    "Name cannot be empty!");
        }
    }
}
