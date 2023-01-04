package Exercises.barracksWars.core.factories;

import Exercises.barracksWars.interfaces.Unit;
import Exercises.barracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME = "Exercises.barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) {
        try {
            Class<?> clazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
            Constructor<?> constructor = clazz.getConstructor();
            Object object = constructor.newInstance();

            if (object instanceof Unit) {
                return (Unit) object;
            }

        } catch (ClassNotFoundException
                | NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
