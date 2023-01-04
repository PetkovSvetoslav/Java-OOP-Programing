package Lab;

import Lab.Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static class MethodComparator implements Comparator<Method> {

        @Override
        public int compare(Method f, Method s) {
            return f.getName().compareTo(s.getName());
        }
    }

    public static class FieldComparator implements Comparator<Field> {

        @Override
        public int compare(Field f, Field s) {
            return f.getName().compareTo(s.getName());
        }
    }

    public static void main(String[] args) {


        Class<Reflection> reflectionClass = Reflection.class;
        Field[] declaredFields = reflectionClass.getDeclaredFields();
        Method[] declaredMethods = reflectionClass.getDeclaredMethods();

        Set<Field> nonPrivateFields = new TreeSet<>(new FieldComparator());
        Set<Method> nonPublicGetters = new TreeSet<>(new MethodComparator());
        Set<Method> nonPrivateSetters = new TreeSet<>(new MethodComparator());

        Arrays.stream(declaredFields)
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .forEach(nonPrivateFields::add);

        Arrays.stream(declaredMethods)
                .filter(m -> m.getName().contains("get"))
                .filter(m -> !Modifier.isPublic(m.getModifiers()))
                .forEach(nonPublicGetters::add);

        Arrays.stream(declaredMethods)
                .filter(m -> m.getName().contains("set"))
                .filter(m -> !Modifier.isPrivate(m.getModifiers()))
                .forEach(nonPrivateSetters::add);

        nonPrivateFields.forEach(f -> System.out.println(f.getName() + " must be private!"));
        nonPublicGetters.forEach(g -> System.out.println(g.getName() + " have to be public!"));
        nonPrivateSetters.forEach(s -> System.out.println(s.getName() + " have to be private!"));
    }
}
