package Exercises.blackBoxInteger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException,
            NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException,
            NoSuchFieldException {

        var reader = new BufferedReader(new InputStreamReader(System.in));

        Class<BlackBoxInt> clazz = BlackBoxInt.class;

        Field innerValue = clazz.getDeclaredField("innerValue");
        innerValue.setAccessible(true);

        Constructor<BlackBoxInt> ctor = clazz.getDeclaredConstructor();
        ctor.setAccessible(true);

        BlackBoxInt blackBoxInt = ctor.newInstance();

        Map<String, Method> methodsByName =
                Arrays.stream(clazz.getDeclaredMethods())
                        .peek(m -> m.setAccessible(true))
                        .collect(Collectors.toMap(Method::getName, m -> m));

        StringBuilder output = new StringBuilder();

        String input;
        while (!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split("_");

            String methodName = tokens[0];
            int parameter = Integer.parseInt(tokens[1]);

            methodsByName.get(methodName)
                    .invoke(blackBoxInt, parameter);

            output.append(innerValue.getInt(blackBoxInt))
                    .append(System.lineSeparator());
        }

        System.out.println(output);
    }
}
