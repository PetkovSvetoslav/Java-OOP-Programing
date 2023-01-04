package Lab;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Tracker {
    @Author(name = "George")
    public static void main(String[] args) {
        Tracker.printMethodsByAuthors(Tracker.class);
    }

    @Author(name = "Peter")
    public static void printMethodsByAuthors(Class<?> clazz) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            Author author = method.getAnnotation(Author.class);
            if (author != null) {
                System.out.printf("%s: %s(%s)%n",
                        author.name(),
                        method.getName(),
                        Arrays.stream(method.getParameters())
                                .map(p -> p.getType().getSimpleName())
                                .collect(Collectors.joining(", ")));
            }
        }
    }
}
