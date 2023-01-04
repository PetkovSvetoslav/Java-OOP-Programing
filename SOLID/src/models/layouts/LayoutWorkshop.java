package models.layouts;

import interfaces.Layout;

public class LayoutWorkshop {

    public static Layout produce(String type) {
        return switch (type) {
            case "SimpleLayout" -> new SimpleLayout();
            case "XmlLayout" -> new XmlLayout();
            default -> throw new IllegalArgumentException("Invalid layout type: " + type + "!");
        };
    }
}
