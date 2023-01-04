package Exercises.harvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));

        BiPredicate<Field, String> containsModifier =
                (field, modifier) ->
                        Modifier.toString(field.getModifiers()).contains(modifier)
                                || modifier.equals("all");

        Function<Field, String> formatField =
                f -> String.format("%s %s %s",
                        Modifier.toString(f.getModifiers()),
                        f.getType().getSimpleName(),
                        f.getName());

        Class<RichSoilLand> richSoilLandClass = RichSoilLand.class;

        Field[] declaredFields = richSoilLandClass.getDeclaredFields();

        StringBuilder output = new StringBuilder();

        String input;
        while (!"HARVEST".equals(input = reader.readLine())) {
            String modifier = input;

            output.append(Arrays.stream(declaredFields)
                    .filter(f -> containsModifier.test(f, modifier))
                    .map(formatField)
                    .collect(Collectors.joining(System.lineSeparator())));

            output.append(System.lineSeparator());
        }

        System.out.println(output);
    }
}
