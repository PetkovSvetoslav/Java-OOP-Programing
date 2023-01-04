package models.layouts;

public class SimpleLayout extends BaseLayout {

    @Override
    public String format(String dateTime, String reportLevel, String message) {
        return String.format("%s - %s - %s", dateTime, reportLevel, message);
    }

    @Override
    public String getLayoutType() {
        return "SimpleLayout";
    }
}
