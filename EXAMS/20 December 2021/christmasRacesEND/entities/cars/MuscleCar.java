package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar implements Car {
    private static final double cubicCentimeters = 5000;


    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, cubicCentimeters);
        setHorsePower(horsePower);
    }

    @Override
    protected void setHorsePower(int horsePower) {
        if (horsePower < 400 || horsePower > 600) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));

        }
        super.setHorsePower(horsePower);

    }

}
