package Lab.BorderControl;

public class Robot implements Identifiable {
    private String id;
    private String model;

    public Robot(String model, String id) {
        this.model = model;
        this.id = id;
    }

    public static Robot parseRobot(String[] data) {
        if (data.length != 2) {
            throw new IllegalArgumentException("The robot must contains only model and ID");
        }

        String model = data[0];
        String id = data[1];

        return new Robot(model, id);
    }


    @Override
    public String getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }
}
