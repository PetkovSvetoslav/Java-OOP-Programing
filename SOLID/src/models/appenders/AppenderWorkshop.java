package models.appenders;

import interfaces.Appender;
import interfaces.Layout;

public class AppenderWorkshop {

    public static Appender produce(String type, Layout layout) {
        return switch (type) {
            case "ConsoleAppender" -> new ConsoleAppender(layout);
            case "FileAppender" -> new FileAppender(layout);
            default -> throw new IllegalArgumentException("Invalid appender type: " + type + "!");
        };
    }
}
