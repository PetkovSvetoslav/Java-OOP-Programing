package Exercises.barracksWars;

import Exercises.barracksWars.interfaces.Repository;
import Exercises.barracksWars.interfaces.Runnable;
import Exercises.barracksWars.interfaces.UnitFactory;
import Exercises.barracksWars.core.Engine;
import Exercises.barracksWars.core.factories.UnitFactoryImpl;
import Exercises.barracksWars.data.UnitRepository;

public class Main {
    //IT WON'T WORK IN THIS PACKAGE !!!
    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
